package mm.aeon.com.dto.information;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8124825477805850300L;
	private Integer cityId;
	private String name;
	private List<TownshipInfoDto> townshipInfoList;

}
