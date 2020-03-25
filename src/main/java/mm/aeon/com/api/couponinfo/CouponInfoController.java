package mm.aeon.com.api.couponinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mm.aeon.com.common.VCSMPasswordEncoder;
import mm.aeon.com.dto.couponinfo.CouponInfoReqDto;
import mm.aeon.com.dto.couponinfo.CouponInfoResDto;
import mm.aeon.com.dto.couponinfo.CouponInfoUseReqDto;
import mm.aeon.com.services.couponinfo.CouponInfoService;
import mm.aeon.com.zconfig.MessageCode;
import mm.aeon.com.zconfig.exception.BusinessLogicException;

@RestController
@RequestMapping("/coupon-info")
public class CouponInfoController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CouponInfoService couponInfoService;

	@RequestMapping(value = "/customer-coupon-info-list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public List<CouponInfoResDto> getCouponInfoListWithCustomerId(@RequestBody CouponInfoReqDto couponInfoReqDto) throws Exception {
		// check input fields
		if (StringUtils.isEmpty(couponInfoReqDto.getCustomerId())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		List<CouponInfoResDto> couponInfoResDtoList = couponInfoService.getCouponInfoListWithCustomerId(couponInfoReqDto.getCustomerId());
		return couponInfoResDtoList;

	}

	@RequestMapping(value = "/use-coupon-info", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void useCouponInfo(@RequestBody CouponInfoUseReqDto couponInfoUseReqDto) throws Exception {
		validateCouponInfoUseInput(couponInfoUseReqDto);

		// get customer coupon password list
		List<String> shopCustomerCouponPasswordList = couponInfoService.getShopCustomerCouponPasswordWithCustomerIdAndCouponId(couponInfoUseReqDto.getCustomerId(),
				couponInfoUseReqDto.getCouponId());

		// check customer coupon password list is empty
		if (shopCustomerCouponPasswordList.isEmpty()) {
			throw new BusinessLogicException(MessageCode.COUPON_REDEEM, messageSource.getMessage(MessageCode.COUPON_REDEEM, null, null));
		} else {
			// encode customer coupon password
			String customerCouponPassword = VCSMPasswordEncoder.encode(couponInfoUseReqDto.getCouponPassword());
			if (shopCustomerCouponPasswordList.contains(customerCouponPassword)) {
				couponInfoService.updateCustomerCouponStatusWithCustomerIdAndCouponId(couponInfoUseReqDto.getCustomerId(), couponInfoUseReqDto.getCouponId());
			} else {
				throw new BusinessLogicException(MessageCode.INCORRECT_PASSWORD, messageSource.getMessage(MessageCode.INCORRECT_PASSWORD, null, null));
			}

		}
	}

	private void validateCouponInfoUseInput(CouponInfoUseReqDto couponInfoUseReqDto) {
		// check input fields
		if (StringUtils.isEmpty(couponInfoUseReqDto.getCouponId()) || StringUtils.isEmpty(couponInfoUseReqDto.getCouponPassword())
				|| StringUtils.isEmpty(couponInfoUseReqDto.getCustomerId())) {

			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

	}

}
