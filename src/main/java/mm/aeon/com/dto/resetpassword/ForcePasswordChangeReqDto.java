package mm.aeon.com.dto.resetpassword;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForcePasswordChangeReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3208785887200385689L;
	private String phoneNo;
	private String nrcNo;
	private String password;

}
