package mm.aeon.com.api.newsinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mm.aeon.com.dto.newsinfo.NewsInfoResDto;
import mm.aeon.com.services.newsinfo.NewsInfoService;

@RestController
@RequestMapping("/news-info")
public class NewsInfoController {

	@Autowired
	private NewsInfoService newsInfoService;

	@RequestMapping(value = "/news-info-list", method = RequestMethod.GET)
	public List<NewsInfoResDto> getNewsInfoList() throws Exception {

		List<NewsInfoResDto> newsInfoResDtoList = newsInfoService.getNewsInfoList();
		return newsInfoResDtoList;

	}

}
