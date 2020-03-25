package mm.aeon.com.dao.information;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.custommapper.information.InformationCustomMapper;
import mm.aeon.com.dto.information.ApplicationTypeResDto;
import mm.aeon.com.dto.information.ChatAutoReplyMessageResDto;
import mm.aeon.com.dto.information.CityInfoDto;
import mm.aeon.com.dto.information.CompanyInfoResDto;
import mm.aeon.com.dto.information.FAQInfo;
import mm.aeon.com.dto.information.ItemLabelResDto;
import mm.aeon.com.dto.information.LoanTypeResDto;
import mm.aeon.com.dto.information.ProductTypeResDto;
import mm.aeon.com.dto.information.TermsAndConditionsResDto;
import mm.aeon.com.dto.information.TownshipCodeResDto;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class InformationDao {

	@Autowired
	private InformationCustomMapper informationCustomMapper;

	public CompanyInfoResDto getCompanyInfo() {
		CompanyInfoResDto companyInfoResDto = informationCustomMapper.getCompanyInfo();
		return companyInfoResDto;
	}

	public List<FAQInfo> getFAQInfoList() {
		List<FAQInfo> fAQInfoList = informationCustomMapper.getFAQInfoList();
		return fAQInfoList;
	}

	public List<TownshipCodeResDto> getTownshipCodeList() {
		List<TownshipCodeResDto> townshipCodeResDtoList = informationCustomMapper.getTownshipCodeList();
		return townshipCodeResDtoList;
	}

	public List<ItemLabelResDto> getItemLabelEngList() {
		List<ItemLabelResDto> itemLabelResDtoList = informationCustomMapper.getItemLabelEngList();
		return itemLabelResDtoList;
	}

	public List<ItemLabelResDto> getItemLabelMymList() {
		List<ItemLabelResDto> itemLabelResDtoList = informationCustomMapper.getItemLabelMymList();
		return itemLabelResDtoList;
	}

	public List<LoanTypeResDto> getLoanTypeList() {
		List<LoanTypeResDto> loanTypeResDtoList = informationCustomMapper.getLoanTypeList();
		return loanTypeResDtoList;
	}

	public List<ApplicationTypeResDto> getApplicationTypeList() {
		List<ApplicationTypeResDto> applicationTypeResDtoList = informationCustomMapper.getApplicationTypeList();
		return applicationTypeResDtoList;
	}

	public List<ProductTypeResDto> getProductTypeList() {
		List<ProductTypeResDto> productTypeResDtoList = informationCustomMapper.getProductTypeList();
		return productTypeResDtoList;
	}

	public TermsAndConditionsResDto getTermsAndConditions() {
		TermsAndConditionsResDto termsAndConditionsResDto = informationCustomMapper.getTermsAndConditions();
		return termsAndConditionsResDto;
	}

	public List<CityInfoDto> getCityTownshipInfoList() {
		List<CityInfoDto> cityInfoDtoList = informationCustomMapper.getCityTownshipInfoList();
		return cityInfoDtoList;
	}

	public ChatAutoReplyMessageResDto getChatAutoReplyMessage() {
		ChatAutoReplyMessageResDto chatAutoReplyMessageResDto = informationCustomMapper.getChatAutoReplyMessage();
		return chatAutoReplyMessageResDto;
	}
}
