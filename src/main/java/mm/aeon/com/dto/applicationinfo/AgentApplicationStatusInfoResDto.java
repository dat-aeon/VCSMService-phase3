package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentApplicationStatusInfoResDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2472671745898580975L;
	private Integer daApplicationInfoId;
	private String customerName;
	private String nrcNo;
	private String phoneNo;
	private String applicationNo;
	private Double financeAmount;
	private Integer financeTerm;
	private Double approvedFinanceAmount;
	private Integer approvedFinanceTerm;
	private Integer status;

}
