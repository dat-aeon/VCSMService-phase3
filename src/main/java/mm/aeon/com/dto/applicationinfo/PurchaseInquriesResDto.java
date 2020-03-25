package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseInquriesResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 833951074936958277L;
	private Integer daPurchaseInfoId;
	private Integer daApplicationInfoId;
	private String applicationNo;
	private String outletName;
	private String agentName;
	private String agreementNo;
	private Date purchaseDate;
	private Double financeAmount;
	private Integer financeTerm;
	private Date settlementDate;
	private Double compulsoryAmount;
	private Double processingFees;
	private Double settlementAmount;
	private Integer applicationStatus;

}
