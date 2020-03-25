package mm.aeon.com.services.mobileversionconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.dao.mobileversionconfig.MobileVersionConfigDao;
import mm.aeon.com.dto.mobileversionconfig.MobileVersionConfigDto;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MobileVersionConfigService {

	@Autowired
	private MobileVersionConfigDao mobileVersionConfigDao;

	public MobileVersionConfigDto getMobileVersionConfig(String versionNo, Integer osType) {
		return mobileVersionConfigDao.getMobileVersionConfig(versionNo, osType);
	}

}
