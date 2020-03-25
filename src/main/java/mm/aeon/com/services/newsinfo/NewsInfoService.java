package mm.aeon.com.services.newsinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.dao.newsinfo.NewsInfoDao;
import mm.aeon.com.dto.newsinfo.NewsInfoResDto;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NewsInfoService {

	@Autowired
	private NewsInfoDao newsInfoDao;

	public List<NewsInfoResDto> getNewsInfoList() {
		return newsInfoDao.getNewsInfoList();
	}

}
