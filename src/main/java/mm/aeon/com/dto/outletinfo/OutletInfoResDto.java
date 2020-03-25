package mm.aeon.com.dto.outletinfo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutletInfoResDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4792861201253593805L;
	private Double outletLimitMetre;
	private List<OutletInfoDto> outletInfoList;

}
