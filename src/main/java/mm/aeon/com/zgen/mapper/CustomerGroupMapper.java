package mm.aeon.com.zgen.mapper;

import java.util.List;
import mm.aeon.com.zgen.entity.CustomerGroup;
import mm.aeon.com.zgen.entity.CustomerGroupExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CustomerGroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_group
     *
     * @mbg.generated
     */
    long countByExample(CustomerGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_group
     *
     * @mbg.generated
     */
    int deleteByExample(CustomerGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_group
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer custGroupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_group
     *
     * @mbg.generated
     */
    int insert(CustomerGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_group
     *
     * @mbg.generated
     */
    int insertSelective(CustomerGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_group
     *
     * @mbg.generated
     */
    List<CustomerGroup> selectByExampleWithRowbounds(CustomerGroupExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_group
     *
     * @mbg.generated
     */
    List<CustomerGroup> selectByExample(CustomerGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_group
     *
     * @mbg.generated
     */
    CustomerGroup selectByPrimaryKey(Integer custGroupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_group
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CustomerGroup record, @Param("example") CustomerGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_group
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CustomerGroup record, @Param("example") CustomerGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_group
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CustomerGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_group
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CustomerGroup record);
}