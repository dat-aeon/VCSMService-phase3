package mm.aeon.com.dto.information;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HighestEducationTypeResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7951425660406089356L;

	private Integer highestEducationTypeId;

	private String name;

}
