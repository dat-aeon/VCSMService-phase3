package mm.aeon.com.dto.locancalculator;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanCalculatorReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3478756008625904331L;
	private Double financeAmount;
	private Integer loanTerm;
	private boolean motorCycleLoanFlag;

}
