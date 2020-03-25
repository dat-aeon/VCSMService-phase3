package mm.aeon.com.api.promotionsinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mm.aeon.com.dto.promotionsinfo.PromotionsInfoResDto;
import mm.aeon.com.services.promotionsinfo.PromotionsInfoService;

@RestController
@RequestMapping("/promotions-info")
public class PromotionsInfoController {

	@Autowired
	private PromotionsInfoService promotionsInfoService;

	@RequestMapping(value = "/promotions-info-list", method = RequestMethod.GET)
	public List<PromotionsInfoResDto> getPromotionsInfoList() throws Exception {

		List<PromotionsInfoResDto> promotionsInfoResDtoList = promotionsInfoService.getPromotionsInfoList();
		return promotionsInfoResDtoList;

	}

}
