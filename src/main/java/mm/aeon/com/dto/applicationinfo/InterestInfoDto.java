package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8572322355524737743L;
	private Integer daInterestInfoId;
	private Double interestRate;

}
