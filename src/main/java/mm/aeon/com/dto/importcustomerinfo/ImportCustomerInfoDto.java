package mm.aeon.com.dto.importcustomerinfo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportCustomerInfoDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 3684381083708063808L;
	private Integer importCustomerInfoId;
	private String customerNo;
	private String phoneNo;
	private String membercardId;
	private Integer membercardStatus;
	private String name;
	private Double salary;
	private Date dob;
	private Short gender;
	private String companyName;
	private String nrcNo;
	private String address;
}
