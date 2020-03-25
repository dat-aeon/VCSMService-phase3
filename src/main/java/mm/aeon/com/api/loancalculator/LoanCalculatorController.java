package mm.aeon.com.api.loancalculator;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mm.aeon.com.common.LoanCalculator;
import mm.aeon.com.dto.locancalculator.LoanCalculatorReqDto;
import mm.aeon.com.dto.locancalculator.LoanCalculatorResDto;
import mm.aeon.com.zconfig.MessageCode;
import mm.aeon.com.zconfig.exception.BusinessLogicException;

@RestController
@RequestMapping("/loan-calculator")
public class LoanCalculatorController {

	@Autowired
	private MessageSource messageSource;

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

	// public static void main(String[] args) {
	// double totalProductPrice = 200000;
	// boolean motorcycleLoanFlag = false;
	// int loanTerm = 9;
	// double processingFees;
	// double totalRepayment;
	// double firstPayment;
	// double monthlyPayment;
	// double financeAmount;
	// double totalInterest;
	// double downPayment = 0;
	// double promoDiscount = 0;
	// double insPremium = 0;
	// double vatAmount = 0;
	// double financeAmountForPSG = 0;
	// boolean paymentOnSaleDate = true;
	// double interestRateMonthly = 1.4;
	// double totalInterestForPSG = 0;
	// double monthlyInstallmentForPSG = 0;
	// int monthlyInstallmentRound = -2;
	// double monthlyInstallment = 0;
	// double conSaving = 1000;
	// double totalConSaving = 0;
	// int firstInstallmentRound = -2;
	// double initialPaymentForPSG = 0;
	// double firstPaymentForPSG = 0;
	// double lastPayment = 0;
	//
	// processingFees = calculateProcessingFees(motorcycleLoanFlag,
	// totalProductPrice);
	// financeAmount = calculateFinanceAmountForPSG(totalProductPrice,
	// downPayment, promoDiscount, insPremium, vatAmount, financeAmountForPSG,
	// paymentOnSaleDate);
	// totalInterest = calculateTotalInterest(financeAmount, loanTerm,
	// interestRateMonthly, totalInterestForPSG);
	// totalRepayment = calculateTotalRepayment(financeAmount, totalInterest);
	// monthlyInstallment = calculateMonthlyInstallment(totalRepayment,
	// monthlyInstallmentForPSG, loanTerm, monthlyInstallmentRound);
	// totalConSaving = calculateTotalConSaving(conSaving, loanTerm);
	// firstPaymentForPSG = calculateFirstPaymentForPSG(initialPaymentForPSG,
	// paymentOnSaleDate);
	// firstPayment = calculateFirstPayment(initialPaymentForPSG,
	// totalRepayment, monthlyInstallment, loanTerm, firstInstallmentRound,
	// firstPaymentForPSG);
	// monthlyPayment = monthlyInstallment;
	// lastPayment = calculateLastPayment(totalProductPrice, firstPayment,
	// monthlyPayment, totalInterest, loanTerm, monthlyInstallment);
	// System.out.println("Processing Fees => " + processingFees);
	// System.out.println("Finance Amount => " + financeAmount);
	// System.out.println("Total Interest => " + totalInterest);
	// System.out.println("Total Repayment => " + totalRepayment);
	// System.out.println("Monthly Installment => " + monthlyInstallment);
	// System.out.println("Total Con Saving => " + totalConSaving);
	// System.out.println("First Payment For SG => " + firstPaymentForPSG);
	// System.out.println("First Payment => " + firstPayment);
	// System.out.println("Monthly Payment => " + monthlyPayment);
	// System.out.println("Last Payment => " + lastPayment);
	//
	// }

}
