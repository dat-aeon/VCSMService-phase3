package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationStatusChangedCountReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1532470401603892927L;

	@NotNull
	private Integer customerId;

}
