package mm.aeon.com.dao.resetpassword;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.custommapper.resetpassword.ResetPasswordCustomMapper;
import mm.aeon.com.dto.customersecurityquestion.CustomerSecurityQuestionDto;
import mm.aeon.com.dto.resetpassword.SecurityQuestionResDto;
import mm.aeon.com.zgen.entity.PasswordInfo;
import mm.aeon.com.zgen.entity.PasswordInfoExample;
import mm.aeon.com.zgen.mapper.PasswordInfoMapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ResetPasswordDao {

	@Autowired
	private ResetPasswordCustomMapper resetPasswordCustomMapper;

	@Autowired
	private PasswordInfoMapper passwordInfoMapper;

	public SecurityQuestionResDto getSecurityQuestionList() {
		SecurityQuestionResDto securityQuestionResDto = resetPasswordCustomMapper.getSecurityQuestionList();
		return securityQuestionResDto;
	}

	public List<CustomerSecurityQuestionDto> getCustomerSecurityQuestionListWithCustomerId(Integer customerId) {
		List<CustomerSecurityQuestionDto> customerSecurityQuestionDtoList = resetPasswordCustomMapper.getCustomerSecurityQuestionListWithCustomerId(customerId);
		return customerSecurityQuestionDtoList;
	}

	public void updatePasswordWithUserIdAndUserTypeId(Integer userId, Integer userTypeId, String encodedPassword) {
		PasswordInfo passwordInfo = new PasswordInfo();
		passwordInfo.setPassword(encodedPassword);
		passwordInfo.setUpdatedBy(userId.toString());
		passwordInfo.setUpdatedTime(CommonUtil.getCurrentTime());
		PasswordInfoExample example = new PasswordInfoExample();
		example.createCriteria().andUserIdEqualTo(userId).andUserTypeIdEqualTo(userTypeId);
		passwordInfoMapper.updateByExampleSelective(passwordInfo, example);
	}

	public int getCustomerSecurityQuestionCount(String customerPhoneNo) {
		int count = resetPasswordCustomMapper.getCustomerSecurityQuestionCount(customerPhoneNo);
		return count;
	}

}
