package mm.aeon.com.dao.loginuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mm.aeon.com.common.BeanConverter;
import mm.aeon.com.common.CommonUtil;
import mm.aeon.com.custommapper.loginuser.LoginUserCustomMapper;
import mm.aeon.com.dto.LoginUserDto;
import mm.aeon.com.zgen.entity.LoginUser;
import mm.aeon.com.zgen.entity.LoginUserExample;
import mm.aeon.com.zgen.mapper.LoginUserMapper;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class LoginUserDao {

	@Autowired
	private LoginUserCustomMapper loginUserCustomMapper;

	@Autowired
	private LoginUserMapper loginUserMapper;

	@Autowired
	private BeanConverter beanConverter;

	public void loginUserRegistration(LoginUserDto loginUserDto) {
		LoginUser loginUser = beanConverter.convert(loginUserDto, LoginUser.class);
		loginUser.setCreatedDate(CommonUtil.getCurrentTime());
		loginUserMapper.insertSelective(loginUser);
	}

	public void updateLoginUser(LoginUser loginUser) {
		loginUser.setUpdatedDate(CommonUtil.getCurrentTime());
		loginUserMapper.updateByPrimaryKeySelective(loginUser);
	}

	public LoginUser searchLoginUserWithEmailAndUserId(String username, Long userId) {
		return loginUserCustomMapper.searchLoginUserWithEmailAndUserId(username, userId);
	}

	public LoginUser searchLoginUserWithUsername(String username) {
		return loginUserCustomMapper.searchLoginUserWithUsername(username);
	}

	public void updateByExampleSelectiveWithUserIdAndRoleId(LoginUser loginUser, Long roleId) {
		loginUser.setUpdatedDate(CommonUtil.getCurrentTime());
		LoginUserExample loginUserExample = new LoginUserExample();
		loginUserExample.createCriteria().andUserIdEqualTo(loginUser.getUserId()).andRoleIdEqualTo(roleId);
		loginUserMapper.updateByExampleSelective(loginUser, loginUserExample);
		loginUserMapper.updateByPrimaryKeySelective(loginUser);
	}

}
