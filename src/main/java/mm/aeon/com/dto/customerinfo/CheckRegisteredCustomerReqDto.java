package mm.aeon.com.dto.customerinfo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckRegisteredCustomerReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2579831374721189299L;

	@NotNull
	private Date dateOfBirth;

	@NotNull
	@NotEmpty
	private String nrcNo;

	@NotNull
	@NotEmpty
	private String phoneNo;

}
