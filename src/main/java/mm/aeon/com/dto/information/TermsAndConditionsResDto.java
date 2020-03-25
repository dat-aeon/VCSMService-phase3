package mm.aeon.com.dto.information;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TermsAndConditionsResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3099012845989249986L;
	private String descriptionEng;
	private String descriptionMyn;
}
