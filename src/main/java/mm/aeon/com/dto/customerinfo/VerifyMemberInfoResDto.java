package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyMemberInfoResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5178149041490631094L;
	private String verifyStatus; // "VALID" | "NOT_VALID"
	private String customerNo;

}
