package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckRegisteredCustomerResDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -811246343112507548L;

	private boolean registeredFlag;

}
