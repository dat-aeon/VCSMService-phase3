package mm.aeon.com.custommapper.applicationinfo;

import java.util.List;

import mm.aeon.com.dto.applicationinfo.ApplicationInfoAttachmentDto;
import mm.aeon.com.dto.applicationinfo.ApplicationInfoDto;
import mm.aeon.com.dto.applicationinfo.ApplicationInquriesSearchCriteriaDto;
import mm.aeon.com.dto.applicationinfo.CompulsoryInfoDto;
import mm.aeon.com.dto.applicationinfo.InterestInfoDto;
import mm.aeon.com.zgen.entity.DaApplicationInfo;

public interface ApplicationInfoCustomMapper {

	Integer insertApplicationInfo(DaApplicationInfo daApplicationInfo);

	String getLastApplicationNo();

	InterestInfoDto getInterestInfo();

	CompulsoryInfoDto getCompulsoryInfo();

	List<Double> getActiveFinanceAmountList(Integer customerId, Integer daApplicationInfoId);

	List<ApplicationInfoDto> getApplicationInquriesList(ApplicationInquriesSearchCriteriaDto applicationInquriesSearchCriteriaDto);

	int getApplicationInquriesCount(ApplicationInquriesSearchCriteriaDto applicationInquriesSearchCriteriaDto);

	ApplicationInfoDto getApplicationInfoDetail(Integer daApplicationInfoId);

	ApplicationInfoDto getLastApplicationInfo(Integer customerId);

	List<ApplicationInfoAttachmentDto> getPurchaseApplicationAttachmentList(Integer daApplicationInfoId);

	int getApplicationStatusCount(Integer customerId, Integer status);

	int getApplicationStatusChangedCount(Integer customerId);
}
