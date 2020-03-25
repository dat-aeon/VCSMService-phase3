package mm.aeon.com.dao.importcustomerinfo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.custommapper.importcustomerinfo.ImportCustomerInfoCustomMapper;
import mm.aeon.com.dto.custagreement.CustomerAgreementDto;
import mm.aeon.com.dto.importcustomerinfo.ImportCustomerInfoDto;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ImportCustomerInfoDao {

	@Autowired
	private ImportCustomerInfoCustomMapper importCustomerInfoCustomMapper;

	public int checkMemberWithDobAndNrc(Date dateOfBirth, String nrcNo) {
		return importCustomerInfoCustomMapper.checkMemberWithDobAndNrc(dateOfBirth, nrcNo);
	}

	public ImportCustomerInfoDto getImportCustomerInfoWithDobAndNrc(Date dateOfBirth, String nrcNo) {
		ImportCustomerInfoDto importCustomerInfoDto = importCustomerInfoCustomMapper.getImportCustomerInfoWithDobAndNrc(dateOfBirth, nrcNo);
		return importCustomerInfoDto;
	}

	public ImportCustomerInfoDto getImportCustomerInfoWithCustomerNo(String customerNo) {
		ImportCustomerInfoDto importCustomerInfoDto = importCustomerInfoCustomMapper.getImportCustomerInfoWithCustomerNo(customerNo);
		return importCustomerInfoDto;
	}

	public List<CustomerAgreementDto> getCustomerAgreementListWithImportCustomerId(Integer importCustomerId) {
		List<CustomerAgreementDto> customerAgreementDtoList = importCustomerInfoCustomMapper.getCustomerAgreementListWithImportCustomerId(importCustomerId);
		return customerAgreementDtoList;
	}

	public int getNrcNoCount(String nrcNo) {
		int count;
		count = importCustomerInfoCustomMapper.getNrcNoCount(nrcNo);
		return count;
	}

	public int getPhoneNoCount(String phoneNo) {
		int count;
		count = importCustomerInfoCustomMapper.getPhoneNoCount(phoneNo);
		return count;
	}

}
