package mm.aeon.com.dto.freemessage;

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
public class FreeMessageRoomSyncReqDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -1383707347558562422L;

	@NotNull
	@NotBlank
	@Size(max = 20)
	private String phoneNo;

}
