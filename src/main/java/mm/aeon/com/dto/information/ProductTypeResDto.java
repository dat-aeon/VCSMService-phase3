package mm.aeon.com.dto.information;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypeResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8210704856370111013L;
	private Integer productTypeId;
	private String name;
}
