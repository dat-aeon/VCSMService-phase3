package mm.aeon.com.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileVersionConfigReqBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3864321895410596187L;
	private String curVersion;

}
