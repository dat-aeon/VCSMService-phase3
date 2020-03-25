package mm.aeon.com.services.appusageinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.dao.appusageinfo.AppUsageInfoDao;
import mm.aeon.com.dto.appusageinfo.AppUsageDetailDto;
import mm.aeon.com.dto.appusageinfo.AppUsageInfoDto;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AppUsageInfoService {

	@Autowired
	private AppUsageInfoDao appUsageInfoDao;

	public AppUsageInfoDto getAppUsageInfoWithCustomerId(Integer customerId) {
		return appUsageInfoDao.getAppUsageInfoWithCustomerId(customerId);
	}

	public AppUsageDetailDto getLastAppUsageDetailWithCustomerId(Integer customerId) {
		return appUsageInfoDao.getLastAppUsageDetailWithCustomerId(customerId);
	}

	public void updateAppUsageDetail(AppUsageDetailDto appUsageDetailDto) {
		appUsageInfoDao.updateAppUsageDetail(appUsageDetailDto);
	}

}
