package mm.aeon.com.services.applicationinfo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

import mm.aeon.com.common.BeanConverter;
import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.common.FileHandler;
import mm.aeon.com.common.VCSMPasswordEncoder;
import mm.aeon.com.dao.applicantcompanyinfo.ApplicantCompanyInfoDao;
import mm.aeon.com.dao.applicationinfo.ApplicationInfoDao;
import mm.aeon.com.dao.applicationinfoattachment.ApplicationInfoAttachmentDao;
import mm.aeon.com.dao.appusageinfo.AppUsageInfoDao;
import mm.aeon.com.dao.customerinfo.CustomerInfoDao;
import mm.aeon.com.dao.emergencycontactinfo.EmergencyContactInfoDao;
import mm.aeon.com.dao.guarantorinfo.GuarantorInfoDao;
import mm.aeon.com.dao.purchaseinfo.PurchaseInfoDao;
import mm.aeon.com.dto.applicationinfo.ApplicantCompanyInfoDto;
import mm.aeon.com.dto.applicationinfo.ApplicationInfoAttachmentDto;
import mm.aeon.com.dto.applicationinfo.ApplicationInfoDto;
import mm.aeon.com.dto.applicationinfo.ApplicationInquriesSearchCriteriaDto;
import mm.aeon.com.dto.applicationinfo.CompulsoryInfoDto;
import mm.aeon.com.dto.applicationinfo.EmergencyContactInfoDto;
import mm.aeon.com.dto.applicationinfo.FreeApplicationInfoDto;
import mm.aeon.com.dto.applicationinfo.GuarantorInfoDto;
import mm.aeon.com.dto.applicationinfo.InterestInfoDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInfoDto;
import mm.aeon.com.dto.customerinfo.CustRoomInfoDto;
import mm.aeon.com.dto.customerinfo.CustomerInfoDto;
import mm.aeon.com.dto.customersecurityquestion.CustomerSecurityQuestionDto;
import mm.aeon.com.zgen.entity.PasswordInfo;
import mm.aeon.com.zgen.entity.SecurityQuestion;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ApplicationInfoService {

	@Autowired
	private ApplicationInfoDao applicationInfoDao;

	@Autowired
	private ApplicationInfoAttachmentDao applicationInfoAttachmentDao;

	@Autowired
	private ApplicantCompanyInfoDao applicantCompanyInfoDao;

	@Autowired
	private EmergencyContactInfoDao emergencyContactInfoDao;

	@Autowired
	private PurchaseInfoDao purchaseInfoDao;

	@Autowired
	private GuarantorInfoDao guarantorInfoDao;

	@Autowired
	private CustomerInfoDao customerInfoDao;

	@Autowired
	private AppUsageInfoDao appUsageInfoDao;

	@Value("${properties.imageBaseFilePath}")
	private String imageBaseFilePath;

	@Value("${properties.digitalApplicationImageFolder}")
	private String digitalApplicationImageFolder;

	@Value("${properties.serverAddress}")
	private String serverAddress;

	@Value("${properties.serverPort}")
	private Integer serverPort;

	@Value("${properties.serverUsername}")
	private String serverUsername;

	@Value("${properties.serverPassword}")
	private String serverPassword;

	@Autowired
	private BeanConverter beanConverter;

	public void applicationRegistrationAttachmentUpload(ApplicationInfoDto applicationInfoDto, String generatedApplicationNo, Integer applicationInfoId)
			throws JSchException, SftpException {

		Session session = null;
		ChannelSftp channelSftp = null;

		JSch jsch = new JSch();
		session = jsch.getSession(serverUsername, serverAddress, serverPort);
		session.setPassword(serverPassword);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		channelSftp = (ChannelSftp) session.openChannel("sftp");
		channelSftp.connect();
		channelSftp.cd("/");

		String[] destinationPath = { imageBaseFilePath, digitalApplicationImageFolder, applicationInfoDto.getCustomerId().toString(), generatedApplicationNo };

		for (String directory : destinationPath) {
			String currentDir = channelSftp.pwd();
			SftpATTRS sftpATTRS = null;
			try {
				if (currentDir.equals("/")) {

					sftpATTRS = channelSftp.stat(directory);

				} else {
					sftpATTRS = channelSftp.stat(currentDir + "/" + directory);
				}
			} catch (SftpException e) {
				continue;
			} finally {
				if (sftpATTRS != null) { // if directory existed.
					channelSftp.chmod(Integer.parseInt("777", 8), directory);
					channelSftp.cd(directory);
				} else { // if directory does not exist.
					channelSftp.mkdir(directory);
					channelSftp.chmod(Integer.parseInt("777", 8), directory);
					channelSftp.cd(directory);
				}
			}

		}

		for (ApplicationInfoAttachmentDto applicationInfoAttachmentDto : applicationInfoDto.getApplicationInfoAttachmentDtoList()) {
			String fileName = CommonUtil.formatByPattern(CommonUtil.getCurrentTime(), "yyyyMMddhhmmssSSS") + ".jpg";
			InputStream targetStream = new ByteArrayInputStream(applicationInfoAttachmentDto.getPhotoByte());
			channelSftp.put(targetStream, fileName, ChannelSftp.APPEND);
			channelSftp.chmod(Integer.parseInt("777", 8), fileName);

			applicationInfoAttachmentDto.setFilePath(applicationInfoDto.getCustomerId() + "/" + generatedApplicationNo + "/" + fileName);
			applicationInfoAttachmentDto.setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicationInfoAttachmentDto.setDaApplicationInfoId(applicationInfoId);
			applicationInfoAttachmentDao.insertApplicationInfoAttachment(applicationInfoAttachmentDto);

		}
		channelSftp.disconnect();
		session.disconnect();

	}

	public void applicationRegistrationAttachmentUploadWithMultipart(ApplicationInfoDto applicationInfoDto, String generatedApplicationNo, Integer applicationInfoId,
			List<MultipartFile> multipartFileList) throws JSchException, SftpException, IOException {

		Session session = null;
		ChannelSftp channelSftp = null;

		JSch jsch = new JSch();
		session = jsch.getSession(serverUsername, serverAddress, serverPort);
		session.setPassword(serverPassword);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		channelSftp = (ChannelSftp) session.openChannel("sftp");
		channelSftp.connect();
		channelSftp.cd("/");

		String[] destinationPath = { imageBaseFilePath, digitalApplicationImageFolder, applicationInfoDto.getCustomerId().toString(), generatedApplicationNo };

		for (String directory : destinationPath) {
			String currentDir = channelSftp.pwd();
			SftpATTRS sftpATTRS = null;
			try {
				if (currentDir.equals("/")) {

					sftpATTRS = channelSftp.stat(directory);

				} else {
					sftpATTRS = channelSftp.stat(currentDir + "/" + directory);
				}
			} catch (SftpException e) {
				continue;
			} finally {
				if (sftpATTRS != null) { // if directory existed.
					channelSftp.chmod(Integer.parseInt("777", 8), directory);
					channelSftp.cd(directory);
				} else { // if directory does not exist.
					channelSftp.mkdir(directory);
					channelSftp.chmod(Integer.parseInt("777", 8), directory);
					channelSftp.cd(directory);
				}
			}

		}

		for (ApplicationInfoAttachmentDto applicationInfoAttachmentDto : applicationInfoDto.getApplicationInfoAttachmentDtoList()) {
			String filePath = applicationInfoDto.getCustomerId() + "/" + generatedApplicationNo + "/" + applicationInfoAttachmentDto.getFileName();

			applicationInfoAttachmentDto.setFilePath(filePath);
			applicationInfoAttachmentDto.setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicationInfoAttachmentDto.setDaApplicationInfoId(applicationInfoId);
			applicationInfoAttachmentDao.insertApplicationInfoAttachment(applicationInfoAttachmentDto);
		}

		for (MultipartFile multipartFile : multipartFileList) {
			channelSftp.put(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), ChannelSftp.APPEND);
			channelSftp.chmod(Integer.parseInt("777", 8), multipartFile.getOriginalFilename());

		}
		channelSftp.disconnect();
		session.disconnect();

	}

	public Integer registerNewCustomer(CustomerInfoDto customerInfoDto) {

		// insert customer info
		Integer customerId = customerInfoDao.insertCustomerInfo(customerInfoDto);

		PasswordInfo passwordInfo = new PasswordInfo();
		passwordInfo.setUserTypeId(customerInfoDto.getUserTypeId());
		passwordInfo.setUserId(customerId);
		passwordInfo.setPassword(VCSMPasswordEncoder.encode(customerInfoDto.getPassword()));
		// insert password info
		customerInfoDao.insertPasswordInfo(passwordInfo);

		for (CustomerSecurityQuestionDto customerSecurityQuestionDto : customerInfoDto.getCustomerSecurityQuestionDtoList()) {
			SecurityQuestion securityQuestion = customerInfoDao.getSecurityQuestionWithSecQuesId(customerSecurityQuestionDto.getSecQuesId());
			if (securityQuestion != null && securityQuestion.getDelFlag() == 1) {
				securityQuestion.setDelFlag((short) 0);
				securityQuestion.setUpdatedBy(customerId + "(Customer Overwirte)");
				// update security question
				customerInfoDao.updateSecurityQuestion(securityQuestion);
			}
			customerSecurityQuestionDto.setCustomerId(customerId);
			// insert customer security question
			customerInfoDao.insertCustomerSecurityQuestion(customerSecurityQuestionDto);
		}

		if (customerInfoDto.getAppUsageInfoDto() != null) {
			customerInfoDto.getAppUsageInfoDto().setCustomerId(customerId);
			customerInfoDto.getAppUsageInfoDto().setRegistrationDateTime(CommonUtil.getCurrentTime());
			// insert app usage info
			appUsageInfoDao.insertAppUsageInfo(customerInfoDto.getAppUsageInfoDto());
		}

		// get active admin list
		List<Integer> adminIdList = customerInfoDao.getAdminIdList();

		CustRoomInfoDto custRoomInfoDto = new CustRoomInfoDto();
		custRoomInfoDto.setCustomerId(customerId);
		custRoomInfoDto.setCreatedTime(CommonUtil.getCurrentTime());
		custRoomInfoDto.setLastSendTime(CommonUtil.getCurrentTime());
		custRoomInfoDto.setConverLockFlag((short) 0);

		// insert customer room info for messaging
		Integer custRoomId = customerInfoDao.insertCustRoomInfo(custRoomInfoDto);
		customerInfoDao.insertAdminCustRoom(adminIdList, custRoomId);
		return customerId;

	}

	public Integer freeApplicationRegistration(FreeApplicationInfoDto freeApplicationInfoDto) throws Exception {

		CustomerInfoDto customerInfoDto = new CustomerInfoDto();
		customerInfoDto.setName(freeApplicationInfoDto.getName().toUpperCase());
		customerInfoDto.setDob(freeApplicationInfoDto.getDob());
		customerInfoDto.setNrcNo(freeApplicationInfoDto.getNrcNo());
		customerInfoDto.setJoinDate(CommonUtil.getCurrentTime());
		customerInfoDto.setCustomerTypeId(CommonConstants.NON_MEMBER_TYPE); // NON_MEMBER
		customerInfoDto.setUserTypeId(CommonConstants.CUSTOMER_TYPE); // CUSTOMER_TYPE
		customerInfoDto.setPhoneNo(CommonUtil.modifyInvalidPhone(freeApplicationInfoDto.getMobileNo()));
		customerInfoDto.setPassword(freeApplicationInfoDto.getPassword());
		customerInfoDto.setCustomerSecurityQuestionDtoList(freeApplicationInfoDto.getCustomerSecurityQuestionDtoList());
		Integer customerId = registerNewCustomer(customerInfoDto);

		freeApplicationInfoDto.setCustomerId(customerId);
		InterestInfoDto interestInfoDto = applicationInfoDao.getInterestInfo();
		CompulsoryInfoDto compulsoryInfoDto = applicationInfoDao.getCompulsoryInfo();
		String lastApplicationNo = applicationInfoDao.getLastApplicationNo();
		String generatedApplicationNo = generateApplicationNo(lastApplicationNo);
		freeApplicationInfoDto.setApplicationNo(generatedApplicationNo);
		freeApplicationInfoDto.setCreatedBy(freeApplicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		freeApplicationInfoDto.setAppliedDate(CommonUtil.getCurrentTime());
		freeApplicationInfoDto.setStatus(CommonConstants.APP_STATUS_APPLY_NEW);
		freeApplicationInfoDto.setDaInterestInfoId(interestInfoDto.getDaInterestInfoId());
		freeApplicationInfoDto.setDaCompulsoryInfoId(compulsoryInfoDto.getDaCompulsoryInfoId());
		Integer applicationInfoId = null;
		ApplicationInfoDto applicationInfoDto = beanConverter.convert(freeApplicationInfoDto, ApplicationInfoDto.class);

		applicationInfoId = applicationInfoDao.insertApplicationInfo(applicationInfoDto);

		applicationInfoDto.getApplicantCompanyInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.getApplicantCompanyInfoDto().setDaApplicationInfoId(applicationInfoId);
		applicantCompanyInfoDao.insertApplicantCompanyInfo(applicationInfoDto.getApplicantCompanyInfoDto());

		applicationInfoDto.getEmergencyContactInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.getEmergencyContactInfoDto().setDaApplicationInfoId(applicationInfoId);
		emergencyContactInfoDao.insertEmergencyContactInfo(applicationInfoDto.getEmergencyContactInfoDto());

		applicationInfoDto.getGuarantorInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.getGuarantorInfoDto().setDaApplicationInfoId(applicationInfoId);
		guarantorInfoDao.insertGuarantorInfo(applicationInfoDto.getGuarantorInfoDto());

		applicationRegistrationAttachmentUpload(applicationInfoDto, generatedApplicationNo, applicationInfoId);

		applicationInfoDto.setDaApplicationInfoId(applicationInfoId);
		applicationInfoDto.setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDao.insertApplicationInfoHistory(applicationInfoDto);

		return applicationInfoId;
	}

	public Integer applicationRegistration(ApplicationInfoDto applicationInfoDto) throws Exception {
		InterestInfoDto interestInfoDto = applicationInfoDao.getInterestInfo();
		CompulsoryInfoDto compulsoryInfoDto = applicationInfoDao.getCompulsoryInfo();
		String lastApplicationNo = applicationInfoDao.getLastApplicationNo();
		String generatedApplicationNo = generateApplicationNo(lastApplicationNo);
		applicationInfoDto.setApplicationNo(generatedApplicationNo);
		applicationInfoDto.setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.setAppliedDate(CommonUtil.getCurrentTime());
		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_APPLY_NEW);
		applicationInfoDto.setDaInterestInfoId(interestInfoDto.getDaInterestInfoId());
		applicationInfoDto.setDaCompulsoryInfoId(compulsoryInfoDto.getDaCompulsoryInfoId());
		Integer applicationInfoId = null;
		applicationInfoId = applicationInfoDao.insertApplicationInfo(applicationInfoDto);

		applicationInfoDto.getApplicantCompanyInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.getApplicantCompanyInfoDto().setDaApplicationInfoId(applicationInfoId);
		applicantCompanyInfoDao.insertApplicantCompanyInfo(applicationInfoDto.getApplicantCompanyInfoDto());

		applicationInfoDto.getEmergencyContactInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.getEmergencyContactInfoDto().setDaApplicationInfoId(applicationInfoId);
		emergencyContactInfoDao.insertEmergencyContactInfo(applicationInfoDto.getEmergencyContactInfoDto());

		applicationInfoDto.getGuarantorInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.getGuarantorInfoDto().setDaApplicationInfoId(applicationInfoId);
		guarantorInfoDao.insertGuarantorInfo(applicationInfoDto.getGuarantorInfoDto());

		applicationRegistrationAttachmentUpload(applicationInfoDto, generatedApplicationNo, applicationInfoId);

		applicationInfoDto.setDaApplicationInfoId(applicationInfoId);
		applicationInfoDto.setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDao.insertApplicationInfoHistory(applicationInfoDto);

		return applicationInfoId;
	}

	public String applicationRegistrationWithMultipart(ApplicationInfoDto applicationInfoDto, List<MultipartFile> multipartFileList) throws Exception {
		InterestInfoDto interestInfoDto = applicationInfoDao.getInterestInfo();
		CompulsoryInfoDto compulsoryInfoDto = applicationInfoDao.getCompulsoryInfo();
		String lastApplicationNo = applicationInfoDao.getLastApplicationNo();
		String generatedApplicationNo = generateApplicationNo(lastApplicationNo);
		applicationInfoDto.setApplicationNo(generatedApplicationNo);
		applicationInfoDto.setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.setAppliedDate(CommonUtil.getCurrentTime());
		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_APPLY_NEW);
		applicationInfoDto.setDaInterestInfoId(interestInfoDto.getDaInterestInfoId());
		applicationInfoDto.setDaCompulsoryInfoId(compulsoryInfoDto.getDaCompulsoryInfoId());
		Integer applicationInfoId = applicationInfoDao.insertApplicationInfo(applicationInfoDto);

		applicationInfoDto.getApplicantCompanyInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.getApplicantCompanyInfoDto().setDaApplicationInfoId(applicationInfoId);
		applicantCompanyInfoDao.insertApplicantCompanyInfo(applicationInfoDto.getApplicantCompanyInfoDto());

		applicationInfoDto.getEmergencyContactInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.getEmergencyContactInfoDto().setDaApplicationInfoId(applicationInfoId);
		emergencyContactInfoDao.insertEmergencyContactInfo(applicationInfoDto.getEmergencyContactInfoDto());

		applicationInfoDto.getGuarantorInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.getGuarantorInfoDto().setDaApplicationInfoId(applicationInfoId);
		guarantorInfoDao.insertGuarantorInfo(applicationInfoDto.getGuarantorInfoDto());

		applicationRegistrationAttachmentUploadWithMultipart(applicationInfoDto, generatedApplicationNo, applicationInfoId, multipartFileList);

		applicationInfoDto.setDaApplicationInfoId(applicationInfoId);
		applicationInfoDto.setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDao.insertApplicationInfoHistory(applicationInfoDto);

		return applicationInfoDto.getApplicationNo();
	}

	public void applicationDraftRegister(ApplicationInfoDto applicationInfoDto) throws Exception {
		InterestInfoDto interestInfoDto = applicationInfoDao.getInterestInfo();
		CompulsoryInfoDto compulsoryInfoDto = applicationInfoDao.getCompulsoryInfo();
		String lastApplicationNo = applicationInfoDao.getLastApplicationNo();
		String generatedApplicationNo = generateApplicationNo(lastApplicationNo);
		applicationInfoDto.setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.setAppliedDate(CommonUtil.getCurrentTime());
		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_APPLY_NEW);
		applicationInfoDto.setDaInterestInfoId(interestInfoDto.getDaInterestInfoId());
		applicationInfoDto.setDaCompulsoryInfoId(compulsoryInfoDto.getDaCompulsoryInfoId());
		applicationInfoDto.setApplicationNo(generatedApplicationNo);
		applicationInfoDao.updateApplicationInfo(applicationInfoDto);

		if (applicationInfoDto.getApplicantCompanyInfoDto().getDaApplicantCompanyInfoId() == null) {
			applicationInfoDto.getApplicantCompanyInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicationInfoDto.getApplicantCompanyInfoDto().setDaApplicationInfoId(applicationInfoDto.getDaApplicationInfoId());
			applicantCompanyInfoDao.insertApplicantCompanyInfo(applicationInfoDto.getApplicantCompanyInfoDto());
		} else {
			applicationInfoDto.getApplicantCompanyInfoDto().setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicantCompanyInfoDao.updateApplicantCompanyInfo(applicationInfoDto.getApplicantCompanyInfoDto());
		}

		if (applicationInfoDto.getEmergencyContactInfoDto().getDaEmergencyContactInfoId() == null) {
			applicationInfoDto.getEmergencyContactInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicationInfoDto.getEmergencyContactInfoDto().setDaApplicationInfoId(applicationInfoDto.getDaApplicationInfoId());
			emergencyContactInfoDao.insertEmergencyContactInfo(applicationInfoDto.getEmergencyContactInfoDto());

		} else {
			applicationInfoDto.getEmergencyContactInfoDto().setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			emergencyContactInfoDao.updateEmergencyContactInfo(applicationInfoDto.getEmergencyContactInfoDto());
		}

		if (applicationInfoDto.getGuarantorInfoDto().getDaGuarantorInfoId() == null) {
			applicationInfoDto.getGuarantorInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicationInfoDto.getGuarantorInfoDto().setDaApplicationInfoId(applicationInfoDto.getDaApplicationInfoId());
			guarantorInfoDao.insertGuarantorInfo(applicationInfoDto.getGuarantorInfoDto());
		} else {
			applicationInfoDto.getGuarantorInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			guarantorInfoDao.updateGuarantorInfo(applicationInfoDto.getGuarantorInfoDto());
		}

		applicationRegistrationAttachmentUpload(applicationInfoDto, generatedApplicationNo, applicationInfoDto.getDaApplicationInfoId());
		applicationInfoDto.setDaApplicationInfoId(applicationInfoDto.getDaApplicationInfoId());
		applicationInfoDto.setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDao.insertApplicationInfoHistory(applicationInfoDto);

	}

	public String applicationDraftRegisterWithMultipart(ApplicationInfoDto applicationInfoDto, List<MultipartFile> multipartFileList) throws Exception {
		InterestInfoDto interestInfoDto = applicationInfoDao.getInterestInfo();
		CompulsoryInfoDto compulsoryInfoDto = applicationInfoDao.getCompulsoryInfo();
		String lastApplicationNo = applicationInfoDao.getLastApplicationNo();
		String generatedApplicationNo = generateApplicationNo(lastApplicationNo);
		applicationInfoDto.setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.setAppliedDate(CommonUtil.getCurrentTime());
		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_APPLY_NEW);
		applicationInfoDto.setDaInterestInfoId(interestInfoDto.getDaInterestInfoId());
		applicationInfoDto.setDaCompulsoryInfoId(compulsoryInfoDto.getDaCompulsoryInfoId());
		applicationInfoDto.setApplicationNo(generatedApplicationNo);
		applicationInfoDao.updateApplicationInfo(applicationInfoDto);

		if (applicationInfoDto.getApplicantCompanyInfoDto().getDaApplicantCompanyInfoId() == null) {
			applicationInfoDto.getApplicantCompanyInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicationInfoDto.getApplicantCompanyInfoDto().setDaApplicationInfoId(applicationInfoDto.getDaApplicationInfoId());
			applicantCompanyInfoDao.insertApplicantCompanyInfo(applicationInfoDto.getApplicantCompanyInfoDto());
		} else {
			applicationInfoDto.getApplicantCompanyInfoDto().setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicantCompanyInfoDao.updateApplicantCompanyInfo(applicationInfoDto.getApplicantCompanyInfoDto());
		}

		if (applicationInfoDto.getEmergencyContactInfoDto().getDaEmergencyContactInfoId() == null) {
			applicationInfoDto.getEmergencyContactInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicationInfoDto.getEmergencyContactInfoDto().setDaApplicationInfoId(applicationInfoDto.getDaApplicationInfoId());
			emergencyContactInfoDao.insertEmergencyContactInfo(applicationInfoDto.getEmergencyContactInfoDto());

		} else {
			applicationInfoDto.getEmergencyContactInfoDto().setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			emergencyContactInfoDao.updateEmergencyContactInfo(applicationInfoDto.getEmergencyContactInfoDto());
		}

		if (applicationInfoDto.getGuarantorInfoDto().getDaGuarantorInfoId() == null) {
			applicationInfoDto.getGuarantorInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicationInfoDto.getGuarantorInfoDto().setDaApplicationInfoId(applicationInfoDto.getDaApplicationInfoId());
			guarantorInfoDao.insertGuarantorInfo(applicationInfoDto.getGuarantorInfoDto());
		} else {
			applicationInfoDto.getGuarantorInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			guarantorInfoDao.updateGuarantorInfo(applicationInfoDto.getGuarantorInfoDto());
		}

		applicationRegistrationAttachmentUploadWithMultipart(applicationInfoDto, generatedApplicationNo, applicationInfoDto.getDaApplicationInfoId(), multipartFileList);
		applicationInfoDto.setDaApplicationInfoId(applicationInfoDto.getDaApplicationInfoId());
		applicationInfoDto.setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDao.insertApplicationInfoHistory(applicationInfoDto);
		return applicationInfoDto.getApplicationNo();

	}

	public ApplicationInfoDto applicationSaveDraft(ApplicationInfoDto applicationInfoDto) {
		InterestInfoDto interestInfoDto = applicationInfoDao.getInterestInfo();
		CompulsoryInfoDto compulsoryInfoDto = applicationInfoDao.getCompulsoryInfo();
		applicationInfoDto.setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.setAppliedDate(CommonUtil.getCurrentTime());
		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_DRAFT);
		applicationInfoDto.setDaInterestInfoId(interestInfoDto.getDaInterestInfoId());
		applicationInfoDto.setDaCompulsoryInfoId(compulsoryInfoDto.getDaCompulsoryInfoId());

		if (applicationInfoDto.getCurrentAddressTownship() != null && applicationInfoDto.getCurrentAddressTownship() == 0) {
			applicationInfoDto.setCurrentAddressTownship(null);
		}

		if (applicationInfoDto.getCurrentAddressCity() != null && applicationInfoDto.getCurrentAddressCity() == 0) {
			applicationInfoDto.setCurrentAddressCity(null);
		}

		if (applicationInfoDto.getPermanentAddressTownship() != null && applicationInfoDto.getPermanentAddressTownship() == 0) {
			applicationInfoDto.setPermanentAddressTownship(null);
		}

		if (applicationInfoDto.getPermanentAddressCity() != null && applicationInfoDto.getPermanentAddressCity() == 0) {
			applicationInfoDto.setPermanentAddressCity(null);
		}

		Integer applicationInfoId = applicationInfoDao.insertApplicationInfo(applicationInfoDto);
		applicationInfoDto.setDaApplicationInfoId(applicationInfoId);

		if (applicationInfoDto.getApplicantCompanyInfoDto() != null) {
			if (applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressCity() != null && applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressCity() == 0) {
				applicationInfoDto.getApplicantCompanyInfoDto().setCompanyAddressCity(null);
			}

			if (applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressTownship() != null
					&& applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressTownship() == 0) {
				applicationInfoDto.getApplicantCompanyInfoDto().setCompanyAddressTownship(null);
			}

			applicationInfoDto.getApplicantCompanyInfoDto().setDaApplicantCompanyInfoId(null);
			applicationInfoDto.getApplicantCompanyInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicationInfoDto.getApplicantCompanyInfoDto().setDaApplicationInfoId(applicationInfoId);
			Integer applicantCompanyInfoId = applicantCompanyInfoDao.insertApplicantCompanyInfo(applicationInfoDto.getApplicantCompanyInfoDto());
			applicationInfoDto.getApplicantCompanyInfoDto().setDaApplicantCompanyInfoId(applicantCompanyInfoId);
		}

		if (applicationInfoDto.getEmergencyContactInfoDto() != null) {
			if (applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressTownship() != null
					&& applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressTownship() == 0) {
				applicationInfoDto.getEmergencyContactInfoDto().setCurrentAddressTownship(null);
			}

			if (applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressCity() != null && applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressCity() == 0) {
				applicationInfoDto.getEmergencyContactInfoDto().setCurrentAddressCity(null);
			}

			applicationInfoDto.getEmergencyContactInfoDto().setDaEmergencyContactInfoId(null);
			applicationInfoDto.getEmergencyContactInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicationInfoDto.getEmergencyContactInfoDto().setDaApplicationInfoId(applicationInfoId);
			Integer emergencyContactInfoId = emergencyContactInfoDao.insertEmergencyContactInfo(applicationInfoDto.getEmergencyContactInfoDto());
			applicationInfoDto.getEmergencyContactInfoDto().setDaEmergencyContactInfoId(emergencyContactInfoId);
		}

		if (applicationInfoDto.getGuarantorInfoDto() != null) {
			if (applicationInfoDto.getGuarantorInfoDto().getCurrentAddressTownship() != null && applicationInfoDto.getGuarantorInfoDto().getCurrentAddressTownship() == 0) {
				applicationInfoDto.getGuarantorInfoDto().setCurrentAddressTownship(null);
			}

			if (applicationInfoDto.getGuarantorInfoDto().getCurrentAddressCity() != null && applicationInfoDto.getGuarantorInfoDto().getCurrentAddressCity() == 0) {
				applicationInfoDto.getGuarantorInfoDto().setCurrentAddressCity(null);
			}

			if (applicationInfoDto.getGuarantorInfoDto().getCompanyAddressCity() != null && applicationInfoDto.getGuarantorInfoDto().getCompanyAddressCity() == 0) {
				applicationInfoDto.getGuarantorInfoDto().setCompanyAddressCity(null);
			}

			if (applicationInfoDto.getGuarantorInfoDto().getCompanyAddressTownship() != null && applicationInfoDto.getGuarantorInfoDto().getCompanyAddressTownship() == 0) {
				applicationInfoDto.getGuarantorInfoDto().setCompanyAddressTownship(null);
			}

			applicationInfoDto.getGuarantorInfoDto().setDaGuarantorInfoId(null);
			applicationInfoDto.getGuarantorInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicationInfoDto.getGuarantorInfoDto().setDaApplicationInfoId(applicationInfoId);
			Integer guarantorInfoId = guarantorInfoDao.insertGuarantorInfo(applicationInfoDto.getGuarantorInfoDto());
			applicationInfoDto.getGuarantorInfoDto().setDaGuarantorInfoId(guarantorInfoId);
		}

		return applicationInfoDto;
	}

	public ApplicationInfoDto applicationSaveDraftUpdate(ApplicationInfoDto applicationInfoDto) {
		InterestInfoDto interestInfoDto = applicationInfoDao.getInterestInfo();
		CompulsoryInfoDto compulsoryInfoDto = applicationInfoDao.getCompulsoryInfo();
		applicationInfoDto.setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.setAppliedDate(CommonUtil.getCurrentTime());
		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_DRAFT);
		applicationInfoDto.setDaInterestInfoId(interestInfoDto.getDaInterestInfoId());
		applicationInfoDto.setDaCompulsoryInfoId(compulsoryInfoDto.getDaCompulsoryInfoId());
		if (applicationInfoDto.getCurrentAddressTownship() != null && applicationInfoDto.getCurrentAddressTownship() == 0) {
			applicationInfoDto.setCurrentAddressTownship(null);
		}

		if (applicationInfoDto.getCurrentAddressCity() != null && applicationInfoDto.getCurrentAddressCity() == 0) {
			applicationInfoDto.setCurrentAddressCity(null);
		}

		if (applicationInfoDto.getPermanentAddressTownship() != null && applicationInfoDto.getPermanentAddressTownship() == 0) {
			applicationInfoDto.setPermanentAddressTownship(null);
		}

		if (applicationInfoDto.getPermanentAddressCity() != null && applicationInfoDto.getPermanentAddressCity() == 0) {
			applicationInfoDto.setPermanentAddressCity(null);
		}
		applicationInfoDao.updateApplicationInfo(applicationInfoDto);

		if (applicationInfoDto.getApplicantCompanyInfoDto() != null) {
			if (applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressCity() != null && applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressCity() == 0) {
				applicationInfoDto.getApplicantCompanyInfoDto().setCompanyAddressCity(null);
			}

			if (applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressTownship() != null
					&& applicationInfoDto.getApplicantCompanyInfoDto().getCompanyAddressTownship() == 0) {
				applicationInfoDto.getApplicantCompanyInfoDto().setCompanyAddressTownship(null);
			}
			ApplicantCompanyInfoDto applicantCompanyInfoDto = applicantCompanyInfoDao.getApplicantCompanyInfoDto(applicationInfoDto.getDaApplicationInfoId());
			if (applicantCompanyInfoDto == null) {
				applicationInfoDto.getApplicantCompanyInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
				applicationInfoDto.getApplicantCompanyInfoDto().setDaApplicationInfoId(applicationInfoDto.getDaApplicationInfoId());
				Integer applicantCompanyInfoId = applicantCompanyInfoDao.insertApplicantCompanyInfo(applicationInfoDto.getApplicantCompanyInfoDto());
				applicationInfoDto.getApplicantCompanyInfoDto().setDaApplicantCompanyInfoId(applicantCompanyInfoId);
			} else {
				applicationInfoDto.getApplicantCompanyInfoDto().setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
				applicationInfoDto.getApplicantCompanyInfoDto().setDaApplicationInfoId(applicationInfoDto.getDaApplicationInfoId());
				applicationInfoDto.getApplicantCompanyInfoDto().setDaApplicantCompanyInfoId(applicantCompanyInfoDto.getDaApplicantCompanyInfoId());
				applicantCompanyInfoDao.updateApplicantCompanyInfo(applicationInfoDto.getApplicantCompanyInfoDto());
			}
		}

		if (applicationInfoDto.getEmergencyContactInfoDto() != null) {
			if (applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressTownship() != null
					&& applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressTownship() == 0) {
				applicationInfoDto.getEmergencyContactInfoDto().setCurrentAddressTownship(null);
			}

			if (applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressCity() != null && applicationInfoDto.getEmergencyContactInfoDto().getCurrentAddressCity() == 0) {
				applicationInfoDto.getEmergencyContactInfoDto().setCurrentAddressCity(null);
			}
			EmergencyContactInfoDto emergencyContactInfoDto = emergencyContactInfoDao.getEmergencyContactInfoDto(applicationInfoDto.getDaApplicationInfoId());
			if (emergencyContactInfoDto == null) {
				applicationInfoDto.getEmergencyContactInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
				applicationInfoDto.getEmergencyContactInfoDto().setDaApplicationInfoId(applicationInfoDto.getDaApplicationInfoId());
				Integer emergencyContactInfoId = emergencyContactInfoDao.insertEmergencyContactInfo(applicationInfoDto.getEmergencyContactInfoDto());
				applicationInfoDto.getEmergencyContactInfoDto().setDaEmergencyContactInfoId(emergencyContactInfoId);
			} else {
				applicationInfoDto.getEmergencyContactInfoDto().setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
				applicationInfoDto.getEmergencyContactInfoDto().setDaApplicationInfoId(applicationInfoDto.getDaApplicationInfoId());
				applicationInfoDto.getEmergencyContactInfoDto().setDaEmergencyContactInfoId(emergencyContactInfoDto.getDaEmergencyContactInfoId());
				emergencyContactInfoDao.updateEmergencyContactInfo(applicationInfoDto.getEmergencyContactInfoDto());
			}
		}

		if (applicationInfoDto.getGuarantorInfoDto() != null) {
			if (applicationInfoDto.getGuarantorInfoDto().getCurrentAddressTownship() != null && applicationInfoDto.getGuarantorInfoDto().getCurrentAddressTownship() == 0) {
				applicationInfoDto.getGuarantorInfoDto().setCurrentAddressTownship(null);
			}

			if (applicationInfoDto.getGuarantorInfoDto().getCurrentAddressCity() != null && applicationInfoDto.getGuarantorInfoDto().getCurrentAddressCity() == 0) {
				applicationInfoDto.getGuarantorInfoDto().setCurrentAddressCity(null);
			}

			if (applicationInfoDto.getGuarantorInfoDto().getCompanyAddressCity() != null && applicationInfoDto.getGuarantorInfoDto().getCompanyAddressCity() == 0) {
				applicationInfoDto.getGuarantorInfoDto().setCompanyAddressCity(null);
			}

			if (applicationInfoDto.getGuarantorInfoDto().getCompanyAddressTownship() != null && applicationInfoDto.getGuarantorInfoDto().getCompanyAddressTownship() == 0) {
				applicationInfoDto.getGuarantorInfoDto().setCompanyAddressTownship(null);
			}
			GuarantorInfoDto guarantorInfoDto = guarantorInfoDao.getGuarantorInfoDto(applicationInfoDto.getDaApplicationInfoId());
			if (guarantorInfoDto == null) {
				applicationInfoDto.getGuarantorInfoDto().setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
				applicationInfoDto.getGuarantorInfoDto().setDaApplicationInfoId(applicationInfoDto.getDaApplicationInfoId());
				Integer guarantorInfoId = guarantorInfoDao.insertGuarantorInfo(applicationInfoDto.getGuarantorInfoDto());
				applicationInfoDto.getGuarantorInfoDto().setDaGuarantorInfoId(guarantorInfoId);
			} else {
				applicationInfoDto.getGuarantorInfoDto().setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
				applicationInfoDto.getGuarantorInfoDto().setDaApplicationInfoId(applicationInfoDto.getDaApplicationInfoId());
				applicationInfoDto.getGuarantorInfoDto().setDaGuarantorInfoId(guarantorInfoDto.getDaGuarantorInfoId());
				guarantorInfoDao.updateGuarantorInfo(applicationInfoDto.getGuarantorInfoDto());
			}

		}

		return applicationInfoDto;
	}

	public void attachmentEditUpload(ApplicationInfoDto applicationInfoDto) throws JSchException, SftpException, IOException {

		Session session = null;
		ChannelSftp channelSftp = null;

		JSch jsch = new JSch();
		session = jsch.getSession(serverUsername, serverAddress, serverPort);
		session.setPassword(serverPassword);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		channelSftp = (ChannelSftp) session.openChannel("sftp");
		channelSftp.connect();
		channelSftp.cd("/");

		String[] destinationPath = { imageBaseFilePath, digitalApplicationImageFolder };

		for (String directory : destinationPath) {
			String currentDir = channelSftp.pwd();
			SftpATTRS sftpATTRS = null;
			try {
				if (currentDir.equals("/")) {

					sftpATTRS = channelSftp.stat(directory);

				} else {
					sftpATTRS = channelSftp.stat(currentDir + "/" + directory);
				}
			} catch (SftpException e) {
				continue;
			} finally {
				if (sftpATTRS != null) { // if directory existed.
					channelSftp.chmod(Integer.parseInt("777", 8), directory);
					channelSftp.cd(directory);
				} else { // if directory does not exist.
					channelSftp.mkdir(directory);
					channelSftp.chmod(Integer.parseInt("777", 8), directory);
					channelSftp.cd(directory);
				}
			}

		}

		ChannelExec channelExec = null;
		for (ApplicationInfoAttachmentDto applicationInfoAttachmentDto : applicationInfoDto.getApplicationInfoAttachmentDtoList()) {
			channelExec = (ChannelExec) session.openChannel("exec");
			InputStream in = channelExec.getInputStream();
			String fileName = applicationInfoAttachmentDto.getFilePath();
			String deleteCommand = "rm -rf " + imageBaseFilePath + "/" + digitalApplicationImageFolder + "/" + fileName;
			channelExec.setCommand(deleteCommand);
			channelExec.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;

			while ((line = reader.readLine()) != null) {
			}

		}
		channelExec.disconnect();

		for (ApplicationInfoAttachmentDto applicationInfoAttachmentDto : applicationInfoDto.getApplicationInfoAttachmentDtoList()) {

			String fileName = CommonUtil.formatByPattern(CommonUtil.getCurrentTime(), "yyyyMMddhhmmssSSS") + ".jpg";

			FileHandler.createFile(new File(imageBaseFilePath + "/" + digitalApplicationImageFolder + "/" + applicationInfoDto.getCustomerId() + "/"
					+ applicationInfoDto.getApplicationNo() + "/" + fileName), applicationInfoAttachmentDto.getPhotoByte());

			applicationInfoAttachmentDto.setFilePath(applicationInfoDto.getCustomerId() + "/" + applicationInfoDto.getApplicationNo() + "/" + fileName);
			applicationInfoAttachmentDto.setEditFlag(null);
			applicationInfoAttachmentDto.setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicationInfoAttachmentDao.updateApplicationInfoAttachment(applicationInfoAttachmentDto);

		}

		channelSftp.disconnect();
		session.disconnect();

	}

	public void attachmentEditUploadWithMultipart(ApplicationInfoDto applicationInfoDto, List<MultipartFile> multipartFileList) throws JSchException, SftpException, IOException {

		Session session = null;
		ChannelSftp channelSftp = null;

		JSch jsch = new JSch();
		session = jsch.getSession(serverUsername, serverAddress, serverPort);
		session.setPassword(serverPassword);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		channelSftp = (ChannelSftp) session.openChannel("sftp");
		channelSftp.connect();
		channelSftp.cd("/");

		String[] destinationPath = { imageBaseFilePath, digitalApplicationImageFolder, applicationInfoDto.getCustomerId().toString(), applicationInfoDto.getApplicationNo() };

		for (String directory : destinationPath) {
			String currentDir = channelSftp.pwd();
			SftpATTRS sftpATTRS = null;
			try {
				if (currentDir.equals("/")) {

					sftpATTRS = channelSftp.stat(directory);

				} else {
					sftpATTRS = channelSftp.stat(currentDir + "/" + directory);
				}
			} catch (SftpException e) {
				continue;
			} finally {
				if (sftpATTRS != null) { // if directory existed.
					channelSftp.chmod(Integer.parseInt("777", 8), directory);
					channelSftp.cd(directory);
				} else { // if directory does not exist.
					channelSftp.mkdir(directory);
					channelSftp.chmod(Integer.parseInt("777", 8), directory);
					channelSftp.cd(directory);
				}
			}

		}

		ChannelExec channelExec = null;
		for (ApplicationInfoAttachmentDto applicationInfoAttachmentDto : applicationInfoDto.getApplicationInfoAttachmentDtoList()) {
			channelExec = (ChannelExec) session.openChannel("exec");
			InputStream in = channelExec.getInputStream();
			String fileName = applicationInfoAttachmentDto.getFilePath();
			String deleteCommand = "rm -rf " + imageBaseFilePath + "/" + digitalApplicationImageFolder + "/" + fileName;
			channelExec.setCommand(deleteCommand);
			channelExec.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;

			while ((line = reader.readLine()) != null) {
			}

		}

		for (ApplicationInfoAttachmentDto applicationInfoAttachmentDto : applicationInfoDto.getApplicationInfoAttachmentDtoList()) {
			String filePath = applicationInfoDto.getCustomerId() + "/" + applicationInfoDto.getApplicationNo() + "/" + applicationInfoAttachmentDto.getFileName();

			applicationInfoAttachmentDto.setEditFlag(null);
			applicationInfoAttachmentDto.setFilePath(filePath);
			applicationInfoAttachmentDto.setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicationInfoAttachmentDao.updateApplicationInfoAttachment(applicationInfoAttachmentDto);

		}

		for (MultipartFile multipartFile : multipartFileList) {

			channelSftp.put(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), ChannelSftp.APPEND);
			channelSftp.chmod(Integer.parseInt("777", 8), multipartFile.getOriginalFilename());

		}

		channelExec.disconnect();
		channelSftp.disconnect();
		session.disconnect();

	}

	public void attachmentEdit(ApplicationInfoDto applicationInfoDto) throws Exception {

		attachmentEditUpload(applicationInfoDto);
		applicationInfoDto.setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_DOCUMENT_UPDATED);
		applicationInfoDao.updateApplicationInfo(applicationInfoDto);

		applicationInfoDto.setUpdatedBy(null);
		applicationInfoDto.setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDao.insertApplicationInfoHistory(applicationInfoDto);
	}

	public void attachmentEditWithMultipart(ApplicationInfoDto applicationInfoDto, List<MultipartFile> multipartFileList) throws Exception {

		attachmentEditUploadWithMultipart(applicationInfoDto, multipartFileList);
		applicationInfoDto.setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_DOCUMENT_UPDATED);
		applicationInfoDao.updateApplicationInfo(applicationInfoDto);

		applicationInfoDto.setUpdatedBy(null);
		applicationInfoDto.setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDao.insertApplicationInfoHistory(applicationInfoDto);
	}

	public void applicationCancel(ApplicationInfoDto applicationInfoDto) {

		applicationInfoDto.setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_CANCEL);
		applicationInfoDao.updateApplicationInfo(applicationInfoDto);

		applicationInfoDto.setUpdatedBy(null);
		applicationInfoDto.setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDao.insertApplicationInfoHistory(applicationInfoDto);

		purchaseInfoDao.updateQRNotShowCustAgreement(applicationInfoDto.getApplicationNo());

	}

	public String generateApplicationNo(String lastApplicationNo) {
		String generatedApplicationNo = null;
		if (lastApplicationNo == null) {
			generatedApplicationNo = CommonUtil.formatByPattern(CommonUtil.getCurrentTime(), "YYMM") + "000001";
		} else {
			String lastApplicationYearAndMonth = lastApplicationNo.substring(0, 4);
			String currentYearAndMonth = CommonUtil.formatByPattern(CommonUtil.getCurrentTime(), "YYMM");
			if (!lastApplicationYearAndMonth.equals(currentYearAndMonth)) {
				generatedApplicationNo = currentYearAndMonth + "000001";
			} else {
				Integer applicationNo = Integer.parseInt(lastApplicationNo);
				applicationNo += 1;
				generatedApplicationNo = applicationNo.toString();
			}

		}
		return generatedApplicationNo;
	}

	public List<Double> getActiveFinanceAmountList(Integer customerId, Integer daApplicationInfoId) {
		return applicationInfoDao.getActiveFinanceAmountList(customerId, daApplicationInfoId);
	}

	public List<ApplicationInfoDto> getApplicationInquriesList(ApplicationInquriesSearchCriteriaDto applicationInquriesSearchCriteriaDto) {
		return applicationInfoDao.getApplicationInquriesList(applicationInquriesSearchCriteriaDto);
	}

	public int getApplicationInquriesCount(ApplicationInquriesSearchCriteriaDto applicationInquriesSearchCriteriaDto) {
		return applicationInfoDao.getApplicationInquriesCount(applicationInquriesSearchCriteriaDto);
	}

	public ApplicationInfoDto getApplicationInfoDetail(Integer daApplicationInfoId) {
		return applicationInfoDao.getApplicationInfoDetail(daApplicationInfoId);
	}

	public PurchaseInfoDto getPurchaseInfoDetail(Integer daApplicationInfoId) {
		return applicationInfoDao.getPurchaseInfoDetail(daApplicationInfoId);
	}

	public ApplicationInfoDto getLastApplicationInfo(Integer customerId) {
		return applicationInfoDao.getLastApplicationInfo(customerId);
	}

	public List<ApplicationInfoAttachmentDto> getPurchaseApplicationAttachmentList(Integer daApplicationInfoId) {
		return applicationInfoDao.getPurchaseApplicationAttachmentList(daApplicationInfoId);
	}

	public int getApplicationStatusCount(Integer customerId, Integer status) {
		return applicationInfoDao.getApplicationStatusCount(customerId, status);
	}

	public void updateLoanInformation(ApplicationInfoDto applicationInfoDto) {
		applicationInfoDto.setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_MODIFY_REQUEST);
		applicationInfoDao.updateApplicationInfo(applicationInfoDto);

		applicationInfoDto.setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDao.insertApplicationInfoHistory(applicationInfoDto);

	}

	public void updateApplicationStatusChangedReadFlag(Integer customerId) {
		applicationInfoDao.updateApplicationStatusChangedReadFlag(customerId);

	}

	public int getApplicationStatusChangedCount(Integer customerId) {
		return applicationInfoDao.getApplicationStatusChangedCount(customerId);
	}
}
