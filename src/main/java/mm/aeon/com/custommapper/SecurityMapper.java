package mm.aeon.com.custommapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import mm.aeon.com.dto.LoginUserDto;

@Mapper
public interface SecurityMapper {
	/**
	 * For Spring Security
	 */
	public LoginUserDto findLoginUserByPhoneNo(@Param("phoneNo") String phoneNo);
}