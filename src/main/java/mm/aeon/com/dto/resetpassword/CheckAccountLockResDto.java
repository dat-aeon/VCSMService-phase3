package mm.aeon.com.dto.resetpassword;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckAccountLockResDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5611676703812753079L;
	private String phoneNo;
	private Short lockStatus;
	private String hotlinePhone;
	private Integer customerSecurityQuestionCount;

}
