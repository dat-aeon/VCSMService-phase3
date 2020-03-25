package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationInquireResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8920352178719710136L;
	private List<ApplicationInfoDto> applicationInfoDtoList;
	private int totalSize;

}
