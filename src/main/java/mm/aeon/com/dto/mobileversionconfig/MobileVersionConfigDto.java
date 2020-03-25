package mm.aeon.com.dto.mobileversionconfig;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileVersionConfigDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5994071419658143054L;
	private Integer id;
	private String versionNo;
	private Short forceUpdFlag;
	private String versionUpdateInfo;

}
