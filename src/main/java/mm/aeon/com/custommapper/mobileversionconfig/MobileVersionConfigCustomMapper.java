package mm.aeon.com.custommapper.mobileversionconfig;

import org.apache.ibatis.annotations.Param;

import mm.aeon.com.dto.mobileversionconfig.MobileVersionConfigDto;

public interface MobileVersionConfigCustomMapper {

	MobileVersionConfigDto getMobileVersionConfig(@Param("versionNo") String versionNo, @Param("osType") Integer osType);

}
