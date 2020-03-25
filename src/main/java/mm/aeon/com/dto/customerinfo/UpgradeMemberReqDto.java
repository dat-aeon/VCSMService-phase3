package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpgradeMemberReqDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7907647804275937732L;
	private Integer customerId;
	private String customerNo;
	private byte[] photoByte;

}
