package mm.aeon.com.dto.information;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TownshipInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5785934605140710408L;
	private Integer townshipId;
	private String name;

}
