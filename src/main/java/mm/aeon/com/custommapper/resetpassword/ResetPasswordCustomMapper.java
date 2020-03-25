package mm.aeon.com.custommapper.resetpassword;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mm.aeon.com.dto.customersecurityquestion.CustomerSecurityQuestionDto;
import mm.aeon.com.dto.resetpassword.SecurityQuestionResDto;

public interface ResetPasswordCustomMapper {

	SecurityQuestionResDto getSecurityQuestionList();

	List<CustomerSecurityQuestionDto> getCustomerSecurityQuestionListWithCustomerId(@Param("customerId") Integer customerId);

	int getCustomerSecurityQuestionCount(@Param("customerPhoneNo") String customerPhoneNo);

}
