package mm.aeon.com.dao.freemessage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.common.BeanConverter;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.custommapper.freemessage.FreeMessageCustomMapper;
import mm.aeon.com.dto.freemessage.FreeCustRoomInfoDto;
import mm.aeon.com.zgen.entity.FreeCustRoomInfo;
import mm.aeon.com.zgen.entity.FreeCustomerInfo;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class FreeMessageDao {

	@Autowired
	private FreeMessageCustomMapper freeMessageCustomMapper;

	@Autowired
	private BeanConverter beanConverter;

	public Integer insertFreeCustomerInfo(String phoneNo) {
		Integer freeCustomerInfoId;
		FreeCustomerInfo freeCustomerInfo = new FreeCustomerInfo();
		freeCustomerInfo.setPhoneNo(phoneNo);
		freeCustomerInfo.setCreatedBy(phoneNo);
		freeCustomerInfo.setCreatedTime(CommonUtil.getCurrentTime());
		freeMessageCustomMapper.insertFreeCustomerInfo(freeCustomerInfo);
		freeCustomerInfoId = freeCustomerInfo.getFreeCustomerInfoId();
		return freeCustomerInfoId;
	}

	public Integer insertFreeCustRoomInfo(FreeCustRoomInfoDto freeCustRoomInfoDto) {
		Integer freeCustRoomInfoId;
		FreeCustRoomInfo freeCustRoomInfo = beanConverter.convert(freeCustRoomInfoDto, FreeCustRoomInfo.class);
		freeCustRoomInfo.setCreatedTime(CommonUtil.getCurrentTime());
		freeCustRoomInfo.setLastSendTime(CommonUtil.getCurrentTime());
		freeMessageCustomMapper.insertFreeCustRoomInfo(freeCustRoomInfo);
		freeCustRoomInfoId = freeCustRoomInfo.getFreeCustRoomInfoId();
		return freeCustRoomInfoId;
	}

	public void insertFreeAdminCustRoom(List<Integer> adminIdList, Integer freeCustRoomInfoId) {
		freeMessageCustomMapper.insertFreeAdminCustRoom(adminIdList, freeCustRoomInfoId);
	}

}
