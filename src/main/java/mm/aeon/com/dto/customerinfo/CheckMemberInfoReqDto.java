package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckMemberInfoReqDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 378857481908304657L;
	private String name;
	private Date dateOfBirth;
	private String nrcNo;
	private String phoneNo;
	private String password;

}
