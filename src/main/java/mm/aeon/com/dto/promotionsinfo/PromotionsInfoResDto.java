package mm.aeon.com.dto.promotionsinfo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionsInfoResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7951080915416081052L;
	private Integer promotionsInfoId;
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
	private String announcementUrl;

}
