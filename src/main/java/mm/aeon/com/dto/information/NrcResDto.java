package mm.aeon.com.dto.information;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NrcResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1365166840077929067L;
	private List<String> nrcTypeList;
	private List<TownshipCodeResDto> townshipCodeResDtoList;
}
