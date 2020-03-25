package mm.aeon.com.api.offlinelogout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mm.aeon.com.dto.appusageinfo.AppUsageDetailDto;
import mm.aeon.com.dto.offlinelogout.OfflineLogoutReqDto;
import mm.aeon.com.services.appusageinfo.AppUsageInfoService;
import mm.aeon.com.zconfig.MessageCode;
import mm.aeon.com.zconfig.exception.BusinessLogicException;

@RestController
@RequestMapping("/offline-logout")
public class OfflineLogoutController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AppUsageInfoService appUsageInfoService;

	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void updateAppUsageDetailForOfflineLogout(@RequestBody OfflineLogoutReqDto offlineLogoutReqDto) throws Exception {
		// check input fields
		if (StringUtils.isEmpty(offlineLogoutReqDto.getCustomerId()) || StringUtils.isEmpty(offlineLogoutReqDto.getLogoutTime())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}
		AppUsageDetailDto appUsageDetailDto = appUsageInfoService.getLastAppUsageDetailWithCustomerId(offlineLogoutReqDto.getCustomerId());
		if (appUsageDetailDto != null) {
			appUsageDetailDto.setEndUsageDateTime(offlineLogoutReqDto.getLogoutTime());
			appUsageInfoService.updateAppUsageDetail(appUsageDetailDto);
		}

	}

}
