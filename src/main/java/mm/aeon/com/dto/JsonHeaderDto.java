package mm.aeon.com.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonHeaderDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8921145791690125531L;
	private String status;
	private String identifier;
	private String gateway;
	private String messageId;

}
