package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfoEditReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5787022870214899304L;
	private Integer daCustomerInfoEditReqId;
	private Integer customerId;
	private String customerNo;
	private String name;
	private BigDecimal salary;
	private Date dob;
	private Short gender;
	private String companyName;
	private String nrcNo;
	private String phoneNo;
	private String photoPath;
	private String address;
	private Integer status;
	private String comment;

}
