package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerIdResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3580434876997436351L;
	private Integer customerId;

}
