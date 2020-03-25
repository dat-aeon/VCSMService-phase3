package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLoanInformationReqDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7825667786513368959L;

	@NotNull
	private Integer daApplicationInfoId;

	@NotNull
	private Double updateFinanceAmount;

	@NotNull
	private Integer updateLoanTerm;

}
