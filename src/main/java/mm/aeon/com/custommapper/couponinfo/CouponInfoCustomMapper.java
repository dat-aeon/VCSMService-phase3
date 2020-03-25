package mm.aeon.com.custommapper.couponinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mm.aeon.com.dto.couponinfo.CouponInfoResDto;

public interface CouponInfoCustomMapper {

	List<CouponInfoResDto> getCouponInfoListWithCustomerId(@Param("customerId") Integer customerId);

	List<String> getShopCustomerCouponPasswordWithCustomerIdAndCouponId(@Param("customerId") Integer customerId, @Param("couponId") Integer couponId);

}
