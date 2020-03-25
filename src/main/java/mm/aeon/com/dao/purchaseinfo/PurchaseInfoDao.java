package mm.aeon.com.dao.purchaseinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.common.BeanConverter;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.custommapper.purchaseinfo.PurchaseInfoCustomMapper;
import mm.aeon.com.dto.applicationinfo.AgentApplicationStatusInfoReqDto;
import mm.aeon.com.dto.applicationinfo.AgentApplicationStatusInfoResDto;
import mm.aeon.com.dto.applicationinfo.CheckingAttachmentDto;
import mm.aeon.com.dto.applicationinfo.PurchaseApplicationInfoDto;
import mm.aeon.com.dto.applicationinfo.PurchaseAttachmentEditInfoReqDto;
import mm.aeon.com.dto.applicationinfo.PurchaseAttachmentEditInfoResDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInfoAttachmentDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInfoDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInfoProductDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInquriesResDto;
import mm.aeon.com.dto.applicationinfo.PurchaseInquriesSearchCriteriaDto;
import mm.aeon.com.zgen.entity.CustAgreementList;
import mm.aeon.com.zgen.entity.CustAgreementListExample;
import mm.aeon.com.zgen.entity.DaCheckingAttachment;
import mm.aeon.com.zgen.entity.DaPurchaseInfo;
import mm.aeon.com.zgen.entity.DaPurchaseInfoAttachment;
import mm.aeon.com.zgen.entity.DaPurchaseInfoProduct;
import mm.aeon.com.zgen.entity.DaPurchaseInfoProductExample;
import mm.aeon.com.zgen.mapper.CustAgreementListMapper;
import mm.aeon.com.zgen.mapper.DaCheckingAttachmentMapper;
import mm.aeon.com.zgen.mapper.DaPurchaseInfoAttachmentMapper;
import mm.aeon.com.zgen.mapper.DaPurchaseInfoMapper;
import mm.aeon.com.zgen.mapper.DaPurchaseInfoProductMapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class PurchaseInfoDao {

	@Autowired
	private BeanConverter beanConverter;

	@Autowired
	private PurchaseInfoCustomMapper purchaseInfoCustomMapper;

	@Autowired
	private DaPurchaseInfoMapper daPurchaseInfoMapper;

	@Autowired
	private DaPurchaseInfoProductMapper daPurchaseInfoProductMapper;

	@Autowired
	private DaPurchaseInfoAttachmentMapper daPurchaseInfoAttachmentMapper;

	@Autowired
	private DaCheckingAttachmentMapper daCheckingAttachmentMapper;

	@Autowired
	private CustAgreementListMapper custAgreementListMapper;

	public Integer insertPurchaseInfo(PurchaseInfoDto purchaseInfoDto) {
		Integer purchaseInfoId;
		DaPurchaseInfo daPurchaseInfo = beanConverter.convert(purchaseInfoDto, DaPurchaseInfo.class);
		daPurchaseInfo.setCreatedTime(CommonUtil.getCurrentTime());
		purchaseInfoCustomMapper.insertPurchaseInfo(daPurchaseInfo);
		purchaseInfoId = daPurchaseInfo.getDaPurchaseInfoId();
		return purchaseInfoId;
	}

	public void updatePurchaseInfo(PurchaseInfoDto purchaseInfoDto) {
		DaPurchaseInfo daPurchaseInfo = beanConverter.convert(purchaseInfoDto, DaPurchaseInfo.class);
		daPurchaseInfo.setUpdatedTime(CommonUtil.getCurrentTime());
		daPurchaseInfoMapper.updateByPrimaryKeySelective(daPurchaseInfo);
	}

	public void updatePurchaseInfoProduct(PurchaseInfoProductDto purchaseInfoProductDto) {
		DaPurchaseInfoProduct daPurchaseInfoProduct = beanConverter.convert(purchaseInfoProductDto, DaPurchaseInfoProduct.class);
		daPurchaseInfoProduct.setUpdatedTime(CommonUtil.getCurrentTime());
		daPurchaseInfoProductMapper.updateByPrimaryKeySelective(daPurchaseInfoProduct);
	}

	public void insertPurchaseInfoProduct(PurchaseInfoProductDto purchaseInfoProductDto) {
		DaPurchaseInfoProduct daPurchaseInfoProduct = beanConverter.convert(purchaseInfoProductDto, DaPurchaseInfoProduct.class);
		daPurchaseInfoProduct.setCreatedTime(CommonUtil.getCurrentTime());
		daPurchaseInfoProductMapper.insertSelective(daPurchaseInfoProduct);
	}

	public void deletePurchaseInfoProductWithPurchaseInfoId(Integer purchaseInfoId) {
		DaPurchaseInfoProductExample example = new DaPurchaseInfoProductExample();
		example.createCriteria().andDaPurchaseInfoIdEqualTo(purchaseInfoId);
		daPurchaseInfoProductMapper.deleteByExample(example);
	}

	public PurchaseApplicationInfoDto getPurchaseApplicationInfoDto(Integer daApplicationInfoId) {
		PurchaseApplicationInfoDto purchaseApplicationInfoDto;
		purchaseApplicationInfoDto = purchaseInfoCustomMapper.getPurchaseApplicationInfoDto(daApplicationInfoId);
		return purchaseApplicationInfoDto;
	}

	public void insertCheckingAttachment(CheckingAttachmentDto checkingAttachmentDto) {
		DaCheckingAttachment daCheckingAttachment = beanConverter.convert(checkingAttachmentDto, DaCheckingAttachment.class);
		daCheckingAttachment.setCreatedTime(CommonUtil.getCurrentTime());
		daCheckingAttachmentMapper.insertSelective(daCheckingAttachment);
	}

	public void updateCheckingAttachment(CheckingAttachmentDto checkingAttachmentDto) {
		DaCheckingAttachment daCheckingAttachment = beanConverter.convert(checkingAttachmentDto, DaCheckingAttachment.class);
		daCheckingAttachment.setCreatedTime(CommonUtil.getCurrentTime());
		daCheckingAttachmentMapper.updateByPrimaryKeySelective(daCheckingAttachment);
	}

	public void updatePurchaseInfoAttachment(PurchaseInfoAttachmentDto purchaseInfoAttachmentDto) {
		DaPurchaseInfoAttachment daPurchaseInfoAttachment = beanConverter.convert(purchaseInfoAttachmentDto, DaPurchaseInfoAttachment.class);
		daPurchaseInfoAttachment.setUpdatedTime(CommonUtil.getCurrentTime());
		daPurchaseInfoAttachmentMapper.updateByPrimaryKeySelective(daPurchaseInfoAttachment);
	}

	public void updateQRNotShowCustAgreement(String applicationNo) {
		CustAgreementListExample example = new CustAgreementListExample();
		example.createCriteria().andApplicationNoEqualTo(applicationNo);
		CustAgreementList custAgreementList = new CustAgreementList();
		custAgreementList.setQrShow((short) 1);
		custAgreementList.setUpdatedTime(CommonUtil.getCurrentTime());
		custAgreementListMapper.updateByExampleSelective(custAgreementList, example);
	}

	public CheckingAttachmentDto getCheckingAttachmentDtoWithPurchaseIdAndFileType(Integer daPurchaseInfoId, Integer fileType) {
		CheckingAttachmentDto checkingAttachmentDto;
		checkingAttachmentDto = purchaseInfoCustomMapper.getCheckingAttachmentDtoWithPurchaseIdAndFileType(daPurchaseInfoId, fileType);
		return checkingAttachmentDto;
	}

	public List<CheckingAttachmentDto> getCheckingAttachmentList(Integer daPurchaseInfoId) {
		List<CheckingAttachmentDto> checkingAttachmentDtoList;
		checkingAttachmentDtoList = purchaseInfoCustomMapper.getCheckingAttachmentList(daPurchaseInfoId);
		return checkingAttachmentDtoList;
	}

	public List<PurchaseInquriesResDto> getPurchaseInquriesList(PurchaseInquriesSearchCriteriaDto purchaseInquriesSearchCriteriaDto) {
		List<PurchaseInquriesResDto> purchaseInquriesResDtoList;
		purchaseInquriesResDtoList = purchaseInfoCustomMapper.getPurchaseInquriesList(purchaseInquriesSearchCriteriaDto);
		return purchaseInquriesResDtoList;
	}

	public List<PurchaseAttachmentEditInfoResDto> getPurchaseAttachmentEditInfoList(PurchaseAttachmentEditInfoReqDto purchaseAttachmentEditInfoReqDto) {
		List<PurchaseAttachmentEditInfoResDto> purchaseAttachmentEditInfoResDto;
		purchaseAttachmentEditInfoResDto = purchaseInfoCustomMapper.getPurchaseAttachmentEditInfoList(purchaseAttachmentEditInfoReqDto);
		return purchaseAttachmentEditInfoResDto;
	}

	public AgentApplicationStatusInfoResDto getAgentApplicationStatusInfo(AgentApplicationStatusInfoReqDto agentApplicationStatusInfoReqDto) {
		AgentApplicationStatusInfoResDto agentApplicationStatusInfoResDto;
		agentApplicationStatusInfoResDto = purchaseInfoCustomMapper.getAgentApplicationStatusInfo(agentApplicationStatusInfoReqDto);
		return agentApplicationStatusInfoResDto;
	}
}
