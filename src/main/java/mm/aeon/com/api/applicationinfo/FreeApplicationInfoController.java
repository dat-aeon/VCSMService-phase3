package mm.aeon.com.api.applicationinfo;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.common.FreeApplicationInfoUnicodeConverter;
import mm.aeon.com.dto.applicationinfo.ApplicationInfoAttachmentDto;
import mm.aeon.com.dto.applicationinfo.FreeApplicationInfoDto;
import mm.aeon.com.dto.customersecurityquestion.CustomerSecurityQuestionDto;
import mm.aeon.com.services.applicationinfo.ApplicationInfoService;
import mm.aeon.com.zconfig.MessageCode;
import mm.aeon.com.zconfig.exception.BusinessLogicException;

@RestController
@RequestMapping("/free-application")
public class FreeApplicationInfoController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ApplicationInfoService applicationInfoService;

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void registerNewApplication(@Valid @RequestBody FreeApplicationInfoDto freeApplicationInfoDto) throws Exception {

		checkAttachmentsAndValidation(freeApplicationInfoDto);
		freeApplicationInfoDto.setDaApplicationInfoId(null);
		freeApplicationInfoDto.getApplicantCompanyInfoDto().setDaApplicantCompanyInfoId(null);
		freeApplicationInfoDto.getEmergencyContactInfoDto().setDaEmergencyContactInfoId(null);
		freeApplicationInfoDto.getGuarantorInfoDto().setDaGuarantorInfoId(null);
		applicationInfoService.freeApplicationRegistration(FreeApplicationInfoUnicodeConverter.convertZawgyiToUnicode(freeApplicationInfoDto));

	}

	private void checkAttachmentsAndValidation(FreeApplicationInfoDto freeApplicationInfoDto) {

		if (freeApplicationInfoDto.getFinanceTerm() < 6) {
			throw new BusinessLogicException(MessageCode.INVALID_LOAN_TERM_MINIMUM, messageSource.getMessage(MessageCode.INVALID_LOAN_TERM_MINIMUM, new String[] { "6" }, null));
		}

		boolean nrcFrontContain = false;
		boolean nrcBackContain = false;
		boolean residentProofContain = false;
		boolean incomeProofContain = false;
		boolean guarantorNrcFrontContain = false;
		boolean guarantorNrcBackContain = false;
		boolean houseHoldOrCriminalClearanceContain = false;
		boolean applicantPhotoContain = false;
		boolean signatureContain = false;
		boolean guarantorSignatureContain = false;

		for (ApplicationInfoAttachmentDto dto : freeApplicationInfoDto.getApplicationInfoAttachmentDtoList()) {
			if (ArrayUtils.isEmpty(dto.getPhotoByte())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_PHOTO_BYTE, messageSource.getMessage(MessageCode.REQUIRED_PHOTO_BYTE, null, null));
			}
			dto.setDaApplicationInfoAttachmentId(null);
			if (dto.getFileType().equals(CommonConstants.NRC_FRONT)) {
				nrcFrontContain = true;
			}
			if (dto.getFileType().equals(CommonConstants.NRC_BACK)) {
				nrcBackContain = true;
			}
			if (dto.getFileType().equals(CommonConstants.RESIDENT_PROOF)) {
				residentProofContain = true;
			}
			if (dto.getFileType().equals(CommonConstants.INCOME_PROOF)) {
				incomeProofContain = true;
			}
			if (dto.getFileType().equals(CommonConstants.GUARANTOR_NRC_FRONT)) {
				guarantorNrcFrontContain = true;
			}
			if (dto.getFileType().equals(CommonConstants.GUARANTOR_NRC_BACK)) {
				guarantorNrcBackContain = true;
			}
			if (dto.getFileType().equals(CommonConstants.HOUSE_HOLD_OR_CRIMINAL_CLEARANCE)) {
				houseHoldOrCriminalClearanceContain = true;
			}
			if (dto.getFileType().equals(CommonConstants.APPLICANT_PHOTO)) {
				applicantPhotoContain = true;
			}
			if (dto.getFileType().equals(CommonConstants.SIGNATURE)) {
				signatureContain = true;
			}
			if (dto.getFileType().equals(CommonConstants.GUARANTOR_SIGNATURE)) {
				guarantorSignatureContain = true;
			}
		}

		// check input fields
		if (!nrcFrontContain || !nrcBackContain || !residentProofContain || !incomeProofContain || !guarantorNrcFrontContain || !guarantorNrcBackContain
				|| !houseHoldOrCriminalClearanceContain || !applicantPhotoContain || !signatureContain || !guarantorSignatureContain) {

			throw new BusinessLogicException(MessageCode.INSUFFICIENT_ATTACHMENT_INFO, messageSource.getMessage(MessageCode.INSUFFICIENT_ATTACHMENT_INFO, null, null));
		}

		if (freeApplicationInfoDto.getApplicationInfoAttachmentDtoList().size() > 10) {
			throw new BusinessLogicException(MessageCode.ATTACHMENT_INFO_LIST_SIZE, messageSource.getMessage(MessageCode.ATTACHMENT_INFO_LIST_SIZE, null, null));
		}

		if (freeApplicationInfoDto.getApplicantCompanyInfoDto().getMonthlyBasicIncome() * 2 < freeApplicationInfoDto.getFinanceAmount()) {
			throw new BusinessLogicException(MessageCode.INVALID_FINANCE_AMOUNT, messageSource.getMessage(MessageCode.INVALID_FINANCE_AMOUNT, null, null));
		}

		if (freeApplicationInfoDto.getFinanceAmount() < 50000) {
			throw new BusinessLogicException(MessageCode.INVALID_FINANCE_AMOUNT_MINIMUM,
					messageSource.getMessage(MessageCode.INVALID_FINANCE_AMOUNT_MINIMUM, new String[] { "50000" }, null));
		}

		if (freeApplicationInfoDto.getFinanceAmount() > 2000000) {
			throw new BusinessLogicException(MessageCode.INVALID_FINANCE_AMOUNT_MAXIMUM,
					messageSource.getMessage(MessageCode.INVALID_FINANCE_AMOUNT_MAXIMUM, new String[] { "2000000" }, null));
		}

		List<Double> activeFinanceAmountList = applicationInfoService.getActiveFinanceAmountList(freeApplicationInfoDto.getCustomerId(), null);

		if (activeFinanceAmountList.size() > 1) {
			throw new BusinessLogicException(MessageCode.APPLICATION_LIMIT, messageSource.getMessage(MessageCode.APPLICATION_LIMIT, null, null));
		} else {
			Double totalFinanceAmount = 0.0;
			for (Double financeAmount : activeFinanceAmountList) {
				totalFinanceAmount += financeAmount;
			}
			totalFinanceAmount += freeApplicationInfoDto.getFinanceAmount();
			if (totalFinanceAmount > freeApplicationInfoDto.getApplicantCompanyInfoDto().getMonthlyBasicIncome() * 2) {
				throw new BusinessLogicException(MessageCode.INVALID_TOTAL_FINANCE_AMOUNT, messageSource.getMessage(MessageCode.INVALID_TOTAL_FINANCE_AMOUNT, null, null));
			}

		}

		if (CollectionUtils.isEmpty(freeApplicationInfoDto.getCustomerSecurityQuestionDtoList())) {
			throw new BusinessLogicException(MessageCode.INSUFFICIENT_CUSTOMER_SECURITY_QUESTION_ANSWER_INFO,
					messageSource.getMessage(MessageCode.INSUFFICIENT_CUSTOMER_SECURITY_QUESTION_ANSWER_INFO, null, null));
		} else {
			for (CustomerSecurityQuestionDto customerSecurityQuestionDto : freeApplicationInfoDto.getCustomerSecurityQuestionDtoList()) {
				if (StringUtils.isEmpty(customerSecurityQuestionDto.getSecQuesId()) || StringUtils.isEmpty(customerSecurityQuestionDto.getAnswer())) {
					throw new BusinessLogicException(MessageCode.INSUFFICIENT_CUSTOMER_SECURITY_QUESTION_ANSWER_INFO,
							messageSource.getMessage(MessageCode.INSUFFICIENT_CUSTOMER_SECURITY_QUESTION_ANSWER_INFO, null, null));
				}

				if (!CommonUtil.isPureAscii(customerSecurityQuestionDto.getAnswer())) {
					throw new BusinessLogicException(MessageCode.INVALID_CHARACTER, messageSource.getMessage(MessageCode.INVALID_CHARACTER,
							new String[] { "secQuesId-" + customerSecurityQuestionDto.getSecQuesId().toString() + " : answer" }, null));
				}
			}
		}

		if (StringUtils.isEmpty(freeApplicationInfoDto.getPassword())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_PASSWORD, messageSource.getMessage(MessageCode.REQUIRED_PASSWORD, null, null));
		}

		if (freeApplicationInfoDto.getPassword().length() < CommonConstants.PASSWORD_LENGTH_MIN
				|| freeApplicationInfoDto.getPassword().length() > CommonConstants.PASSWORD_LENGTH_MAX) {
			throw new BusinessLogicException(MessageCode.INVALID_PASSWORD_LENGTH, messageSource.getMessage(MessageCode.INVALID_PASSWORD_LENGTH, null, null));
		}

		Calendar birthDate = Calendar.getInstance();
		birthDate.setTime(freeApplicationInfoDto.getDob());
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
		if (age < 18) {
			throw new BusinessLogicException(MessageCode.INVALID_AGE, messageSource.getMessage(MessageCode.INVALID_AGE, null, null));
		}

		for (CustomerSecurityQuestionDto customerSecurityQuestionDto : freeApplicationInfoDto.getCustomerSecurityQuestionDtoList()) {
			if (StringUtils.isEmpty(customerSecurityQuestionDto.getAnswer()) || StringUtils.isEmpty(customerSecurityQuestionDto.getSecQuesId())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
			}

		}

	}

}
