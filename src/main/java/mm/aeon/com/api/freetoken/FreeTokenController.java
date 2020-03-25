package mm.aeon.com.api.freetoken;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import mm.aeon.com.common.LoanCalculator;
import mm.aeon.com.dto.customerinfo.CheckMultiLoginReqDto;
import mm.aeon.com.dto.customerinfo.CheckMultiLoginResDto;
import mm.aeon.com.dto.customerinfo.CustomerInfoDto;
import mm.aeon.com.dto.locancalculator.LoanCalculatorReqDto;
import mm.aeon.com.dto.locancalculator.LoanCalculatorResDto;
import mm.aeon.com.dto.newsinfo.NewsInfoResDto;
import mm.aeon.com.dto.outletinfo.OutletInfoResDto;
import mm.aeon.com.dto.promotionsinfo.PromotionsInfoResDto;
import mm.aeon.com.services.customerinfo.CustomerInfoService;
import mm.aeon.com.services.newsinfo.NewsInfoService;
import mm.aeon.com.services.promotionsinfo.PromotionsInfoService;
import mm.aeon.com.zconfig.MessageCode;
import mm.aeon.com.zconfig.exception.BusinessLogicException;

@RestController
@RequestMapping("/free-token")
public class FreeTokenController {

	@Autowired
	private CustomerInfoService customerInfoService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private NewsInfoService newsInfoService;

	@Autowired
	private PromotionsInfoService promotionsInfoService;

	@Value("${properties.outletInfoListApiLink}")
	private String outletInfoListApiLink;

	@Value("${properties.outletImageApiLink}")
	private String outletImageApiLink;

	@Value("${properties.outletLimitMetre}")
	private Double outletLimitMetre;

