package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompulsoryInfoDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 475493785873710367L;
	private Integer daCompulsoryInfoId;
	private Double compulsoryAmount;

}
