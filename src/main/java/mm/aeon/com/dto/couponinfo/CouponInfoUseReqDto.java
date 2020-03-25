package mm.aeon.com.dto.couponinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponInfoUseReqDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -411953836688499691L;
	private Integer customerId;
	private Integer couponId;
	private String couponPassword;

}
