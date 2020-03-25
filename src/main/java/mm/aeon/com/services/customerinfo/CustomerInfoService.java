package mm.aeon.com.services.customerinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.common.VCSMPasswordEncoder;
import mm.aeon.com.dao.appusageinfo.AppUsageInfoDao;
import mm.aeon.com.dao.customerinfo.CustomerInfoDao;
import mm.aeon.com.dto.customerinfo.CustRoomInfoDto;
import mm.aeon.com.dto.customerinfo.CustomerInfoDto;
import mm.aeon.com.dto.customerinfo.CustomerInfoEditReqDto;
import mm.aeon.com.dto.customersecurityquestion.CustomerSecurityQuestionDto;
import mm.aeon.com.zgen.entity.MemberInfo;
import mm.aeon.com.zgen.entity.PasswordInfo;
import mm.aeon.com.zgen.entity.SecurityQuestion;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CustomerInfoService {

	@Autowired
	private CustomerInfoDao customerInfoDao;

	@Autowired
	private AppUsageInfoDao appUsageInfoDao;

	public CustomerInfoDto getCustomerInfoWithPhoneNoAndNrcNo(String phoneNo, String nrcNo) {
		return customerInfoDao.getCustomerInfoWithPhoneNoAndNrcNo(phoneNo, nrcNo);
	}

	public CustomerInfoDto getCustomerInfoWithPhoneNo(String phoneNo) {
		return customerInfoDao.getCustomerInfoWithPhoneNo(phoneNo);
	}

	public CustomerInfoDto getCustomerInfoWithCustomerId(Integer customerId) {
		return customerInfoDao.getCustomerInfoWithCustomerId(customerId);
	}

	public int getNrcNoCount(String nrcNo, Integer customerId) {
		return customerInfoDao.getNrcNoCount(nrcNo, customerId);
	}

	public int getPhoneNoCount(String phoneNo, Integer customerId) {
		return customerInfoDao.getPhoneNoCount(phoneNo, customerId);
	}

	public int checkAlreadyRegisteredCustomer(String phoneNo, String nrcNo) {
		return customerInfoDao.checkAlreadyRegisteredCustomer(phoneNo, nrcNo);
	}

	public Integer registerNewCustomer(CustomerInfoDto customerInfoDto) {

		// insert customer info
		Integer customerId = customerInfoDao.insertCustomerInfo(customerInfoDto);

		PasswordInfo passwordInfo = new PasswordInfo();
		passwordInfo.setUserTypeId(customerInfoDto.getUserTypeId());
		passwordInfo.setUserId(customerId);
		passwordInfo.setPassword(VCSMPasswordEncoder.encode(customerInfoDto.getPassword()));
		// insert password info
		customerInfoDao.insertPasswordInfo(passwordInfo);

		for (CustomerSecurityQuestionDto customerSecurityQuestionDto : customerInfoDto.getCustomerSecurityQuestionDtoList()) {
			SecurityQuestion securityQuestion = customerInfoDao.getSecurityQuestionWithSecQuesId(customerSecurityQuestionDto.getSecQuesId());
			if (securityQuestion != null && securityQuestion.getDelFlag() == 1) {
				securityQuestion.setDelFlag((short) 0);
				securityQuestion.setUpdatedBy(customerId + "(Customer Overwirte)");
				// update security question
				customerInfoDao.updateSecurityQuestion(securityQuestion);
			}
			customerSecurityQuestionDto.setCustomerId(customerId);
			// insert customer security question
			customerInfoDao.insertCustomerSecurityQuestion(customerSecurityQuestionDto);
		}

		if (customerInfoDto.getAppUsageInfoDto() != null) {
			customerInfoDto.getAppUsageInfoDto().setCustomerId(customerId);
			customerInfoDto.getAppUsageInfoDto().setRegistrationDateTime(CommonUtil.getCurrentTime());
			// insert app usage info
			appUsageInfoDao.insertAppUsageInfo(customerInfoDto.getAppUsageInfoDto());
		}

		// get active admin list
		List<Integer> adminIdList = customerInfoDao.getAdminIdList();

		CustRoomInfoDto custRoomInfoDto = new CustRoomInfoDto();
		custRoomInfoDto.setCustomerId(customerId);
		custRoomInfoDto.setCreatedTime(CommonUtil.getCurrentTime());
		custRoomInfoDto.setLastSendTime(CommonUtil.getCurrentTime());
		custRoomInfoDto.setConverLockFlag((short) 0);

		// insert customer room info for messaging
		Integer custRoomId = customerInfoDao.insertCustRoomInfo(custRoomInfoDto);
		customerInfoDao.insertAdminCustRoom(adminIdList, custRoomId);
		return customerId;

	}

	public Integer registerOldCustomer(CustomerInfoDto customerInfoDto) {

		// insert customer info
		Integer customerId = customerInfoDao.insertCustomerInfo(customerInfoDto);

		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setCustomerId(customerId);
		memberInfo.setMemberTypeId(CommonConstants.MEMBER_TYPE_BROWN_ID); // _
		// insert member info
		customerInfoDao.insertMemberInfo(memberInfo);

		PasswordInfo passwordInfo = new PasswordInfo();
		passwordInfo.setUserTypeId(customerInfoDto.getUserTypeId());
		passwordInfo.setUserId(customerId);
		passwordInfo.setPassword(VCSMPasswordEncoder.encode(customerInfoDto.getPassword()));
		// insert password info
		customerInfoDao.insertPasswordInfo(passwordInfo);

		for (CustomerSecurityQuestionDto customerSecurityQuestionDto : customerInfoDto.getCustomerSecurityQuestionDtoList()) {
			SecurityQuestion securityQuestion = customerInfoDao.getSecurityQuestionWithSecQuesId(customerSecurityQuestionDto.getSecQuesId());
			if (securityQuestion != null && securityQuestion.getDelFlag() == 1) {
				securityQuestion.setDelFlag((short) 0);
				securityQuestion.setUpdatedBy(customerId + "(Customer Overwirte)");
				// update security question
				customerInfoDao.updateSecurityQuestion(securityQuestion);
			}
			customerSecurityQuestionDto.setCustomerId(customerId);
			// insert customer security question
			customerInfoDao.insertCustomerSecurityQuestion(customerSecurityQuestionDto);
		}

		if (customerInfoDto.getAppUsageInfoDto() != null) {
			customerInfoDto.getAppUsageInfoDto().setCustomerId(customerId);
			customerInfoDto.getAppUsageInfoDto().setRegistrationDateTime(CommonUtil.getCurrentTime());
			// insert app usage info
			appUsageInfoDao.insertAppUsageInfo(customerInfoDto.getAppUsageInfoDto());
		}

		// get active admin list
		List<Integer> adminIdList = customerInfoDao.getAdminIdList();

		CustRoomInfoDto custRoomInfoDto = new CustRoomInfoDto();
		custRoomInfoDto.setCustomerId(customerId);
		custRoomInfoDto.setCreatedTime(CommonUtil.getCurrentTime());
		custRoomInfoDto.setLastSendTime(CommonUtil.getCurrentTime());
		custRoomInfoDto.setConverLockFlag((short) 0);

		// insert customer room info for messaging
		Integer custRoomId = customerInfoDao.insertCustRoomInfo(custRoomInfoDto);
		customerInfoDao.insertAdminCustRoom(adminIdList, custRoomId);
		return customerId;
	}

	public void upgradeMemeber(CustomerInfoDto customerInfoDto) {

		// update customer info
		customerInfoDao.updateCustomerInfo(customerInfoDto);

		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setCustomerId(customerInfoDto.getCustomerId());
		memberInfo.setMemberTypeId(CommonConstants.MEMBER_TYPE_BROWN_ID); // _
		// insert member info
		customerInfoDao.insertMemberInfo(memberInfo);

	}

	public void updateCustomerSecurityQuestionAnswer(List<CustomerSecurityQuestionDto> customerSecurityQuestionDtoList) {

		// update customer security question answer
		for (CustomerSecurityQuestionDto customerSecurityQuestionDto : customerSecurityQuestionDtoList) {
			customerInfoDao.updateCustomerSecurityQuestionAnswer(customerSecurityQuestionDto);
		}

	}

	public void updateCustomerPassword(PasswordInfo passwordInfo) {
		customerInfoDao.updateCustomerPassword(passwordInfo);

	}

	public int checkAlreadyRegisteredCustomerRoom(Integer customerId) {
		return customerInfoDao.checkAlreadyRegisteredCustomerRoom(customerId);
	}

	public List<Integer> getAdminIdList() {
		return customerInfoDao.getAdminIdList();
	}

	public Integer insertCustRoomInfo(CustRoomInfoDto custRoomInfoDto) {
		return customerInfoDao.insertCustRoomInfo(custRoomInfoDto);
	}

	public void insertAdminCustRoom(List<Integer> adminIdList, Integer custRoomId) {
		customerInfoDao.insertAdminCustRoom(adminIdList, custRoomId);
	}

	public void updateCustomerInfo(CustomerInfoDto customerInfoDto) {
		customerInfoDao.updateCustomerInfo(customerInfoDto);
	}

	public void insertDaCustomerInfoEdit(CustomerInfoEditReqDto customerInfoEditReqDto) {
		customerInfoDao.insertDaCustomerInfoEdit(customerInfoEditReqDto);
	}

	public void updateDaCustomerInfoEdit(CustomerInfoEditReqDto customerInfoEditReqDto) {
		customerInfoDao.updateDaCustomerInfoEdit(customerInfoEditReqDto);
	}

	public int checkCustomerInfoEditReqAlreadyExist(Integer customerId) {
		return customerInfoDao.checkCustomerInfoEditReqAlreadyExist(customerId);
	}

	public CustomerInfoEditReqDto getCustomerInfoEditReq(Integer customerId) {
		return customerInfoDao.getCustomerInfoEditReq(customerId);
	}

}
