package mm.aeon.com.api.resetpassword;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.common.VCSMPasswordEncoder;
import mm.aeon.com.dto.customerinfo.CustomerInfoDto;
import mm.aeon.com.dto.customersecurityquestion.CustomerSecurityQuestionDto;
import mm.aeon.com.dto.information.CompanyInfoResDto;
import mm.aeon.com.dto.resetpassword.CheckAccountLockReqDto;
import mm.aeon.com.dto.resetpassword.CheckAccountLockResDto;
import mm.aeon.com.dto.resetpassword.ConfirmSecurityQuestionAnswerReqDto;
import mm.aeon.com.dto.resetpassword.ConfirmSecurityQuestionAnswerResDto;
import mm.aeon.com.dto.resetpassword.ForcePasswordChangeReqDto;
import mm.aeon.com.dto.resetpassword.ResetPasswordReqDto;
import mm.aeon.com.dto.resetpassword.SecurityQuestionAnswerReqDto;
import mm.aeon.com.dto.resetpassword.SecurityQuestionResDto;
import mm.aeon.com.services.customerinfo.CustomerInfoService;
import mm.aeon.com.services.information.InformationService;
import mm.aeon.com.services.resetpassword.ResetPasswordService;
import mm.aeon.com.zconfig.MessageCode;
import mm.aeon.com.zconfig.exception.BusinessLogicException;

