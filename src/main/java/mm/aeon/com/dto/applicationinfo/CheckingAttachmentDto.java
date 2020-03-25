package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckingAttachmentDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5849395121049939186L;
	private Integer daCheckingAttachmentId;

	@NotNull
	private Integer daPurchaseInfoId;

	@NotNull
	@NotEmpty
	@Size(max = 50)
	private String agreementNo;

	@NotNull
	@NotEmpty
	private String oldFilePath;

	private String newFilePath;

	private byte[] newFileByte;

	@NotNull
	private Integer fileType;
	private Integer status;

	private String rejectComment;

}
