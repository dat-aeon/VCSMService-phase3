package mm.aeon.com.zgen.mapper;

import java.util.List;
import mm.aeon.com.zgen.entity.PlMessageInfo;
import mm.aeon.com.zgen.entity.PlMessageInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PlMessageInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_message_info
     *
     * @mbg.generated
     */
    long countByExample(PlMessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_message_info
     *
     * @mbg.generated
     */
    int deleteByExample(PlMessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_message_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer plMessageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_message_info
     *
     * @mbg.generated
     */
    int insert(PlMessageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_message_info
     *
     * @mbg.generated
     */
    int insertSelective(PlMessageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_message_info
     *
     * @mbg.generated
     */
    List<PlMessageInfo> selectByExampleWithRowbounds(PlMessageInfoExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_message_info
     *
     * @mbg.generated
     */
    List<PlMessageInfo> selectByExample(PlMessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_message_info
     *
     * @mbg.generated
     */
    PlMessageInfo selectByPrimaryKey(Integer plMessageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_message_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") PlMessageInfo record, @Param("example") PlMessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_message_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") PlMessageInfo record, @Param("example") PlMessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_message_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PlMessageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_message_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PlMessageInfo record);
}