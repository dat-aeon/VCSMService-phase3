package mm.aeon.com.dto.appusageinfo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUsageDetailDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -677760973431557208L;
	private Integer appUsageDetailId;
	private Integer appUsageId;
	private Date startUsageDateTime;
	private Date endUsageDateTime;

}
