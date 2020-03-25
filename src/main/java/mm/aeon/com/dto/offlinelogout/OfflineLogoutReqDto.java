package mm.aeon.com.dto.offlinelogout;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfflineLogoutReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8507396278351726771L;
	private Integer customerId;
	private Date logoutTime;

}
