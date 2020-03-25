package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationInfoAttachmentDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7064586629378095992L;
	private Integer daApplicationInfoAttachmentId;
	private Integer daApplicationInfoId;
	private String filePath;

	@NotNull
	private Integer fileType;

	private String fileName;

	private byte[] photoByte;

	private Boolean editFlag;
	private String createdBy;
	private String updatedBy;

}
