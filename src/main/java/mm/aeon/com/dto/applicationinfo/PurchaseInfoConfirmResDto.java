package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseInfoConfirmResDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3074781157021544854L;
	private boolean purchaseConfirm;

}
