package mm.aeon.com.dto.resetpassword;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmSecurityQuestionAnswerReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4202756278729287406L;
	private String phoneNo;
	private String nrcNo;
	private Integer customerId;
	private List<SecurityQuestionAnswerReqDto> securityQuestionAnswerReqDtoList;

}
