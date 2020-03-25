package mm.aeon.com.services.importcustomerinfo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.dao.importcustomerinfo.ImportCustomerInfoDao;
import mm.aeon.com.dto.custagreement.CustomerAgreementDto;
import mm.aeon.com.dto.importcustomerinfo.ImportCustomerInfoDto;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ImportCustomerInfoService {

	@Autowired
	private ImportCustomerInfoDao importCustomerInfoDao;

	public int checkMemberWithDobAndNrc(Date dateOfBirth, String nrcNo) {
		return importCustomerInfoDao.checkMemberWithDobAndNrc(dateOfBirth, nrcNo);
	}

	public ImportCustomerInfoDto getImportCustomerInfoWithDobAndNrc(Date dateOfBirth, String nrcNo) {
		return importCustomerInfoDao.getImportCustomerInfoWithDobAndNrc(dateOfBirth, nrcNo);
	}

	public ImportCustomerInfoDto getImportCustomerInfoWithCustomerNo(String customerNo) {
		return importCustomerInfoDao.getImportCustomerInfoWithCustomerNo(customerNo);
	}

	public List<CustomerAgreementDto> getCustomerAgreementListWithImportCustomerId(Integer importCustomerId) {
		return importCustomerInfoDao.getCustomerAgreementListWithImportCustomerId(importCustomerId);
	}

	public int getNrcNoCount(String nrcNo) {
		return importCustomerInfoDao.getNrcNoCount(nrcNo);
	}

	public int getPhoneNoCount(String phoneNo) {
		return importCustomerInfoDao.getPhoneNoCount(phoneNo);
	}

}
