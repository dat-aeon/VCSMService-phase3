package mm.aeon.com.dao.loancalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.custommapper.loancalculator.LoanCalculatorCustomMapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class LoanCalculatorDao {

	@Autowired
	private LoanCalculatorCustomMapper loanCalculatorCustomMapper;

	public Double getInterestRate() {
		Double interestRate = loanCalculatorCustomMapper.getInterestRate();
		return interestRate;
	}

	public Double getCompulsoryAmount() {
		Double compulsoryAmount = loanCalculatorCustomMapper.getCompulsoryAmount();
		return compulsoryAmount;
	}

}
