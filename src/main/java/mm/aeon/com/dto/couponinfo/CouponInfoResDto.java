package mm.aeon.com.dto.couponinfo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponInfoResDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 3075289709122687630L;
	private Integer couponId;
	private String couponCode;
	private String couponNameMM;
	private String couponNameEN;
	private String descriptionMM;
	private String descriptionEN;
	private Double couponAmount;
	private Double goodsPrice;
	private Date startTime;
	private Date expiredTime;
	private String discountUnit;
	private String unuseImagePath;
	private String useImagePath;
	private Integer totalNo;
	private String specialEventMM;
	private String specialEventEN;
	private Integer customerId;
	private String status;

}
