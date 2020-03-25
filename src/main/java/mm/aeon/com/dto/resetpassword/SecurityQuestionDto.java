package mm.aeon.com.dto.resetpassword;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityQuestionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9030371706958158237L;
	private int secQuestionId;
	private String questionMM;
	private String questionEN;

}
