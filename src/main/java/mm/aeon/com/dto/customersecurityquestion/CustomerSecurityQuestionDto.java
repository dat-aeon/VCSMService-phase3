package mm.aeon.com.dto.customersecurityquestion;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSecurityQuestionDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -957422445401663490L;
	private Integer custSecQuesId;
	private Integer secQuesId;
	private Integer customerId;
	private String questionMyan;
	private String questionEng;
	private String answer;

}
