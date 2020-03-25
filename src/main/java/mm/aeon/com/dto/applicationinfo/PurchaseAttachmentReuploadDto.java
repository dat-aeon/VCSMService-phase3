package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseAttachmentReuploadDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -8697451923369980105L;

	@NotNull
	private String daPurchaseInfoAttachmentId;

	@NotNull
	@NotEmpty
	private String filePath;

	@NotNull
	private Integer fileType;

	@NotNull
	@NotEmpty
	private String fileName;

}
