package mm.aeon.com.dto.mobileversionconfig;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileVersionConfigResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -183581487634381428L;
	private Integer id;
	private String forceUpdFlag;
	private String appStoreUrl;
	private String versionUpdateInfo;

}
