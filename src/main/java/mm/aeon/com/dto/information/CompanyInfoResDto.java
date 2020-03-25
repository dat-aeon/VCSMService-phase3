package mm.aeon.com.dto.information;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInfoResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7994420268158529909L;
	private Integer companyInfoId;
	private String addressEn;
	private String addressMm;
	private String hotlinePhone;
	private String webAddress;
	private String socialMediaAddress;
	private String aboutCompanyEn;
	private String aboutCompanyMm;
}
