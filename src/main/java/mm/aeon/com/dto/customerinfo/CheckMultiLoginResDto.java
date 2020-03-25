package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckMultiLoginResDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -1712045240492717723L;
	private Boolean logoutFlag;

}
