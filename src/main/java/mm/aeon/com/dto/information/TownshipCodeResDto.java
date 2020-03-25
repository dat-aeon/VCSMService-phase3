package mm.aeon.com.dto.information;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TownshipCodeResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2524722377809449863L;
	private Short stateId;
	private List<String> townshipCodeList;
}
