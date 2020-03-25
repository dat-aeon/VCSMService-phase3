package mm.aeon.com.zgen.mapper;

import java.util.List;
import mm.aeon.com.zgen.entity.AtBrand;
import mm.aeon.com.zgen.entity.AtBrandExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AtBrandMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_brand
     *
     * @mbg.generated
     */
    long countByExample(AtBrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_brand
     *
     * @mbg.generated
     */
    int deleteByExample(AtBrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_brand
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer brandId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_brand
     *
     * @mbg.generated
     */
    int insert(AtBrand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_brand
     *
     * @mbg.generated
     */
    int insertSelective(AtBrand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_brand
     *
     * @mbg.generated
     */
    List<AtBrand> selectByExampleWithRowbounds(AtBrandExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_brand
     *
     * @mbg.generated
     */
    List<AtBrand> selectByExample(AtBrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_brand
     *
     * @mbg.generated
     */
    AtBrand selectByPrimaryKey(Integer brandId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_brand
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") AtBrand record, @Param("example") AtBrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_brand
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") AtBrand record, @Param("example") AtBrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_brand
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AtBrand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_brand
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AtBrand record);
}