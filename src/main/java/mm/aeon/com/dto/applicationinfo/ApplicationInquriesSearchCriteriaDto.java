package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationInquriesSearchCriteriaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2080103373793140803L;

	@NotNull
	private Integer customerId;

	private Integer daLoanTypeId;
	private Date appliedDate;
	private String appliedDateStr;
	private String applicationNo;
	private Integer status;
	private Integer limit;
	private Integer offset;

}
