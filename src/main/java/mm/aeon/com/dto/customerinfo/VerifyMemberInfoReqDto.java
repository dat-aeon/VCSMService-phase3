package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyMemberInfoReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1517451397705151318L;
	private Integer customerId;
	private Date dateOfBirth;
	private String nrcNo;
	private String agreementNo;

}
