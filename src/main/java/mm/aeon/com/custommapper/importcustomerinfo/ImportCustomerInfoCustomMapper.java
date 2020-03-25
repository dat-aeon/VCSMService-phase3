package mm.aeon.com.custommapper.importcustomerinfo;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import mm.aeon.com.dto.custagreement.CustomerAgreementDto;
import mm.aeon.com.dto.importcustomerinfo.ImportCustomerInfoDto;

public interface ImportCustomerInfoCustomMapper {

	int checkMemberWithDobAndNrc(@Param("dateOfBirth") Date dateOfBirth, @Param("nrcNo") String nrcNo);

	ImportCustomerInfoDto getImportCustomerInfoWithDobAndNrc(@Param("dateOfBirth") Date dateOfBirth, @Param("nrcNo") String nrcNo);

	ImportCustomerInfoDto getImportCustomerInfoWithCustomerNo(@Param("customerNo") String customerNo);

	List<CustomerAgreementDto> getCustomerAgreementListWithImportCustomerId(@Param("importCustomerId") Integer importCustomerId);

	int getNrcNoCount(@Param("nrcNo") String nrcNo);

	int getPhoneNoCount(@Param("phoneNo") String phoneNo);
}
