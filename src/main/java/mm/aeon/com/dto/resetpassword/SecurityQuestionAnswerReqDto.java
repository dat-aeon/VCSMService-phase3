package mm.aeon.com.dto.resetpassword;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityQuestionAnswerReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4302743724781311637L;
	private Integer secQuesId;
	private String answer;

}
