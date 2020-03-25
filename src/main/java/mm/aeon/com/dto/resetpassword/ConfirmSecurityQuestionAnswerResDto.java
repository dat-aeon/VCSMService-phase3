package mm.aeon.com.dto.resetpassword;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmSecurityQuestionAnswerResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -803331766896882650L;
	private Integer customerId;
	private Integer userTypeId;

}
