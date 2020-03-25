package mm.aeon.com.custommapper.loginuser;

import org.apache.ibatis.annotations.Param;

import mm.aeon.com.zgen.entity.LoginUser;

public interface LoginUserCustomMapper {

	LoginUser searchLoginUserWithEmailAndUserId(@Param("username") String username, @Param("userId") Long userId);

	LoginUser searchLoginUserWithUsername(@Param("username") String username);
}
