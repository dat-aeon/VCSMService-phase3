package mm.aeon.com.dto.information;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanTypeResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1138420215930166220L;
	private Integer loanTypeId;
	private String name;
}
