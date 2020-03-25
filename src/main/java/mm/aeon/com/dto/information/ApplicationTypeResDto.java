package mm.aeon.com.dto.information;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationTypeResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5198208499467524027L;
	private Integer applicationTypeId;
	private String name;
}
