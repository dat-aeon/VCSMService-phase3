package mm.aeon.com.dto.resetpassword;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityQuestionResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1943628835315380746L;
	private int numOfSecQues;
	private int numOfAnsChar;
	private List<SecurityQuestionDto> securityQuestionDtoList;

}
