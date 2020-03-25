package mm.aeon.com.api.customerinfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Random;

import javax.validation.Valid;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import mm.aeon.com.common.CommonConstants;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.common.FileHandler;
import mm.aeon.com.dto.JsonHeaderDto;
import mm.aeon.com.dto.customerinfo.CheckMemberInfoReqDto;
import mm.aeon.com.dto.customerinfo.CheckMemberInfoResDto;
import mm.aeon.com.dto.customerinfo.CheckRegisteredCustomerReqDto;
import mm.aeon.com.dto.customerinfo.CheckRegisteredCustomerResDto;
import mm.aeon.com.dto.customerinfo.CustomerIdResDto;
import mm.aeon.com.dto.customerinfo.CustomerInfoDto;
import mm.aeon.com.dto.customerinfo.OTPInfoReqDto;
import mm.aeon.com.dto.customerinfo.OTPInfoResDto;
import mm.aeon.com.dto.customersecurityquestion.CustomerSecurityQuestionDto;
import mm.aeon.com.dto.importcustomerinfo.ImportCustomerInfoDto;
import mm.aeon.com.dto.information.CompanyInfoResDto;
import mm.aeon.com.services.customerinfo.CustomerInfoService;
import mm.aeon.com.services.importcustomerinfo.ImportCustomerInfoService;
import mm.aeon.com.services.information.InformationService;
import mm.aeon.com.zconfig.MessageCode;
import mm.aeon.com.zconfig.exception.BusinessLogicException;

