package mm.aeon.com.zconfig.oauth2;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.common.VCSMPasswordEncoder;
import mm.aeon.com.dao.appusageinfo.AppUsageInfoDao;
import mm.aeon.com.dto.LoginUserDto;
import mm.aeon.com.dto.appusageinfo.AppUsageDetailDto;
import mm.aeon.com.dto.appusageinfo.AppUsageInfoDto;
import mm.aeon.com.dto.custagreement.CustomerAgreementDto;
import mm.aeon.com.dto.customerinfo.CustRoomInfoDto;
import mm.aeon.com.dto.customerinfo.CustomerInfoDto;
import mm.aeon.com.dto.customerinfo.UserInformationResDto;
import mm.aeon.com.dto.importcustomerinfo.ImportCustomerInfoDto;
import mm.aeon.com.dto.information.CompanyInfoResDto;
import mm.aeon.com.services.customerinfo.CustomerInfoService;
import mm.aeon.com.services.importcustomerinfo.ImportCustomerInfoService;
import mm.aeon.com.services.information.InformationService;
import mm.aeon.com.zconfig.MessageCode;
import mm.aeon.com.zconfig.exception.BusinessLogicException;

@Service(value = "UserService")
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AppUsageInfoDao appUsageInfoDao;

	@Autowired
	public CustomPasswordEncoder passwordEncoder;

	@Autowired
	private CustomerInfoService customerInfoService;

	@Autowired
	private ImportCustomerInfoService importCustomerInfoService;

	@Autowired
	private InformationService informationService;

	@Autowired
	private MessageSource messageSource;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String password = (String) request.getParameter("password");

		String loginDeviceId = (String) request.getParameter("login_device_id");
		LoginUserDto user;
		user = userDao.findLoginUserByPhoneNo(username);
		CustomerInfoDto customerInfoDtoDb = customerInfoService.getCustomerInfoWithPhoneNo(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		if (customerInfoDtoDb.getApplockFlag() == CommonConstants.LOCK) {
			throw new BusinessLogicException(MessageCode.ACCOUNT_LOCKED, messageSource.getMessage(MessageCode.ACCOUNT_LOCKED, null, null));
		}

		if (!StringUtils.isEmpty(password)) {
			String encodedPassword = VCSMPasswordEncoder.encode(password);
			if (encodedPassword.equals(user.getPassword())) {
				if (!StringUtils.isEmpty(loginDeviceId)) {
					CustomerInfoDto customerInfo = new CustomerInfoDto();
					customerInfo.setCustomerId(customerInfoDtoDb.getCustomerId());
					customerInfo.setLoginDeviceId(loginDeviceId);
					customerInfoService.updateCustomerInfo(customerInfo);
				}
			}
		}

		AppUsageInfoDto appUsageInfoDto = appUsageInfoDao.getAppUsageInfoWithCustomerId(user.getLoginUserId());
		if (appUsageInfoDto != null) {
			AppUsageDetailDto appUsageDetailDto = new AppUsageDetailDto();
			appUsageDetailDto.setAppUsageId(appUsageInfoDto.getAppUsageId());
			appUsageDetailDto.setStartUsageDateTime(CommonUtil.getCurrentTime());
			appUsageDetailDto.setEndUsageDateTime(CommonUtil.getCurrentTime());
			appUsageInfoDao.insertAppUsageDetail(appUsageDetailDto);
		}

		UserInformationResDto userInformationResDto = new UserInformationResDto();
		if (customerInfoDtoDb != null) {

			int customerRoomRegisterCount = customerInfoService.checkAlreadyRegisteredCustomerRoom(customerInfoDtoDb.getCustomerId());

			if (customerRoomRegisterCount == 0) {
				// get active admin list
				List<Integer> adminIdList = customerInfoService.getAdminIdList();
				CustRoomInfoDto custRoomInfoDto = new CustRoomInfoDto();
				custRoomInfoDto.setCustomerId(customerInfoDtoDb.getCustomerId());
				custRoomInfoDto.setCreatedTime(CommonUtil.getCurrentTime());
				custRoomInfoDto.setLastSendTime(CommonUtil.getCurrentTime());
				custRoomInfoDto.setConverLockFlag((short) 0);
				// insert customer room info for messaging
				Integer custRoomId = customerInfoService.insertCustRoomInfo(custRoomInfoDto);
				customerInfoService.insertAdminCustRoom(adminIdList, custRoomId);
			}

			userInformationResDto.setCustomerId(customerInfoDtoDb.getCustomerId());
			userInformationResDto.setCustomerNo(customerInfoDtoDb.getCustomerNo());
			userInformationResDto.setCustomerTypeId(customerInfoDtoDb.getCustomerTypeId());
			userInformationResDto.setDateOfBirth(customerInfoDtoDb.getDob());
			userInformationResDto.setName(customerInfoDtoDb.getName());
			userInformationResDto.setNrcNo(customerInfoDtoDb.getNrcNo());
			userInformationResDto.setPhoneNo(customerInfoDtoDb.getPhoneNo());
			userInformationResDto.setPhotoPath(customerInfoDtoDb.getPhotoPath());
			userInformationResDto.setUserTypeId(customerInfoDtoDb.getUserTypeId());

			if (customerInfoDtoDb.getCustomerNo() != null) {
				ImportCustomerInfoDto importCustomerInfoDto = importCustomerInfoService.getImportCustomerInfoWithCustomerNo(customerInfoDtoDb.getCustomerNo());
				if (importCustomerInfoDto != null) {
					userInformationResDto.setMemberNo(importCustomerInfoDto.getMembercardId());
					List<CustomerAgreementDto> customerAgreementDtoList = importCustomerInfoService
							.getCustomerAgreementListWithImportCustomerId(importCustomerInfoDto.getImportCustomerInfoId());
					userInformationResDto.setCustomerAgreementDtoList(customerAgreementDtoList);

					if (importCustomerInfoDto.getMembercardStatus() != null && importCustomerInfoDto.getMembercardStatus() == 0) {
						userInformationResDto.setMemberNoValid(true);
					} else {
						userInformationResDto.setMemberNoValid(false);
					}
				}
			}
		}
		CompanyInfoResDto companyInfoResDto = informationService.getCompanyInfo();
		if (companyInfoResDto != null) {
			userInformationResDto.setHotlinePhone(companyInfoResDto.getHotlinePhone());
		}
		user.setUserInformationResDto(userInformationResDto);

		return user;
	}

}