package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentApplicationStatusInfoReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3872826898261956672L;

	@NotNull
	@NotBlank
	@Size(max = 50)
	private String applicationNo;

}
