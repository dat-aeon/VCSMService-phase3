package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OTPInfoReqDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 6945605467336413091L;
	private String phoneNo;

}
