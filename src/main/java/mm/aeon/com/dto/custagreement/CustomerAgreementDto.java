package mm.aeon.com.dto.custagreement;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAgreementDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -3121041980077755823L;
	private Integer custAgreementId;
	private Integer importCustomerId;
	private String agreementNo;
	private Short qrShow;
	private Short financialStatus;
	private Double financialAmt;
	private Short financialTerm;
	private Integer daApplicationInfoId;
	private String applicationNo;
	private String encodeStringForQr;
	private Date lastPaymentDate;
}
