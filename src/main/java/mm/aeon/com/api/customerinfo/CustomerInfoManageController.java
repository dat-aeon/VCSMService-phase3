package mm.aeon.com.api.customerinfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mm.aeon.com.common.AESFactory;
import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.common.FileHandler;
import mm.aeon.com.common.QRScanObj;
import mm.aeon.com.common.VCSMPasswordEncoder;
import mm.aeon.com.dto.appusageinfo.AppUsageDetailDto;
import mm.aeon.com.dto.custagreement.CustomerAgreementDto;
import mm.aeon.com.dto.customerinfo.ChangePasswordReqDto;
import mm.aeon.com.dto.customerinfo.CustomerInfoDto;
import mm.aeon.com.dto.customerinfo.CustomerInfoEditReqDto;
import mm.aeon.com.dto.customerinfo.CustomerProfileImageResDto;
import mm.aeon.com.dto.customerinfo.UpgradeMemberReqDto;
import mm.aeon.com.dto.customerinfo.UserInformationResDto;
import mm.aeon.com.dto.customerinfo.VerifyMemberInfoReqDto;
import mm.aeon.com.dto.customerinfo.VerifyMemberInfoResDto;
import mm.aeon.com.dto.customersecurityquestion.CustomerSecurityQuestionDto;
import mm.aeon.com.dto.customersecurityquestion.CustomerSecurityQuestionResDto;
import mm.aeon.com.dto.customersecurityquestion.UpdateCustomerSecQAReqDto;
import mm.aeon.com.dto.importcustomerinfo.ImportCustomerInfoDto;
import mm.aeon.com.dto.information.CompanyInfoResDto;
import mm.aeon.com.dto.resetpassword.ConfirmSecurityQuestionAnswerReqDto;
import mm.aeon.com.dto.resetpassword.SecurityQuestionAnswerReqDto;
import mm.aeon.com.dto.resetpassword.SecurityQuestionResDto;
import mm.aeon.com.services.appusageinfo.AppUsageInfoService;
import mm.aeon.com.services.customerinfo.CustomerInfoService;
import mm.aeon.com.services.importcustomerinfo.ImportCustomerInfoService;
import mm.aeon.com.services.information.InformationService;
import mm.aeon.com.services.resetpassword.ResetPasswordService;
import mm.aeon.com.zconfig.MessageCode;
import mm.aeon.com.zconfig.exception.BusinessLogicException;
import mm.aeon.com.zgen.entity.PasswordInfo;

@RestController
@RequestMapping("/customer-info-manage")
public class CustomerInfoManageController {

	@Autowired
	private InformationService informationService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CustomerInfoService customerInfoService;

	@Autowired
	private ImportCustomerInfoService importCustomerInfoService;

	@Autowired
	private ResetPasswordService resetPasswordService;

	@Autowired
	private AppUsageInfoService appUsageInfoService;

	@Value("${properties.imageBaseFilePath}")
	private String imageBaseFilePath;

	@Value("${properties.profileImageFolder}")
	private String profileImageFolder;

