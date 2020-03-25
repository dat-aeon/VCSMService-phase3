package mm.aeon.com.services.couponinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.dao.couponinfo.CouponInfoDao;
import mm.aeon.com.dto.couponinfo.CouponInfoResDto;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CouponInfoService {

	@Autowired
	private CouponInfoDao couponInfoDao;

	public List<CouponInfoResDto> getCouponInfoListWithCustomerId(Integer customerId) {
		return couponInfoDao.getCouponInfoListWithCustomerId(customerId);
	}

	public List<String> getShopCustomerCouponPasswordWithCustomerIdAndCouponId(Integer customerId, Integer couponId) {
		return couponInfoDao.getShopCustomerCouponPasswordWithCustomerIdAndCouponId(customerId, couponId);
	}

	public void updateCustomerCouponStatusWithCustomerIdAndCouponId(Integer customerId, Integer couponId) {
		couponInfoDao.updateCustomerCouponStatusWithCustomerIdAndCouponId(customerId, couponId);
	}

}
