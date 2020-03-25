package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustRoomInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6683251507145788072L;
	private Integer custRoomId;
	private Integer customerId;
	private Date lastSendTime;
	private Date createdTime;
	private Short converLockFlag;

}
