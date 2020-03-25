package mm.aeon.com.dto.appusageinfo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUsageInfoDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 231854253000353497L;
	private Integer appUsageId;
	private Integer customerId;
	private Date registrationDateTime;
	private String phoneModel;
	private String manufacture;
	private String sdk;
	private String osType;
	private String osVersion;
	private String resolution;
	private String instructionSet;
	private String cpuArchitecture;

}
