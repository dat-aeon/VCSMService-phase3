package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantCompanyInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 968860168093650554L;

	private Integer daApplicantCompanyInfoId;

	private Integer daApplicationInfoId;

	@NotNull
	@NotBlank
	@Size(max = 256)
	private String companyName;

	@Size(max = 512)
	private String companyAddress;

	@NotNull
	@NotBlank
	@Size(max = 20)
	private String companyTelNo;

	private String contactTimeFrom;

	private String contactTimeTo;

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
	private Integer companyStatus;

	private String companyStatusOther;

	@NotNull
	private Double monthlyBasicIncome;

	private Double otherIncome;

	@NotNull
	private Double totalIncome;

	@NotNull
	private Integer salaryDay;

	private String createdBy;
	private String updatedBy;

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
