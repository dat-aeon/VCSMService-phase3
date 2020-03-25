package mm.aeon.com.api.purchaseinfo;

import java.text.DecimalFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.common.LoanCalculator;
import mm.aeon.com.dto.applicationinfo.AgentApplicationStatusInfoReqDto;
import mm.aeon.com.dto.applicationinfo.AgentApplicationStatusInfoResDto;
import mm.aeon.com.dto.applicationinfo.ApplicationInfoAttachmentDto;
import mm.aeon.com.dto.applicationinfo.ApplicationInfoDto;
import mm.aeon.com.dto.applicationinfo.CheckingAttachmentDto;
import mm.aeon.com.dto.applicationinfo.PurchaseApplicationInfoDto;
import mm.aeon.com.dto.applicationinfo.PurchaseAttachmentEditInfoReqDto;
import mm.aeon.com.dto.applicationinfo.PurchaseAttachmentEditInfoResDto;
import mm.aeon.com.dto.applicationinfo.PurchaseAttachmentReuploadDto;
import mm.aeon.com.dto.applicationinfo.PurchaseAttachmentReuploadReqDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInfoAttachmentDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInfoConfirmResDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInfoDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInfoIdResDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInquriesResDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInquriesSearchCriteriaDto;
import mm.aeon.com.dto.applicationinfo.UpdateLoanInformationReqDto;
import mm.aeon.com.services.applicationinfo.ApplicationInfoService;
import mm.aeon.com.services.purchaseinfo.PurchaseInfoService;
import mm.aeon.com.zconfig.MessageCode;
import mm.aeon.com.zconfig.exception.BusinessLogicException;

