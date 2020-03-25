package mm.aeon.com.dto.resetpassword;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckAccountLockReqDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 9152493193650155362L;
	private String phoneNo;
	private String nrcNo;

}
