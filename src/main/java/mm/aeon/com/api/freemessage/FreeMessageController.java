package mm.aeon.com.api.freemessage;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mm.aeon.com.dto.freemessage.FreeMessageRoomSyncReqDto;
import mm.aeon.com.dto.freemessage.FreeMessageRoomSyncResDto;
import mm.aeon.com.services.freemessage.FreeMessageService;

@RestController
@RequestMapping("/free-message")
public class FreeMessageController {

	@Autowired
	private FreeMessageService freeMessageService;

	@RequestMapping(value = "/room-sync", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public FreeMessageRoomSyncResDto freeMessageRoomSync(@Valid @RequestBody FreeMessageRoomSyncReqDto freeMessageRoomSyncReqDto) throws Exception {

		FreeMessageRoomSyncResDto freeMessageRoomSyncResDto = new FreeMessageRoomSyncResDto();
		Integer freeCustomerInfoId = freeMessageService.freeMessageRoomSync(freeMessageRoomSyncReqDto);
		freeMessageRoomSyncResDto.setFreeCustomerInfoId(freeCustomerInfoId);
		return freeMessageRoomSyncResDto;
	}

}
