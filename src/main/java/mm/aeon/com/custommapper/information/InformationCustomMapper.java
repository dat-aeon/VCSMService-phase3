package mm.aeon.com.custommapper.information;

import java.util.List;

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

public interface InformationCustomMapper {

	CompanyInfoResDto getCompanyInfo();

	List<FAQInfo> getFAQInfoList();

	List<TownshipCodeResDto> getTownshipCodeList();

	List<ItemLabelResDto> getItemLabelEngList();

	List<ItemLabelResDto> getItemLabelMymList();

	List<LoanTypeResDto> getLoanTypeList();

	List<ApplicationTypeResDto> getApplicationTypeList();

	List<ProductTypeResDto> getProductTypeList();

	TermsAndConditionsResDto getTermsAndConditions();

	List<CityInfoDto> getCityTownshipInfoList();

	ChatAutoReplyMessageResDto getChatAutoReplyMessage();
}
