package mm.aeon.com.custommapper.freemessage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mm.aeon.com.zgen.entity.FreeCustRoomInfo;
import mm.aeon.com.zgen.entity.FreeCustomerInfo;

public interface FreeMessageCustomMapper {

	Integer insertFreeCustomerInfo(FreeCustomerInfo freeCustomerInfo);

	Integer insertFreeCustRoomInfo(FreeCustRoomInfo FreeCustRoomInfo);

	Integer insertFreeAdminCustRoom(@Param("adminIdList") List<Integer> adminIdList, @Param("freeCustRoomInfoId") Integer freeCustRoomInfoId);

}
