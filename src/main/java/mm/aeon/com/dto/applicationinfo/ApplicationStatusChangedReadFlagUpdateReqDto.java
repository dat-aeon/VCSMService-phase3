package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationStatusChangedReadFlagUpdateReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6510074109280972042L;

	@NotNull
	private Integer customerId;

}