@RestController
@RequestMapping("/purchase-info")
public class PurchaseInfoController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PurchaseInfoService purchaseInfoService;

	@Autowired
	private ApplicationInfoService applicationInfoService;

	@Value("${properties.imageBaseFilePath}")
	private String imageBaseFilePath;

	@Value("${properties.checkingImageFolder}")
	private String checkingImageFolder;

	// @RequestMapping(value = "/initial-register", method = RequestMethod.POST,
	// produces = "application/json; charset=utf-8")
	// public PurchaseInfoIdResDto purchaseInfoInitialRegister(@RequestBody
	// PurchaseInfoDto purchaseInfoDto) throws Exception {
	//
	// checkPurchaseInfoInitialRegisterInput(purchaseInfoDto);
	// ApplicationInfoDto applicationInfoDto =
	// applicationInfoService.getApplicationInfoDetail(purchaseInfoDto.getDaApplicationInfoId());
	// if (applicationInfoDto == null) {
	// throw new BusinessLogicException(MessageCode.INVALID_APPLICATION_INFO,
	// messageSource.getMessage(MessageCode.INVALID_APPLICATION_INFO, null,
	// null));
	// }
	//
	// PurchaseInfoDto oldPurchaseInfoDto =
	// applicationInfoService.getPurchaseInfoDetail(purchaseInfoDto.getDaApplicationInfoId());
	// PurchaseInfoIdResDto purchaseInfoIdResDto = new PurchaseInfoIdResDto();
	// int purchaseInfoId;
	// if (oldPurchaseInfoDto == null) {
	// purchaseInfoId =
	// purchaseInfoService.purchaseInfoInitialRegister(purchaseInfoDto);
	// } else {
	// if
	// (oldPurchaseInfoDto.getStatus().equals(CommonConstants.PURCHASE_STATUS_CONFIRM))
	// {
	// throw new BusinessLogicException(MessageCode.PURCHASE_ALREADY_CONFIRMED,
	// messageSource.getMessage(MessageCode.PURCHASE_ALREADY_CONFIRMED, null,
	// null));
	// }
	// purchaseInfoDto.setDaPurchaseInfoId(oldPurchaseInfoDto.getDaPurchaseInfoId());
	// purchaseInfoService.purchaseInfoInitialUpdate(purchaseInfoDto,
	// oldPurchaseInfoDto.getPurchaseInfoAttachmentDtoList().get(0).getFilePath());
	// purchaseInfoId = oldPurchaseInfoDto.getDaPurchaseInfoId();
	// }
	//
	// purchaseInfoIdResDto.setPurchaseInfoId(purchaseInfoId);
	// return purchaseInfoIdResDto;
	// }

	@RequestMapping(value = "/get-agent-application-status-info", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public AgentApplicationStatusInfoResDto getAgentApplicationStatusInfo(@Valid @RequestBody AgentApplicationStatusInfoReqDto agentApplicationStatusInfoReqDto) {

		AgentApplicationStatusInfoResDto agentApplicationStatusInfoResDto = purchaseInfoService.getAgentApplicationStatusInfo(agentApplicationStatusInfoReqDto);

		return agentApplicationStatusInfoResDto;
	}

	@RequestMapping(value = "/update-loan-information", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void updateLoanInformation(@Valid @RequestBody UpdateLoanInformationReqDto updateLoanInformationReqDto) {

		ApplicationInfoDto applicationInfoDto = applicationInfoService.getApplicationInfoDetail(updateLoanInformationReqDto.getDaApplicationInfoId());
		if (applicationInfoDto == null) {
			throw new BusinessLogicException(MessageCode.INVALID_APPLICATION_INFO, messageSource.getMessage(MessageCode.INVALID_APPLICATION_INFO, null, null));
		}

		PurchaseInfoDto oldPurchaseInfoDto = applicationInfoService.getPurchaseInfoDetail(applicationInfoDto.getDaApplicationInfoId());

		if (oldPurchaseInfoDto != null) {
			if (oldPurchaseInfoDto.getStatus().equals(CommonConstants.PURCHASE_STATUS_COMPLETE)) {
				throw new BusinessLogicException(MessageCode.PURCHASE_ALREADY_COMPLETED, messageSource.getMessage(MessageCode.PURCHASE_ALREADY_COMPLETED, null, null));
			}
		}

		applicationInfoDto.setModifyFinanceAmount(updateLoanInformationReqDto.getUpdateFinanceAmount());
		applicationInfoDto.setModifyFinanceTerm(updateLoanInformationReqDto.getUpdateLoanTerm());

		double percentageMonthlyBasicIncome = (applicationInfoDto.getApplicantCompanyInfoDto().getMonthlyBasicIncome() / 100) * 5;

		DecimalFormat decimalformat = new DecimalFormat("###,###,###,###");

		if ((applicationInfoDto.getApplicantCompanyInfoDto().getMonthlyBasicIncome() * 2) + percentageMonthlyBasicIncome < applicationInfoDto.getModifyFinanceAmount()) {
			throw new BusinessLogicException(MessageCode.INVALID_FINANCE_AMOUNT, messageSource.getMessage(MessageCode.INVALID_UPDATE_FINANCE_AMOUNT, null, null));
		}

		if (applicationInfoDto.getModifyFinanceAmount() < 50000) {
			throw new BusinessLogicException(MessageCode.INVALID_FINANCE_AMOUNT_MINIMUM,
					messageSource.getMessage(MessageCode.INVALID_FINANCE_AMOUNT_MINIMUM, new String[] { "50000" }, null));
		}

		if (applicationInfoDto.getModifyFinanceAmount() > 2000000) {
			throw new BusinessLogicException(MessageCode.INVALID_FINANCE_AMOUNT_MAXIMUM,
					messageSource.getMessage(MessageCode.INVALID_FINANCE_AMOUNT_MAXIMUM, new String[] { "2000000" }, null));
		}

		List<Double> activeFinanceAmountList = applicationInfoService.getActiveFinanceAmountList(applicationInfoDto.getCustomerId(), applicationInfoDto.getDaApplicationInfoId());

		Double totalFinanceAmount = 0.0;
		for (Double financeAmount : activeFinanceAmountList) {
			totalFinanceAmount += financeAmount;
		}
		totalFinanceAmount += applicationInfoDto.getModifyFinanceAmount();

		if (totalFinanceAmount > (applicationInfoDto.getApplicantCompanyInfoDto().getMonthlyBasicIncome() * 2) + percentageMonthlyBasicIncome) {
			throw new BusinessLogicException(MessageCode.INVALID_TOTAL_FINANCE_AMOUNT, messageSource.getMessage(MessageCode.INVALID_UPDATE_TOTAL_FINANCE_AMOUNT,
					new String[] { decimalformat.format(applicationInfoDto.getApplicantCompanyInfoDto().getMonthlyBasicIncome()) }, null));
		}

		applicationInfoService.updateLoanInformation(applicationInfoDto);
	}

	@RequestMapping(value = "/initial-register-multipart", method = RequestMethod.POST, produces = "application/json; charset=utf-8", headers = ("content-type=multipart/*"))
	public PurchaseInfoIdResDto purchaseInfoInitialRegisterWithMultipart(@RequestPart(required = true) PurchaseInfoDto purchaseInfoDto,
			@RequestPart(name = "img", required = true) MultipartFile multipartFile) throws Exception {

		checkPurchaseInfoInitialRegisterInputWithMultipart(purchaseInfoDto, multipartFile);
		ApplicationInfoDto applicationInfoDto = applicationInfoService.getApplicationInfoDetail(purchaseInfoDto.getDaApplicationInfoId());
		if (applicationInfoDto == null) {
			throw new BusinessLogicException(MessageCode.INVALID_APPLICATION_INFO, messageSource.getMessage(MessageCode.INVALID_APPLICATION_INFO, null, null));
		}

		PurchaseInfoDto oldPurchaseInfoDto = applicationInfoService.getPurchaseInfoDetail(purchaseInfoDto.getDaApplicationInfoId());
		PurchaseInfoIdResDto purchaseInfoIdResDto = new PurchaseInfoIdResDto();
		int purchaseInfoId;
		if (oldPurchaseInfoDto == null) {
			purchaseInfoId = purchaseInfoService.purchaseInfoInitialRegisterWithMultipart(purchaseInfoDto, multipartFile, null);
		} else {
			if (oldPurchaseInfoDto.getStatus().equals(CommonConstants.PURCHASE_STATUS_COMPLETE)) {
				throw new BusinessLogicException(MessageCode.PURCHASE_ALREADY_COMPLETED, messageSource.getMessage(MessageCode.PURCHASE_ALREADY_COMPLETED, null, null));
			}
			// if (oldPurchaseInfoDto.getOutletId() !=
			// purchaseInfoDto.getOutletId()) {
			// purchaseInfoId =
			// purchaseInfoService.purchaseInfoInitialRegisterWithMultipart(purchaseInfoDto,
			// multipartFile, oldPurchaseInfoDto);
			// } else {
			// purchaseInfoDto.setDaPurchaseInfoId(oldPurchaseInfoDto.getDaPurchaseInfoId());
			// purchaseInfoService.purchaseInfoInitialUpdateWithMultipart(purchaseInfoDto,
			// oldPurchaseInfoDto.getPurchaseInfoAttachmentDtoList().get(0).getFilePath(),
			// multipartFile);
			// purchaseInfoId = oldPurchaseInfoDto.getDaPurchaseInfoId();
			// }
			purchaseInfoId = purchaseInfoService.purchaseInfoInitialRegisterWithMultipart(purchaseInfoDto, multipartFile, oldPurchaseInfoDto);

		}

		purchaseInfoIdResDto.setPurchaseInfoId(purchaseInfoId);
		return purchaseInfoIdResDto;
	}

	@RequestMapping(value = "/check-confirm", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public PurchaseInfoConfirmResDto purchaseInfoCheckConfirm(@RequestBody PurchaseInfoDto purchaseInfoDto) {

		if (StringUtils.isEmpty(purchaseInfoDto.getCustomerId()) || StringUtils.isEmpty(purchaseInfoDto.getDaApplicationInfoId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		PurchaseInfoDto dbPurchaseInfoDto = applicationInfoService.getPurchaseInfoDetail(purchaseInfoDto.getDaApplicationInfoId());

		PurchaseInfoConfirmResDto purchaseInfoConfirmResDto = new PurchaseInfoConfirmResDto();
		if (dbPurchaseInfoDto != null && dbPurchaseInfoDto.getStatus().equals(CommonConstants.PURCHASE_STATUS_CONFIRM)) {
			purchaseInfoConfirmResDto.setPurchaseConfirm(true);
		}
		return purchaseInfoConfirmResDto;
	}

	@RequestMapping(value = "/set-confirm-waiting", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void setPurchaseInfoConfirmWaiting(@Valid @RequestBody PurchaseInfoDto purchaseInfoDto) {
		ApplicationInfoDto applicationInfoDto = applicationInfoService.getApplicationInfoDetail(purchaseInfoDto.getDaApplicationInfoId());
		if (applicationInfoDto == null) {
			throw new BusinessLogicException(MessageCode.INVALID_APPLICATION_INFO, messageSource.getMessage(MessageCode.INVALID_APPLICATION_INFO, null, null));
		}

		double processingFees = LoanCalculator.calculateProcessingFees(false, applicationInfoDto.getApprovedFinanceAmount());
		double settlementAmount = applicationInfoDto.getApprovedFinanceAmount() - (processingFees + applicationInfoDto.getCompulsoryInfoDto().getCompulsoryAmount());

		purchaseInfoDto.setSettlementAmount(settlementAmount);
		purchaseInfoService.setPurchaseInfoConfirmWaiting(purchaseInfoDto, applicationInfoDto);
	}

	@RequestMapping(value = "/get-purchase-application-info", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public PurchaseApplicationInfoDto getPurchaseApplicationInfo(@RequestBody PurchaseInfoDto purchaseInfoDto) {

		if (StringUtils.isEmpty(purchaseInfoDto.getDaApplicationInfoId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		PurchaseApplicationInfoDto purchaseApplicationInfoDto = purchaseInfoService.getPurchaseApplicationInfoDto(purchaseInfoDto.getDaApplicationInfoId());
		if (purchaseApplicationInfoDto != null) {
			if (purchaseApplicationInfoDto.getApplicationStatus().equals(CommonConstants.APP_STATUS_MODIFY_REQUEST)
					|| purchaseApplicationInfoDto.getApplicationStatus().equals(CommonConstants.APP_STATUS_MODIFY_UPLOADED)) {
				return null;
			}
			double processingFees = LoanCalculator.calculateProcessingFees(false, purchaseApplicationInfoDto.getFinanceAmount());
			purchaseApplicationInfoDto.setProcessingFees(processingFees);
			purchaseApplicationInfoDto.setCompulsorySaving(purchaseApplicationInfoDto.getCompulsoryInfoDto().getCompulsoryAmount());
			double settlementAmount = purchaseApplicationInfoDto.getFinanceAmount() - (processingFees + purchaseApplicationInfoDto.getCompulsoryInfoDto().getCompulsoryAmount());
			purchaseApplicationInfoDto.setSettlementAmount(settlementAmount);
		}
		return purchaseApplicationInfoDto;
	}

	@RequestMapping(value = "/send-check-purchase-application-attachment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void sendCheckPurchaseApplicationAttachment(@RequestBody CheckingAttachmentDto checkingAttachmentDto) throws Exception {

		purchaseInfoService.sendCheckPurchaseApplicationAttachment(checkingAttachmentDto);

	}

	@RequestMapping(value = "/send-check-purchase-application-attachment-multipart", method = RequestMethod.POST, produces = "application/json; charset=utf-8", headers = ("content-type=multipart/*"))
	public void sendCheckPurchaseApplicationAttachmentWithMultipart(@Valid @RequestPart(required = true) CheckingAttachmentDto checkingAttachmentDto,
			@RequestPart(name = "img", required = true) MultipartFile img) throws Exception {

		purchaseInfoService.sendCheckPurchaseApplicationAttachmentWithMultipart(checkingAttachmentDto, img);

	}

	@RequestMapping(value = "/get-checking-attachment-list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public List<CheckingAttachmentDto> getCheckingAttachmentList(@RequestBody PurchaseInfoDto purchaseInfoDto) {

		List<CheckingAttachmentDto> checkingAttachmentDtoList = purchaseInfoService.getCheckingAttachmentList(purchaseInfoDto.getDaPurchaseInfoId());
		return checkingAttachmentDtoList;
	}

	// @RequestMapping(value = "/final-register", method = RequestMethod.POST,
	// produces = "application/json; charset=utf-8")
	// public void purchaseInfoFinalRegister(@RequestBody PurchaseInfoDto
	// purchaseInfoDto) throws Exception {
	// checkPurchaseInfoFinalRegisterInput(purchaseInfoDto);
	//
	// ApplicationInfoDto applicationInfoDto =
	// applicationInfoService.getApplicationInfoDetail(purchaseInfoDto.getDaApplicationInfoId());
	// if (applicationInfoDto == null) {
	// throw new BusinessLogicException(MessageCode.INVALID_APPLICATION_INFO,
	// messageSource.getMessage(MessageCode.INVALID_APPLICATION_INFO, null,
	// null));
	// }
	//
	// if
	// (applicationInfoDto.getStatus().equals(CommonConstants.APP_STATUS_PURCHASE_COMPLETE))
	// {
	// throw new BusinessLogicException(MessageCode.PURCHASE_ALREADY_COMPLETED,
	// messageSource.getMessage(MessageCode.PURCHASE_ALREADY_COMPLETED, null,
	// null));
	// }
	//
	// purchaseInfoService.purchaseInfoFinalRegister(purchaseInfoDto,
	// applicationInfoDto);
	// }

	@RequestMapping(value = "/final-register-multipart", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	public void purchaseInfoFinalRegisterWithMultipart(@RequestPart(required = true) PurchaseInfoDto purchaseInfoDto,
			@RequestPart(name = "img", required = true) List<MultipartFile> multipartFileList) throws Exception {

		checkPurchaseInfoFinalRegisterInputWithMultipart(purchaseInfoDto, multipartFileList);

		ApplicationInfoDto applicationInfoDto = applicationInfoService.getApplicationInfoDetail(purchaseInfoDto.getDaApplicationInfoId());
		if (applicationInfoDto == null) {
			throw new BusinessLogicException(MessageCode.INVALID_APPLICATION_INFO, messageSource.getMessage(MessageCode.INVALID_APPLICATION_INFO, null, null));
		}

		if (applicationInfoDto.getStatus().equals(CommonConstants.APP_STATUS_PURCHASE_COMPLETE)) {
			throw new BusinessLogicException(MessageCode.PURCHASE_ALREADY_COMPLETED, messageSource.getMessage(MessageCode.PURCHASE_ALREADY_COMPLETED, null, null));
		}

		purchaseInfoService.purchaseInfoFinalRegisterWithMultipart(purchaseInfoDto, applicationInfoDto, multipartFileList);
	}

	@RequestMapping(value = "/get-purchase-application-attachment-list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public List<ApplicationInfoAttachmentDto> getPurchaseApplicationAttachmentList(@RequestBody PurchaseInfoDto purchaseInfoDto) {

		if (StringUtils.isEmpty(purchaseInfoDto.getDaApplicationInfoId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		List<ApplicationInfoAttachmentDto> applicationInfoAttachmentDtoList = applicationInfoService.getPurchaseApplicationAttachmentList(purchaseInfoDto.getDaApplicationInfoId());
		return applicationInfoAttachmentDtoList;
	}

	@RequestMapping(value = "/purchase-info-cancel-agent", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void purchaseInfoCancelByAgent(@RequestBody PurchaseInfoDto purchaseInfoDto) {
		if (StringUtils.isEmpty(purchaseInfoDto.getDaPurchaseInfoId()) || StringUtils.isEmpty(purchaseInfoDto.getOutletId()) || StringUtils.isEmpty(purchaseInfoDto.getOutletName())
				|| StringUtils.isEmpty(purchaseInfoDto.getDaApplicationInfoId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		ApplicationInfoDto applicationInfoDto = applicationInfoService.getApplicationInfoDetail(purchaseInfoDto.getDaApplicationInfoId());
		if (applicationInfoDto == null) {
			throw new BusinessLogicException(MessageCode.INVALID_APPLICATION_INFO, messageSource.getMessage(MessageCode.INVALID_APPLICATION_INFO, null, null));
		}
		purchaseInfoService.purchaseInfoCancel(purchaseInfoDto, applicationInfoDto);
	}

	@RequestMapping(value = "/purchase-inquries-list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public List<PurchaseInquriesResDto> getPurchaseInquriesList(@Valid @RequestBody PurchaseInquriesSearchCriteriaDto purchaseInquriesSearchCriteriaDto) {

		String settlementDateStr = CommonUtil.formatByPattern(purchaseInquriesSearchCriteriaDto.getSettlementDate(), "yyyyMMdd");
		purchaseInquriesSearchCriteriaDto.setSettlementDateStr(settlementDateStr);
		List<PurchaseInquriesResDto> purchaseInquriesResDtoList = purchaseInfoService.getPurchaseInquriesList(purchaseInquriesSearchCriteriaDto);

		for (PurchaseInquriesResDto dto : purchaseInquriesResDtoList) {
			double processingFees = LoanCalculator.calculateProcessingFees(false, dto.getFinanceAmount());
			dto.setProcessingFees(processingFees);
			dto.setCompulsoryAmount(dto.getCompulsoryAmount());
			double settlementAmount = dto.getFinanceAmount() - (processingFees + dto.getCompulsoryAmount());
			dto.setSettlementAmount(settlementAmount);
		}
		return purchaseInquriesResDtoList;

	}

	@RequestMapping(value = "/get-purchase-attachment-edit-info-list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public List<PurchaseAttachmentEditInfoResDto> getPurchaseAttachmentEditInfoList(@Valid @RequestBody PurchaseAttachmentEditInfoReqDto purchaseAttachmentEditInfoReqDto) {
		return purchaseInfoService.getPurchaseAttachmentEditInfoList(purchaseAttachmentEditInfoReqDto);

	}

	@RequestMapping(value = "/purchase-attachment-reupload-with-multipart", method = RequestMethod.POST, produces = "application/json; charset=utf-8", headers = ("content-type=multipart/*"))
	public void purchaseAttachmentReuploadWithMultipart(@Valid @RequestPart(required = true) PurchaseAttachmentReuploadReqDto purchaseAttachmentReuploadReqDto,
			@RequestPart(name = "img", required = true) List<MultipartFile> multipartFileList) throws Exception {

		checkPurchaseAttachmentReuploadInputWithMultipart(purchaseAttachmentReuploadReqDto, multipartFileList);
		purchaseInfoService.purchaseAttachmentReuploadWithMultipart(purchaseAttachmentReuploadReqDto, multipartFileList);
	}

	// private void checkPurchaseInfoInitialRegisterInput(PurchaseInfoDto
	// purchaseInfoDto) {
	//
	// if (StringUtils.isEmpty(purchaseInfoDto.getCustomerId()) ||
	// StringUtils.isEmpty(purchaseInfoDto.getDaApplicationInfoId())
	// || StringUtils.isEmpty(purchaseInfoDto.getOutletId()) ||
	// StringUtils.isEmpty(purchaseInfoDto.getOutletName()) ||
	// StringUtils.isEmpty(purchaseInfoDto.getAgentId())
	// || StringUtils.isEmpty(purchaseInfoDto.getAgentName()) ||
	// StringUtils.isEmpty(purchaseInfoDto.getAgreementNo())
	// ||
	// CollectionUtils.isEmpty(purchaseInfoDto.getPurchaseInfoAttachmentDtoList()))
	// {
	// throw new BusinessLogicException(MessageCode.REQUIRED_INPUT,
	// messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
	// }
	//
	// boolean memberCardContain = false;
	//
	// for (PurchaseInfoAttachmentDto dto :
	// purchaseInfoDto.getPurchaseInfoAttachmentDtoList()) {
	// if
	// (dto.getFileType().equals(CommonConstants.PURCHASE_FILE_TYPE_MEMBER_CARD))
	// {
	// memberCardContain = true;
	// }
	//
	// if (ArrayUtils.isEmpty(dto.getPhotoByte())) {
	// throw new BusinessLogicException(MessageCode.REQUIRED_INPUT,
	// messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
	// }
	// }
	//
	// // check input fields
	// if (!memberCardContain) {
	// throw new
	// BusinessLogicException(MessageCode.INSUFFICIENT_ATTACHMENT_INFO,
	// messageSource.getMessage(MessageCode.INSUFFICIENT_ATTACHMENT_INFO, null,
	// null));
	// }
	// }

	private void checkPurchaseInfoInitialRegisterInputWithMultipart(PurchaseInfoDto purchaseInfoDto, MultipartFile multipartFile) {

		if (StringUtils.isEmpty(purchaseInfoDto.getCustomerId()) || StringUtils.isEmpty(purchaseInfoDto.getDaApplicationInfoId())
				|| StringUtils.isEmpty(purchaseInfoDto.getOutletId()) || StringUtils.isEmpty(purchaseInfoDto.getOutletName()) || StringUtils.isEmpty(purchaseInfoDto.getAgentId())
				|| StringUtils.isEmpty(purchaseInfoDto.getAgentName()) || StringUtils.isEmpty(purchaseInfoDto.getAgreementNo())
				|| StringUtils.isEmpty(purchaseInfoDto.getPurchaseLocation()) || CollectionUtils.isEmpty(purchaseInfoDto.getPurchaseInfoAttachmentDtoList())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		boolean memberCardContain = false;

		for (PurchaseInfoAttachmentDto dto : purchaseInfoDto.getPurchaseInfoAttachmentDtoList()) {

			if (StringUtils.isEmpty(dto.getFileName())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_ATTACHMENT_FILE_NAME, messageSource.getMessage(MessageCode.REQUIRED_ATTACHMENT_FILE_NAME, null, null));
			}
			if (!dto.getFileName().equals(multipartFile.getOriginalFilename())) {
				throw new BusinessLogicException(MessageCode.NOT_SAME_ATTACHMENT_FILE_NAME_AND_MULTIPART_FILE_NAME,
						messageSource.getMessage(MessageCode.NOT_SAME_ATTACHMENT_FILE_NAME_AND_MULTIPART_FILE_NAME, null, null));
			}

			if (dto.getFileType().equals(CommonConstants.PURCHASE_FILE_TYPE_MEMBER_CARD)) {
				memberCardContain = true;
			}
		}

		// check input fields
		if (!memberCardContain) {
			throw new BusinessLogicException(MessageCode.INSUFFICIENT_ATTACHMENT_INFO, messageSource.getMessage(MessageCode.INSUFFICIENT_ATTACHMENT_INFO, null, null));
		}
	}

	// private void checkPurchaseInfoFinalRegisterInput(PurchaseInfoDto
	// purchaseInfoDto) {
	//
	// if (StringUtils.isEmpty(purchaseInfoDto.getCustomerId()) ||
	// StringUtils.isEmpty(purchaseInfoDto.getDaApplicationInfoId())
	// || StringUtils.isEmpty(purchaseInfoDto.getDaPurchaseInfoId()) ||
	// StringUtils.isEmpty(purchaseInfoDto.getOutletId())
	// || StringUtils.isEmpty(purchaseInfoDto.getOutletName()) ||
	// StringUtils.isEmpty(purchaseInfoDto.getAgentId())
	// || StringUtils.isEmpty(purchaseInfoDto.getAgentName())) {
	// throw new BusinessLogicException(MessageCode.REQUIRED_INPUT,
	// messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
	// }
	//
	// boolean uloanContain = false;
	// boolean invoiceContain = false;
	//
	// for (PurchaseInfoAttachmentDto dto :
	// purchaseInfoDto.getPurchaseInfoAttachmentDtoList()) {
	// if (dto.getFileType().equals(CommonConstants.PURCHASE_FILE_TYPE_ULOAN)) {
	// uloanContain = true;
	// }
	// if (dto.getFileType().equals(CommonConstants.PURCHASE_FILE_TYPE_INVOICE))
	// {
	// invoiceContain = true;
	// }
	// }
	//
	// // check input fields
	// if (!uloanContain || !invoiceContain) {
	//
	// throw new
	// BusinessLogicException(MessageCode.INSUFFICIENT_ATTACHMENT_INFO,
	// messageSource.getMessage(MessageCode.INSUFFICIENT_ATTACHMENT_INFO, null,
	// null));
	// }
	//
	// }

	private void checkPurchaseAttachmentReuploadInputWithMultipart(PurchaseAttachmentReuploadReqDto purchaseAttachmentReuploadReqDto, List<MultipartFile> multipartFileList) {

		if (multipartFileList.size() == 0 || multipartFileList.size() != purchaseAttachmentReuploadReqDto.getPurchaseAttachmentReuploadDtoList().size()) {
			throw new BusinessLogicException(MessageCode.INSUFFICIENT_MULTIPART_FILE_LIST_SIZE,
					messageSource.getMessage(MessageCode.INSUFFICIENT_MULTIPART_FILE_LIST_SIZE, null, null));
		}

		for (PurchaseAttachmentReuploadDto dto : purchaseAttachmentReuploadReqDto.getPurchaseAttachmentReuploadDtoList()) {
			if (StringUtils.isEmpty(dto.getFileName())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_ATTACHMENT_FILE_NAME, messageSource.getMessage(MessageCode.REQUIRED_ATTACHMENT_FILE_NAME, null, null));
			}
			if (!containsName(multipartFileList, dto.getFileName())) {
				throw new BusinessLogicException(MessageCode.NOT_SAME_ATTACHMENT_FILE_NAME_AND_MULTIPART_FILE_NAME,
						messageSource.getMessage(MessageCode.NOT_SAME_ATTACHMENT_FILE_NAME_AND_MULTIPART_FILE_NAME, null, null));
			}
		}

	}

	private void checkPurchaseInfoFinalRegisterInputWithMultipart(PurchaseInfoDto purchaseInfoDto, List<MultipartFile> multipartFileList) {

		if (StringUtils.isEmpty(purchaseInfoDto.getCustomerId()) || StringUtils.isEmpty(purchaseInfoDto.getDaApplicationInfoId())
				|| StringUtils.isEmpty(purchaseInfoDto.getDaPurchaseInfoId()) || StringUtils.isEmpty(purchaseInfoDto.getOutletId())
				|| StringUtils.isEmpty(purchaseInfoDto.getOutletName()) || StringUtils.isEmpty(purchaseInfoDto.getAgentId()) || StringUtils.isEmpty(purchaseInfoDto.getAgentName())
				|| StringUtils.isEmpty(purchaseInfoDto.getInvoiceNo())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		boolean uloanContain = false;
		boolean invoiceContain = false;
		boolean letterOfAgreementContain = false;
		boolean cashReceiptContain = false;

		if (multipartFileList.size() == 0 || multipartFileList.size() != purchaseInfoDto.getPurchaseInfoAttachmentDtoList().size()) {
			throw new BusinessLogicException(MessageCode.INSUFFICIENT_MULTIPART_FILE_LIST_SIZE,
					messageSource.getMessage(MessageCode.INSUFFICIENT_MULTIPART_FILE_LIST_SIZE, null, null));
		}

		for (PurchaseInfoAttachmentDto dto : purchaseInfoDto.getPurchaseInfoAttachmentDtoList()) {
			if (StringUtils.isEmpty(dto.getFileName())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_ATTACHMENT_FILE_NAME, messageSource.getMessage(MessageCode.REQUIRED_ATTACHMENT_FILE_NAME, null, null));
			}
			if (!containsName(multipartFileList, dto.getFileName())) {
				throw new BusinessLogicException(MessageCode.NOT_SAME_ATTACHMENT_FILE_NAME_AND_MULTIPART_FILE_NAME,
						messageSource.getMessage(MessageCode.NOT_SAME_ATTACHMENT_FILE_NAME_AND_MULTIPART_FILE_NAME, null, null));
			}
			if (dto.getFileType().equals(CommonConstants.PURCHASE_FILE_TYPE_ULOAN)) {
				uloanContain = true;
			}
			if (dto.getFileType().equals(CommonConstants.PURCHASE_FILE_TYPE_INVOICE)) {
				invoiceContain = true;
			}
			if (dto.getFileType().equals(CommonConstants.PURCHASE_FILE_TYPE_LETTER_OF_AGREEMENT)) {
				letterOfAgreementContain = true;
			}
			if (dto.getFileType().equals(CommonConstants.PURCHASE_FILE_TYPE_CASH_RECEIPT)) {
				cashReceiptContain = true;
			}
		}

		// check input fields
		if (!uloanContain || !invoiceContain || !letterOfAgreementContain || !cashReceiptContain) {

			throw new BusinessLogicException(MessageCode.INSUFFICIENT_ATTACHMENT_INFO, messageSource.getMessage(MessageCode.INSUFFICIENT_ATTACHMENT_INFO, null, null));
		}

	}

	public boolean containsName(final List<MultipartFile> list, final String name) {
		return list.stream().filter(o -> o.getOriginalFilename().equals(name)).findFirst().isPresent();
	}

	public static void main(String[] args) {
		String string = "{\"agentId\":129,\"agentName\":\"AEON MICROFINANCE YGN\",\"agreementNo\":\"2019-5-0010010109-3\",\"customerId\":302307,\"daApplicationInfoId\":539,\"daPurchaseInfoId\":65,\"outletId\":863,\"outletName\":\"129 head-office\",\"purchaseInfoAttachmentDtoList\":[{\"fileName\":\"201912031227334055429.jpeg\",\"fileType\":2},{\"fileName\":\"2019120312271190564628.jpeg\",\"fileType\":4},{},{},{},{\"fileName\":\"2019120312271127782207.jpeg\",\"fileType\":3},{},{},{},{}]}";
		PurchaseInfoDto purchaseInfoDtoObject = new Gson().fromJson(string, PurchaseInfoDto.class);
		System.out.println(purchaseInfoDtoObject);
	}

}
