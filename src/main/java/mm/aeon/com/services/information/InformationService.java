package mm.aeon.com.services.information;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.dao.information.InformationDao;
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

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class InformationService {

	@Autowired
	private InformationDao informationDao;

	public CompanyInfoResDto getCompanyInfo() {
		return informationDao.getCompanyInfo();
	}

	public List<FAQInfo> getFAQInfoList() {
		return informationDao.getFAQInfoList();
	}

	public List<TownshipCodeResDto> getTownshipCodeList() {
		return informationDao.getTownshipCodeList();
	}

	public List<ItemLabelResDto> getItemLabelEngList() {
		return informationDao.getItemLabelEngList();
	}

	public List<ItemLabelResDto> getItemLabelMymList() {
		return informationDao.getItemLabelMymList();
	}

	public List<LoanTypeResDto> getLoanTypeList() {
		return informationDao.getLoanTypeList();
	}

	public List<ApplicationTypeResDto> getApplicationTypeList() {
		return informationDao.getApplicationTypeList();
	}

	public List<ProductTypeResDto> getProductTypeList() {
		return informationDao.getProductTypeList();
	}

	public TermsAndConditionsResDto getTermsAndConditions() {
		return informationDao.getTermsAndConditions();
	}

	public List<CityInfoDto> getCityTownshipInfoList() {
		return informationDao.getCityTownshipInfoList();
	}

	public ChatAutoReplyMessageResDto getChatAutoReplyMessage() {
		return informationDao.getChatAutoReplyMessage();
	}

}
