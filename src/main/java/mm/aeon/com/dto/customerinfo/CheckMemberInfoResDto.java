package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckMemberInfoResDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -4345980817437765709L;
	private String memberStatus;
	private String hotlinePhone;
	private String memberPhoneNo;

}
