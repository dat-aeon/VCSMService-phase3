package mm.aeon.com.zconfig.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.custommapper.SecurityMapper;
import mm.aeon.com.dto.LoginUserDto;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class UserDao {
	@Autowired
	private SecurityMapper securityMapper;

	public LoginUserDto findLoginUserByPhoneNo(String phoneNo) {
		return securityMapper.findLoginUserByPhoneNo(phoneNo);
	}

}
