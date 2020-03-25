package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseApplicationInfoDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7393801698575807484L;
	private Integer daApplicationInfoId;
	private Integer customerId;
	private String name;
	private String customerNo;
	private String agreementNo;
	private String nrcNo;
	private Double financeAmount;
	private Integer financeTerm;
	private Integer daLoanTypeId;
	private Double settlementAmount;
	private Double compulsorySaving;
	private Double processingFees;
	private Integer applicationStatus;
	private InterestInfoDto interestInfoDto;
	private CompulsoryInfoDto compulsoryInfoDto;
}