@RestController
@RequestMapping("/reset-password")
public class ResetPasswordController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ResetPasswordService resetPasswordService;

	@Autowired
	private CustomerInfoService customerInfoService;

	@Autowired
	private InformationService informationService;

	@RequestMapping(value = "/security-question-list", method = RequestMethod.GET)
	public SecurityQuestionResDto getSecurityQuestionList() throws Exception {
		return resetPasswordService.getSecurityQuestionList();
	}

	@RequestMapping(value = "/check-account-lock", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public CheckAccountLockResDto checkAccountLock(@RequestBody CheckAccountLockReqDto checkAccountLockReqDto) throws Exception {
		validateCheckAccountLockInput(checkAccountLockReqDto);

		// check customer info exist
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoWithPhoneNoAndNrcNo(checkAccountLockReqDto.getPhoneNo(), checkAccountLockReqDto.getNrcNo());
		if (customerInfoDto == null) {
			throw new BusinessLogicException(MessageCode.NOT_EXIST_CUSTOMER_INFO, messageSource.getMessage(MessageCode.NOT_EXIST_CUSTOMER_INFO, null, null));
		}

		CheckAccountLockResDto checkAccountLockResDto = new CheckAccountLockResDto();
		checkAccountLockResDto.setPhoneNo(checkAccountLockReqDto.getPhoneNo());
		if (customerInfoDto.getApplockFlag() == CommonConstants.LOCK) {
			checkAccountLockResDto.setLockStatus(CommonConstants.LOCK);
		} else {
			checkAccountLockResDto.setLockStatus(CommonConstants.UNLOCK);
		}
		CompanyInfoResDto companyInfoResDto = informationService.getCompanyInfo();
		checkAccountLockResDto.setHotlinePhone(companyInfoResDto.getHotlinePhone());

		int customerSecurityQuestionCount = resetPasswordService.getCustomerSecurityQuestionCount(checkAccountLockReqDto.getPhoneNo());
		checkAccountLockResDto.setCustomerSecurityQuestionCount(customerSecurityQuestionCount);
		return checkAccountLockResDto;
	}

	@RequestMapping(value = "/confirm-security-question-answer", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ConfirmSecurityQuestionAnswerResDto confirmSecurityQuestionAnswer(@RequestBody ConfirmSecurityQuestionAnswerReqDto confirmSecurityQuestionAnswerReqDto)
			throws Exception {
		ConfirmSecurityQuestionAnswerResDto confirmSecurityQuestionAnswerResDto = new ConfirmSecurityQuestionAnswerResDto();

		validateConfirmSecurityQuestionAnswerInput(confirmSecurityQuestionAnswerReqDto);

		// check customer info exist
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoWithPhoneNoAndNrcNo(confirmSecurityQuestionAnswerReqDto.getPhoneNo(),
				confirmSecurityQuestionAnswerReqDto.getNrcNo());
		if (customerInfoDto == null) {
			throw new BusinessLogicException(MessageCode.NOT_EXIST_CUSTOMER_INFO, messageSource.getMessage(MessageCode.NOT_EXIST_CUSTOMER_INFO, null, null));
		}

		List<CustomerSecurityQuestionDto> customerSecurityQuestionDtoList = resetPasswordService.getCustomerSecurityQuestionListWithCustomerId(customerInfoDto.getCustomerId());

		// check customer security question exist
		if (CollectionUtils.isEmpty(customerSecurityQuestionDtoList)) {
			throw new BusinessLogicException(MessageCode.NOT_EXIST_CUSTOMER_ANSWER, messageSource.getMessage(MessageCode.NOT_EXIST_CUSTOMER_ANSWER, null, null));
		}

		validateCustomerAnswer(confirmSecurityQuestionAnswerReqDto.getSecurityQuestionAnswerReqDtoList(), customerSecurityQuestionDtoList);
		confirmSecurityQuestionAnswerResDto.setCustomerId(customerInfoDto.getCustomerId());
		confirmSecurityQuestionAnswerResDto.setUserTypeId(customerInfoDto.getUserTypeId());
		return confirmSecurityQuestionAnswerResDto;
	}

	@RequestMapping(value = "/reset-password", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void resetPassword(@RequestBody ResetPasswordReqDto resetPasswordReqDto) throws Exception {
		validateResetPasswordInput(resetPasswordReqDto);

		// check customer info exist
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoWithCustomerId(resetPasswordReqDto.getCustomerId());
		if (customerInfoDto == null) {
			throw new BusinessLogicException(MessageCode.NOT_EXIST_CUSTOMER_INFO, messageSource.getMessage(MessageCode.NOT_EXIST_CUSTOMER_INFO, null, null));
		}

		resetPasswordService.updatePasswordWithUserIdAndUserTypeId(resetPasswordReqDto.getCustomerId(), resetPasswordReqDto.getUserTypeId(),
				VCSMPasswordEncoder.encode(resetPasswordReqDto.getPassword()));
	}

	@RequestMapping(value = "/force-password-change", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void forcePasswordChange(@RequestBody ForcePasswordChangeReqDto forcePasswordChangeReqDto) throws Exception {
		validateForcePasswordChangeInput(forcePasswordChangeReqDto);

		// check customer info exist
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoWithPhoneNoAndNrcNo(forcePasswordChangeReqDto.getPhoneNo(), forcePasswordChangeReqDto.getNrcNo());
		if (customerInfoDto == null) {
			throw new BusinessLogicException(MessageCode.NOT_EXIST_CUSTOMER_INFO, messageSource.getMessage(MessageCode.NOT_EXIST_CUSTOMER_INFO, null, null));
		}

		resetPasswordService.forcePasswordChange(customerInfoDto, VCSMPasswordEncoder.encode(forcePasswordChangeReqDto.getPassword()));
	}

	// validate customer answer for forget password
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

	private void validateResetPasswordInput(ResetPasswordReqDto resetPasswordReqDto) {
		// check input fields
		if (StringUtils.isEmpty(resetPasswordReqDto.getCustomerId()) || StringUtils.isEmpty(resetPasswordReqDto.getUserTypeId())
				|| StringUtils.isEmpty(resetPasswordReqDto.getPassword())) {

			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		// check password length
		if (resetPasswordReqDto.getPassword().length() < CommonConstants.PASSWORD_LENGTH_MIN || resetPasswordReqDto.getPassword().length() > CommonConstants.PASSWORD_LENGTH_MAX) {
			throw new BusinessLogicException(MessageCode.INVALID_PASSWORD_LENGTH, messageSource.getMessage(MessageCode.INVALID_PASSWORD_LENGTH, null, null));
		}

		// check password strength
		// String passwordStrength =
		// CommonUtil.checkPasswordStrength(resetPasswordReqDto.getPassword());
		// if (passwordStrength.equals(CommonConstants.PASSWORD_WEAK)) {
		// throw new BusinessLogicException(MessageCode.PASSWORD_WEAK,
		// messageSource.getMessage(MessageCode.PASSWORD_WEAK, null, null));
		// }
	}

	private void validateConfirmSecurityQuestionAnswerInput(ConfirmSecurityQuestionAnswerReqDto confirmSecurityQuestionAnswerReqDto) {
		// check input fields
		if (StringUtils.isEmpty(confirmSecurityQuestionAnswerReqDto.getNrcNo()) || StringUtils.isEmpty(confirmSecurityQuestionAnswerReqDto.getPhoneNo())
				|| CollectionUtils.isEmpty(confirmSecurityQuestionAnswerReqDto.getSecurityQuestionAnswerReqDtoList())) {

			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
	}

	private void validateCheckAccountLockInput(CheckAccountLockReqDto checkAccountLockReqDto) {
		// check input fields
		if (StringUtils.isEmpty(checkAccountLockReqDto.getNrcNo()) || StringUtils.isEmpty(checkAccountLockReqDto.getPhoneNo())) {

			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
	}

	private void validateForcePasswordChangeInput(ForcePasswordChangeReqDto forcePasswordChangeReqDto) {
		// check input fields
		if (StringUtils.isEmpty(forcePasswordChangeReqDto.getNrcNo()) || StringUtils.isEmpty(forcePasswordChangeReqDto.getPhoneNo())
				|| StringUtils.isEmpty(forcePasswordChangeReqDto.getPassword())) {

			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		// check password length
		if (forcePasswordChangeReqDto.getPassword().length() < CommonConstants.PASSWORD_LENGTH_MIN
				|| forcePasswordChangeReqDto.getPassword().length() > CommonConstants.PASSWORD_LENGTH_MAX) {
			throw new BusinessLogicException(MessageCode.INVALID_PASSWORD_LENGTH, messageSource.getMessage(MessageCode.INVALID_PASSWORD_LENGTH, null, null));
		}

		// check password strength
		// String passwordStrength =
		// CommonUtil.checkPasswordStrength(forcePasswordChangeReqDto.getPassword());
		// if (passwordStrength.equals(CommonConstants.PASSWORD_WEAK)) {
		// throw new BusinessLogicException(MessageCode.PASSWORD_WEAK,
		// messageSource.getMessage(MessageCode.PASSWORD_WEAK, null, null));
		// }
	}

}
