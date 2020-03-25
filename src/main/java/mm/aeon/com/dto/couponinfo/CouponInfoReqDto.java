package mm.aeon.com.dto.couponinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponInfoReqDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6568366114177453304L;
	private Integer customerId;

}
