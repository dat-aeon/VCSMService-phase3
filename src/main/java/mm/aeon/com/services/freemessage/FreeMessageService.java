package mm.aeon.com.services.freemessage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.dao.customerinfo.CustomerInfoDao;
import mm.aeon.com.dao.freemessage.FreeMessageDao;
import mm.aeon.com.dto.freemessage.FreeCustRoomInfoDto;
import mm.aeon.com.dto.freemessage.FreeMessageRoomSyncReqDto;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FreeMessageService {

	@Autowired
	private FreeMessageDao freeMessageDao;

	@Autowired
	private CustomerInfoDao customerInfoDao;

	public Integer freeMessageRoomSync(FreeMessageRoomSyncReqDto freeMessageRoomSyncReqDto) {

		Integer freeCustomerInfoId = freeMessageDao.insertFreeCustomerInfo(freeMessageRoomSyncReqDto.getPhoneNo());

		// get active admin list
		List<Integer> adminIdList = customerInfoDao.getAdminIdListForFreeMessaging();

		FreeCustRoomInfoDto freeCustRoomInfoDto = new FreeCustRoomInfoDto();
		freeCustRoomInfoDto.setFreeCustomerInfoId(freeCustomerInfoId);
		freeCustRoomInfoDto.setCreatedTime(CommonUtil.getCurrentTime());
		freeCustRoomInfoDto.setLastSendTime(CommonUtil.getCurrentTime());
		freeCustRoomInfoDto.setConverLockFlag((short) 0);

		// insert free customer room info for free messaging
		Integer freeCustRoomInfoId = freeMessageDao.insertFreeCustRoomInfo(freeCustRoomInfoDto);
		freeMessageDao.insertFreeAdminCustRoom(adminIdList, freeCustRoomInfoId);
		return freeCustomerInfoId;

	}

}
