package mm.aeon.com.dao.appusageinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.common.BeanConverter;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.custommapper.appusageinfo.AppUsageInfoCustomMapper;
import mm.aeon.com.dto.appusageinfo.AppUsageDetailDto;
import mm.aeon.com.dto.appusageinfo.AppUsageInfoDto;
import mm.aeon.com.zgen.entity.AppUsageDetail;
import mm.aeon.com.zgen.entity.AppUsageInfo;
import mm.aeon.com.zgen.mapper.AppUsageDetailMapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class AppUsageInfoDao {

	@Autowired
	private AppUsageInfoCustomMapper appUsageInfoCustomMapper;

	@Autowired
	private AppUsageDetailMapper appUsageDetailMapper;

	@Autowired
	private BeanConverter beanConverter;

	public Integer insertAppUsageInfo(AppUsageInfoDto appUsageInfoDto) {
		Integer appUsageId;
		AppUsageInfo appUsageInfo = beanConverter.convert(appUsageInfoDto, AppUsageInfo.class);
		appUsageInfo.setCreatedBy(appUsageInfoDto.getCustomerId().toString());
		appUsageInfo.setCreatedTime(CommonUtil.getCurrentTime());
		appUsageInfoCustomMapper.insertAppUsageInfo(appUsageInfo);
		appUsageId = appUsageInfo.getAppUsageId();
		return appUsageId;
	}

	public AppUsageInfoDto getAppUsageInfoWithCustomerId(Integer customerId) {
		AppUsageInfoDto appUsageInfoDto = appUsageInfoCustomMapper.getAppUsageInfoWithCustomerId(customerId);
		return appUsageInfoDto;
	}

	public AppUsageDetailDto getLastAppUsageDetailWithCustomerId(Integer customerId) {
		AppUsageDetailDto appUsageDetailDto = appUsageInfoCustomMapper.getLastAppUsageDetailWithCustomerId(customerId);
		return appUsageDetailDto;
	}

	public void insertAppUsageDetail(AppUsageDetailDto appUsageDetailDto) {
		AppUsageDetail appUsageDetail = beanConverter.convert(appUsageDetailDto, AppUsageDetail.class);
		appUsageDetailMapper.insertSelective(appUsageDetail);
	}

	public void updateAppUsageDetail(AppUsageDetailDto appUsageDetailDto) {
		AppUsageDetail appUsageDetail = beanConverter.convert(appUsageDetailDto, AppUsageDetail.class);
		appUsageDetailMapper.updateByPrimaryKeySelective(appUsageDetail);
	}

}
