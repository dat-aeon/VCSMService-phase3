package mm.aeon.com.dao.promotionsinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.custommapper.promotionsinfo.PromotionsInfoCustomMapper;
import mm.aeon.com.dto.promotionsinfo.PromotionsInfoResDto;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class PromotionsInfoDao {

	@Autowired
	private PromotionsInfoCustomMapper promotionsInfoCustomMapper;

	public List<PromotionsInfoResDto> getPromotionsInfoList() {
		List<PromotionsInfoResDto> promotionsInfoResDtoList = promotionsInfoCustomMapper.getPromotionsInfoList();
		return promotionsInfoResDtoList;
	}

}
