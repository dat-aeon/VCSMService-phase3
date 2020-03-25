package mm.aeon.com.dto.customersecurityquestion;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSecurityQuestionResDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5795624179061075806L;
	private int numOfSecQues;
	private int numOfAnsChar;
	private List<CustomerSecurityQuestionDto> customerSecurityQuestionDtoList;
}
