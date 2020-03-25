package mm.aeon.com.api.mobileversionconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.common.CommonURL;
import mm.aeon.com.dto.mobileversionconfig.MobileVersionConfigDto;
import mm.aeon.com.dto.mobileversionconfig.MobileVersionConfigReqDto;
import mm.aeon.com.dto.mobileversionconfig.MobileVersionConfigResDto;
import mm.aeon.com.services.mobileversionconfig.MobileVersionConfigService;
import mm.aeon.com.zconfig.MessageCode;
import mm.aeon.com.zconfig.exception.BusinessLogicException;

@RestController
@RequestMapping("/mobile-version-config")
public class MobileVersionConfigController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private MobileVersionConfigService mobileVersionConfigService;

	@RequestMapping(value = "/check-update-status", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public MobileVersionConfigResDto checkMobileVersionUpdateStatus(@RequestBody MobileVersionConfigReqDto mobileVersionConfigReqDto) throws Exception {
		// check input fields
		if (StringUtils.isEmpty(mobileVersionConfigReqDto.getVersionNo()) || StringUtils.isEmpty(mobileVersionConfigReqDto.getOsType())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		MobileVersionConfigResDto mobileVersionConfigResDto = new MobileVersionConfigResDto();
		MobileVersionConfigDto mobileVersionConfigDto = mobileVersionConfigService.getMobileVersionConfig(mobileVersionConfigReqDto.getVersionNo(),
				mobileVersionConfigReqDto.getOsType());
		if (mobileVersionConfigDto != null) {
			mobileVersionConfigResDto.setId(mobileVersionConfigDto.getId());
			mobileVersionConfigResDto.setVersionUpdateInfo(mobileVersionConfigDto.getVersionUpdateInfo());
			// check must update or not
			if (mobileVersionConfigDto.getForceUpdFlag() == CommonConstants.FLAG_OFF) {
				mobileVersionConfigResDto.setForceUpdFlag(CommonConstants.MUST_UPDATE);
			} else {
				mobileVersionConfigResDto.setForceUpdFlag(CommonConstants.SHOULD_UPDATE);
			}
		} else {
			mobileVersionConfigResDto.setForceUpdFlag(CommonConstants.SHOULD_UPDATE);
		}
		if (mobileVersionConfigReqDto.getOsType() == CommonConstants.OS_TYPE_IOS) {
			mobileVersionConfigResDto.setAppStoreUrl(CommonURL.APP_STORE_URL_V2);
		}

		return mobileVersionConfigResDto;
	}

}
