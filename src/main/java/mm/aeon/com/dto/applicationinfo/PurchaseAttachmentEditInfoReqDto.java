package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseAttachmentEditInfoReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2617732897161373122L;

	@NotNull
	private Integer daPurchaseInfoId;

}
