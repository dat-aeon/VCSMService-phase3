package mm.aeon.com.dto.resetpassword;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7681410465291776062L;
	private Integer customerId;
	private Integer userTypeId;
	private String password;

}
