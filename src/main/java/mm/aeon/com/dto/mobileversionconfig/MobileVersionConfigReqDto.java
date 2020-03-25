package mm.aeon.com.dto.mobileversionconfig;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileVersionConfigReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2418306442918134361L;
	private String versionNo;
	private Integer osType;

}
