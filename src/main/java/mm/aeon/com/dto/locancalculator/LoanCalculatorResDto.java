package mm.aeon.com.dto.locancalculator;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanCalculatorResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8616362673015474531L;
	private Double processingFees;
	private Double totalRepayment;
	private Double firstPayment;
	private Double monthlyPayment;
	private Double lastPayment;
	private Double conSaving;
	private Double totalConSaving;

}
