package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mm.aeon.com.dto.appusageinfo.AppUsageInfoDto;
import mm.aeon.com.dto.customersecurityquestion.CustomerSecurityQuestionDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfoDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2284174505612954335L;
	private Integer customerId;
	private String customerNo;
	private String phoneNo;
	private Integer customerTypeId;
	private Integer userTypeId;
	private String name;
	private Double salary;
	private Date dob;
	private Short gender;
	private String companyName;
	private String nrcNo;
	private String address;
	private Date joinDate;
	private String photoPath;
	private byte[] photoByte;
	private String password;
	private Short applockFlag;
	private List<CustomerSecurityQuestionDto> customerSecurityQuestionDtoList;
	private AppUsageInfoDto appUsageInfoDto;
	private String loginDeviceId;

}
