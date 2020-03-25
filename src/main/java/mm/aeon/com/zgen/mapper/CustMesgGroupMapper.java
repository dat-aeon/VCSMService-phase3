package mm.aeon.com.zgen.mapper;

import java.util.List;
import mm.aeon.com.zgen.entity.CustMesgGroup;
import mm.aeon.com.zgen.entity.CustMesgGroupExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CustMesgGroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.cust_mesg_group
     *
     * @mbg.generated
     */
    long countByExample(CustMesgGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.cust_mesg_group
     *
     * @mbg.generated
     */
    int deleteByExample(CustMesgGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.cust_mesg_group
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer custMesgGroupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.cust_mesg_group
     *
     * @mbg.generated
     */
    int insert(CustMesgGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.cust_mesg_group
     *
     * @mbg.generated
     */
    int insertSelective(CustMesgGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.cust_mesg_group
     *
     * @mbg.generated
     */
    List<CustMesgGroup> selectByExampleWithRowbounds(CustMesgGroupExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.cust_mesg_group
     *
     * @mbg.generated
     */
    List<CustMesgGroup> selectByExample(CustMesgGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.cust_mesg_group
     *
     * @mbg.generated
     */
    CustMesgGroup selectByPrimaryKey(Integer custMesgGroupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.cust_mesg_group
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CustMesgGroup record, @Param("example") CustMesgGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.cust_mesg_group
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CustMesgGroup record, @Param("example") CustMesgGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.cust_mesg_group
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CustMesgGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.cust_mesg_group
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CustMesgGroup record);
}