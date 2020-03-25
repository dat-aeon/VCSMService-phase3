package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseInfoProductDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -7088663148504880722L;

	private Integer daPurchaseInfoProductId;

	@NotNull
	private Integer daPurchaseInfoId;

	@NotNull
	private Integer daLoanTypeId;

	@NotNull
	private Integer daProductTypeId;

	private String productDescription;

	@NotNull
	@NotBlank
	@Size(max = 256)
	private String brand;

	@NotNull
	@NotBlank
	@Size(max = 256)
	private String model;

	@NotNull
	private Double price;

	private Double cashDownAmount;

	private String createdBy;

	private String updatedBy;

	private Boolean delFlag;
}
