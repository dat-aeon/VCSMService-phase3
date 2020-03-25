package mm.aeon.com.dao.guarantorinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import mm.aeon.com.common.BeanConverter;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.custommapper.guarantorinfo.GuarantorInfoCustomMapper;
import mm.aeon.com.dto.applicationinfo.GuarantorInfoDto;
import mm.aeon.com.zgen.entity.DaGuarantorInfo;
import mm.aeon.com.zgen.entity.DaGuarantorInfoExample;
import mm.aeon.com.zgen.mapper.DaGuarantorInfoMapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class GuarantorInfoDao {

	@Autowired
	private GuarantorInfoCustomMapper guarantorInfoCustomMapper;

	@Autowired
	private DaGuarantorInfoMapper daGuarantorInfoMapper;

	@Autowired
	private BeanConverter beanConverter;

	public Integer insertGuarantorInfo(GuarantorInfoDto guarantorInfoDto) {
		Integer guarantorInfoId;
		DaGuarantorInfo daGuarantorInfo = beanConverter.convert(guarantorInfoDto, DaGuarantorInfo.class);
		daGuarantorInfo.setCreatedTime(CommonUtil.getCurrentTime());
		guarantorInfoCustomMapper.insertGuarantorInfo(daGuarantorInfo);
		guarantorInfoId = daGuarantorInfo.getDaGuarantorInfoId();
		return guarantorInfoId;
	}

	public void updateGuarantorInfo(GuarantorInfoDto guarantorInfoDto) {
		DaGuarantorInfo daGuarantorInfo = beanConverter.convert(guarantorInfoDto, DaGuarantorInfo.class);
		daGuarantorInfo.setUpdatedTime(CommonUtil.getCurrentTime());
		daGuarantorInfoMapper.updateByPrimaryKeySelective(daGuarantorInfo);
	}

	public GuarantorInfoDto getGuarantorInfoDto(Integer applicationInfoId) {
		DaGuarantorInfoExample example = new DaGuarantorInfoExample();
		example.createCriteria().andDaApplicationInfoIdEqualTo(applicationInfoId);
		List<DaGuarantorInfo> daGuarantorInfoList = daGuarantorInfoMapper.selectByExample(example);
		GuarantorInfoDto guarantorInfoDto = null;
		if (!CollectionUtils.isEmpty(daGuarantorInfoList)) {
			guarantorInfoDto = beanConverter.convert(daGuarantorInfoList.get(0), GuarantorInfoDto.class);
		}
		return guarantorInfoDto;
	}

}
