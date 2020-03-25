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
public class EmergencyContactInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8157754670604458446L;

	private Integer daEmergencyContactInfoId;

	private Integer daApplicationInfoId;

	@NotNull
	@NotBlank
	@Size(max = 256)
	private String name;

	@NotNull
	private Integer relationship;

	@Size(max = 256)
	private String relationshipOther;

	@Size(max = 256)
	private String currentAddress;

	@NotNull
	@NotBlank
	@Size(max = 20)
	private String mobileNo;

	@Size(max = 20)
	private String residentTelNo;

	@Size(max = 20)
	private String otherPhoneNo;

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

}
