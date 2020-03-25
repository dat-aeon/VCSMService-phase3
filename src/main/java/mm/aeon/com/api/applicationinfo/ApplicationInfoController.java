package mm.aeon.com.api.applicationinfo;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mm.aeon.com.common.ApplicationInfoUnicodeConverter;
import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.common.LoanCalculator;
import mm.aeon.com.dto.applicationinfo.ApplicationInfoApplicationNoResDto;
import mm.aeon.com.dto.applicationinfo.ApplicationInfoAttachmentDto;
import mm.aeon.com.dto.applicationinfo.ApplicationInfoDto;
import mm.aeon.com.dto.applicationinfo.ApplicationInquireResDto;
import mm.aeon.com.dto.applicationinfo.ApplicationInquriesSearchCriteriaDto;
import mm.aeon.com.dto.applicationinfo.ApplicationStatusChangedCountReqDto;
import mm.aeon.com.dto.applicationinfo.ApplicationStatusChangedCountResDto;
import mm.aeon.com.dto.applicationinfo.ApplicationStatusChangedReadFlagUpdateReqDto;
import mm.aeon.com.dto.applicationinfo.DashboardInfoDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInfoDto;
import mm.aeon.com.dto.custagreement.CustomerAgreementDto;
import mm.aeon.com.services.applicationinfo.ApplicationInfoService;
import mm.aeon.com.services.purchaseinfo.PurchaseInfoService;
import mm.aeon.com.zconfig.MessageCode;
import mm.aeon.com.zconfig.exception.BusinessLogicException;

