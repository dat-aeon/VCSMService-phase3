package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuarantorInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7390731192755691078L;
	private Integer daGuarantorInfoId;

	private Integer daApplicationInfoId;

	@NotNull
	@NotBlank
	@Size(max = 256)
	private String name;

	@NotNull
	private Date dob;

	@NotNull
	@NotBlank
	@Size(max = 256)
	private String nrcNo;

	@NotNull
	private Integer nationality;

	@Size(max = 256)
	private String nationalityOther;

	@NotNull
	@NotBlank
	@Size(max = 20)
	private String mobileNo;

	@Size(max = 20)
	private String residentTelNo;

	private Integer relationship;

	@Size(max = 256)
	private String relationshipOther;

	@Size(max = 512)
	private String currentAddress;

	@NotNull
	private Integer typeOfResidence;

	@Size(max = 256)
	private String typeOfResidenceOther;

	private Integer livingWith;

	@Size(max = 256)
	private String livingWithOther;

	@NotNull
	private Integer gender;

	@NotNull
	private Integer maritalStatus;

	private Integer yearOfStayYear;

	private Integer yearOfStayMonth;

	@NotNull
	@NotBlank
	@Size(max = 256)
	private String companyName;

	@NotNull
	@NotBlank
	@Size(max = 20)
	private String companyTelNo;

	@Size(max = 512)
	private String companyAddress;

	@NotNull
	@NotBlank
	@Size(max = 256)
	private String department;

	@NotNull
	@NotBlank
	@Size(max = 256)
	private String position;

	private Integer yearOfServiceYear;

	private Integer yearOfServiceMonth;

	@NotNull
	private Double monthlyBasicIncome;

	@NotNull
	private Double totalIncome;

	private String createdBy;
	private String updatedBy;

	@Size(max = 100)
	private String currentAddressFloor;

	@Size(max = 100)
	private String currentAddressBuildingNo;

	@Size(max = 100)
	private String currentAddressRoomNo;

	@Size(max = 100)
	private String currentAddressStreet;

	@Size(max = 100)
	private String currentAddressQtr;

	private Integer currentAddressTownship;
	private Integer currentAddressCity;

	@Size(max = 100)
	private String companyAddressBuildingNo;

	@Size(max = 100)
	private String companyAddressRoomNo;

	@Size(max = 100)
	private String companyAddressFloor;

	@Size(max = 100)
	private String companyAddressStreet;

	@Size(max = 100)
	private String companyAddressQtr;

	private Integer companyAddressTownship;
	private Integer companyAddressCity;

}
