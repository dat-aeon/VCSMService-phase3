package mm.aeon.com.dto.information;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FAQInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 476350535264694435L;
	private Integer faqCategoryId;
	private String faqCategoryEng;
	private String faqCategoryMyn;
	private String faqCategory;
	private List<FAQInfoResDto> faqInfoResDtoList;

}
