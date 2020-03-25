package mm.aeon.com.dto.outletinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutletInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8119451009059806288L;
	private Integer outletId;
	private String outletName;
	private String outletAddress;
	private String imagePath;
	private String longitude;
	private String latitude;
	private String phoneNo;
	private Boolean isAeon;
	private Integer roleId;

}
