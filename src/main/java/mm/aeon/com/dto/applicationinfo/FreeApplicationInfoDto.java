package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mm.aeon.com.dto.customersecurityquestion.CustomerSecurityQuestionDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreeApplicationInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -881415092459842358L;

	private Integer daApplicationInfoId;

	private String applicationNo;

	@NotNull
	private Integer daApplicationTypeId;

	private Date appliedDate;

	@NotNull
	@NotBlank
	@Size(max = 256)
	private String name;

	@NotNull
	private Date dob;

	@NotNull
	@NotBlank
	@Size(max = 50)
	private String nrcNo;

	@NotNull
	@NotBlank
	@Size(max = 256)
	private String fatherName;

	@NotNull
	private Integer nationality;

	@Size(max = 256)
	private String nationalityOther;

	@NotNull
	private Integer gender;

	@NotNull
	private Integer maritalStatus;

	@Size(max = 512)
	private String currentAddress;

	@Size(max = 512)
	private String permanentAddress;

	@NotNull
	private Integer typeOfResidence;

	@Size(max = 256)
	private String typeOfResidenceOther;

	@NotNull
	private Integer livingWith;

	@Size(max = 256)
	private String livingWithOther;

	private Integer yearOfStayYear;
	private Integer yearOfStayMonth;

	@NotNull
	@NotBlank
	@Size(max = 20)
	private String mobileNo;

	@Size(max = 20)
	private String residentTelNo;

	@Size(max = 20)
	private String otherPhoneNo;

	@Size(max = 50)
	private String email;

	private Integer customerId;

	private Integer status;

	private String settlementPendingComment;

	private Integer daLoanTypeId;

	@NotNull
	private Double financeAmount;

	@NotNull
	private Integer financeTerm;

	private Double approvedAmount;

	private Integer daProductTypeId;

	@Size(max = 256)
	private String productDescription;

	@NotNull
	private Integer channelType;

	private Boolean saleEntryCheckFlag;

	@NotNull
	private Integer highestEducationTypeId;

	@NotNull
	@Valid
	private ApplicantCompanyInfoDto applicantCompanyInfoDto;

	@NotNull
	@Valid
	private EmergencyContactInfoDto emergencyContactInfoDto;

	@NotNull
	@Valid
	private GuarantorInfoDto guarantorInfoDto;

	@NotNull
	@NotEmpty
	@Valid
	private List<ApplicationInfoAttachmentDto> applicationInfoAttachmentDtoList;

	private String createdBy;
	private String updatedBy;

	private Integer daInterestInfoId;
	private Integer daCompulsoryInfoId;
	private InterestInfoDto interestInfoDto;
	private CompulsoryInfoDto compulsoryInfoDto;

	private Double processingFees;
	private Double totalInterest;
	private Double totalRepayment;
	private Double monthlyInstallment;
	private Double firstPaymentForPSG;
	private Double firstPayment;
	private Double lastPayment;
	private Double modifyTotalRepayment;
	private Double totalConSaving;

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
	private String permanentAddressBuildingNo;

	@Size(max = 100)
	private String permanentAddressRoomNo;

	@Size(max = 100)
	private String permanentAddressFloor;

	@Size(max = 100)
	private String permanentAddressStreet;

	@Size(max = 100)
	private String permanentAddressQtr;

	private Integer permanentAddressTownship;
	private Integer permanentAddressCity;

	private List<CustomerSecurityQuestionDto> customerSecurityQuestionDtoList;
	private String password;

	private Double approvedFinanceAmount;
	private Integer approvedFinanceTerm;
	private Double modifyFinanceAmount;
	private Integer modifyFinanceTerm;

}
