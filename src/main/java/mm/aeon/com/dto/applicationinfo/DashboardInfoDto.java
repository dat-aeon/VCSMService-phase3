package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2242251353751260594L;
	private int rejectedCount;
	private int purchaseConfirmedCount;
	private int purchaseCompletedCount;
	private int purchaseCanceledCount;
	private int canceledCount;
	private int appliedCount;
	private int approvedCount;
	private int attachmentEditRequestedCount;

}
