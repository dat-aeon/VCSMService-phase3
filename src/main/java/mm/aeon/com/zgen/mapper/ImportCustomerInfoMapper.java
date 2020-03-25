package mm.aeon.com.zgen.mapper;

import java.util.List;
import mm.aeon.com.zgen.entity.ImportCustomerInfo;
import mm.aeon.com.zgen.entity.ImportCustomerInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ImportCustomerInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.import_customer_info
     *
     * @mbg.generated
     */
    long countByExample(ImportCustomerInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.import_customer_info
     *
     * @mbg.generated
     */
    int deleteByExample(ImportCustomerInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.import_customer_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer importCustomerInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.import_customer_info
     *
     * @mbg.generated
     */
    int insert(ImportCustomerInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.import_customer_info
     *
     * @mbg.generated
     */
    int insertSelective(ImportCustomerInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.import_customer_info
     *
     * @mbg.generated
     */
    List<ImportCustomerInfo> selectByExampleWithRowbounds(ImportCustomerInfoExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.import_customer_info
     *
     * @mbg.generated
     */
    List<ImportCustomerInfo> selectByExample(ImportCustomerInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.import_customer_info
     *
     * @mbg.generated
     */
    ImportCustomerInfo selectByPrimaryKey(Integer importCustomerInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.import_customer_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ImportCustomerInfo record, @Param("example") ImportCustomerInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.import_customer_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ImportCustomerInfo record, @Param("example") ImportCustomerInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.import_customer_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ImportCustomerInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.import_customer_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ImportCustomerInfo record);
}