package mm.aeon.com.dto.information;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemLabelResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1202199419308571672L;
	private Integer daItemLabelId;
	private String itemLabelCode;
	private String itemLabelEng;
	private String itemLabelMym;
	private String category;

}
