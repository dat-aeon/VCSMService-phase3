package mm.aeon.com.dao.couponinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.custommapper.couponinfo.CouponInfoCustomMapper;
import mm.aeon.com.dto.couponinfo.CouponInfoResDto;
import mm.aeon.com.zgen.entity.CustomerCoupon;
import mm.aeon.com.zgen.entity.CustomerCouponExample;
import mm.aeon.com.zgen.mapper.CustomerCouponMapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class CouponInfoDao {

	@Autowired
	private CouponInfoCustomMapper couponInfoCustomMapper;

	@Autowired
	private CustomerCouponMapper customerCouponMapper;

	public List<CouponInfoResDto> getCouponInfoListWithCustomerId(Integer customerId) {
		List<CouponInfoResDto> couponInfoResDtoList = couponInfoCustomMapper.getCouponInfoListWithCustomerId(customerId);
		return couponInfoResDtoList;
	}

	public List<String> getShopCustomerCouponPasswordWithCustomerIdAndCouponId(Integer customerId, Integer couponId) {
		List<String> shopCustomerCouponPasswordList = couponInfoCustomMapper.getShopCustomerCouponPasswordWithCustomerIdAndCouponId(customerId, couponId);
		return shopCustomerCouponPasswordList;
	}

	public void updateCustomerCouponStatusWithCustomerIdAndCouponId(Integer customerId, Integer couponId) {
		CustomerCoupon customerCoupon = new CustomerCoupon();
		customerCoupon.setStatus(CommonConstants.COUPON_USED_STATUS);
		customerCoupon.setUpdatedBy(customerId.toString());
		customerCoupon.setUpdatedTime(CommonUtil.getCurrentTime());
		CustomerCouponExample customerCouponExample = new CustomerCouponExample();
		customerCouponExample.createCriteria().andCustomerIdEqualTo(customerId).andCouponIdEqualTo(couponId);
		customerCouponMapper.updateByExampleSelective(customerCoupon, customerCouponExample);
	}
}
