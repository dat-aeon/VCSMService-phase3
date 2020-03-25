package mm.aeon.com.dao.customerinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.common.BeanConverter;
import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.custommapper.customerinfo.CustomerInfoCustomMapper;
import mm.aeon.com.dto.customerinfo.CustRoomInfoDto;
import mm.aeon.com.dto.customerinfo.CustomerInfoDto;
import mm.aeon.com.dto.customerinfo.CustomerInfoEditReqDto;
import mm.aeon.com.dto.customersecurityquestion.CustomerSecurityQuestionDto;
import mm.aeon.com.zgen.entity.AppUsageDetail;
import mm.aeon.com.zgen.entity.CustRoomInfo;
import mm.aeon.com.zgen.entity.CustSecQuestion;
import mm.aeon.com.zgen.entity.CustSecQuestionExample;
import mm.aeon.com.zgen.entity.CustomerInfo;
import mm.aeon.com.zgen.entity.DaCustomerInfoEditReq;
import mm.aeon.com.zgen.entity.MemberInfo;
import mm.aeon.com.zgen.entity.PasswordInfo;
import mm.aeon.com.zgen.entity.PasswordInfoExample;
import mm.aeon.com.zgen.entity.SecurityQuestion;
import mm.aeon.com.zgen.mapper.AppUsageDetailMapper;
import mm.aeon.com.zgen.mapper.CustSecQuestionMapper;
import mm.aeon.com.zgen.mapper.CustomerInfoMapper;
import mm.aeon.com.zgen.mapper.DaCustomerInfoEditReqMapper;
import mm.aeon.com.zgen.mapper.MemberInfoMapper;
import mm.aeon.com.zgen.mapper.PasswordInfoMapper;
import mm.aeon.com.zgen.mapper.SecurityQuestionMapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class CustomerInfoDao {

	@Autowired
	private CustomerInfoCustomMapper customerInfoCustomMapper;

	@Autowired
	private PasswordInfoMapper passwordInfoMapper;

	@Autowired
	private CustSecQuestionMapper custSecQuestionMapper;

	@Autowired
	private AppUsageDetailMapper appUsageDetailMapper;

	@Autowired
	private SecurityQuestionMapper securityQuestionMapper;

	@Autowired
	private MemberInfoMapper memberInfoMapper;

	@Autowired
	private BeanConverter beanConverter;

	@Autowired
	private CustomerInfoMapper customerInfoMapper;

	@Autowired
	private DaCustomerInfoEditReqMapper daCustomerInfoEditReqMapper;

	public CustomerInfoDto getCustomerInfoWithPhoneNoAndNrcNo(String phoneNo, String nrcNo) {
		CustomerInfoDto customerInfoDto = customerInfoCustomMapper.getCustomerInfoWithPhoneNoAndNrcNo(phoneNo, nrcNo);
		return customerInfoDto;
	}

	public CustomerInfoDto getCustomerInfoWithPhoneNo(String phoneNo) {
		CustomerInfoDto customerInfoDto = customerInfoCustomMapper.getCustomerInfoWithPhoneNo(phoneNo);
		return customerInfoDto;
	}

	public CustomerInfoDto getCustomerInfoWithCustomerId(Integer customerId) {
		CustomerInfoDto customerInfoDto = customerInfoCustomMapper.getCustomerInfoWithCustomerId(customerId);
		return customerInfoDto;
	}

	public int getNrcNoCount(String nrcNo, Integer customerId) {
		int count;
		count = customerInfoCustomMapper.getNrcNoCount(nrcNo, customerId);
		return count;
	}

	public int getPhoneNoCount(String phoneNo, Integer customerId) {
		int count;
		count = customerInfoCustomMapper.getPhoneNoCount(phoneNo, customerId);
		return count;
	}

	public int checkAlreadyRegisteredCustomer(String phoneNo, String nrcNo) {
		int count;
		count = customerInfoCustomMapper.checkAlreadyRegisteredCustomer(phoneNo, nrcNo);
		return count;
	}

	public Integer insertCustomerInfo(CustomerInfoDto customerInfoDto) {
		Integer customerId;
		CustomerInfo customerInfo = beanConverter.convert(customerInfoDto, CustomerInfo.class);
		customerInfo.setCreatedBy(customerInfoDto.getName());
		customerInfo.setCreatedTime(CommonUtil.getCurrentTime());
		customerInfoCustomMapper.insertCustomerInfo(customerInfo);
		customerId = customerInfo.getCustomerId();
		return customerId;
	}

	public void insertPasswordInfo(PasswordInfo passwordInfo) {
		passwordInfo.setCreatedBy(passwordInfo.getUserId().toString());
		passwordInfo.setCreatedTime(CommonUtil.getCurrentTime());
		passwordInfoMapper.insertSelective(passwordInfo);
	}

	public void insertCustomerSecurityQuestion(CustomerSecurityQuestionDto customerSecurityQuestionDto) {
		CustSecQuestion custSecQuestion = beanConverter.convert(customerSecurityQuestionDto, CustSecQuestion.class);
		custSecQuestion.setCreatedBy(customerSecurityQuestionDto.getCustomerId().toString());
		custSecQuestion.setCreatedTime(CommonUtil.getCurrentTime());
		custSecQuestionMapper.insertSelective(custSecQuestion);
	}

	public void insertAppUsageDetail(AppUsageDetail appUsageDetail) {
		appUsageDetail.setStartUsageDateTime(CommonUtil.getCurrentTime());
		appUsageDetail.setEndUsageDateTime(CommonUtil.getCurrentTime());
		appUsageDetailMapper.insertSelective(appUsageDetail);
	}

	public SecurityQuestion getSecurityQuestionWithSecQuesId(Integer secQuesId) {
		SecurityQuestion securityQuestion = securityQuestionMapper.selectByPrimaryKey(secQuesId);
		return securityQuestion;
	}

	public void updateSecurityQuestion(SecurityQuestion securityQuestion) {
		securityQuestion.setUpdatedTime(CommonUtil.getCurrentTime());
		securityQuestionMapper.updateByPrimaryKeySelective(securityQuestion);

	}

	public void insertMemberInfo(MemberInfo memberInfo) {
		memberInfo.setCreatedBy(memberInfo.getCustomerId().toString());
		memberInfo.setCreatedTime(CommonUtil.getCurrentTime());
		memberInfoMapper.insertSelective(memberInfo);
	}

	public void updateCustomerPassword(PasswordInfo passwordInfo) {
		passwordInfo.setUpdatedTime(CommonUtil.getCurrentTime());
		PasswordInfoExample passwordInfoExample = new PasswordInfoExample();
		passwordInfoExample.createCriteria().andUserIdEqualTo(passwordInfo.getUserId()).andUserTypeIdEqualTo(CommonConstants.CUSTOMER_TYPE);
		passwordInfoMapper.updateByExampleSelective(passwordInfo, passwordInfoExample);
	}

	public void updateCustomerInfo(CustomerInfoDto customerInfoDto) {
		CustomerInfo customerInfo = beanConverter.convert(customerInfoDto, CustomerInfo.class);
		customerInfo.setUpdatedBy(customerInfoDto.getName());
		customerInfo.setUpdatedTime(CommonUtil.getCurrentTime());
		customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
	}

	public void updateCustomerSecurityQuestionAnswer(CustomerSecurityQuestionDto customerSecurityQuestionDto) {
		CustSecQuestion custSecQuestion = beanConverter.convert(customerSecurityQuestionDto, CustSecQuestion.class);
		custSecQuestion.setUpdatedBy(custSecQuestion.getCustomerId().toString());
		custSecQuestion.setUpdatedTime(CommonUtil.getCurrentTime());
		CustSecQuestionExample custSecQuestionExample = new CustSecQuestionExample();
		custSecQuestionExample.createCriteria().andCustomerIdEqualTo(custSecQuestion.getCustomerId()).andSecQuesIdEqualTo(custSecQuestion.getSecQuesId());
		custSecQuestionMapper.updateByExampleSelective(custSecQuestion, custSecQuestionExample);

	}

	public List<Integer> getAdminIdList() {
		List<Integer> adminIdList;
		adminIdList = customerInfoCustomMapper.getAdminIdList();
		return adminIdList;
	}

	public List<Integer> getAdminIdListForFreeMessaging() {
		List<Integer> adminIdList;
		adminIdList = customerInfoCustomMapper.getAdminIdListForFreeMessaging();
		return adminIdList;
	}

	public Integer insertCustRoomInfo(CustRoomInfoDto custRoomInfoDto) {
		Integer custRoomId;
		CustRoomInfo custRoomInfo = beanConverter.convert(custRoomInfoDto, CustRoomInfo.class);
		customerInfoCustomMapper.insertCustRoomInfo(custRoomInfo);
		custRoomId = custRoomInfo.getCustRoomId();
		return custRoomId;
	}

	public void insertAdminCustRoom(List<Integer> adminIdList, Integer custRoomId) {
		customerInfoCustomMapper.insertAdminCustRoom(adminIdList, custRoomId);
	}

	public int checkAlreadyRegisteredCustomerRoom(Integer customerId) {
		int count;
		count = customerInfoCustomMapper.checkAlreadyRegisteredCustomerRoom(customerId);
		return count;
	}

	public void insertDaCustomerInfoEdit(CustomerInfoEditReqDto customerInfoEditReqDto) {
		DaCustomerInfoEditReq daCustomerInfoEditReq = beanConverter.convert(customerInfoEditReqDto, DaCustomerInfoEditReq.class);
		daCustomerInfoEditReq.setCreatedBy(customerInfoEditReqDto.getName());
		daCustomerInfoEditReq.setCreatedTime(CommonUtil.getCurrentTime());
		daCustomerInfoEditReqMapper.insertSelective(daCustomerInfoEditReq);
	}

	public void updateDaCustomerInfoEdit(CustomerInfoEditReqDto customerInfoEditReqDto) {
		DaCustomerInfoEditReq daCustomerInfoEditReq = beanConverter.convert(customerInfoEditReqDto, DaCustomerInfoEditReq.class);
		daCustomerInfoEditReq.setDelFlag(false);
		daCustomerInfoEditReq.setUpdatedBy(daCustomerInfoEditReq.getName());
		daCustomerInfoEditReq.setUpdatedTime(CommonUtil.getCurrentTime());
		daCustomerInfoEditReqMapper.updateByPrimaryKeySelective(daCustomerInfoEditReq);
	}

	public int checkCustomerInfoEditReqAlreadyExist(Integer customerId) {
		int count;
		count = customerInfoCustomMapper.checkCustomerInfoEditReqAlreadyExist(customerId);
		return count;
	}

	public CustomerInfoEditReqDto getCustomerInfoEditReq(Integer customerId) {
		CustomerInfoEditReqDto customerInfoEditReqDto = customerInfoCustomMapper.getCustomerInfoEditReq(customerId);
		return customerInfoEditReqDto;
	}

}
