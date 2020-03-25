package mm.aeon.com.common;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QRScanObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7729541459465280482L;
	private Integer customerId;
	private Integer daApplicationInfoId;
	private String agreementNo;

}
