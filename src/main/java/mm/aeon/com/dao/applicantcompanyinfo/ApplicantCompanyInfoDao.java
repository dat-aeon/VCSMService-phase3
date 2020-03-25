package mm.aeon.com.dao.applicantcompanyinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import mm.aeon.com.common.BeanConverter;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.custommapper.applicantcompanyinfo.ApplicantCompanyInfoCustomMapper;
import mm.aeon.com.dto.applicationinfo.ApplicantCompanyInfoDto;
import mm.aeon.com.zgen.entity.DaApplicantCompanyInfo;
import mm.aeon.com.zgen.entity.DaApplicantCompanyInfoExample;
import mm.aeon.com.zgen.mapper.DaApplicantCompanyInfoMapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ApplicantCompanyInfoDao {

	@Autowired
	private ApplicantCompanyInfoCustomMapper applicantCompanyInfoCustomMapper;

	@Autowired
	private DaApplicantCompanyInfoMapper daApplicantCompanyInfoMapper;

	@Autowired
	private BeanConverter beanConverter;

	public Integer insertApplicantCompanyInfo(ApplicantCompanyInfoDto applicantCompanyInfoDto) {
		Integer applicantCompanyInfoId;
		DaApplicantCompanyInfo daApplicantCompanyInfo = beanConverter.convert(applicantCompanyInfoDto, DaApplicantCompanyInfo.class);
		daApplicantCompanyInfo.setCreatedTime(CommonUtil.getCurrentTime());
		applicantCompanyInfoCustomMapper.insertApplicantCompanyInfo(daApplicantCompanyInfo);
		applicantCompanyInfoId = daApplicantCompanyInfo.getDaApplicantCompanyInfoId();
		return applicantCompanyInfoId;
	}

	public void updateApplicantCompanyInfo(ApplicantCompanyInfoDto applicantCompanyInfoDto) {
		DaApplicantCompanyInfo daApplicantCompanyInfoDto = beanConverter.convert(applicantCompanyInfoDto, DaApplicantCompanyInfo.class);
		daApplicantCompanyInfoDto.setUpdatedTime(CommonUtil.getCurrentTime());
		daApplicantCompanyInfoMapper.updateByPrimaryKeySelective(daApplicantCompanyInfoDto);
	}

	public ApplicantCompanyInfoDto getApplicantCompanyInfoDto(Integer applicationInfoId) {
		DaApplicantCompanyInfoExample example = new DaApplicantCompanyInfoExample();
		example.createCriteria().andDaApplicationInfoIdEqualTo(applicationInfoId);
		List<DaApplicantCompanyInfo> daApplicantCompanyInfoList = daApplicantCompanyInfoMapper.selectByExample(example);
		ApplicantCompanyInfoDto applicantCompanyInfoDto = null;
		if (!CollectionUtils.isEmpty(daApplicantCompanyInfoList)) {
			applicantCompanyInfoDto = beanConverter.convert(daApplicantCompanyInfoList.get(0), ApplicantCompanyInfoDto.class);
		}
		return applicantCompanyInfoDto;
	}

}
