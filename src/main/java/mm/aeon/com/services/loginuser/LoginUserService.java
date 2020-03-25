package mm.aeon.com.services.loginuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.dao.loginuser.LoginUserDao;
import mm.aeon.com.dto.LoginUserDto;
import mm.aeon.com.zgen.entity.LoginUser;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LoginUserService {

	@Autowired
	private LoginUserDao loginUserDao;

	public void loginUserRegistration(LoginUserDto loginUserDto) {
		loginUserDao.loginUserRegistration(loginUserDto);
	}

	public void updateLoginUser(LoginUser loginUser) {
		loginUserDao.updateLoginUser(loginUser);
	}

	public LoginUser searchLoginUserWithEmailAndUserId(String username, Long userId) {
		return loginUserDao.searchLoginUserWithEmailAndUserId(username, userId);
	}

	public LoginUser searchLoginUserWithUsername(String username) {
		return loginUserDao.searchLoginUserWithUsername(username);
	}

	public void updateByExampleSelectiveWithUserIdAndRoleId(LoginUser loginUser, Long roleId) {
		loginUserDao.updateByExampleSelectiveWithUserIdAndRoleId(loginUser, roleId);
	}

}
