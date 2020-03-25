package mm.aeon.com.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileVersionConfigResBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5304567853887809522L;
	private String forceUpdFlag;
	private String statusCode;
	private String statusMessage;

}
