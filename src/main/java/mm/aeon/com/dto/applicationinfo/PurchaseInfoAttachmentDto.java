package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseInfoAttachmentDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6552990427574976593L;
	private Integer daPurchaseInfoAttachmentId;
	private Integer daPurchaseInfoId;
	private String filePath;
	private String fileName;

	@NotNull
	private Integer fileType;

	@NotNull
	@NotEmpty
	private byte[] photoByte;

	private String createdBy;
	private String updatedBy;

}