@RestController
@RequestMapping("/customer-info-registration")
public class CustomerInfoRegistrationController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CustomerInfoService customerInfoService;

	@Autowired
	private ImportCustomerInfoService importCustomerInfoService;

	@Autowired
	private InformationService informationService;

	@Value("${properties.imageBaseFilePath}")
	private String imageBaseFilePath;

	@Value("${properties.profileImageFolder}")
	private String profileImageFolder;

	@Value("${properties.otpMainUrl}")
	private String otpMainUrl;

	@Value("${properties.otpPhoneUrl}")
	private String otpPhoneUrl;

	@Value("${properties.otpUsernameUrl}")
	private String otpUsernameUrl;

	@Value("${properties.otpPasswordUrl}")
	private String otpPasswordUrl;

	@Value("${properties.otpUnicodeUrl}")
	private String otpUnicodeUrl;

	@Value("${properties.otpMessageUrl}")
	private String otpMessageUrl;

	@Value("${properties.otpProjectIdUrl}")
	private String otpProjectIdUrl;

	@Value("${properties.otpProductionFlag}")
	private Boolean otpProductionFlag;

	@RequestMapping(value = "/check-member", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public CheckMemberInfoResDto checkMember(@RequestBody CheckMemberInfoReqDto checkMemberInfoReqDto) throws JSONException, IOException {
		validateCheckMemberInfoInput(checkMemberInfoReqDto);

		CheckMemberInfoResDto checkMemberInfoResDto = new CheckMemberInfoResDto();
		// check already member or not
		ImportCustomerInfoDto importCustomerInfoDto = importCustomerInfoService.getImportCustomerInfoWithDobAndNrc(checkMemberInfoReqDto.getDateOfBirth(),
				checkMemberInfoReqDto.getNrcNo());
		if (importCustomerInfoDto != null) {
			CompanyInfoResDto companyInfoResDto = informationService.getCompanyInfo();
			if (companyInfoResDto == null) {
				throw new BusinessLogicException(MessageCode.MISSING_COMPANY_INFO, messageSource.getMessage(MessageCode.MISSING_COMPANY_INFO, null, null));
			} else {
				importCustomerInfoDto.setPhoneNo(CommonUtil.modifyInvalidPhone(importCustomerInfoDto.getPhoneNo()));
				// check duplicate import phone no
				int phoneNoCount = customerInfoService.getPhoneNoCount(importCustomerInfoDto.getPhoneNo(), null);
				if (phoneNoCount > 0) {
					throw new BusinessLogicException(MessageCode.DUPLICATED_IMPORT_PHONE_NO, messageSource.getMessage(MessageCode.DUPLICATED_IMPORT_PHONE_NO, null, null));
				}

				// check duplicate import nrc no
				int nrcNoCount = customerInfoService.getNrcNoCount(importCustomerInfoDto.getNrcNo(), null);
				if (nrcNoCount > 0) {
					throw new BusinessLogicException(MessageCode.DUPLICATED_IMPORT_NRC_NO, messageSource.getMessage(MessageCode.DUPLICATED_IMPORT_NRC_NO, null, null));
				}

				checkMemberInfoResDto.setHotlinePhone(companyInfoResDto.getHotlinePhone());
				checkMemberInfoResDto.setMemberStatus(CommonConstants.MEMBER);
				checkMemberInfoResDto.setMemberPhoneNo(importCustomerInfoDto.getPhoneNo());
			}
		} else {

			// check duplicate nrc no in import customer info table
			int nrcNoCount = importCustomerInfoService.getNrcNoCount(checkMemberInfoReqDto.getNrcNo());
			if (nrcNoCount > 0) {
				throw new BusinessLogicException(MessageCode.DUPLICATED_NRC_NO_CORE_SYSTEM, messageSource.getMessage(MessageCode.DUPLICATED_NRC_NO_CORE_SYSTEM, null, null));
			}

			checkMemberInfoResDto.setMemberStatus(CommonConstants.NON_MEMBER);
		}

		return checkMemberInfoResDto;
	}

	@RequestMapping(value = "/check-registered-customer", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public CheckRegisteredCustomerResDto checkRegisteredCustomer(@Valid @RequestBody CheckRegisteredCustomerReqDto checkRegisteredCustomerReqDto)
			throws JSONException, IOException {
		CheckRegisteredCustomerResDto checkRegisteredCustomerResDto = new CheckRegisteredCustomerResDto();

		int alreadyRegisteredCount = customerInfoService.checkAlreadyRegisteredCustomer(checkRegisteredCustomerReqDto.getPhoneNo(), checkRegisteredCustomerReqDto.getNrcNo());
		if (alreadyRegisteredCount > 0) {
			checkRegisteredCustomerResDto.setRegisteredFlag(true);
		} else {
			// check duplicate phone no
			int phoneNoCount = customerInfoService.getPhoneNoCount(checkRegisteredCustomerReqDto.getPhoneNo(), null);
			if (phoneNoCount > 0) {
				throw new BusinessLogicException(MessageCode.DUPLICATED_PHONE_NO, messageSource.getMessage(MessageCode.DUPLICATED_PHONE_NO, null, null));
			}

			// check duplicate nrc no
			int nrcNoCount = customerInfoService.getNrcNoCount(checkRegisteredCustomerReqDto.getNrcNo(), null);
			if (nrcNoCount > 0) {
				throw new BusinessLogicException(MessageCode.DUPLICATED_NRC_NO, messageSource.getMessage(MessageCode.DUPLICATED_NRC_NO, null, null));
			}

			checkRegisteredCustomerResDto.setRegisteredFlag(false);
		}

		return checkRegisteredCustomerResDto;
	}

	@RequestMapping(value = "/register-new-customer", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public CustomerIdResDto registerNewCustomer(@RequestBody CustomerInfoDto customerInfoDto) {
		validateRegisterNewCustomerInput(customerInfoDto);
		customerInfoDto.setName(customerInfoDto.getName().toUpperCase());
		customerInfoDto.setJoinDate(CommonUtil.getCurrentTime());
		customerInfoDto.setCustomerTypeId(CommonConstants.NON_MEMBER_TYPE); // NON_MEMBER
		customerInfoDto.setUserTypeId(CommonConstants.CUSTOMER_TYPE); // CUSTOMER_TYPE
		customerInfoDto.setPhoneNo(CommonUtil.modifyInvalidPhone(customerInfoDto.getPhoneNo()));
		Integer customerId = customerInfoService.registerNewCustomer(customerInfoDto);
		CustomerIdResDto customerIdResDto = new CustomerIdResDto();
		customerIdResDto.setCustomerId(customerId);
		return customerIdResDto;
	}

	@RequestMapping(value = "/register-old-customer", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public CustomerIdResDto registerOldCustomer(@RequestBody CustomerInfoDto customerInfoDto) {
		validateRegisterOldCustomerInput(customerInfoDto);

		ImportCustomerInfoDto importCustomerInfoDto = importCustomerInfoService.getImportCustomerInfoWithDobAndNrc(customerInfoDto.getDob(), customerInfoDto.getNrcNo());
		if (importCustomerInfoDto == null) {
			throw new BusinessLogicException(MessageCode.MISSING_IMPORT_CUSTOMER_INFO, messageSource.getMessage(MessageCode.MISSING_IMPORT_CUSTOMER_INFO, null, null));
		}
		importCustomerInfoDto.setPhoneNo(CommonUtil.modifyInvalidPhone(importCustomerInfoDto.getPhoneNo()));

		// check duplicate phone no in customer info table with old customer
		// phone no
		int phoneNoCount = customerInfoService.getPhoneNoCount(importCustomerInfoDto.getPhoneNo(), null);
		if (phoneNoCount > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_IMPORT_PHONE_NO, messageSource.getMessage(MessageCode.DUPLICATED_IMPORT_PHONE_NO, null, null));
		}

		// check duplicate nrc no in customer info table with old customer
		// nrc no
		int nrcNoCount = customerInfoService.getNrcNoCount(importCustomerInfoDto.getNrcNo(), null);
		if (nrcNoCount > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_IMPORT_NRC_NO, messageSource.getMessage(MessageCode.DUPLICATED_IMPORT_NRC_NO, null, null));
		}

		// transfer import customer info data to customer info
		customerInfoDto.setAddress(importCustomerInfoDto.getAddress());
		customerInfoDto.setCompanyName(importCustomerInfoDto.getCompanyName());
		customerInfoDto.setCustomerNo(importCustomerInfoDto.getCustomerNo());
		customerInfoDto.setDob(importCustomerInfoDto.getDob());
		customerInfoDto.setGender(importCustomerInfoDto.getGender());
		customerInfoDto.setName(importCustomerInfoDto.getName().toUpperCase());
		customerInfoDto.setNrcNo(importCustomerInfoDto.getNrcNo());
		customerInfoDto.setPhoneNo(importCustomerInfoDto.getPhoneNo());
		customerInfoDto.setSalary(importCustomerInfoDto.getSalary());
		customerInfoDto.setJoinDate(CommonUtil.getCurrentTime());
		customerInfoDto.setCustomerTypeId(CommonConstants.MEMBER_TYPE); // NON_MEMBER
		customerInfoDto.setUserTypeId(CommonConstants.CUSTOMER_TYPE); // CUSTOMER_TYPE

		// image file create
		if (ArrayUtils.isNotEmpty(customerInfoDto.getPhotoByte())) {
			String fileName = CommonUtil.formatByPattern(CommonUtil.getCurrentTime(), "yyyyMMddhhmmssSSS") + ".png";
			customerInfoDto.setPhotoPath(fileName);
			try {
				FileHandler.createFile(new File(imageBaseFilePath + profileImageFolder + fileName), customerInfoDto.getPhotoByte());
			} catch (IOException e) {
				throw new BusinessLogicException(MessageCode.FILE_WRITING_ERROR, messageSource.getMessage(MessageCode.FILE_WRITING_ERROR, null, null));
			}
		}

		Integer customerId = customerInfoService.registerOldCustomer(customerInfoDto);
		CustomerIdResDto customerIdResDto = new CustomerIdResDto();
		customerIdResDto.setCustomerId(customerId);
		return customerIdResDto;

	}

	@RequestMapping(value = "/send-otp", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public OTPInfoResDto sendOtp(@RequestBody OTPInfoReqDto otpInfoReqDto) throws JSONException, IOException {
		OTPInfoResDto oTPInfoResDto = new OTPInfoResDto();
		Random r = new Random();
		String randomNumber = String.format("%04d", (Object) Integer.valueOf(r.nextInt(10000)));
		String phoneNo = otpInfoReqDto.getPhoneNo().substring(1, otpInfoReqDto.getPhoneNo().length());

		if (otpProductionFlag) {
			connectURL(phoneNo, randomNumber);
			oTPInfoResDto.setOtpCode(randomNumber);
		} else {
			oTPInfoResDto.setOtpCode(randomNumber);
		}

		return oTPInfoResDto;
	}

	// Async
	private JsonHeaderDto connectURL(String phoneNo, String otpCode) throws IOException, JSONException {

		String url = otpMainUrl + otpPhoneUrl + phoneNo + otpUsernameUrl + otpPasswordUrl + otpUnicodeUrl + otpMessageUrl;
		String message = "AEON Member OTP Code : " + otpCode;

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url + URLEncoder.encode(message, "UTF-8") + otpProjectIdUrl);

		// add request header
		HttpResponse response = client.execute(request);
		int status = response.getStatusLine().getStatusCode();
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";

		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();

		JsonHeaderDto jsonHeaderDto = null;

		if (status == 200) {

			jsonHeaderDto = new Gson().fromJson(result.toString(), JsonHeaderDto.class);

		}
		return jsonHeaderDto;
	}

	private void validateRegisterOldCustomerInput(CustomerInfoDto customerInfoDto) {
		// check input fields
		if (StringUtils.isEmpty(customerInfoDto.getDob()) || StringUtils.isEmpty(customerInfoDto.getNrcNo()) || StringUtils.isEmpty(customerInfoDto.getPhoneNo())
				|| StringUtils.isEmpty(customerInfoDto.getPassword())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		if (CollectionUtils.isEmpty(customerInfoDto.getCustomerSecurityQuestionDtoList())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		} else {
			for (CustomerSecurityQuestionDto customerSecurityQuestionDto : customerInfoDto.getCustomerSecurityQuestionDtoList()) {
				if (StringUtils.isEmpty(customerSecurityQuestionDto.getSecQuesId()) || StringUtils.isEmpty(customerSecurityQuestionDto.getAnswer())) {
					throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
				}
			}
		}

		if (customerInfoDto.getAppUsageInfoDto() == null) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		} else {
			if (StringUtils.isEmpty(customerInfoDto.getAppUsageInfoDto().getPhoneModel()) || StringUtils.isEmpty(customerInfoDto.getAppUsageInfoDto().getManufacture())
					|| StringUtils.isEmpty(customerInfoDto.getAppUsageInfoDto().getSdk()) || StringUtils.isEmpty(customerInfoDto.getAppUsageInfoDto().getOsType())
					|| StringUtils.isEmpty(customerInfoDto.getAppUsageInfoDto().getOsVersion())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
			}
		}

		// check password length
		if (customerInfoDto.getPassword().length() < CommonConstants.PASSWORD_LENGTH_MIN || customerInfoDto.getPassword().length() > CommonConstants.PASSWORD_LENGTH_MAX) {
			throw new BusinessLogicException(MessageCode.INVALID_PASSWORD_LENGTH, messageSource.getMessage(MessageCode.INVALID_PASSWORD_LENGTH, null, null));
		}

		// check password strength
		// String passwordStrength =
		// CommonUtil.checkPasswordStrength(customerInfoDto.getPassword());
		// if (passwordStrength.equals(CommonConstants.PASSWORD_WEAK)) {
		// throw new BusinessLogicException(MessageCode.PASSWORD_WEAK,
		// messageSource.getMessage(MessageCode.PASSWORD_WEAK, null, null));
		// }

		// check already registered customer
		int alreadyRegisteredCount = customerInfoService.checkAlreadyRegisteredCustomer(customerInfoDto.getPhoneNo(), customerInfoDto.getNrcNo());
		if (alreadyRegisteredCount > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_CUSTOMER_INFO, messageSource.getMessage(MessageCode.DUPLICATED_CUSTOMER_INFO, null, null));
		}

		// check duplicate phone no
		int phoneNoCount = customerInfoService.getPhoneNoCount(customerInfoDto.getPhoneNo(), null);
		if (phoneNoCount > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_PHONE_NO, messageSource.getMessage(MessageCode.DUPLICATED_PHONE_NO, null, null));
		}

		// check duplicate nrc no
		int nrcNoCount = customerInfoService.getNrcNoCount(customerInfoDto.getNrcNo(), null);
		if (nrcNoCount > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_NRC_NO, messageSource.getMessage(MessageCode.DUPLICATED_NRC_NO, null, null));
		}

		// validation of minimum date of birth year must be 18.
		Calendar birthDate = Calendar.getInstance();
		birthDate.setTime(customerInfoDto.getDob());
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
		if (age < 18) {
			throw new BusinessLogicException(MessageCode.INVALID_AGE, messageSource.getMessage(MessageCode.INVALID_AGE, null, null));
		}

		for (CustomerSecurityQuestionDto customerSecurityQuestionDto : customerInfoDto.getCustomerSecurityQuestionDtoList()) {
			if (StringUtils.isEmpty(customerSecurityQuestionDto.getAnswer()) || StringUtils.isEmpty(customerSecurityQuestionDto.getSecQuesId())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
			}

			if (!CommonUtil.isPureAscii(customerSecurityQuestionDto.getAnswer())) {
				throw new BusinessLogicException(MessageCode.INVALID_CHARACTER, messageSource.getMessage(MessageCode.INVALID_CHARACTER,
						new String[] { "secQuesId-" + customerSecurityQuestionDto.getSecQuesId().toString() + " : answer" }, null));
			}
		}

	}

	private void validateRegisterNewCustomerInput(CustomerInfoDto customerInfoDto) {

		// check input fields
		if (StringUtils.isEmpty(customerInfoDto.getDob()) || StringUtils.isEmpty(customerInfoDto.getNrcNo()) || StringUtils.isEmpty(customerInfoDto.getPhoneNo())
				|| StringUtils.isEmpty(customerInfoDto.getName()) || StringUtils.isEmpty(customerInfoDto.getPassword())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		if (!CommonUtil.isPureAscii(customerInfoDto.getName())) {
			throw new BusinessLogicException(MessageCode.INVALID_CHARACTER, messageSource.getMessage(MessageCode.INVALID_CHARACTER, new String[] { "Name" }, null));
		}

		if (CollectionUtils.isEmpty(customerInfoDto.getCustomerSecurityQuestionDtoList())) {
			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		} else {
			for (CustomerSecurityQuestionDto customerSecurityQuestionDto : customerInfoDto.getCustomerSecurityQuestionDtoList()) {
				if (StringUtils.isEmpty(customerSecurityQuestionDto.getSecQuesId()) || StringUtils.isEmpty(customerSecurityQuestionDto.getAnswer())) {
					throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
				}
			}
		}

		if (customerInfoDto.getAppUsageInfoDto() != null) {
			if (StringUtils.isEmpty(customerInfoDto.getAppUsageInfoDto().getPhoneModel()) || StringUtils.isEmpty(customerInfoDto.getAppUsageInfoDto().getManufacture())
					|| StringUtils.isEmpty(customerInfoDto.getAppUsageInfoDto().getSdk()) || StringUtils.isEmpty(customerInfoDto.getAppUsageInfoDto().getOsType())
					|| StringUtils.isEmpty(customerInfoDto.getAppUsageInfoDto().getOsVersion())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
			}
		}

		// check password length
		if (customerInfoDto.getPassword().length() < CommonConstants.PASSWORD_LENGTH_MIN || customerInfoDto.getPassword().length() > CommonConstants.PASSWORD_LENGTH_MAX) {
			throw new BusinessLogicException(MessageCode.INVALID_PASSWORD_LENGTH, messageSource.getMessage(MessageCode.INVALID_PASSWORD_LENGTH, null, null));
		}

		// check password strength
		// String passwordStrength =
		// CommonUtil.checkPasswordStrength(customerInfoDto.getPassword());
		// if (passwordStrength.equals(CommonConstants.PASSWORD_WEAK)) {
		// throw new BusinessLogicException(MessageCode.PASSWORD_WEAK,
		// messageSource.getMessage(MessageCode.PASSWORD_WEAK, null, null));
		// }

		// check already registered customer
		int alreadyRegisteredCount = customerInfoService.checkAlreadyRegisteredCustomer(customerInfoDto.getPhoneNo(), customerInfoDto.getNrcNo());
		if (alreadyRegisteredCount > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_CUSTOMER_INFO, messageSource.getMessage(MessageCode.DUPLICATED_CUSTOMER_INFO, null, null));
		}

		// check duplicate phone no
		int phoneNoCount = customerInfoService.getPhoneNoCount(customerInfoDto.getPhoneNo(), null);
		if (phoneNoCount > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_PHONE_NO, messageSource.getMessage(MessageCode.DUPLICATED_PHONE_NO, null, null));
		}

		// check duplicate nrc no
		int nrcNoCount = customerInfoService.getNrcNoCount(customerInfoDto.getNrcNo(), null);
		if (nrcNoCount > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_NRC_NO, messageSource.getMessage(MessageCode.DUPLICATED_NRC_NO, null, null));
		}

		// check duplicate nrc no in import customer info table
		int nrcNoCountInImportCustomer = importCustomerInfoService.getNrcNoCount(customerInfoDto.getNrcNo());
		if (nrcNoCountInImportCustomer > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_NRC_NO_CORE_SYSTEM, messageSource.getMessage(MessageCode.DUPLICATED_NRC_NO_CORE_SYSTEM, null, null));
		}

		// validation of minimum date of birth year must be 18.
		Calendar birthDate = Calendar.getInstance();
		birthDate.setTime(customerInfoDto.getDob());
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
		if (age < 18) {
			throw new BusinessLogicException(MessageCode.INVALID_AGE, messageSource.getMessage(MessageCode.INVALID_AGE, null, null));
		}

		for (CustomerSecurityQuestionDto customerSecurityQuestionDto : customerInfoDto.getCustomerSecurityQuestionDtoList()) {
			if (StringUtils.isEmpty(customerSecurityQuestionDto.getAnswer()) || StringUtils.isEmpty(customerSecurityQuestionDto.getSecQuesId())) {
				throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
			}

			if (!CommonUtil.isPureAscii(customerSecurityQuestionDto.getAnswer())) {
				throw new BusinessLogicException(MessageCode.INVALID_CHARACTER, messageSource.getMessage(MessageCode.INVALID_CHARACTER,
						new String[] { "secQuesId-" + customerSecurityQuestionDto.getSecQuesId().toString() + " : answer" }, null));
			}
		}

	}

	private void validateCheckMemberInfoInput(CheckMemberInfoReqDto checkMemberInfoReqDto) {
		// check input fields
		if (StringUtils.isEmpty(checkMemberInfoReqDto.getDateOfBirth()) || StringUtils.isEmpty(checkMemberInfoReqDto.getName())
				|| StringUtils.isEmpty(checkMemberInfoReqDto.getNrcNo()) || StringUtils.isEmpty(checkMemberInfoReqDto.getPassword())
				|| StringUtils.isEmpty(checkMemberInfoReqDto.getPhoneNo())) {

			throw new BusinessLogicException(MessageCode.REQUIRED_INPUT, messageSource.getMessage(MessageCode.REQUIRED_INPUT, null, null));
		}

		if (!CommonUtil.isPureAscii(checkMemberInfoReqDto.getName())) {
			throw new BusinessLogicException(MessageCode.INVALID_CHARACTER, messageSource.getMessage(MessageCode.INVALID_CHARACTER, new String[] { "Name" }, null));
		}

		// check password length
		if (checkMemberInfoReqDto.getPassword().length() < CommonConstants.PASSWORD_LENGTH_MIN
				|| checkMemberInfoReqDto.getPassword().length() > CommonConstants.PASSWORD_LENGTH_MAX) {
			throw new BusinessLogicException(MessageCode.INVALID_PASSWORD_LENGTH, messageSource.getMessage(MessageCode.INVALID_PASSWORD_LENGTH, null, null));
		}

		// check password strength
		// String passwordStrength =
		// CommonUtil.checkPasswordStrength(checkMemberInfoReqDto.getPassword());
		// if (passwordStrength.equals(CommonConstants.PASSWORD_WEAK)) {
		// throw new BusinessLogicException(MessageCode.PASSWORD_WEAK,
		// messageSource.getMessage(MessageCode.PASSWORD_WEAK, null, null));
		// }

		// check already registered customer
		int alreadyRegisteredCount = customerInfoService.checkAlreadyRegisteredCustomer(checkMemberInfoReqDto.getPhoneNo(), checkMemberInfoReqDto.getNrcNo());
		if (alreadyRegisteredCount > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_CUSTOMER_INFO, messageSource.getMessage(MessageCode.DUPLICATED_CUSTOMER_INFO, null, null));
		}

		// check duplicate phone no
		int phoneNoCount = customerInfoService.getPhoneNoCount(checkMemberInfoReqDto.getPhoneNo(), null);
		if (phoneNoCount > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_PHONE_NO, messageSource.getMessage(MessageCode.DUPLICATED_PHONE_NO, null, null));
		}

		// check duplicate nrc no
		int nrcNoCount = customerInfoService.getNrcNoCount(checkMemberInfoReqDto.getNrcNo(), null);
		if (nrcNoCount > 0) {
			throw new BusinessLogicException(MessageCode.DUPLICATED_NRC_NO, messageSource.getMessage(MessageCode.DUPLICATED_NRC_NO, null, null));
		}

		// validation of minimum date of birth year must be 18.
		Calendar birthDate = Calendar.getInstance();
		birthDate.setTime(checkMemberInfoReqDto.getDateOfBirth());
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
		if (age < 18) {
			throw new BusinessLogicException(MessageCode.INVALID_AGE, messageSource.getMessage(MessageCode.INVALID_AGE, null, null));
		}
	}

}
