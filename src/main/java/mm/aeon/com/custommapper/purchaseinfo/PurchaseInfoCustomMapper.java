package mm.aeon.com.custommapper.purchaseinfo;

import java.util.List;

import mm.aeon.com.dto.applicationinfo.AgentApplicationStatusInfoReqDto;
import mm.aeon.com.dto.applicationinfo.AgentApplicationStatusInfoResDto;
import mm.aeon.com.dto.applicationinfo.CheckingAttachmentDto;
import mm.aeon.com.dto.applicationinfo.PurchaseApplicationInfoDto;
import mm.aeon.com.dto.applicationinfo.PurchaseAttachmentEditInfoReqDto;
import mm.aeon.com.dto.applicationinfo.PurchaseAttachmentEditInfoResDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInfoDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInquriesResDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInquriesSearchCriteriaDto;
import mm.aeon.com.zgen.entity.DaPurchaseInfo;

public interface PurchaseInfoCustomMapper {

	PurchaseInfoDto getPurchaseInfoDetail(Integer daApplicationInfoId);

	Integer insertPurchaseInfo(DaPurchaseInfo daPurchaseInfo);

	PurchaseApplicationInfoDto getPurchaseApplicationInfoDto(Integer daApplicationInfoId);

	CheckingAttachmentDto getCheckingAttachmentDtoWithPurchaseIdAndFileType(Integer daPurchaseInfoId, Integer fileType);

	List<CheckingAttachmentDto> getCheckingAttachmentList(Integer daPurchaseInfoId);

	List<PurchaseInquriesResDto> getPurchaseInquriesList(PurchaseInquriesSearchCriteriaDto purchaseInquriesSearchCriteriaDto);

	List<PurchaseAttachmentEditInfoResDto> getPurchaseAttachmentEditInfoList(PurchaseAttachmentEditInfoReqDto purchaseAttachmentEditInfoReqDto);

	AgentApplicationStatusInfoResDto getAgentApplicationStatusInfo(AgentApplicationStatusInfoReqDto agentApplicationStatusInfoReqDto);

}
