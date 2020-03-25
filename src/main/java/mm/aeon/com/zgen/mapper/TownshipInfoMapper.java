package mm.aeon.com.zgen.mapper;

import java.util.List;
import mm.aeon.com.zgen.entity.TownshipInfo;
import mm.aeon.com.zgen.entity.TownshipInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TownshipInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.township_info
     *
     * @mbg.generated
     */
    long countByExample(TownshipInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.township_info
     *
     * @mbg.generated
     */
    int deleteByExample(TownshipInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.township_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.township_info
     *
     * @mbg.generated
     */
    int insert(TownshipInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.township_info
     *
     * @mbg.generated
     */
    int insertSelective(TownshipInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.township_info
     *
     * @mbg.generated
     */
    List<TownshipInfo> selectByExampleWithRowbounds(TownshipInfoExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.township_info
     *
     * @mbg.generated
     */
    List<TownshipInfo> selectByExample(TownshipInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.township_info
     *
     * @mbg.generated
     */
    TownshipInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.township_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TownshipInfo record, @Param("example") TownshipInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.township_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TownshipInfo record, @Param("example") TownshipInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.township_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TownshipInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.township_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TownshipInfo record);
}