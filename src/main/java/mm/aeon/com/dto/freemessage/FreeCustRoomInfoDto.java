package mm.aeon.com.dto.freemessage;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreeCustRoomInfoDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 9116223889579991855L;
	private Integer freeCustRoomInfoId;
	private Integer freeCustomerInfoId;
	private Date lastSendTime;
	private Date createdTime;
	private Short converLockFlag;

}
