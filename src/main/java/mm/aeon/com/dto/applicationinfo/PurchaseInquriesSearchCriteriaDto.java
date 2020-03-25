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
public class PurchaseInquriesSearchCriteriaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5572046501998080863L;

	@NotNull
	private Integer agentId;
	private Date settlementDate;
	private String settlementDateStr;

}
