package mm.aeon.com.dao.mobileversionconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.custommapper.mobileversionconfig.MobileVersionConfigCustomMapper;
import mm.aeon.com.dto.mobileversionconfig.MobileVersionConfigDto;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class MobileVersionConfigDao {

	@Autowired
	private MobileVersionConfigCustomMapper mobileVersionConfigCustomMapper;

	public MobileVersionConfigDto getMobileVersionConfig(String versionNo, Integer osType) {
		MobileVersionConfigDto mobileVersionConfigDto = mobileVersionConfigCustomMapper.getMobileVersionConfig(versionNo, osType);
		return mobileVersionConfigDto;
	}

}
