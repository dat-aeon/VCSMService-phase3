package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseAttachmentReuploadReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1949350677362525307L;

	@NotNull
	private Integer daApplicationInfoId;

	@NotNull
	@NotEmpty
	private String applicationNo;

	@NotNull
	@NotEmpty
	@Valid
	private List<PurchaseAttachmentReuploadDto> purchaseAttachmentReuploadDtoList;
}
