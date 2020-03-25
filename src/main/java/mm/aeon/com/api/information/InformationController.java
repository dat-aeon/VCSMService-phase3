package mm.aeon.com.api.information;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mm.aeon.com.dto.information.ApplicationTypeResDto;
import mm.aeon.com.dto.information.ChatAutoReplyMessageResDto;
import mm.aeon.com.dto.information.CityInfoDto;
import mm.aeon.com.dto.information.CompanyInfoResDto;
import mm.aeon.com.dto.information.FAQInfo;
import mm.aeon.com.dto.information.HighestEducationTypeResDto;
import mm.aeon.com.dto.information.HotlinePhoneResDto;
import mm.aeon.com.dto.information.HowToUseVideoFileNameResDto;
import mm.aeon.com.dto.information.ItemLabelResDto;
import mm.aeon.com.dto.information.LoanTypeResDto;
import mm.aeon.com.dto.information.NrcResDto;
import mm.aeon.com.dto.information.ProductTypeResDto;
import mm.aeon.com.dto.information.TermsAndConditionsResDto;
import mm.aeon.com.dto.information.TownshipCodeResDto;
import mm.aeon.com.services.information.InformationService;

@RestController
@RequestMapping("/information")
public class InformationController {

	@Autowired
	private InformationService informationService;

	@Value("${properties.imageBaseFilePath}")
	private String imageBaseFilePath;

	@Value("${properties.howToUseVideoFolder}")
	private String howToUseVideoFolder;

	@RequestMapping(value = "/highest-education-type-list", method = RequestMethod.GET)
	public List<HighestEducationTypeResDto> getHighestEducationTypeList() throws Exception {
		List<HighestEducationTypeResDto> highestEducationTypeResDtoList = new ArrayList<HighestEducationTypeResDto>();

		HighestEducationTypeResDto highestEducationTypeResDto1 = new HighestEducationTypeResDto();
		highestEducationTypeResDto1.setHighestEducationTypeId(1);
		highestEducationTypeResDto1.setName("High School");

		HighestEducationTypeResDto highestEducationTypeResDto2 = new HighestEducationTypeResDto();
		highestEducationTypeResDto2.setHighestEducationTypeId(2);
		highestEducationTypeResDto2.setName("University/College");

		HighestEducationTypeResDto highestEducationTypeResDto3 = new HighestEducationTypeResDto();
		highestEducationTypeResDto3.setHighestEducationTypeId(3);
		highestEducationTypeResDto3.setName("Post-Gradute");

		highestEducationTypeResDtoList.add(highestEducationTypeResDto1);
		highestEducationTypeResDtoList.add(highestEducationTypeResDto2);
		highestEducationTypeResDtoList.add(highestEducationTypeResDto3);

		return highestEducationTypeResDtoList;
	}

	@RequestMapping(value = "/about-us", method = RequestMethod.GET)
	public CompanyInfoResDto getCompanyInfo() throws Exception {
		return informationService.getCompanyInfo();
	}

	@RequestMapping(value = "/faq-info-list", method = RequestMethod.GET)
	public List<FAQInfo> getFAQInfoList() throws Exception {
		return informationService.getFAQInfoList();
	}

	@RequestMapping(value = "/item-label-eng-list", method = RequestMethod.GET)
	public Map<String, String> getItemLabelEngList() throws Exception {
		List<ItemLabelResDto> itemLabelResDtoList = informationService.getItemLabelEngList();

		Map<String, String> itemLabelEngResMap = itemLabelResDtoList.stream()
				.collect(Collectors.toMap(ItemLabelResDto::getItemLabelCode, ItemLabelResDto::getItemLabelEng, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		return itemLabelEngResMap;
	}

	@RequestMapping(value = "/item-label-mym-list", method = RequestMethod.GET)
	public Map<String, String> getItemLabelMymList() throws Exception {
		List<ItemLabelResDto> itemLabelResDtoList = informationService.getItemLabelMymList();

		Map<String, String> itemLabelMymResMap = itemLabelResDtoList.stream()
				.collect(Collectors.toMap(ItemLabelResDto::getItemLabelCode, ItemLabelResDto::getItemLabelMym, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		return itemLabelMymResMap;
	}

	@RequestMapping(value = "/township-code-list", method = RequestMethod.GET)
	public List<TownshipCodeResDto> getTownshipCodeList() throws Exception {
		return informationService.getTownshipCodeList();
	}

	@RequestMapping(value = "/township-code-list-web", method = RequestMethod.GET)
	public NrcResDto getTownshipCodeListWeb() throws Exception {
		List<TownshipCodeResDto> townshipCodeResDtoList = informationService.getTownshipCodeList();
		NrcResDto nrcResDto = new NrcResDto();
		List<String> nrcTypeList = new ArrayList<String>();
		nrcTypeList.add("(N)");
		nrcTypeList.add("(P)");
		nrcTypeList.add("(E)");
		nrcResDto.setNrcTypeList(nrcTypeList);
		nrcResDto.setTownshipCodeResDtoList(townshipCodeResDtoList);
		return nrcResDto;
	}

	@RequestMapping(value = "/hotline", method = RequestMethod.GET)
	public HotlinePhoneResDto getHotlinePhone() throws Exception {
		CompanyInfoResDto companyInfoResDto = informationService.getCompanyInfo();
		HotlinePhoneResDto hotlinePhoneResDto = new HotlinePhoneResDto();
		hotlinePhoneResDto.setHotlinePhone(companyInfoResDto.getHotlinePhone());
		return hotlinePhoneResDto;
	}

	@RequestMapping(value = "/loan-type-list", method = RequestMethod.GET)
	public List<LoanTypeResDto> getLoanTypeList() throws Exception {
		return informationService.getLoanTypeList();
	}

	@RequestMapping(value = "/application-type-list", method = RequestMethod.GET)
	public List<ApplicationTypeResDto> getApplicationTypeList() throws Exception {
		return informationService.getApplicationTypeList();
	}

	@RequestMapping(value = "/product-type-list", method = RequestMethod.GET)
	public List<ProductTypeResDto> getProductTypeList() throws Exception {
		return informationService.getProductTypeList();
	}

	@RequestMapping(value = "/terms-and-conditions", method = RequestMethod.GET)
	public TermsAndConditionsResDto getTermsAndConditions() throws Exception {
		return informationService.getTermsAndConditions();
	}

	@RequestMapping(value = "/city-township-info-list", method = RequestMethod.GET)
	public List<CityInfoDto> getCityTownshipInfoList() throws Exception {
		return informationService.getCityTownshipInfoList();
	}

	@RequestMapping(value = "/get-how-to-use-video-file-name", method = RequestMethod.GET)
	public HowToUseVideoFileNameResDto getHowToUseVideoFileName() throws Exception {

		File file = new File(imageBaseFilePath + howToUseVideoFolder);
		File[] files = file.listFiles();
		HowToUseVideoFileNameResDto howToUseVideoFileNameResDto = new HowToUseVideoFileNameResDto();
		for (File f : files) {
			howToUseVideoFileNameResDto.setFileName(f.getName());
		}

		return howToUseVideoFileNameResDto;
	}

	@RequestMapping(value = "/get-chat-auto-reply-message", method = RequestMethod.GET)
	public ChatAutoReplyMessageResDto getChatAutoReplyMessage() throws Exception {
		return informationService.getChatAutoReplyMessage();
	}

}
