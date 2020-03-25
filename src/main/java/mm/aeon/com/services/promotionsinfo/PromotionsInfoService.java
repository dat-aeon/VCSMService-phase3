package mm.aeon.com.services.promotionsinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.dao.promotionsinfo.PromotionsInfoDao;
import mm.aeon.com.dto.promotionsinfo.PromotionsInfoResDto;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PromotionsInfoService {

	@Autowired
	private PromotionsInfoDao promotionsInfoDao;

	public List<PromotionsInfoResDto> getPromotionsInfoList() {
		return promotionsInfoDao.getPromotionsInfoList();
	}

}
