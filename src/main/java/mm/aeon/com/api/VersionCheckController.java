package mm.aeon.com.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.common.CommonURL;
import mm.aeon.com.dto.MobileVersionConfigReqBean;
import mm.aeon.com.dto.MobileVersionConfigResBean;
import mm.aeon.com.dto.mobileversionconfig.MobileVersionConfigDto;
import mm.aeon.com.services.mobileversionconfig.MobileVersionConfigService;
import mm.aeon.com.zconfig.aop.LoggingAspect;

@RestController
@RequestMapping("/versionconfig")
public class VersionCheckController {

	@Autowired
	MobileVersionConfigService mobileVersionConfigService;

	private Logger logger = LogManager.getLogger(LoggingAspect.class);

	@RequestMapping(value = "/update_status")
	public MobileVersionConfigResBean checkUpdateStatus(@RequestBody(required = false) String curVerInfo) {

		logger.info("JSON : " + curVerInfo);
		MobileVersionConfigResBean mobileVerConfigResBean;
		MobileVersionConfigReqBean mobileVerConfigReqBean;

		mobileVerConfigResBean = new MobileVersionConfigResBean();
		mobileVerConfigReqBean = new Gson().fromJson(curVerInfo, MobileVersionConfigReqBean.class);

		try {
			MobileVersionConfigDto mobileVersionConfigDto = mobileVersionConfigService.getMobileVersionConfig(mobileVerConfigReqBean.getCurVersion(), null);
			mobileVerConfigResBean.setStatusCode(CommonConstants.STATUS_200);
			mobileVerConfigResBean.setStatusMessage(CommonURL.APP_STORE_URL_V1);

			if (mobileVersionConfigDto != null) {

				int updateStatus = mobileVersionConfigDto.getForceUpdFlag();

				logger.info("STATUS : " + updateStatus);
				if (updateStatus == CommonConstants.FLAG_OFF) {
					logger.info("----------------- | Version Must Update :" + updateStatus);
					mobileVerConfigResBean.setForceUpdFlag(CommonConstants.MUST_UPDATE);
					return mobileVerConfigResBean;
				} else {
					logger.info("----------------- | Version Should Update: " + updateStatus);
					mobileVerConfigResBean.setForceUpdFlag(CommonConstants.SHOULD_UPDATE);
					return mobileVerConfigResBean;
				}

			} else {
				logger.info("----------------- | Version don't have on DB.");
				mobileVerConfigResBean.setForceUpdFlag(CommonConstants.SHOULD_UPDATE);
				return mobileVerConfigResBean;
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.info("----------------- | Version Update Error" + e.getMessage());
			mobileVerConfigResBean.setForceUpdFlag(CommonConstants.ERROR);
			mobileVerConfigResBean.setStatusCode(CommonConstants.STATUS_500);
			mobileVerConfigResBean.setStatusMessage(e.getMessage());
			return mobileVerConfigResBean;
		}
	}
}