	@RequestMapping(value = "/verify-member-info", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public VerifyMemberInfoResDto verifyMemberInfo(@RequestBody VerifyMemberInfoReqDto verifyMemberInfoReqDto) {
		validateVerifyMemberInput(verifyMemberInfoReqDto);

		VerifyMemberInfoResDto verifyMemberInfoResDto = new VerifyMemberInfoResDto();

		// get import customer info for check member
		ImportCustomerInfoDto importCustomerInfoDto = importCustomerInfoService.getImportCustomerInfoWithDobAndNrc(verifyMemberInfoReqDto.getDateOfBirth(),
				verifyMemberInfoReqDto.getNrcNo());
		if (importCustomerInfoDto == null) {
			verifyMemberInfoResDto.setVerifyStatus(CommonConstants.NOT_VALID);
			return verifyMemberInfoResDto;
		}

		// get customer agreement list for check valid agreement no
		List<CustomerAgreementDto> customerAgreementDtoList = importCustomerInfoService
				.getCustomerAgreementListWithImportCustomerId(importCustomerInfoDto.getImportCustomerInfoId());
		if (CollectionUtils.isEmpty(customerAgreementDtoList)) {
			throw new BusinessLogicException(MessageCode.MISSING_CUSTOMER_AGREEMENT_INFO, messageSource.getMessage(MessageCode.MISSING_CUSTOMER_AGREEMENT_INFO, null, null));
		}

		boolean agreementValid = false;
		// validate agreement no
		for (CustomerAgreementDto customerAgreementDto : customerAgreementDtoList) {
			if (customerAgreementDto.getAgreementNo().equals(verifyMemberInfoReqDto.getAgreementNo())) {
				agreementValid = true;
				break;
			}
		}

		if (agreementValid) {
			// if agreement is valid, check nrc no is other customer nrc or not
			int nrcNoCount = customerInfoService.getNrcNoCount(verifyMemberInfoReqDto.getNrcNo(), verifyMemberInfoReqDto.getCustomerId());
			if (nrcNoCount > 0) {
				throw new BusinessLogicException(MessageCode.INVALID_MEMBER_INFO, messageSource.getMessage(MessageCode.INVALID_MEMBER_INFO, null, null));
			}

		} else {
			throw new BusinessLogicException(MessageCode.INVALID_AGREEMENT_NO, messageSource.getMessage(MessageCode.INVALID_AGREEMENT_NO, null, null));
		}
		verifyMemberInfoResDto.setCustomerNo(importCustomerInfoDto.getCustomerNo());
		verifyMemberInfoResDto.setVerifyStatus(CommonConstants.VALID);
		return verifyMemberInfoResDto;

	}

	@RequestMapping(value = "/confirm-security-question-answer", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void confirmSecurityQuestionAnswer(@RequestBody ConfirmSecurityQuestionAnswerReqDto confirmSecurityQuestionAnswerReqDto) {

		validateConfirmSecurityQuestionAnswerInput(confirmSecurityQuestionAnswerReqDto);

		List<CustomerSecurityQuestionDto> customerSecurityQuestionDtoList = resetPasswordService
				.getCustomerSecurityQuestionListWithCustomerId(confirmSecurityQuestionAnswerReqDto.getCustomerId());

		// check customer security question exist
		if (CollectionUtils.isEmpty(customerSecurityQuestionDtoList)) {
			throw new BusinessLogicException(MessageCode.NOT_EXIST_CUSTOMER_ANSWER, messageSource.getMessage(MessageCode.NOT_EXIST_CUSTOMER_ANSWER, null, null));
		}

		validateCustomerAnswer(confirmSecurityQuestionAnswerReqDto.getSecurityQuestionAnswerReqDtoList(), customerSecurityQuestionDtoList);
	}

	@RequestMapping(value = "/upgrade-member", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public UserInformationResDto upgradeMember(@RequestBody UpgradeMemberReqDto upgradeMemberReqDto) {

		validateUpgradeMemberInput(upgradeMemberReqDto);

		ImportCustomerInfoDto importCustomerInfoDto = importCustomerInfoService.getImportCustomerInfoWithCustomerNo(upgradeMemberReqDto.getCustomerNo());
		if (importCustomerInfoDto == null) {
			throw new BusinessLogicException(MessageCode.MISSING_IMPORT_CUSTOMER_INFO, messageSource.getMessage(MessageCode.MISSING_IMPORT_CUSTOMER_INFO, null, null));
		}
		importCustomerInfoDto.setPhoneNo(CommonUtil.modifyInvalidPhone(importCustomerInfoDto.getPhoneNo()));

		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoWithCustomerId(upgradeMemberReqDto.getCustomerId());
		if (customerInfoDto == null) {
			throw new BusinessLogicException(MessageCode.NOT_EXIST_CUSTOMER_INFO, messageSource.getMessage(MessageCode.NOT_EXIST_CUSTOMER_INFO, null, null));
		}

		// check duplicate phone no
		int phoneNoCount = customerInfoService.getPhoneNoCount(importCustomerInfoDto.getPhoneNo(), customerInfoDto.getCustomerId());
		if (phoneNoCount > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_PHONE_NO, messageSource.getMessage(MessageCode.DUPLICATED_PHONE_NO, null, null));
		}

		// check duplicate nrc no
		int nrcNoCount = customerInfoService.getNrcNoCount(importCustomerInfoDto.getNrcNo(), customerInfoDto.getCustomerId());
		if (nrcNoCount > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_NRC_NO, messageSource.getMessage(MessageCode.DUPLICATED_NRC_NO, null, null));
		}

		// transfer import customer info data to customer info
		customerInfoDto.setCustomerNo(importCustomerInfoDto.getCustomerNo());
		customerInfoDto.setAddress(importCustomerInfoDto.getAddress());
		customerInfoDto.setCompanyName(importCustomerInfoDto.getCompanyName());
		customerInfoDto.setCustomerNo(importCustomerInfoDto.getCustomerNo());
		customerInfoDto.setDob(importCustomerInfoDto.getDob());
		customerInfoDto.setGender(importCustomerInfoDto.getGender());
		customerInfoDto.setName(importCustomerInfoDto.getName().toUpperCase());
		customerInfoDto.setNrcNo(importCustomerInfoDto.getNrcNo());
		customerInfoDto.setPhoneNo(importCustomerInfoDto.getPhoneNo());
		customerInfoDto.setSalary(importCustomerInfoDto.getSalary());
		customerInfoDto.setJoinDate(CommonUtil.getCurrentTime());
		customerInfoDto.setCustomerTypeId(CommonConstants.MEMBER_TYPE); // NON_MEMBER
		customerInfoDto.setUserTypeId(CommonConstants.CUSTOMER_TYPE); // CUSTOMER_TYPE

		// image file create
		if (ArrayUtils.isNotEmpty(upgradeMemberReqDto.getPhotoByte())) {
			String fileName = CommonUtil.formatByPattern(CommonUtil.getCurrentTime(), "yyyyMMddhhmmssSSS") + ".png";
			customerInfoDto.setPhotoPath(fileName);
			try {
				FileHandler.createFile(new File(imageBaseFilePath + profileImageFolder + fileName), upgradeMemberReqDto.getPhotoByte());
			} catch (IOException e) {
				throw new BusinessLogicException(MessageCode.FILE_WRITING_ERROR, messageSource.getMessage(MessageCode.FILE_WRITING_ERROR, null, null));
			}
		}

		customerInfoService.upgradeMemeber(customerInfoDto);

		UserInformationResDto userInformationResDto = new UserInformationResDto();
		userInformationResDto.setCustomerId(customerInfoDto.getCustomerId());
		userInformationResDto.setCustomerNo(customerInfoDto.getCustomerNo());
		userInformationResDto.setCustomerTypeId(customerInfoDto.getCustomerTypeId());
		userInformationResDto.setDateOfBirth(customerInfoDto.getDob());
		userInformationResDto.setName(customerInfoDto.getName());
		userInformationResDto.setNrcNo(customerInfoDto.getNrcNo());
		userInformationResDto.setPhoneNo(customerInfoDto.getPhoneNo());
		userInformationResDto.setPhotoPath(customerInfoDto.getPhotoPath());
		userInformationResDto.setUserTypeId(customerInfoDto.getUserTypeId());
		userInformationResDto.setMemberNo(importCustomerInfoDto.getMembercardId());

		List<CustomerAgreementDto> customerAgreementDtoList = importCustomerInfoService
				.getCustomerAgreementListWithImportCustomerId(importCustomerInfoDto.getImportCustomerInfoId());
		userInformationResDto.setCustomerAgreementDtoList(customerAgreementDtoList);

		CompanyInfoResDto companyInfoResDto = informationService.getCompanyInfo();
		if (companyInfoResDto != null) {
			userInformationResDto.setHotlinePhone(companyInfoResDto.getHotlinePhone());
		}

		if (importCustomerInfoDto.getMembercardStatus() != null && importCustomerInfoDto.getMembercardStatus() == 0) {
			userInformationResDto.setMemberNoValid(true);
		} else {
			userInformationResDto.setMemberNoValid(false);
		}

		return userInformationResDto;

	}

	@RequestMapping(value = "/get-user-information", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public UserInformationResDto getUserInformation(@RequestBody CustomerInfoDto customerInfoDto) {

		if (StringUtils.isEmpty(customerInfoDto.getPhoneNo())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		CustomerInfoDto customerInfoDtoDb = customerInfoService.getCustomerInfoWithPhoneNo(customerInfoDto.getPhoneNo());
		if (customerInfoDtoDb == null) {
			throw new BusinessLogicException(MessageCode.NOT_EXIST_CUSTOMER_INFO, messageSource.getMessage(MessageCode.NOT_EXIST_CUSTOMER_INFO, null, null));
		}

		UserInformationResDto userInformationResDto = new UserInformationResDto();
		userInformationResDto.setCustomerId(customerInfoDtoDb.getCustomerId());
		userInformationResDto.setCustomerNo(customerInfoDtoDb.getCustomerNo());
		userInformationResDto.setCustomerTypeId(customerInfoDtoDb.getCustomerTypeId());
		userInformationResDto.setDateOfBirth(customerInfoDtoDb.getDob());
		userInformationResDto.setName(customerInfoDtoDb.getName());
		userInformationResDto.setNrcNo(customerInfoDtoDb.getNrcNo());
		userInformationResDto.setPhoneNo(customerInfoDtoDb.getPhoneNo());
		userInformationResDto.setPhotoPath(customerInfoDtoDb.getPhotoPath());
		userInformationResDto.setUserTypeId(customerInfoDtoDb.getUserTypeId());

		if (customerInfoDtoDb.getCustomerNo() != null) {
			ImportCustomerInfoDto importCustomerInfoDto = importCustomerInfoService.getImportCustomerInfoWithCustomerNo(customerInfoDtoDb.getCustomerNo());
			if (importCustomerInfoDto != null) {
				userInformationResDto.setMemberNo(importCustomerInfoDto.getMembercardId());
				List<CustomerAgreementDto> customerAgreementDtoList = importCustomerInfoService
						.getCustomerAgreementListWithImportCustomerId(importCustomerInfoDto.getImportCustomerInfoId());
				userInformationResDto.setCustomerAgreementDtoList(customerAgreementDtoList);
			}
		}

		return userInformationResDto;

	}

	@RequestMapping(value = "/update-customer-profile", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void updateCustomerProfile(@RequestBody CustomerInfoDto customerInfoDto) {
		validateUpdateCustomerProfileInput(customerInfoDto);
		CustomerInfoEditReqDto customerInfoEditReqDto = new CustomerInfoEditReqDto();
		customerInfoEditReqDto.setCustomerId(customerInfoDto.getCustomerId());
		customerInfoEditReqDto.setName(customerInfoDto.getName());
		customerInfoEditReqDto.setDob(customerInfoDto.getDob());
		customerInfoEditReqDto.setNrcNo(customerInfoDto.getNrcNo());
		customerInfoEditReqDto.setPhoneNo(CommonUtil.modifyInvalidPhone(customerInfoDto.getPhoneNo()));
		customerInfoEditReqDto.setStatus(CommonConstants.PROFILE_REQ_EDIT);
		CustomerInfoEditReqDto dbCustomerInfoEditReqDto = customerInfoService.getCustomerInfoEditReq(customerInfoEditReqDto.getCustomerId());
		if (dbCustomerInfoEditReqDto != null) {
			customerInfoEditReqDto.setDaCustomerInfoEditReqId(dbCustomerInfoEditReqDto.getDaCustomerInfoEditReqId());
			customerInfoService.updateDaCustomerInfoEdit(customerInfoEditReqDto);
		} else {
			customerInfoService.insertDaCustomerInfoEdit(customerInfoEditReqDto);
		}

	}

	@RequestMapping(value = "/get-customer-info-edit-req", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public CustomerInfoEditReqDto getCustomerInfoEditReq(@RequestBody CustomerInfoDto customerInfoDto) {

		if (StringUtils.isEmpty(customerInfoDto.getCustomerId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		CustomerInfoEditReqDto customerInfoEditReqDto = customerInfoService.getCustomerInfoEditReq(customerInfoDto.getCustomerId());
		return customerInfoEditReqDto;

	}

	@RequestMapping(value = "/get-customer-agreement-list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public List<CustomerAgreementDto> getCustomerAgreementList(@RequestBody CustomerInfoDto customerInfoDto) {

		if (StringUtils.isEmpty(customerInfoDto.getCustomerId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		List<CustomerAgreementDto> customerAgreementDtoList = new ArrayList<CustomerAgreementDto>();
		CustomerInfoDto customerInfoDtoDb = customerInfoService.getCustomerInfoWithCustomerId(customerInfoDto.getCustomerId());
		if (customerInfoDtoDb != null) {
			ImportCustomerInfoDto importCustomerInfoDto = importCustomerInfoService.getImportCustomerInfoWithCustomerNo(customerInfoDtoDb.getCustomerNo());
			if (importCustomerInfoDto != null) {
				customerAgreementDtoList = importCustomerInfoService.getCustomerAgreementListWithImportCustomerId(importCustomerInfoDto.getImportCustomerInfoId());
				for (CustomerAgreementDto customerAgreementDto : customerAgreementDtoList) {
					if (customerAgreementDto.getApplicationNo() != null) {
						QRScanObj qRScanObj = new QRScanObj();
						qRScanObj.setAgreementNo(customerAgreementDto.getAgreementNo());
						qRScanObj.setCustomerId(customerInfoDtoDb.getCustomerId());
						qRScanObj.setDaApplicationInfoId(customerAgreementDto.getDaApplicationInfoId());
						Gson gson = new GsonBuilder().create();
						String jsonString = gson.toJson(qRScanObj);
						customerAgreementDto.setEncodeStringForQr(AESFactory.encrypt(jsonString));
					}

				}
			}
		}

		return customerAgreementDtoList;

	}

	@RequestMapping(value = "/update-customer-security-question-answer", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void updateCustomerSecurityQuestionAnswer(@RequestBody UpdateCustomerSecQAReqDto updateCustomerSecQAReqDto) {
		validateUpdateSecurityQuestionAnswerInput(updateCustomerSecQAReqDto);
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoWithCustomerId(updateCustomerSecQAReqDto.getCustomerId());
		String encodedPassword = VCSMPasswordEncoder.encode(updateCustomerSecQAReqDto.getPassword());
		if (!encodedPassword.equals(customerInfoDto.getPassword())) {
			throw new BusinessLogicException(MessageCode.INCORRECT_PASSWORD, messageSource.getMessage(MessageCode.INCORRECT_PASSWORD, null, null));
		}

		List<CustomerSecurityQuestionDto> customerSecurityQuestionDtoList = new ArrayList<CustomerSecurityQuestionDto>();
		for (SecurityQuestionAnswerReqDto securityQuestionAnswerReqDto : updateCustomerSecQAReqDto.getSecurityQuestionAnswerReqDtoList()) {
			CustomerSecurityQuestionDto customerSecurityQuestionDto = new CustomerSecurityQuestionDto();
			customerSecurityQuestionDto.setCustomerId(updateCustomerSecQAReqDto.getCustomerId());
			customerSecurityQuestionDto.setAnswer(securityQuestionAnswerReqDto.getAnswer());
			customerSecurityQuestionDto.setSecQuesId(securityQuestionAnswerReqDto.getSecQuesId());
			customerSecurityQuestionDtoList.add(customerSecurityQuestionDto);
		}

		customerInfoService.updateCustomerSecurityQuestionAnswer(customerSecurityQuestionDtoList);

	}

	@RequestMapping(value = "/update-app-usage-detail-for-logout", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void updateAppUsageDetailForLogout(@RequestBody CustomerInfoDto customerInfoDto) {
		// check input fields
		if (StringUtils.isEmpty(customerInfoDto.getCustomerId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		AppUsageDetailDto appUsageDetailDto = appUsageInfoService.getLastAppUsageDetailWithCustomerId(customerInfoDto.getCustomerId());
		if (appUsageDetailDto != null) {
			appUsageDetailDto.setEndUsageDateTime(CommonUtil.getCurrentTime());
			appUsageInfoService.updateAppUsageDetail(appUsageDetailDto);
		}

	}

	@RequestMapping(value = "/get-customer-security-question-list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public CustomerSecurityQuestionResDto getCustomerSecurityQuestionList(@RequestBody CustomerInfoDto customerInfoDto) {

		if (StringUtils.isEmpty(customerInfoDto.getCustomerId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		CustomerSecurityQuestionResDto customerSecurityQuestionResDto = new CustomerSecurityQuestionResDto();
		SecurityQuestionResDto securityQuestionResDto = resetPasswordService.getSecurityQuestionList();
		List<CustomerSecurityQuestionDto> customerSecurityQuestionDtoList = resetPasswordService.getCustomerSecurityQuestionListWithCustomerId(customerInfoDto.getCustomerId());
		customerSecurityQuestionResDto.setCustomerSecurityQuestionDtoList(customerSecurityQuestionDtoList);
		customerSecurityQuestionResDto.setNumOfAnsChar(securityQuestionResDto.getNumOfAnsChar());
		customerSecurityQuestionResDto.setNumOfSecQues(securityQuestionResDto.getNumOfSecQues());
		return customerSecurityQuestionResDto;

	}

	@RequestMapping(value = "/check-password", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void checkPassword(@RequestBody CustomerInfoDto customerInfoDto) {

		if (StringUtils.isEmpty(customerInfoDto.getCustomerId()) || StringUtils.isEmpty(customerInfoDto.getPassword())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		CustomerInfoDto customerInfoDBDto = customerInfoService.getCustomerInfoWithCustomerId(customerInfoDto.getCustomerId());

		if (customerInfoDBDto == null) {
			throw new BusinessLogicException(MessageCode.NOT_EXIST_CUSTOMER_INFO, messageSource.getMessage(MessageCode.NOT_EXIST_CUSTOMER_INFO, null, null));
		}

		String hashed = VCSMPasswordEncoder.encode(customerInfoDto.getPassword());
		if (!hashed.equals(customerInfoDBDto.getPassword())) {
			throw new BusinessLogicException(MessageCode.INCORRECT_PASSWORD, messageSource.getMessage(MessageCode.INCORRECT_PASSWORD, null, null));
		}

	}

	@RequestMapping(value = "/update-customer-profile-image", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public CustomerProfileImageResDto updateCustomerProfileImage(@RequestBody CustomerInfoDto customerInfoDto) {

		if (StringUtils.isEmpty(customerInfoDto.getCustomerId()) || ArrayUtils.isEmpty(customerInfoDto.getPhotoByte())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		CustomerInfoDto customerInfoDBDto = customerInfoService.getCustomerInfoWithCustomerId(customerInfoDto.getCustomerId());

		if (customerInfoDBDto == null) {
			throw new BusinessLogicException(MessageCode.NOT_EXIST_CUSTOMER_INFO, messageSource.getMessage(MessageCode.NOT_EXIST_CUSTOMER_INFO, null, null));
		}

		// image file create
		String fileName = CommonUtil.formatByPattern(CommonUtil.getCurrentTime(), "yyyyMMddhhmmssSSS") + ".png";
		customerInfoDto.setPhotoPath(fileName);
		File imageFile = new File(imageBaseFilePath + profileImageFolder + fileName);
		try {
			FileHandler.createFile(imageFile, customerInfoDto.getPhotoByte());
			customerInfoService.updateCustomerInfo(customerInfoDto);

			if (customerInfoDBDto.getPhotoPath() != null) {
				String oldUploadImageFolderPath = imageBaseFilePath + profileImageFolder + customerInfoDBDto.getPhotoPath();
				FileHandler.forceDelete(oldUploadImageFolderPath);

			}
		} catch (IOException e) {
			throw new BusinessLogicException(MessageCode.FILE_WRITING_ERROR, messageSource.getMessage(MessageCode.FILE_WRITING_ERROR, null, null));
		}

		CustomerProfileImageResDto customerProfileImageResDto = new CustomerProfileImageResDto();
		customerProfileImageResDto.setPhotoPath(customerInfoDto.getPhotoPath());
		return customerProfileImageResDto;

	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void changePassword(@RequestBody ChangePasswordReqDto changePasswordReqDto) {
		validateChangePasswordInput(changePasswordReqDto);
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoWithCustomerId(changePasswordReqDto.getCustomerId());
		String encodedPassword = VCSMPasswordEncoder.encode(changePasswordReqDto.getOldPassword());
		if (!encodedPassword.equals(customerInfoDto.getPassword())) {
			throw new BusinessLogicException(MessageCode.INCORRECT_PASSWORD, messageSource.getMessage(MessageCode.INCORRECT_PASSWORD, null, null));
		}
		PasswordInfo passwordInfo = new PasswordInfo();
		passwordInfo.setPassword(VCSMPasswordEncoder.encode(changePasswordReqDto.getNewPassword()));
		passwordInfo.setUserId(customerInfoDto.getCustomerId());
		passwordInfo.setUpdatedBy(customerInfoDto.getName());
		customerInfoService.updateCustomerPassword(passwordInfo);

	}

	private void validateChangePasswordInput(ChangePasswordReqDto changePasswordReqDto) {
		// check input fields
		if (StringUtils.isEmpty(changePasswordReqDto.getCustomerId()) || StringUtils.isEmpty(changePasswordReqDto.getOldPassword())
				|| StringUtils.isEmpty(changePasswordReqDto.getNewPassword()) || StringUtils.isEmpty(changePasswordReqDto.getConfirmNewPassword())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		if (!changePasswordReqDto.getNewPassword().equals(changePasswordReqDto.getConfirmNewPassword())) {
			throw new BusinessLogicException(MessageCode.UNMATCH_CONFIRM_NEW_PASSWORD, messageSource.getMessage(MessageCode.UNMATCH_CONFIRM_NEW_PASSWORD, null, null));
		}

	}

	private void validateUpdateCustomerProfileInput(CustomerInfoDto customerInfoDto) {
		// check input fields
		if (StringUtils.isEmpty(customerInfoDto.getCustomerId()) || StringUtils.isEmpty(customerInfoDto.getNrcNo()) || StringUtils.isEmpty(customerInfoDto.getDob())
				|| StringUtils.isEmpty(customerInfoDto.getPhoneNo()) || StringUtils.isEmpty(customerInfoDto.getName())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		int phoneNoCount = customerInfoService.getPhoneNoCount(customerInfoDto.getPhoneNo(), customerInfoDto.getCustomerId());
		if (phoneNoCount > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_PHONE_NO, messageSource.getMessage(MessageCode.DUPLICATED_PHONE_NO, null, null));
		}

		// check duplicate nrc no
		int nrcNoCount = customerInfoService.getNrcNoCount(customerInfoDto.getNrcNo(), customerInfoDto.getCustomerId());
		if (nrcNoCount > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_NRC_NO, messageSource.getMessage(MessageCode.DUPLICATED_NRC_NO, null, null));
		}

	}

	private void validateUpdateSecurityQuestionAnswerInput(UpdateCustomerSecQAReqDto updateCustomerSecQAReqDto) {
		// check input fields
		if (StringUtils.isEmpty(updateCustomerSecQAReqDto.getCustomerId()) || StringUtils.isEmpty(updateCustomerSecQAReqDto.getPassword())
				|| CollectionUtils.isEmpty(updateCustomerSecQAReqDto.getSecurityQuestionAnswerReqDtoList())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		for (SecurityQuestionAnswerReqDto securityQuestionAnswerReqDto : updateCustomerSecQAReqDto.getSecurityQuestionAnswerReqDtoList()) {
			if (StringUtils.isEmpty(securityQuestionAnswerReqDto.getAnswer()) || StringUtils.isEmpty(securityQuestionAnswerReqDto.getSecQuesId())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
			}

			if (!CommonUtil.isPureAscii(securityQuestionAnswerReqDto.getAnswer())) {
				throw new BusinessLogicException(MessageCode.INVALID_CHARACTER, messageSource.getMessage(MessageCode.INVALID_CHARACTER,
						new String[] { "secQuesId-" + securityQuestionAnswerReqDto.getSecQuesId().toString() + " : answer" }, null));
			}
		}
	}

	private void validateUpgradeMemberInput(UpgradeMemberReqDto upgradeMemberReqDto) {
		// check input fields
		if (StringUtils.isEmpty(upgradeMemberReqDto.getCustomerId()) || StringUtils.isEmpty(upgradeMemberReqDto.getCustomerNo())
				|| ArrayUtils.isEmpty(upgradeMemberReqDto.getPhotoByte())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
	}

	private void validateConfirmSecurityQuestionAnswerInput(ConfirmSecurityQuestionAnswerReqDto confirmSecurityQuestionAnswerReqDto) {
		// check input fields
		if (StringUtils.isEmpty(confirmSecurityQuestionAnswerReqDto.getCustomerId())
				|| CollectionUtils.isEmpty(confirmSecurityQuestionAnswerReqDto.getSecurityQuestionAnswerReqDtoList())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
	}

	// validate customer answer
	private void validateCustomerAnswer(List<SecurityQuestionAnswerReqDto> securityQuestionAnswerReqDtoList, List<CustomerSecurityQuestionDto> customerSecurityQuestionDtoList) {
		boolean answerCorrect = false;
		boolean answerIdExisted = false;
		int customerSecurityQuestionCount = customerSecurityQuestionDtoList.size();
		for (SecurityQuestionAnswerReqDto securityQuestionAnswerReqDto : securityQuestionAnswerReqDtoList) {
			for (CustomerSecurityQuestionDto customerSecurityQuestionDto : customerSecurityQuestionDtoList) {
				if (securityQuestionAnswerReqDto.getSecQuesId() != customerSecurityQuestionDto.getSecQuesId()) {
					continue;
				} else {
					answerIdExisted = true;

					if (customerSecurityQuestionDto.getAnswer().equals(securityQuestionAnswerReqDto.getAnswer())) {
						customerSecurityQuestionCount--;
						answerCorrect = true;
						break;
					} else {
						answerCorrect = false;
					}
				}
				if (!answerCorrect) {
					throw new BusinessLogicException(MessageCode.INVALID_CUSTOMER_ANSWER, messageSource.getMessage(MessageCode.INVALID_CUSTOMER_ANSWER, null, null));
				}
			}
			if (!answerIdExisted) {
				throw new BusinessLogicException(MessageCode.INVALID_CUSTOMER_ANSWER, messageSource.getMessage(MessageCode.INVALID_CUSTOMER_ANSWER, null, null));
			}
		}
		if (customerSecurityQuestionCount != 0) {
			throw new BusinessLogicException(MessageCode.INVALID_CUSTOMER_ANSWER, messageSource.getMessage(MessageCode.INVALID_CUSTOMER_ANSWER, null, null));
		}

	}

	private void validateVerifyMemberInput(VerifyMemberInfoReqDto verifyMemberInfoReqDto) {
		// check input fields
		if (StringUtils.isEmpty(verifyMemberInfoReqDto.getDateOfBirth()) || StringUtils.isEmpty(verifyMemberInfoReqDto.getAgreementNo())
				|| StringUtils.isEmpty(verifyMemberInfoReqDto.getNrcNo()) || StringUtils.isEmpty(verifyMemberInfoReqDto.getCustomerId())) {

			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

	}

}
