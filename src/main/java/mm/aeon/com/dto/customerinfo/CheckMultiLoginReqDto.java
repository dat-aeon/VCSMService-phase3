package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckMultiLoginReqDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 3581859988513888327L;

	@NotNull
	private Integer customerId;

	@NotNull
	@NotBlank
	private String loginDeviceId;

}
