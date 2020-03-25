package mm.aeon.com.services.resetpassword;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.dao.customerinfo.CustomerInfoDao;
import mm.aeon.com.dao.resetpassword.ResetPasswordDao;
import mm.aeon.com.dto.customerinfo.CustomerInfoDto;
import mm.aeon.com.dto.customersecurityquestion.CustomerSecurityQuestionDto;
import mm.aeon.com.dto.resetpassword.SecurityQuestionResDto;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ResetPasswordService {

	@Autowired
	private ResetPasswordDao resetPasswordDao;

	@Autowired
	private CustomerInfoDao customerInfoDao;

	public SecurityQuestionResDto getSecurityQuestionList() {
		return resetPasswordDao.getSecurityQuestionList();
	}

	public List<CustomerSecurityQuestionDto> getCustomerSecurityQuestionListWithCustomerId(Integer customerId) {
		return resetPasswordDao.getCustomerSecurityQuestionListWithCustomerId(customerId);
	}

	public void updatePasswordWithUserIdAndUserTypeId(Integer userId, Integer userTypeId, String encodedPassword) {
		resetPasswordDao.updatePasswordWithUserIdAndUserTypeId(userId, userTypeId, encodedPassword);
	}

	public void forcePasswordChange(CustomerInfoDto customerInfoDto, String encodedPassword) {
		customerInfoDto.setApplockFlag(CommonConstants.UNLOCK);
		customerInfoDao.updateCustomerInfo(customerInfoDto);
		resetPasswordDao.updatePasswordWithUserIdAndUserTypeId(customerInfoDto.getCustomerId(), customerInfoDto.getUserTypeId(), encodedPassword);
	}

	public int getCustomerSecurityQuestionCount(String customerPhoneNo) {
		return resetPasswordDao.getCustomerSecurityQuestionCount(customerPhoneNo);
	}

}
