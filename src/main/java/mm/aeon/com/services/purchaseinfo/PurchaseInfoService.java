package mm.aeon.com.services.purchaseinfo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.common.FileHandler;
import mm.aeon.com.dao.applicationinfo.ApplicationInfoDao;
import mm.aeon.com.dao.purchaseinfo.PurchaseInfoDao;
import mm.aeon.com.dao.purchaseinfoattachment.PurchaseInfoAttachmentDao;
import mm.aeon.com.dto.applicationinfo.AgentApplicationStatusInfoReqDto;
import mm.aeon.com.dto.applicationinfo.AgentApplicationStatusInfoResDto;
import mm.aeon.com.dto.applicationinfo.ApplicationInfoDto;
import mm.aeon.com.dto.applicationinfo.CheckingAttachmentDto;
import mm.aeon.com.dto.applicationinfo.PurchaseApplicationInfoDto;
import mm.aeon.com.dto.applicationinfo.PurchaseAttachmentEditInfoReqDto;
import mm.aeon.com.dto.applicationinfo.PurchaseAttachmentEditInfoResDto;
import mm.aeon.com.dto.applicationinfo.PurchaseAttachmentReuploadReqDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInfoAttachmentDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInfoDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInfoProductDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInquriesResDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInquriesSearchCriteriaDto;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PurchaseInfoService {

	@Autowired
	private PurchaseInfoDao purchaseInfoDao;

	@Autowired
	private PurchaseInfoAttachmentDao purchaseInfoAttachmentDao;

	@Value("${properties.purchaseImageReuploadBaseFilePath}")
	private String purchaseImageReuploadBaseFilePath;

	@Value("${properties.imageBaseFilePath}")
	private String imageBaseFilePath;

	@Value("${properties.purchaseImageFolder}")
	private String purchaseImageFolder;

	@Value("${properties.checkingImageFolder}")
	private String checkingImageFolder;

	@Value("${properties.serverAddress}")
	private String serverAddress;

	@Value("${properties.serverPort}")
	private Integer serverPort;

	@Value("${properties.serverUsername}")
	private String serverUsername;

	@Value("${properties.serverPassword}")
	private String serverPassword;

	@Autowired
	private ApplicationInfoDao applicationInfoDao;

	public Integer purchaseInfoInitialRegister(PurchaseInfoDto purchaseInfoDto) throws Exception {
		purchaseInfoDto.setCreatedBy(purchaseInfoDto.getOutletName());
		purchaseInfoDto.setStatus(CommonConstants.PURCHASE_STATUS_INITIAL);
		Integer purchaseInfoId = purchaseInfoDao.insertPurchaseInfo(purchaseInfoDto);

		for (PurchaseInfoAttachmentDto purchaseInfoAttachmentDto : purchaseInfoDto.getPurchaseInfoAttachmentDtoList()) {
			String fileName = purchaseInfoDto.getCustomerId() + "/" + purchaseInfoId + "/" + CommonUtil.formatByPattern(CommonUtil.getCurrentTime(), "yyyyMMddhhmmssSSS") + ".jpg";
			purchaseInfoAttachmentDto.setFilePath(fileName);
			FileHandler.createFile(new File(imageBaseFilePath + "/" + purchaseImageFolder + "/" + fileName), purchaseInfoAttachmentDto.getPhotoByte());
			purchaseInfoAttachmentDto.setCreatedBy(purchaseInfoDto.getOutletName());
			purchaseInfoAttachmentDto.setDaPurchaseInfoId(purchaseInfoId);
			purchaseInfoAttachmentDao.insertPurchaseInfoAttachment(purchaseInfoAttachmentDto);
		}

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

		String[] destinationPath = { imageBaseFilePath, purchaseImageFolder, purchaseInfoDto.getCustomerId().toString(), purchaseInfoId.toString() };

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

		channelSftp.disconnect();
		session.disconnect();
		return purchaseInfoId;
	}

	public Integer purchaseInfoInitialRegisterWithMultipart(PurchaseInfoDto purchaseInfoDto, MultipartFile multipartFile, PurchaseInfoDto oldPurchaseInfoDto) throws Exception {

		if (oldPurchaseInfoDto != null) {
			oldPurchaseInfoDto.setDelFlag(true);
			oldPurchaseInfoDto.setUpdatedBy(purchaseInfoDto.getOutletName());
			purchaseInfoDao.updatePurchaseInfo(oldPurchaseInfoDto);

			for (PurchaseInfoProductDto purchaseInfoProductDto : oldPurchaseInfoDto.getPurchaseInfoProductDtoList()) {
				purchaseInfoProductDto.setDelFlag(true);
				purchaseInfoProductDto.setUpdatedBy(purchaseInfoDto.getOutletName());
				purchaseInfoDao.updatePurchaseInfoProduct(purchaseInfoProductDto);
			}

		}

		purchaseInfoDto.setCreatedBy(purchaseInfoDto.getOutletName());
		purchaseInfoDto.setStatus(CommonConstants.PURCHASE_STATUS_INITIAL);
		Integer purchaseInfoId = purchaseInfoDao.insertPurchaseInfo(purchaseInfoDto);

		for (PurchaseInfoAttachmentDto purchaseInfoAttachmentDto : purchaseInfoDto.getPurchaseInfoAttachmentDtoList()) {
			String filePath = purchaseInfoDto.getCustomerId() + "/" + purchaseInfoId + "/" + purchaseInfoAttachmentDto.getFileName();
			purchaseInfoAttachmentDto.setFilePath(filePath);
			purchaseInfoAttachmentDto.setCreatedBy(purchaseInfoDto.getOutletName());
			purchaseInfoAttachmentDto.setDaPurchaseInfoId(purchaseInfoId);
			purchaseInfoAttachmentDao.insertPurchaseInfoAttachment(purchaseInfoAttachmentDto);
		}

		byte[] bytes = IOUtils.toByteArray(multipartFile.getInputStream());
		FileHandler.createFile(
				new File(imageBaseFilePath + "/" + purchaseImageFolder + "/" + purchaseInfoDto.getCustomerId() + "/" + purchaseInfoId + "/" + multipartFile.getOriginalFilename()),
				bytes);

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

		String[] destinationPath = { imageBaseFilePath, purchaseImageFolder, purchaseInfoDto.getCustomerId().toString(), purchaseInfoId.toString() };

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

		channelSftp.disconnect();
		session.disconnect();
		return purchaseInfoId;
	}

	public void sendCheckPurchaseApplicationAttachment(CheckingAttachmentDto checkingAttachmentDto) throws Exception {
		String fileName = checkingAttachmentDto.getOldFilePath();
		checkingAttachmentDto.setNewFilePath(fileName);
		FileHandler.createFile(new File(imageBaseFilePath + "/" + checkingImageFolder + "/" + fileName), checkingAttachmentDto.getNewFileByte());

		CheckingAttachmentDto dbCheckingAttachmentDto = purchaseInfoDao.getCheckingAttachmentDtoWithPurchaseIdAndFileType(checkingAttachmentDto.getDaPurchaseInfoId(),
				checkingAttachmentDto.getFileType());
		checkingAttachmentDto.setStatus(CommonConstants.CHECKING_ATTACHMENT_STATUS_REQUEST);
		if (dbCheckingAttachmentDto == null) {
			purchaseInfoDao.insertCheckingAttachment(checkingAttachmentDto);
		} else {
			checkingAttachmentDto.setDaCheckingAttachmentId(dbCheckingAttachmentDto.getDaCheckingAttachmentId());
			purchaseInfoDao.updateCheckingAttachment(checkingAttachmentDto);
		}

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

		String[] destinationPath = { imageBaseFilePath, checkingImageFolder, fileName.substring(0, 6), fileName.substring(7, 17) };

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
		channelSftp.disconnect();
		session.disconnect();
	}

	public void sendCheckPurchaseApplicationAttachmentWithMultipart(CheckingAttachmentDto checkingAttachmentDto, MultipartFile img) throws Exception {
		String fileName = checkingAttachmentDto.getOldFilePath();
		checkingAttachmentDto.setNewFilePath(fileName);
		byte[] bytes = IOUtils.toByteArray(img.getInputStream());
		FileHandler.createFile(new File(imageBaseFilePath + "/" + checkingImageFolder + "/" + fileName), bytes);

		CheckingAttachmentDto dbCheckingAttachmentDto = purchaseInfoDao.getCheckingAttachmentDtoWithPurchaseIdAndFileType(checkingAttachmentDto.getDaPurchaseInfoId(),
				checkingAttachmentDto.getFileType());
		checkingAttachmentDto.setStatus(CommonConstants.CHECKING_ATTACHMENT_STATUS_REQUEST);
		if (dbCheckingAttachmentDto == null) {
			purchaseInfoDao.insertCheckingAttachment(checkingAttachmentDto);
		} else {
			checkingAttachmentDto.setDaCheckingAttachmentId(dbCheckingAttachmentDto.getDaCheckingAttachmentId());
			purchaseInfoDao.updateCheckingAttachment(checkingAttachmentDto);
		}

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

		String[] destinationPath = { imageBaseFilePath, checkingImageFolder, fileName.substring(0, 6), fileName.substring(7, 17) };

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
		channelSftp.disconnect();
		session.disconnect();
	}

	public void purchaseInfoFinalRegister(PurchaseInfoDto purchaseInfoDto, ApplicationInfoDto applicationInfoDto) throws Exception {
		purchaseInfoDto.setUpdatedBy(purchaseInfoDto.getOutletName());
		purchaseInfoDto.setStatus(CommonConstants.PURCHASE_STATUS_COMPLETE);
		purchaseInfoDto.setPurchaseDate(CommonUtil.getCurrentTime());
		purchaseInfoDao.updatePurchaseInfo(purchaseInfoDto);

		applicationInfoDto.setCreatedBy(purchaseInfoDto.getOutletName());
		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_PURCHASE_COMPLETE);
		applicationInfoDao.insertApplicationInfoHistory(applicationInfoDto);

		applicationInfoDto.setCreatedBy(null);
		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_PURCHASE_COMPLETE);
		applicationInfoDto.setUpdatedBy(purchaseInfoDto.getOutletName());
		applicationInfoDao.updateApplicationInfo(applicationInfoDto);

		for (PurchaseInfoAttachmentDto purchaseInfoAttachmentDto : purchaseInfoDto.getPurchaseInfoAttachmentDtoList()) {
			String fileName = purchaseInfoDto.getCustomerId() + "/" + purchaseInfoDto.getDaPurchaseInfoId() + "/"
					+ CommonUtil.formatByPattern(CommonUtil.getCurrentTime(), "yyyyMMddhhmmssSSS") + ".jpg";
			purchaseInfoAttachmentDto.setFilePath(fileName);
			FileHandler.createFile(new File(imageBaseFilePath + "/" + purchaseImageFolder + "/" + fileName), purchaseInfoAttachmentDto.getPhotoByte());
			purchaseInfoAttachmentDto.setCreatedBy(purchaseInfoDto.getOutletName());
			purchaseInfoAttachmentDto.setDaPurchaseInfoId(purchaseInfoDto.getDaPurchaseInfoId());
			purchaseInfoAttachmentDao.insertPurchaseInfoAttachment(purchaseInfoAttachmentDto);
		}

		purchaseInfoDao.updateQRNotShowCustAgreement(applicationInfoDto.getApplicationNo());

	}

	public void purchaseInfoFinalRegisterWithMultipart(PurchaseInfoDto purchaseInfoDto, ApplicationInfoDto applicationInfoDto, List<MultipartFile> multipartFileList)
			throws Exception {
		purchaseInfoDto.setUpdatedBy(purchaseInfoDto.getOutletName());
		purchaseInfoDto.setStatus(CommonConstants.PURCHASE_STATUS_COMPLETE);
		purchaseInfoDto.setPurchaseDate(CommonUtil.getCurrentTime());
		purchaseInfoDao.updatePurchaseInfo(purchaseInfoDto);

		applicationInfoDto.setCreatedBy(purchaseInfoDto.getOutletName());
		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_PURCHASE_COMPLETE);
		applicationInfoDao.insertApplicationInfoHistory(applicationInfoDto);

		applicationInfoDto.setStatusChangedReadFlag(false);
		applicationInfoDto.setCreatedBy(null);
		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_PURCHASE_COMPLETE);
		applicationInfoDto.setUpdatedBy(purchaseInfoDto.getOutletName());
		applicationInfoDao.updateApplicationInfo(applicationInfoDto);

		for (PurchaseInfoAttachmentDto purchaseInfoAttachmentDto : purchaseInfoDto.getPurchaseInfoAttachmentDtoList()) {
			String filePath = purchaseInfoDto.getCustomerId() + "/" + purchaseInfoDto.getDaPurchaseInfoId() + "/" + purchaseInfoAttachmentDto.getFileName();
			purchaseInfoAttachmentDto.setFilePath(filePath);
			purchaseInfoAttachmentDto.setCreatedBy(purchaseInfoDto.getOutletName());
			purchaseInfoAttachmentDto.setDaPurchaseInfoId(purchaseInfoDto.getDaPurchaseInfoId());
			purchaseInfoAttachmentDao.insertPurchaseInfoAttachment(purchaseInfoAttachmentDto);
		}

		for (MultipartFile multipartFile : multipartFileList) {
			byte[] bytes = IOUtils.toByteArray(multipartFile.getInputStream());
			FileHandler.createFile(new File(imageBaseFilePath + "/" + purchaseImageFolder + "/" + purchaseInfoDto.getCustomerId() + "/" + purchaseInfoDto.getDaPurchaseInfoId()
					+ "/" + multipartFile.getOriginalFilename()), bytes);
		}

		purchaseInfoDao.updateQRNotShowCustAgreement(applicationInfoDto.getApplicationNo());

	}

	public void purchaseInfoInitialUpdate(PurchaseInfoDto purchaseInfoDto, String fileName) throws Exception {
		purchaseInfoDto.setUpdatedBy(purchaseInfoDto.getOutletName());
		purchaseInfoDto.setStatus(CommonConstants.PURCHASE_STATUS_INITIAL);
		purchaseInfoDao.updatePurchaseInfo(purchaseInfoDto);

		for (PurchaseInfoAttachmentDto purchaseInfoAttachmentDto : purchaseInfoDto.getPurchaseInfoAttachmentDtoList()) {
			FileHandler.createFile(new File(imageBaseFilePath + "/" + purchaseImageFolder + "/" + fileName), purchaseInfoAttachmentDto.getPhotoByte());
		}

	}

	public void purchaseInfoInitialUpdateWithMultipart(PurchaseInfoDto purchaseInfoDto, String fileName, MultipartFile multipartFile) throws Exception {
		purchaseInfoDto.setUpdatedBy(purchaseInfoDto.getOutletName());
		purchaseInfoDto.setStatus(CommonConstants.PURCHASE_STATUS_INITIAL);
		purchaseInfoDao.updatePurchaseInfo(purchaseInfoDto);

		byte[] bytes = IOUtils.toByteArray(multipartFile.getInputStream());
		FileHandler.createFile(new File(imageBaseFilePath + "/" + purchaseImageFolder + "/" + fileName), bytes);

	}

	public void setPurchaseInfoConfirmWaiting(PurchaseInfoDto purchaseInfoDto, ApplicationInfoDto applicationInfoDto) {
		purchaseInfoDto.setUpdatedBy(purchaseInfoDto.getOutletName());
		purchaseInfoDto.setStatus(CommonConstants.PURCHASE_STATUS_CONFIRM_WAITING);
		purchaseInfoDao.updatePurchaseInfo(purchaseInfoDto);

		purchaseInfoDao.deletePurchaseInfoProductWithPurchaseInfoId(purchaseInfoDto.getDaPurchaseInfoId());

		for (PurchaseInfoProductDto purchaseInfoProductDto : purchaseInfoDto.getPurchaseInfoProductDtoList()) {
			purchaseInfoProductDto.setCreatedBy(purchaseInfoDto.getOutletName());
			purchaseInfoDao.insertPurchaseInfoProduct(purchaseInfoProductDto);
		}

		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_PURCHASE_CONFIRM_WAITING);
		applicationInfoDto.setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDao.updateApplicationInfo(applicationInfoDto);

	}

	public void purchaseInfoConfirm(PurchaseInfoDto purchaseInfoDto, ApplicationInfoDto applicationInfoDto) {
		purchaseInfoDto.setUpdatedBy(purchaseInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		purchaseInfoDto.setStatus(CommonConstants.PURCHASE_STATUS_CONFIRM);
		purchaseInfoDao.updatePurchaseInfo(purchaseInfoDto);

		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_PURCHASE_CONFIRM);
		applicationInfoDto.setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		applicationInfoDao.updateApplicationInfo(applicationInfoDto);
	}

	public void purchaseInfoCancel(PurchaseInfoDto purchaseInfoDto, ApplicationInfoDto applicationInfoDto) {
		if (purchaseInfoDto.getOutletId() != null && purchaseInfoDto.getOutletName() != null) {
			purchaseInfoDto.setUpdatedBy(purchaseInfoDto.getOutletName());
			applicationInfoDto.setCreatedBy(purchaseInfoDto.getOutletName());
		} else {
			purchaseInfoDto.setUpdatedBy(purchaseInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
			applicationInfoDto.setCreatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		}

		applicationInfoDto.setStatus(CommonConstants.APP_STATUS_PURCHASE_CANCEL);
		applicationInfoDao.insertApplicationInfoHistory(applicationInfoDto);

		if (purchaseInfoDto.getOutletId() != null && purchaseInfoDto.getOutletName() != null) {
			applicationInfoDto.setUpdatedBy(purchaseInfoDto.getOutletName());
		} else {
			applicationInfoDto.setUpdatedBy(applicationInfoDto.getCustomerId() + "," + CommonConstants.CUSTOMER_TYPE);
		}
		applicationInfoDto.setCreatedBy(null);
		applicationInfoDao.updateApplicationInfo(applicationInfoDto);

		purchaseInfoDto.setStatus(CommonConstants.PURCHASE_STATUS_CANCEL);
		purchaseInfoDao.updatePurchaseInfo(purchaseInfoDto);
	}

	public PurchaseApplicationInfoDto getPurchaseApplicationInfoDto(Integer daApplicationInfoId) {
		return purchaseInfoDao.getPurchaseApplicationInfoDto(daApplicationInfoId);
	}

	public void insertCheckingAttachment(CheckingAttachmentDto checkingAttachmentDto) {
		purchaseInfoDao.insertCheckingAttachment(checkingAttachmentDto);
	}

	public void updateCheckingAttachment(CheckingAttachmentDto checkingAttachmentDto) {
		purchaseInfoDao.updateCheckingAttachment(checkingAttachmentDto);
	}

	public CheckingAttachmentDto getCheckingAttachmentDtoWithPurchaseIdAndFileType(Integer daPurchaseInfoId, Integer fileType) {
		return purchaseInfoDao.getCheckingAttachmentDtoWithPurchaseIdAndFileType(daPurchaseInfoId, fileType);
	}

	public List<CheckingAttachmentDto> getCheckingAttachmentList(Integer daPurchaseInfoId) {
		return purchaseInfoDao.getCheckingAttachmentList(daPurchaseInfoId);
	}

	public List<PurchaseInquriesResDto> getPurchaseInquriesList(PurchaseInquriesSearchCriteriaDto purchaseInquriesSearchCriteriaDto) {
		return purchaseInfoDao.getPurchaseInquriesList(purchaseInquriesSearchCriteriaDto);
	}

	public List<PurchaseAttachmentEditInfoResDto> getPurchaseAttachmentEditInfoList(PurchaseAttachmentEditInfoReqDto purchaseAttachmentEditInfoReqDto) {
		return purchaseInfoDao.getPurchaseAttachmentEditInfoList(purchaseAttachmentEditInfoReqDto);
	}

	public void purchaseAttachmentReuploadWithMultipart(PurchaseAttachmentReuploadReqDto purchaseAttachmentReuploadReqDto, List<MultipartFile> multipartFileList)
			throws JSchException, SftpException, IOException {

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

		String[] destinationPath = { purchaseImageReuploadBaseFilePath, purchaseAttachmentReuploadReqDto.getApplicationNo(),
				CommonUtil.formatByPattern(CommonUtil.getCurrentTime(), "yyyyMMddhhmmssSSS") };

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

		for (MultipartFile multipartFile : multipartFileList) {

			channelSftp.put(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), ChannelSftp.APPEND);
			channelSftp.chmod(Integer.parseInt("777", 8), multipartFile.getOriginalFilename());

		}

		channelSftp.disconnect();
		session.disconnect();

	}

	public AgentApplicationStatusInfoResDto getAgentApplicationStatusInfo(AgentApplicationStatusInfoReqDto agentApplicationStatusInfoReqDto) {
		return purchaseInfoDao.getAgentApplicationStatusInfo(agentApplicationStatusInfoReqDto);
	}

}
