package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3766406002976761441L;

	@NotNull
	private Integer daPurchaseInfoId;

	@NotNull
	private Integer daApplicationInfoId;

	@NotNull
	private Integer customerId;

	private String agreementNo;
	private Date purchaseDate;

	@NotNull
	private Integer outletId;

	@NotNull
	@NotBlank
	@Size(max = 256)
	private String outletName;

	@Size(max = 256)
	private String invoiceNo;

	@NotNull
	private Integer agentId;

	@NotNull
	@NotBlank
	@Size(max = 256)
	private String agentName;

	private Double financeAmount;

	private Integer financeTerm;

	private Double processingFees;

	private Double compulsoryAmount;

	private Integer status;
	private Double settlementAmount;

	private List<PurchaseInfoAttachmentDto> purchaseInfoAttachmentDtoList;
	private String createdBy;
	private String updatedBy;
	private Boolean delFlag;

	private String purchaseLocation;

	@Valid
	@NotNull
	@NotEmpty
	private List<PurchaseInfoProductDto> purchaseInfoProductDtoList;

}
