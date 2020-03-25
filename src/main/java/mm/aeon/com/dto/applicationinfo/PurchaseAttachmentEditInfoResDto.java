package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseAttachmentEditInfoResDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -5954876024951342721L;

	private Integer daPurchaseInfoAttachmentId;
	private String filePath;
	private Integer fileType;

}
