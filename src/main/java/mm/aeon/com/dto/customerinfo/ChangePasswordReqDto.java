package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7625502795687665326L;
	private Integer customerId;
	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;

}
