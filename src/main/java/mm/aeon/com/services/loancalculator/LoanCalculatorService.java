package mm.aeon.com.services.loancalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.dao.loancalculator.LoanCalculatorDao;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class LoanCalculatorService {

	@Autowired
	private LoanCalculatorDao LoanCalculatorDao;

	public Double getInterestRate() {
		return LoanCalculatorDao.getInterestRate();
	}

	public Double getCompulsoryAmount() {
		return LoanCalculatorDao.getCompulsoryAmount();
	}

}