	@RequestMapping(value = "/loan-calculate", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public LoanCalculatorResDto loanCalculate(@RequestBody LoanCalculatorReqDto loanCalculatorReqDto) throws Exception {

		validateloanCalculateInput(loanCalculatorReqDto);

		double totalProductPrice = loanCalculatorReqDto.getFinanceAmount();
		boolean motorcycleLoanFlag = loanCalculatorReqDto.isMotorCycleLoanFlag();
		int loanTerm = loanCalculatorReqDto.getLoanTerm();
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
		double interestRateMonthly = 1.4;
		double totalInterestForPSG = 0;
		double monthlyInstallmentForPSG = 0;
		double monthlyInstallment = 0;
		double conSaving = 1000;
		double totalConSaving = 0;
		double initialPaymentForPSG = 0;
		double firstPaymentForPSG = 0;
		double lastPayment = 0;

		processingFees = LoanCalculator.calculateProcessingFees(motorcycleLoanFlag, totalProductPrice);
		financeAmount = LoanCalculator.calculateFinanceAmountForPSG(totalProductPrice, downPayment, promoDiscount, insPremium, vatAmount, financeAmountForPSG);
		totalInterest = LoanCalculator.calculateTotalInterest(financeAmount, loanTerm, interestRateMonthly, totalInterestForPSG);
		totalRepayment = LoanCalculator.calculateTotalRepayment(financeAmount, totalInterest);
		monthlyInstallment = LoanCalculator.calculateMonthlyInstallment(totalRepayment, monthlyInstallmentForPSG, loanTerm);
		totalConSaving = LoanCalculator.calculateTotalConSaving(conSaving, loanTerm);
		firstPaymentForPSG = LoanCalculator.calculateFirstPaymentForPSG(initialPaymentForPSG);
		firstPayment = LoanCalculator.calculateFirstPayment(initialPaymentForPSG, totalRepayment, monthlyInstallment, loanTerm, firstPaymentForPSG);
		monthlyPayment = monthlyInstallment;
		lastPayment = LoanCalculator.calculateLastPayment(totalProductPrice, firstPayment, monthlyPayment, totalInterest, loanTerm, monthlyInstallment);
		modifyTotalRepayment = LoanCalculator.modifyCalculateTotalRepayment(monthlyPayment, loanTerm, firstPayment, lastPayment);

		LoanCalculatorResDto loanCalculatorResDto = new LoanCalculatorResDto();
		loanCalculatorResDto.setConSaving(conSaving);
		loanCalculatorResDto.setFirstPayment(firstPayment);
		loanCalculatorResDto.setLastPayment(lastPayment);
		loanCalculatorResDto.setMonthlyPayment(monthlyPayment);
		loanCalculatorResDto.setProcessingFees(processingFees);
		loanCalculatorResDto.setTotalRepayment(modifyTotalRepayment);
		loanCalculatorResDto.setTotalConSaving(totalConSaving);

		return loanCalculatorResDto;

	}

	private void validateloanCalculateInput(LoanCalculatorReqDto loanCalculatorReqDto) {
		// check input fields
		if (StringUtils.isEmpty(loanCalculatorReqDto.getLoanTerm()) || StringUtils.isEmpty(loanCalculatorReqDto.getFinanceAmount())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		if (loanCalculatorReqDto.getLoanTerm() < 6) {
			throw new BusinessLogicException(MessageCode.INVALID_LOAN_TERM_MINIMUM, messageSource.getMessage(MessageCode.INVALID_LOAN_TERM_MINIMUM, new String[] { "6" }, null));
		}

		if (loanCalculatorReqDto.getFinanceAmount() < 50000) {
			throw new BusinessLogicException(MessageCode.INVALID_FINANCE_AMOUNT_MINIMUM,
					messageSource.getMessage(MessageCode.INVALID_FINANCE_AMOUNT_MINIMUM, new String[] { "50000" }, null));
		}

		if (!loanCalculatorReqDto.isMotorCycleLoanFlag()) {
			if (loanCalculatorReqDto.getFinanceAmount() >= 100000 && loanCalculatorReqDto.getFinanceAmount() <= 150000) {
				if (loanCalculatorReqDto.getLoanTerm() < 6 && loanCalculatorReqDto.getLoanTerm() > 6) {
					throw new BusinessLogicException(MessageCode.INVALID_LOAN_TERM, messageSource.getMessage(MessageCode.INVALID_LOAN_TERM, null, null));
				}
			}
			if (loanCalculatorReqDto.getFinanceAmount() > 700000) {
				ArrayList<Integer> maxLoanTermList = new ArrayList<Integer>();
				maxLoanTermList.add(9);
				maxLoanTermList.add(12);
				maxLoanTermList.add(18);
				maxLoanTermList.add(24);

				if (!maxLoanTermList.contains(loanCalculatorReqDto.getLoanTerm())) {
					throw new BusinessLogicException(MessageCode.INVALID_LOAN_TERM, messageSource.getMessage(MessageCode.INVALID_LOAN_TERM, null, null));
				}
			} else {
				ArrayList<Integer> minLoanTermList = new ArrayList<Integer>();
				minLoanTermList.add(6);
				minLoanTermList.add(9);
				minLoanTermList.add(12);
				minLoanTermList.add(18);
				minLoanTermList.add(24);

				if (!minLoanTermList.contains(loanCalculatorReqDto.getLoanTerm())) {
					throw new BusinessLogicException(MessageCode.INVALID_LOAN_TERM, messageSource.getMessage(MessageCode.INVALID_LOAN_TERM, null, null));
				}
			}
		} else {
			ArrayList<Integer> maxLoanTermList = new ArrayList<Integer>();
			maxLoanTermList.add(12);
			maxLoanTermList.add(18);
			maxLoanTermList.add(24);
			if (!maxLoanTermList.contains(loanCalculatorReqDto.getLoanTerm())) {
				throw new BusinessLogicException(MessageCode.INVALID_LOAN_TERM, messageSource.getMessage(MessageCode.INVALID_LOAN_TERM, null, null));
			}
		}

	}

	@RequestMapping(value = "/check-mutli-login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public CheckMultiLoginResDto checkMultiLogin(@Valid @RequestBody CheckMultiLoginReqDto checkMultiLoginReqDto) throws Exception {

		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoWithCustomerId(checkMultiLoginReqDto.getCustomerId());
		if (customerInfoDto == null) {
			throw new BusinessLogicException(MessageCode.NOT_EXIST_CUSTOMER_INFO, messageSource.getMessage(MessageCode.NOT_EXIST_CUSTOMER_INFO, null, null));
		}
		CheckMultiLoginResDto checkMultiLoginResDto = new CheckMultiLoginResDto();
		checkMultiLoginResDto.setLogoutFlag(false);
		if (!StringUtils.isEmpty(customerInfoDto.getLoginDeviceId())) {
			if (!checkMultiLoginReqDto.getLoginDeviceId().equals(customerInfoDto.getLoginDeviceId())) {
				checkMultiLoginResDto.setLogoutFlag(true);
			}
		}

		return checkMultiLoginResDto;

	}

	@RequestMapping(value = "/news-info-list", method = RequestMethod.GET)
	public List<NewsInfoResDto> getNewsInfoList() throws Exception {

		List<NewsInfoResDto> newsInfoResDtoList = newsInfoService.getNewsInfoList();
		return newsInfoResDtoList;

	}

	@RequestMapping(value = "/promotions-info-list", method = RequestMethod.GET)
	public List<PromotionsInfoResDto> getPromotionsInfoList() throws Exception {

		List<PromotionsInfoResDto> promotionsInfoResDtoList = promotionsInfoService.getPromotionsInfoList();
		return promotionsInfoResDtoList;

	}

	@RequestMapping(value = "/outlet-info-list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public OutletInfoResDto getOutletInfoList() throws Exception {
		OutletInfoResDto outletInfoResDto = null;
		try {
			URL url = new URL(outletInfoListApiLink);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new BusinessLogicException(MessageCode.SERVICE_UNAVAILABLE, conn.getResponseMessage());
			}

			JsonParser jp = new JsonParser(); // from gson
			JsonElement root = jp.parse(new InputStreamReader((InputStream) conn.getContent()));
			JsonObject rootobj = root.getAsJsonObject();
			String status = rootobj.get("status").getAsString();

			if (status.equals("SUCCESS")) {
				outletInfoResDto = new Gson().fromJson(rootobj.get("data").getAsJsonObject(), OutletInfoResDto.class);
			}
			conn.disconnect();
		} catch (ConnectException e) {
			throw new BusinessLogicException(MessageCode.SERVICE_UNAVAILABLE, e.getMessage());
		}
		outletInfoResDto.setOutletLimitMetre(outletLimitMetre);
		return outletInfoResDto;

	}

	@RequestMapping(value = "/outlet-image-file/{id:.+}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getOutletImage(@PathVariable("id") String id) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			URL url = new URL(outletImageApiLink + id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new BusinessLogicException(MessageCode.SERVICE_UNAVAILABLE, conn.getResponseMessage());
			}

			InputStream inputStream = conn.getInputStream();
			byte[] byteChunk = new byte[4096]; // Or whatever size you want to
												// read
												// in at a time.
			int n;
			while ((n = inputStream.read(byteChunk)) > 0) {
				baos.write(byteChunk, 0, n);
			}
		} catch (ConnectException e) {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(baos.toByteArray());
		}

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(baos.toByteArray());

	}

}
