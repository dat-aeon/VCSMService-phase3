package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileImageResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1052969076004575283L;
	private String photoPath;

}
