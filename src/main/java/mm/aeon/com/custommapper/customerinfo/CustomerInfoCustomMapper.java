package mm.aeon.com.custommapper.customerinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mm.aeon.com.dto.customerinfo.CustomerInfoDto;
import mm.aeon.com.dto.customerinfo.CustomerInfoEditReqDto;
import mm.aeon.com.zgen.entity.CustRoomInfo;
import mm.aeon.com.zgen.entity.CustomerInfo;

public interface CustomerInfoCustomMapper {

	CustomerInfoDto getCustomerInfoWithPhoneNoAndNrcNo(@Param("phoneNo") String phoneNo, @Param("nrcNo") String nrcNo);

	CustomerInfoDto getCustomerInfoWithPhoneNo(@Param("phoneNo") String phoneNo);

	CustomerInfoDto getCustomerInfoWithCustomerId(@Param("customerId") Integer customerId);

	int getNrcNoCount(@Param("nrcNo") String nrcNo, @Param("customerId") Integer customerId);

	int getPhoneNoCount(@Param("phoneNo") String phoneNo, @Param("customerId") Integer customerId);

	int checkAlreadyRegisteredCustomer(@Param("phoneNo") String phoneNo, @Param("nrcNo") String nrcNo);

	Integer insertCustomerInfo(CustomerInfo customerInfo);

	List<Integer> getAdminIdList();

	List<Integer> getAdminIdListForFreeMessaging();

	Integer insertCustRoomInfo(CustRoomInfo custRoomInfo);

	int insertAdminCustRoom(@Param("adminIdList") List<Integer> adminIdList, @Param("custRoomId") Integer custRoomId);

	int checkAlreadyRegisteredCustomerRoom(@Param("customerId") Integer customerId);

	int checkCustomerInfoEditReqAlreadyExist(@Param("customerId") Integer customerId);

	CustomerInfoEditReqDto getCustomerInfoEditReq(@Param("customerId") Integer customerId);

}
