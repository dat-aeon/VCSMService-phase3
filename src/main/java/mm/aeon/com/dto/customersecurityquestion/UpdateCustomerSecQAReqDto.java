package mm.aeon.com.dto.customersecurityquestion;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mm.aeon.com.dto.resetpassword.SecurityQuestionAnswerReqDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerSecQAReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3642216815815376555L;
	private Integer customerId;
	private String password;
	private List<SecurityQuestionAnswerReqDto> securityQuestionAnswerReqDtoList;

}
