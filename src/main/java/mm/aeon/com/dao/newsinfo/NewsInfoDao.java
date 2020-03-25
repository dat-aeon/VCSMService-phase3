package mm.aeon.com.dao.newsinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.custommapper.newsinfo.NewsInfoCustomMapper;
import mm.aeon.com.dto.newsinfo.NewsInfoResDto;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class NewsInfoDao {

	@Autowired
	private NewsInfoCustomMapper newsInfoCustomMapper;

	public List<NewsInfoResDto> getNewsInfoList() {
		List<NewsInfoResDto> newsInfoResDtoList = newsInfoCustomMapper.getNewsInfoList();
		return newsInfoResDtoList;
	}

}
