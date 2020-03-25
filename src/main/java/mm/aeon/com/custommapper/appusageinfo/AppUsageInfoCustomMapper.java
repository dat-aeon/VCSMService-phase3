package mm.aeon.com.custommapper.appusageinfo;

import org.apache.ibatis.annotations.Param;

import mm.aeon.com.dto.appusageinfo.AppUsageDetailDto;
import mm.aeon.com.dto.appusageinfo.AppUsageInfoDto;
import mm.aeon.com.zgen.entity.AppUsageInfo;

public interface AppUsageInfoCustomMapper {

	Integer insertAppUsageInfo(AppUsageInfo appUsageInfo);

	AppUsageInfoDto getAppUsageInfoWithCustomerId(@Param("customerId") Integer customerId);

	AppUsageDetailDto getLastAppUsageDetailWithCustomerId(@Param("customerId") Integer customerId);

}
