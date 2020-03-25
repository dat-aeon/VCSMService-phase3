package mm.aeon.com.dto.newsinfo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsInfoResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2335617236230135540L;
	private Integer newsInfoId;
	private String titleEng;
	private String titleMyn;
	private String contentEng;
	private String contentMyn;
	private Date displayDate;
	private Date publishedFromDate;
	private Date publishedToDate;
	private String imagePath;
	private String longitude;
	private String latitude;
	private String newsUrl;

}
