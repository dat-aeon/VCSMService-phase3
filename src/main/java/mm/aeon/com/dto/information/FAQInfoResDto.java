package mm.aeon.com.dto.information;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FAQInfoResDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3977541755454570602L;
	private Integer faqId;
	private String questionMM;
	private String questionEN;
	private String answerMM;
	private String answerEN;
	private Integer categoryId;
}
