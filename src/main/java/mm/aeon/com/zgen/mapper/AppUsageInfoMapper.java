package mm.aeon.com.zgen.mapper;

import java.util.List;
import mm.aeon.com.zgen.entity.AppUsageInfo;
import mm.aeon.com.zgen.entity.AppUsageInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AppUsageInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.app_usage_info
     *
     * @mbg.generated
     */
    long countByExample(AppUsageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.app_usage_info
     *
     * @mbg.generated
     */
    int deleteByExample(AppUsageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.app_usage_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer appUsageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.app_usage_info
     *
     * @mbg.generated
     */
    int insert(AppUsageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.app_usage_info
     *
     * @mbg.generated
     */
    int insertSelective(AppUsageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.app_usage_info
     *
     * @mbg.generated
     */
    List<AppUsageInfo> selectByExampleWithRowbounds(AppUsageInfoExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.app_usage_info
     *
     * @mbg.generated
     */
    List<AppUsageInfo> selectByExample(AppUsageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.app_usage_info
     *
     * @mbg.generated
     */
    AppUsageInfo selectByPrimaryKey(Integer appUsageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.app_usage_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") AppUsageInfo record, @Param("example") AppUsageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.app_usage_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") AppUsageInfo record, @Param("example") AppUsageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.app_usage_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AppUsageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.app_usage_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AppUsageInfo record);
}