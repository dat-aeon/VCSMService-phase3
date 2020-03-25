package mm.aeon.com.dto.information;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatAutoReplyMessageResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5669456553025019967L;

	private String message;

}
