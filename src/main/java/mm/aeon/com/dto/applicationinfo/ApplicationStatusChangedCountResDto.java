package mm.aeon.com.dto.applicationinfo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationStatusChangedCountResDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 6608191478204750069L;

	private Integer applicationStatusChangedCount;

}