@RestController
@RequestMapping("/application")
public class ApplicationInfoController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ApplicationInfoService applicationInfoService;

	@Autowired
	private PurchaseInfoService purchaseInfoService;

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void registerNewApplication(@Valid @RequestBody ApplicationInfoDto applicationInfoDto) throws Exception {

		if (applicationInfoDto.getStatus() != null && applicationInfoDto.getStatus().equals(CommonConstants.APP_STATUS_DRAFT)) {
			if (StringUtils.isEmpty(applicationInfoDto.getDaApplicationInfoId())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_DA_APPLICATION_INFO_ID, messageSource.getMessage(MessageCode.REQUIRED_DA_APPLICATION_INFO_ID, null, null));
			}
			checkAttachmentsAndValidation(applicationInfoDto);
			applicationInfoService.applicationDraftRegister(ApplicationInfoUnicodeConverter.convertZawgyiToUnicode(applicationInfoDto));
		} else {
			checkAttachmentsAndValidation(applicationInfoDto);
			applicationInfoDto.setDaApplicationInfoId(null);
			applicationInfoDto.getApplicantCompanyInfoDto().setDaApplicantCompanyInfoId(null);
			applicationInfoDto.getEmergencyContactInfoDto().setDaEmergencyContactInfoId(null);
			applicationInfoDto.getGuarantorInfoDto().setDaGuarantorInfoId(null);
			applicationInfoService.applicationRegistration(ApplicationInfoUnicodeConverter.convertZawgyiToUnicode(applicationInfoDto));
		}
	}

	@RequestMapping(value = "/register-multipart", method = RequestMethod.POST, produces = "application/json; charset=utf-8", headers = ("content-type=multipart/*"))
	public ApplicationInfoApplicationNoResDto registerNewApplicationWithMultipart(@Valid @RequestPart(required = true) ApplicationInfoDto applicationInfoDto,
			@RequestPart(name = "img", required = true) List<MultipartFile> multipartFileList) throws Exception {

		ApplicationInfoApplicationNoResDto applicationInfoApplicationNoResDto = new ApplicationInfoApplicationNoResDto();
		if (applicationInfoDto.getPermanentAddressTownship() != null && applicationInfoDto.getPermanentAddressTownship() == 0) {
			applicationInfoDto.setPermanentAddressTownship(null);
		}

		if (applicationInfoDto.getPermanentAddressCity() != null && applicationInfoDto.getPermanentAddressCity() == 0) {
			applicationInfoDto.setPermanentAddressCity(null);
		}
		String applicationNo = null;
		if (applicationInfoDto.getStatus() != null && applicationInfoDto.getStatus().equals(CommonConstants.APP_STATUS_DRAFT)) {
			if (StringUtils.isEmpty(applicationInfoDto.getDaApplicationInfoId())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_DA_APPLICATION_INFO_ID, messageSource.getMessage(MessageCode.REQUIRED_DA_APPLICATION_INFO_ID, null, null));
			}
			checkAttachmentsAndValidationMultipart(applicationInfoDto, multipartFileList);
			applicationNo = applicationInfoService.applicationDraftRegisterWithMultipart(ApplicationInfoUnicodeConverter.convertZawgyiToUnicode(applicationInfoDto),
					multipartFileList);
		} else {
			checkAttachmentsAndValidationMultipart(applicationInfoDto, multipartFileList);
			applicationInfoDto.setDaApplicationInfoId(null);
			applicationInfoDto.getApplicantCompanyInfoDto().setDaApplicantCompanyInfoId(null);
			applicationInfoDto.getEmergencyContactInfoDto().setDaEmergencyContactInfoId(null);
			applicationInfoDto.getGuarantorInfoDto().setDaGuarantorInfoId(null);
			applicationNo = applicationInfoService.applicationRegistrationWithMultipart(ApplicationInfoUnicodeConverter.convertZawgyiToUnicode(applicationInfoDto),
					multipartFileList);
		}
		applicationInfoApplicationNoResDto.setApplicationNo(applicationNo);
		return applicationInfoApplicationNoResDto;

	}

	@RequestMapping(value = "/save-draft", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ApplicationInfoDto applicationSaveDraft(@RequestBody ApplicationInfoDto applicationInfoDto) {
		if (StringUtils.isEmpty(applicationInfoDto.getCustomerId()) || StringUtils.isEmpty(applicationInfoDto.getDaApplicationTypeId())
				|| StringUtils.isEmpty(applicationInfoDto.getChannelType())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		applicationInfoDto.setApplicationNo(null);
		ApplicationInfoDto dbApplicationInfoDto = applicationInfoService.getLastApplicationInfo(applicationInfoDto.getCustomerId());
		if (dbApplicationInfoDto != null) {
			if (dbApplicationInfoDto.getStatus().equals(CommonConstants.APP_STATUS_DRAFT)) {
				applicationInfoDto.setDaApplicationInfoId(dbApplicationInfoDto.getDaApplicationInfoId());
				applicationInfoService.applicationSaveDraftUpdate(ApplicationInfoUnicodeConverter.convertZawgyiToUnicode(applicationInfoDto));
			} else {
				applicationInfoDto.setDaApplicationInfoId(null);
				applicationInfoService.applicationSaveDraft(ApplicationInfoUnicodeConverter.convertZawgyiToUnicode(applicationInfoDto));
			}
		} else {
			applicationInfoDto.setDaApplicationInfoId(null);
			applicationInfoService.applicationSaveDraft(ApplicationInfoUnicodeConverter.convertZawgyiToUnicode(applicationInfoDto));
		}
		return applicationInfoDto;

	}

	@RequestMapping(value = "/attachment-edit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void attachmentEdit(@RequestBody ApplicationInfoDto applicationInfoDto) throws Exception {

		if (StringUtils.isEmpty(applicationInfoDto.getDaApplicationInfoId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		for (ApplicationInfoAttachmentDto applicationInfoAttachmentDto : applicationInfoDto.getApplicationInfoAttachmentDtoList()) {
			if (StringUtils.isEmpty(applicationInfoAttachmentDto.getDaApplicationInfoAttachmentId()) || StringUtils.isEmpty(applicationInfoAttachmentDto.getFilePath())
					|| StringUtils.isEmpty(applicationInfoAttachmentDto.getFileType()) || ArrayUtils.isEmpty(applicationInfoAttachmentDto.getPhotoByte())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
			}
		}

		ApplicationInfoDto dbApplicationInfoDto = applicationInfoService.getApplicationInfoDetail(applicationInfoDto.getDaApplicationInfoId());

		if (dbApplicationInfoDto == null) {
			throw new BusinessLogicException(MessageCode.INVALID_APPLICATION_INFO, messageSource.getMessage(MessageCode.INVALID_APPLICATION_INFO, null, null));
		}

		if (dbApplicationInfoDto.getStatus().equals(CommonConstants.APP_STATUS_DOCUMENT_UPDATED)) {
			throw new BusinessLogicException(MessageCode.APPLICATION_ALREADY_UPDATED, messageSource.getMessage(MessageCode.APPLICATION_ALREADY_UPDATED, null, null));
		}

		dbApplicationInfoDto.setApplicationInfoAttachmentDtoList(applicationInfoDto.getApplicationInfoAttachmentDtoList());

		applicationInfoService.attachmentEdit(dbApplicationInfoDto);

	}

	@RequestMapping(value = "/attachment-edit-multipart", method = RequestMethod.POST, produces = "application/json; charset=utf-8", headers = ("content-type=multipart/*"))
	public void attachmentEditWithMultipart(@RequestPart(required = true) ApplicationInfoDto applicationInfoDto,
			@RequestPart(name = "img", required = true) List<MultipartFile> multipartFileList) throws Exception {

		checkAttachmentEditWithMultipart(applicationInfoDto, multipartFileList);
		ApplicationInfoDto dbApplicationInfoDto = applicationInfoService.getApplicationInfoDetail(applicationInfoDto.getDaApplicationInfoId());

		if (dbApplicationInfoDto == null) {
			throw new BusinessLogicException(MessageCode.INVALID_APPLICATION_INFO, messageSource.getMessage(MessageCode.INVALID_APPLICATION_INFO, null, null));
		}

		if (dbApplicationInfoDto.getStatus().equals(CommonConstants.APP_STATUS_DOCUMENT_UPDATED)) {
			throw new BusinessLogicException(MessageCode.APPLICATION_ALREADY_UPDATED, messageSource.getMessage(MessageCode.APPLICATION_ALREADY_UPDATED, null, null));
		}

		dbApplicationInfoDto.setApplicationInfoAttachmentDtoList(applicationInfoDto.getApplicationInfoAttachmentDtoList());

		applicationInfoService.attachmentEditWithMultipart(dbApplicationInfoDto, multipartFileList);

	}

	@RequestMapping(value = "/application-cancel", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void applicationCancel(@RequestBody ApplicationInfoDto applicationInfoDto) {

		if (StringUtils.isEmpty(applicationInfoDto.getDaApplicationInfoId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		ApplicationInfoDto dbApplicationInfoDto = applicationInfoService.getApplicationInfoDetail(applicationInfoDto.getDaApplicationInfoId());

		if (dbApplicationInfoDto == null) {
			throw new BusinessLogicException(MessageCode.INVALID_APPLICATION_INFO, messageSource.getMessage(MessageCode.INVALID_APPLICATION_INFO, null, null));
		}

		if (dbApplicationInfoDto.getStatus().equals(CommonConstants.APP_STATUS_CANCEL)) {
			throw new BusinessLogicException(MessageCode.APPLICATION_ALREADY_CANCELLED, messageSource.getMessage(MessageCode.APPLICATION_ALREADY_CANCELLED, null, null));
		}

		applicationInfoService.applicationCancel(dbApplicationInfoDto);

	}

	@RequestMapping(value = "/application-inquries-list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ApplicationInquireResDto getApplicationInquriesList(@RequestBody ApplicationInquriesSearchCriteriaDto applicationInquriesSearchCriteriaDto) {
		if (StringUtils.isEmpty(applicationInquriesSearchCriteriaDto.getCustomerId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		if (applicationInquriesSearchCriteriaDto.getDaLoanTypeId() != null) {
			if (applicationInquriesSearchCriteriaDto.getDaLoanTypeId() == 0) {
				applicationInquriesSearchCriteriaDto.setDaLoanTypeId(null);
			}
		}

		if (applicationInquriesSearchCriteriaDto.getStatus() != null) {
			if (applicationInquriesSearchCriteriaDto.getStatus() == 0) {
				applicationInquriesSearchCriteriaDto.setStatus(null);
			}
		}

		ApplicationInquireResDto applicationInquireResDto = new ApplicationInquireResDto();
		String appliedDateStr = CommonUtil.formatByPattern(applicationInquriesSearchCriteriaDto.getAppliedDate(), "yyyyMMdd");
		applicationInquriesSearchCriteriaDto.setAppliedDateStr(appliedDateStr);
		List<ApplicationInfoDto> applicationInfoDtoList = applicationInfoService.getApplicationInquriesList(applicationInquriesSearchCriteriaDto);
		applicationInquireResDto.setApplicationInfoDtoList(applicationInfoDtoList);
		int applicationInquriesCount = applicationInfoService.getApplicationInquriesCount(applicationInquriesSearchCriteriaDto);
		applicationInquireResDto.setTotalSize(applicationInquriesCount);
		return applicationInquireResDto;

	}

	@RequestMapping(value = "/application-info-detail", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ApplicationInfoDto getApplicationInfoDetail(@RequestBody ApplicationInfoDto applicationInfoDto) {
		if (StringUtils.isEmpty(applicationInfoDto.getDaApplicationInfoId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		ApplicationInfoDto dbApplicationInfoDto = new ApplicationInfoDto();
		dbApplicationInfoDto = applicationInfoService.getApplicationInfoDetail(applicationInfoDto.getDaApplicationInfoId());
		if (dbApplicationInfoDto != null) {
			double totalProductPrice;
			if (dbApplicationInfoDto.getApprovedAmount() != null && dbApplicationInfoDto.getApprovedAmount() > 0) {
				totalProductPrice = dbApplicationInfoDto.getApprovedAmount();
			} else {
				totalProductPrice = dbApplicationInfoDto.getFinanceAmount();
			}
			boolean motorcycleLoanFlag = false;
			int loanTerm = dbApplicationInfoDto.getFinanceTerm();
			double processingFees;
			double totalRepayment;
			double modifyTotalRepayment;
			double firstPayment;
			double monthlyPayment;
			double financeAmount;
			double totalInterest;
			double downPayment = 0;
			double promoDiscount = 0;
			double insPremium = 0;
			double vatAmount = 0;
			double financeAmountForPSG = 0;
			double interestRateMonthly = dbApplicationInfoDto.getInterestInfoDto().getInterestRate();
			double totalInterestForPSG = 0;
			double monthlyInstallmentForPSG = 0;
			double monthlyInstallment = 0;
			double initialPaymentForPSG = 0;
			double firstPaymentForPSG = 0;
			double lastPayment = 0;

			processingFees = LoanCalculator.calculateProcessingFees(motorcycleLoanFlag, totalProductPrice);
			financeAmount = LoanCalculator.calculateFinanceAmountForPSG(totalProductPrice, downPayment, promoDiscount, insPremium, vatAmount, financeAmountForPSG);
			totalInterest = LoanCalculator.calculateTotalInterest(financeAmount, loanTerm, interestRateMonthly, totalInterestForPSG);
			totalRepayment = LoanCalculator.calculateTotalRepayment(financeAmount, totalInterest);
			monthlyInstallment = LoanCalculator.calculateMonthlyInstallment(totalRepayment, monthlyInstallmentForPSG, loanTerm);
			firstPaymentForPSG = LoanCalculator.calculateFirstPaymentForPSG(initialPaymentForPSG);
			firstPayment = LoanCalculator.calculateFirstPayment(initialPaymentForPSG, totalRepayment, monthlyInstallment, loanTerm, firstPaymentForPSG);
			monthlyPayment = monthlyInstallment;
			lastPayment = LoanCalculator.calculateLastPayment(totalProductPrice, firstPayment, monthlyPayment, totalInterest, loanTerm, monthlyInstallment);
			modifyTotalRepayment = LoanCalculator.modifyCalculateTotalRepayment(monthlyPayment, loanTerm, firstPayment, lastPayment);
			double totalConSaving = LoanCalculator.calculateTotalConSaving(dbApplicationInfoDto.getCompulsoryInfoDto().getCompulsoryAmount(),
					dbApplicationInfoDto.getFinanceTerm());

			dbApplicationInfoDto.setProcessingFees(processingFees);
			dbApplicationInfoDto.setTotalInterest(totalInterest);
			dbApplicationInfoDto.setTotalRepayment(totalRepayment);
			dbApplicationInfoDto.setMonthlyInstallment(monthlyInstallment);
			dbApplicationInfoDto.setFirstPaymentForPSG(firstPaymentForPSG);
			dbApplicationInfoDto.setFirstPayment(firstPayment);
			dbApplicationInfoDto.setLastPayment(lastPayment);
			dbApplicationInfoDto.setModifyTotalRepayment(modifyTotalRepayment);
			dbApplicationInfoDto.setTotalConSaving(dbApplicationInfoDto.getCompulsoryInfoDto().getCompulsoryAmount());
		}

		return dbApplicationInfoDto;

	}

	@RequestMapping(value = "/purchase-info-detail", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public PurchaseInfoDto getPurchaseInfoDetail(@RequestBody ApplicationInfoDto applicationInfoDto) {
		if (StringUtils.isEmpty(applicationInfoDto.getDaApplicationInfoId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		PurchaseInfoDto purchaseInfoDto = new PurchaseInfoDto();
		purchaseInfoDto = applicationInfoService.getPurchaseInfoDetail(applicationInfoDto.getDaApplicationInfoId());

		if (purchaseInfoDto != null) {
			double processingFees = LoanCalculator.calculateProcessingFees(false, purchaseInfoDto.getFinanceAmount());
			purchaseInfoDto.setProcessingFees(processingFees);
			purchaseInfoDto.setCompulsoryAmount(purchaseInfoDto.getCompulsoryAmount());
			double settlementAmount = purchaseInfoDto.getFinanceAmount() - (processingFees + purchaseInfoDto.getCompulsoryAmount());
			purchaseInfoDto.setSettlementAmount(settlementAmount);
		}

		return purchaseInfoDto;

	}

	@RequestMapping(value = "/get-purchase-info-confirm-waiting", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public PurchaseInfoDto getPurchaseInfoConfirmWaiting(@RequestBody CustomerAgreementDto customerAgreementDto) {
		if (StringUtils.isEmpty(customerAgreementDto.getDaApplicationInfoId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		PurchaseInfoDto purchaseInfoDto = applicationInfoService.getPurchaseInfoDetail(customerAgreementDto.getDaApplicationInfoId());
		if (purchaseInfoDto != null && purchaseInfoDto.getStatus().equals(CommonConstants.PURCHASE_STATUS_CONFIRM_WAITING)) {
			return purchaseInfoDto;
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/purchase-info-confirm", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void purchaseInfoConfirm(@RequestBody PurchaseInfoDto purchaseInfoDto) {
		if (StringUtils.isEmpty(purchaseInfoDto.getDaPurchaseInfoId()) || StringUtils.isEmpty(purchaseInfoDto.getCustomerId())
				|| StringUtils.isEmpty(purchaseInfoDto.getDaApplicationInfoId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		ApplicationInfoDto applicationInfoDto = applicationInfoService.getApplicationInfoDetail(purchaseInfoDto.getDaApplicationInfoId());
		if (applicationInfoDto == null) {
			throw new BusinessLogicException(MessageCode.INVALID_APPLICATION_INFO, messageSource.getMessage(MessageCode.INVALID_APPLICATION_INFO, null, null));
		}
		purchaseInfoService.purchaseInfoConfirm(purchaseInfoDto, applicationInfoDto);
	}

	@RequestMapping(value = "/purchase-info-cancel", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void purchaseInfoCancel(@RequestBody PurchaseInfoDto purchaseInfoDto) {
		if (StringUtils.isEmpty(purchaseInfoDto.getDaPurchaseInfoId()) || StringUtils.isEmpty(purchaseInfoDto.getCustomerId())
				|| StringUtils.isEmpty(purchaseInfoDto.getDaApplicationInfoId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		ApplicationInfoDto applicationInfoDto = applicationInfoService.getApplicationInfoDetail(purchaseInfoDto.getDaApplicationInfoId());
		if (applicationInfoDto == null) {
			throw new BusinessLogicException(MessageCode.INVALID_APPLICATION_INFO, messageSource.getMessage(MessageCode.INVALID_APPLICATION_INFO, null, null));
		}
		purchaseInfoService.purchaseInfoCancel(purchaseInfoDto, applicationInfoDto);
	}

	@RequestMapping(value = "/last-application-info", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ApplicationInfoDto getLastApplicationInfo(@RequestBody ApplicationInfoDto applicationInfoDto) {
		if (StringUtils.isEmpty(applicationInfoDto.getCustomerId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		return applicationInfoService.getLastApplicationInfo(applicationInfoDto.getCustomerId());

	}

	@RequestMapping(value = "/dashboard-info", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public DashboardInfoDto getDashboardInfo(@RequestBody ApplicationInfoDto applicationInfoDto) {
		if (StringUtils.isEmpty(applicationInfoDto.getCustomerId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		DashboardInfoDto dashboardInfoDto = new DashboardInfoDto();
		int rejectedCount = applicationInfoService.getApplicationStatusCount(applicationInfoDto.getCustomerId(), CommonConstants.APP_STATUS_REJECT);
		int purchaseConfirmedCount = applicationInfoService.getApplicationStatusCount(applicationInfoDto.getCustomerId(), CommonConstants.APP_STATUS_PURCHASE_CONFIRM);
		int purchaseCompletedCount = applicationInfoService.getApplicationStatusCount(applicationInfoDto.getCustomerId(), CommonConstants.APP_STATUS_PURCHASE_COMPLETE);
		int purchaseCanceledCount = applicationInfoService.getApplicationStatusCount(applicationInfoDto.getCustomerId(), CommonConstants.APP_STATUS_PURCHASE_CANCEL);
		int canceledCount = applicationInfoService.getApplicationStatusCount(applicationInfoDto.getCustomerId(), CommonConstants.APP_STATUS_CANCEL);
		int appliedCount = applicationInfoService.getApplicationStatusCount(applicationInfoDto.getCustomerId(), CommonConstants.APP_STATUS_APPLY_NEW);
		int approvedCount = applicationInfoService.getApplicationStatusCount(applicationInfoDto.getCustomerId(), CommonConstants.APP_STATUS_APPROVE);
		int attachmentEditRequestedCount = applicationInfoService.getApplicationStatusCount(applicationInfoDto.getCustomerId(), CommonConstants.APP_STATUS_DOCUMENT_WAIT);

		dashboardInfoDto.setRejectedCount(rejectedCount);
		dashboardInfoDto.setPurchaseConfirmedCount(purchaseConfirmedCount);
		dashboardInfoDto.setPurchaseCanceledCount(purchaseCanceledCount);
		dashboardInfoDto.setCanceledCount(canceledCount);
		dashboardInfoDto.setAppliedCount(appliedCount);
		dashboardInfoDto.setApprovedCount(approvedCount);
		dashboardInfoDto.setAttachmentEditRequestedCount(attachmentEditRequestedCount);
		dashboardInfoDto.setPurchaseCompletedCount(purchaseCompletedCount);
		return dashboardInfoDto;

	}

	@RequestMapping(value = "/get-application-attachment-list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public List<ApplicationInfoAttachmentDto> getApplicationAttachmentList(@RequestBody ApplicationInfoDto applicationInfoDto) {

		if (StringUtils.isEmpty(applicationInfoDto.getDaApplicationInfoId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		List<ApplicationInfoAttachmentDto> applicationInfoAttachmentDtoList = applicationInfoService
				.getPurchaseApplicationAttachmentList(applicationInfoDto.getDaApplicationInfoId());
		return applicationInfoAttachmentDtoList;
	}

	@RequestMapping(value = "/get-application-status-changed-count", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ApplicationStatusChangedCountResDto getApplicationStatusChangedCount(@Valid @RequestBody ApplicationStatusChangedCountReqDto applicationStatusChangedCountReqDto) {

		ApplicationStatusChangedCountResDto applicationStatusChangedCountResDto = new ApplicationStatusChangedCountResDto();
		Integer applicationStatusChangedCount = applicationInfoService.getApplicationStatusChangedCount(applicationStatusChangedCountReqDto.getCustomerId());
		applicationStatusChangedCountResDto.setApplicationStatusChangedCount(applicationStatusChangedCount);
		return applicationStatusChangedCountResDto;
	}

	@RequestMapping(value = "/update-application-status-changed-read-flag", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void updateApplicationStatusChangedReadFlag(@Valid @RequestBody ApplicationStatusChangedReadFlagUpdateReqDto reqDto) {

		applicationInfoService.updateApplicationStatusChangedReadFlag(reqDto.getCustomerId());
	}

	private void checkAttachmentsAndValidation(ApplicationInfoDto applicationInfoDto) {

		if (applicationInfoDto.getFinanceTerm() < 6) {
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

		for (ApplicationInfoAttachmentDto dto : applicationInfoDto.getApplicationInfoAttachmentDtoList()) {
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
		}

		// check input fields
		if (!nrcFrontContain || !nrcBackContain || !residentProofContain || !incomeProofContain || !guarantorNrcFrontContain || !guarantorNrcBackContain
				|| !houseHoldOrCriminalClearanceContain || !applicantPhotoContain || !signatureContain) {

			throw new BusinessLogicException(MessageCode.INSUFFICIENT_ATTACHMENT_INFO, messageSource.getMessage(MessageCode.INSUFFICIENT_ATTACHMENT_INFO, null, null));
		}

		if (applicationInfoDto.getApplicationInfoAttachmentDtoList().size() > 10) {
			throw new BusinessLogicException(MessageCode.ATTACHMENT_INFO_LIST_SIZE, messageSource.getMessage(MessageCode.ATTACHMENT_INFO_LIST_SIZE, null, null));
		}

		double percentageMonthlyBasicIncome = (applicationInfoDto.getApplicantCompanyInfoDto().getMonthlyBasicIncome() / 100) * 5;

		if ((applicationInfoDto.getApplicantCompanyInfoDto().getMonthlyBasicIncome() * 2) + percentageMonthlyBasicIncome < applicationInfoDto.getFinanceAmount()) {
			throw new BusinessLogicException(MessageCode.INVALID_FINANCE_AMOUNT, messageSource.getMessage(MessageCode.INVALID_FINANCE_AMOUNT, null, null));
		}

		if (applicationInfoDto.getFinanceAmount() < 50000) {
			throw new BusinessLogicException(MessageCode.INVALID_FINANCE_AMOUNT_MINIMUM,
					messageSource.getMessage(MessageCode.INVALID_FINANCE_AMOUNT_MINIMUM, new String[] { "50000" }, null));
		}

		if (applicationInfoDto.getFinanceAmount() > 2000000) {
			throw new BusinessLogicException(MessageCode.INVALID_FINANCE_AMOUNT_MAXIMUM,
					messageSource.getMessage(MessageCode.INVALID_FINANCE_AMOUNT_MAXIMUM, new String[] { "2000000" }, null));
		}

		List<Double> activeFinanceAmountList = applicationInfoService.getActiveFinanceAmountList(applicationInfoDto.getCustomerId(), null);

		if (activeFinanceAmountList.size() > 1) {
			throw new BusinessLogicException(MessageCode.APPLICATION_LIMIT, messageSource.getMessage(MessageCode.APPLICATION_LIMIT, null, null));
		} else {
			Double totalFinanceAmount = 0.0;
			for (Double financeAmount : activeFinanceAmountList) {
				totalFinanceAmount += financeAmount;
			}
			totalFinanceAmount += applicationInfoDto.getFinanceAmount();

			if (totalFinanceAmount > (applicationInfoDto.getApplicantCompanyInfoDto().getMonthlyBasicIncome() * 2) + percentageMonthlyBasicIncome) {
				throw new BusinessLogicException(MessageCode.INVALID_TOTAL_FINANCE_AMOUNT, messageSource.getMessage(MessageCode.INVALID_TOTAL_FINANCE_AMOUNT, null, null));
			}

		}

	}

	private void checkAttachmentsAndValidationMultipart(ApplicationInfoDto applicationInfoDto, List<MultipartFile> multipartFileList) {

		if (applicationInfoDto.getFinanceTerm() < 6) {
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

		if (multipartFileList.size() == 0 || multipartFileList.size() != applicationInfoDto.getApplicationInfoAttachmentDtoList().size()) {
			throw new BusinessLogicException(MessageCode.INSUFFICIENT_MULTIPART_FILE_LIST_SIZE,
					messageSource.getMessage(MessageCode.INSUFFICIENT_MULTIPART_FILE_LIST_SIZE, null, null));
		}

		for (ApplicationInfoAttachmentDto dto : applicationInfoDto.getApplicationInfoAttachmentDtoList()) {
			if (StringUtils.isEmpty(dto.getFileName())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_ATTACHMENT_FILE_NAME, messageSource.getMessage(MessageCode.REQUIRED_ATTACHMENT_FILE_NAME, null, null));
			}
			if (!containsName(multipartFileList, dto.getFileName())) {
				throw new BusinessLogicException(MessageCode.NOT_SAME_ATTACHMENT_FILE_NAME_AND_MULTIPART_FILE_NAME,
						messageSource.getMessage(MessageCode.NOT_SAME_ATTACHMENT_FILE_NAME_AND_MULTIPART_FILE_NAME, null, null));
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

		if (applicationInfoDto.getApplicationInfoAttachmentDtoList().size() > 10) {
			throw new BusinessLogicException(MessageCode.ATTACHMENT_INFO_LIST_SIZE, messageSource.getMessage(MessageCode.ATTACHMENT_INFO_LIST_SIZE, null, null));
		}

		double percentageMonthlyBasicIncome = (applicationInfoDto.getApplicantCompanyInfoDto().getMonthlyBasicIncome() / 100) * 5;

		if ((applicationInfoDto.getApplicantCompanyInfoDto().getMonthlyBasicIncome() * 2) + percentageMonthlyBasicIncome < applicationInfoDto.getFinanceAmount()) {
			throw new BusinessLogicException(MessageCode.INVALID_FINANCE_AMOUNT, messageSource.getMessage(MessageCode.INVALID_FINANCE_AMOUNT, null, null));
		}

		if (applicationInfoDto.getFinanceAmount() < 50000) {
			throw new BusinessLogicException(MessageCode.INVALID_FINANCE_AMOUNT_MINIMUM,
					messageSource.getMessage(MessageCode.INVALID_FINANCE_AMOUNT_MINIMUM, new String[] { "50000" }, null));
		}

		if (applicationInfoDto.getFinanceAmount() > 2000000) {
			throw new BusinessLogicException(MessageCode.INVALID_FINANCE_AMOUNT_MAXIMUM,
					messageSource.getMessage(MessageCode.INVALID_FINANCE_AMOUNT_MAXIMUM, new String[] { "2000000" }, null));
		}

		List<Double> activeFinanceAmountList = applicationInfoService.getActiveFinanceAmountList(applicationInfoDto.getCustomerId(), null);

		if (activeFinanceAmountList.size() > 1) {
			throw new BusinessLogicException(MessageCode.APPLICATION_LIMIT, messageSource.getMessage(MessageCode.APPLICATION_LIMIT, null, null));
		} else {
			Double totalFinanceAmount = 0.0;
			for (Double financeAmount : activeFinanceAmountList) {
				totalFinanceAmount += financeAmount;
			}
			totalFinanceAmount += applicationInfoDto.getFinanceAmount();

			if (totalFinanceAmount > (applicationInfoDto.getApplicantCompanyInfoDto().getMonthlyBasicIncome() * 2) + percentageMonthlyBasicIncome) {
				throw new BusinessLogicException(MessageCode.INVALID_TOTAL_FINANCE_AMOUNT, messageSource.getMessage(MessageCode.INVALID_TOTAL_FINANCE_AMOUNT, null, null));
			}

		}

	}

	private void checkAttachmentEditWithMultipart(ApplicationInfoDto applicationInfoDto, List<MultipartFile> multipartFileList) {

		if (StringUtils.isEmpty(applicationInfoDto.getDaApplicationInfoId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		for (ApplicationInfoAttachmentDto applicationInfoAttachmentDto : applicationInfoDto.getApplicationInfoAttachmentDtoList()) {
			if (StringUtils.isEmpty(applicationInfoAttachmentDto.getDaApplicationInfoAttachmentId()) || StringUtils.isEmpty(applicationInfoAttachmentDto.getFilePath())
					|| StringUtils.isEmpty(applicationInfoAttachmentDto.getFileType())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
			}
		}

		if (multipartFileList.size() == 0 || multipartFileList.size() != applicationInfoDto.getApplicationInfoAttachmentDtoList().size()) {
			throw new BusinessLogicException(MessageCode.INSUFFICIENT_MULTIPART_FILE_LIST_SIZE,
					messageSource.getMessage(MessageCode.INSUFFICIENT_MULTIPART_FILE_LIST_SIZE, null, null));
		}

		for (ApplicationInfoAttachmentDto dto : applicationInfoDto.getApplicationInfoAttachmentDtoList()) {
			if (StringUtils.isEmpty(dto.getFileName())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_ATTACHMENT_FILE_NAME, messageSource.getMessage(MessageCode.REQUIRED_ATTACHMENT_FILE_NAME, null, null));
			}
			if (!containsName(multipartFileList, dto.getFileName())) {
				throw new BusinessLogicException(MessageCode.NOT_SAME_ATTACHMENT_FILE_NAME_AND_MULTIPART_FILE_NAME,
						messageSource.getMessage(MessageCode.NOT_SAME_ATTACHMENT_FILE_NAME_AND_MULTIPART_FILE_NAME, null, null));
			}
		}

	}

	public boolean containsName(final List<MultipartFile> list, final String name) {
		return list.stream().filter(o -> o.getOriginalFilename().equals(name)).findFirst().isPresent();
	}

}
