package mm.aeon.com.dao.emergencycontactinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import mm.aeon.com.common.BeanConverter;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.custommapper.emergencycontactinfo.EmergencyContactInfoCustomMapper;
import mm.aeon.com.dto.applicationinfo.EmergencyContactInfoDto;
import mm.aeon.com.zgen.entity.DaEmergencyContactInfo;
import mm.aeon.com.zgen.entity.DaEmergencyContactInfoExample;
import mm.aeon.com.zgen.mapper.DaEmergencyContactInfoMapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class EmergencyContactInfoDao {

	@Autowired
	private EmergencyContactInfoCustomMapper emergencyContactInfoCustomMapper;

	@Autowired
	private DaEmergencyContactInfoMapper daEmergencyContactInfoMapper;

	@Autowired
	private BeanConverter beanConverter;

	public Integer insertEmergencyContactInfo(EmergencyContactInfoDto emergencyContactInfoDto) {
		Integer emergencyContactInfoId;
		DaEmergencyContactInfo daEmergencyContactInfo = beanConverter.convert(emergencyContactInfoDto, DaEmergencyContactInfo.class);
		daEmergencyContactInfo.setCreatedTime(CommonUtil.getCurrentTime());
		emergencyContactInfoCustomMapper.insertEmergencyContactInfo(daEmergencyContactInfo);
		emergencyContactInfoId = daEmergencyContactInfo.getDaEmergencyContactInfoId();
		return emergencyContactInfoId;
	}

	public void updateEmergencyContactInfo(EmergencyContactInfoDto emergencyContactInfoDto) {
		DaEmergencyContactInfo daEmergencyContactInfo = beanConverter.convert(emergencyContactInfoDto, DaEmergencyContactInfo.class);
		daEmergencyContactInfo.setUpdatedTime(CommonUtil.getCurrentTime());
		daEmergencyContactInfoMapper.updateByPrimaryKeySelective(daEmergencyContactInfo);
	}

	public EmergencyContactInfoDto getEmergencyContactInfoDto(Integer applicationInfoId) {
		DaEmergencyContactInfoExample example = new DaEmergencyContactInfoExample();
		example.createCriteria().andDaApplicationInfoIdEqualTo(applicationInfoId);
		List<DaEmergencyContactInfo> daDaEmergencyContactInfoList = daEmergencyContactInfoMapper.selectByExample(example);
		EmergencyContactInfoDto emergencyContactInfoDto = null;
		if (!CollectionUtils.isEmpty(daDaEmergencyContactInfoList)) {
			emergencyContactInfoDto = beanConverter.convert(daDaEmergencyContactInfoList.get(0), EmergencyContactInfoDto.class);
		}
		return emergencyContactInfoDto;
	}

}
