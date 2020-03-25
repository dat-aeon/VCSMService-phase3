package mm.aeon.com.dao.applicationinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.common.BeanConverter;
import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.custommapper.applicationinfo.ApplicationInfoCustomMapper;
import mm.aeon.com.custommapper.purchaseinfo.PurchaseInfoCustomMapper;
import mm.aeon.com.dto.applicationinfo.ApplicationInfoAttachmentDto;
import mm.aeon.com.dto.applicationinfo.ApplicationInfoDto;
import mm.aeon.com.dto.applicationinfo.ApplicationInquriesSearchCriteriaDto;
import mm.aeon.com.dto.applicationinfo.CompulsoryInfoDto;
import mm.aeon.com.dto.applicationinfo.InterestInfoDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInfoDto;
import mm.aeon.com.zgen.entity.DaApplicationInfo;
import mm.aeon.com.zgen.entity.DaApplicationInfoExample;
import mm.aeon.com.zgen.entity.DaApplicationInfoHistory;
import mm.aeon.com.zgen.mapper.DaApplicationInfoHistoryMapper;
import mm.aeon.com.zgen.mapper.DaApplicationInfoMapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ApplicationInfoDao {

	@Autowired
	private ApplicationInfoCustomMapper applicationInfoCustomMapper;

	@Autowired
	private DaApplicationInfoMapper daApplicationInfoMapper;

	@Autowired
	private DaApplicationInfoHistoryMapper daApplicationInfoHistoryMapper;

	@Autowired
	private PurchaseInfoCustomMapper purchaseInfoCustomMapper;

	@Autowired
	private BeanConverter beanConverter;

	public Integer insertApplicationInfo(ApplicationInfoDto applicationInfoDto) {
		Integer applicationInfoId;
		DaApplicationInfo daApplicationInfo = beanConverter.convert(applicationInfoDto, DaApplicationInfo.class);
		daApplicationInfo.setCreatedTime(CommonUtil.getCurrentTime());
		applicationInfoCustomMapper.insertApplicationInfo(daApplicationInfo);
		applicationInfoId = daApplicationInfo.getDaApplicationInfoId();
		return applicationInfoId;
	}

	public void updateApplicationInfo(ApplicationInfoDto applicationInfoDto) {
		DaApplicationInfo daApplicationInfo = beanConverter.convert(applicationInfoDto, DaApplicationInfo.class);
		daApplicationInfo.setUpdatedTime(CommonUtil.getCurrentTime());
		daApplicationInfoMapper.updateByPrimaryKeySelective(daApplicationInfo);
	}

	public String getLastApplicationNo() {
		String lastApplicationNo;
		lastApplicationNo = applicationInfoCustomMapper.getLastApplicationNo();
		return lastApplicationNo;
	}

	public InterestInfoDto getInterestInfo() {
		InterestInfoDto interestInfoDto;
		interestInfoDto = applicationInfoCustomMapper.getInterestInfo();
		return interestInfoDto;
	}

	public CompulsoryInfoDto getCompulsoryInfo() {
		CompulsoryInfoDto compulsoryInfoDto;
		compulsoryInfoDto = applicationInfoCustomMapper.getCompulsoryInfo();
		return compulsoryInfoDto;
	}

	public List<Double> getActiveFinanceAmountList(Integer customerId, Integer daApplicationInfoId) {
		List<Double> activeFinanceAmountList;
		activeFinanceAmountList = applicationInfoCustomMapper.getActiveFinanceAmountList(customerId, daApplicationInfoId);
		return activeFinanceAmountList;
	}

	public List<ApplicationInfoDto> getApplicationInquriesList(ApplicationInquriesSearchCriteriaDto applicationInquriesSearchCriteriaDto) {
		List<ApplicationInfoDto> applicationInfoDtoList;
		applicationInfoDtoList = applicationInfoCustomMapper.getApplicationInquriesList(applicationInquriesSearchCriteriaDto);
		return applicationInfoDtoList;
	}

	public int getApplicationInquriesCount(ApplicationInquriesSearchCriteriaDto applicationInquriesSearchCriteriaDto) {
		int applicationInquriesCount;
		applicationInquriesCount = applicationInfoCustomMapper.getApplicationInquriesCount(applicationInquriesSearchCriteriaDto);
		return applicationInquriesCount;
	}

	public ApplicationInfoDto getApplicationInfoDetail(Integer daApplicationInfoId) {
		ApplicationInfoDto applicationInfoDto;
		applicationInfoDto = applicationInfoCustomMapper.getApplicationInfoDetail(daApplicationInfoId);
		return applicationInfoDto;
	}

	public void insertApplicationInfoHistory(ApplicationInfoDto applicationInfoDto) {
		DaApplicationInfoHistory daApplicationInfoHistory = beanConverter.convert(applicationInfoDto, DaApplicationInfoHistory.class);
		daApplicationInfoHistory.setCreatedTime(CommonUtil.getCurrentTime());
		daApplicationInfoHistory.setUpdatedBy(null);
		daApplicationInfoHistory.setUpdatedTime(null);
		daApplicationInfoHistoryMapper.insertSelective(daApplicationInfoHistory);
	}

	public PurchaseInfoDto getPurchaseInfoDetail(Integer daApplicationInfoId) {
		PurchaseInfoDto purchaseInfoDto;
		purchaseInfoDto = purchaseInfoCustomMapper.getPurchaseInfoDetail(daApplicationInfoId);
		return purchaseInfoDto;
	}

	public ApplicationInfoDto getLastApplicationInfo(Integer customerId) {
		ApplicationInfoDto applicationInfoDto;
		applicationInfoDto = applicationInfoCustomMapper.getLastApplicationInfo(customerId);
		return applicationInfoDto;
	}

	public List<ApplicationInfoAttachmentDto> getPurchaseApplicationAttachmentList(Integer daApplicationInfoId) {
		List<ApplicationInfoAttachmentDto> applicationInfoAttachmentDto;
		applicationInfoAttachmentDto = applicationInfoCustomMapper.getPurchaseApplicationAttachmentList(daApplicationInfoId);
		return applicationInfoAttachmentDto;
	}

	public int getApplicationStatusCount(Integer customerId, Integer status) {
		int applicationStatusCount;
		applicationStatusCount = applicationInfoCustomMapper.getApplicationStatusCount(customerId, status);
		return applicationStatusCount;
	}

	public int getApplicationStatusChangedCount(Integer customerId) {
		int applicationStatusChangedCount;
		applicationStatusChangedCount = applicationInfoCustomMapper.getApplicationStatusChangedCount(customerId);
		return applicationStatusChangedCount;
	}

	public void updateApplicationStatusChangedReadFlag(Integer customerId) {
		DaApplicationInfo daApplicationInfo = new DaApplicationInfo();
		daApplicationInfo.setCustomerId(customerId);
		daApplicationInfo.setUpdatedTime(CommonUtil.getCurrentTime());
		daApplicationInfo.setUpdatedBy(customerId.toString() + "," + CommonConstants.CUSTOMER_TYPE);
		daApplicationInfo.setStatusChangedReadFlag(true);

		DaApplicationInfoExample example = new DaApplicationInfoExample();
		example.createCriteria().andCustomerIdEqualTo(customerId).andStatusChangedReadFlagEqualTo(false);

		daApplicationInfoMapper.updateByExampleSelective(daApplicationInfo, example);
	}

}
