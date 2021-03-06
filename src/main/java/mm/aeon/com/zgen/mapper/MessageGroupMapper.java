package mm.aeon.com.zgen.mapper;

import java.util.List;
import mm.aeon.com.zgen.entity.MessageGroup;
import mm.aeon.com.zgen.entity.MessageGroupExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MessageGroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.message_group
     *
     * @mbg.generated
     */
    long countByExample(MessageGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.message_group
     *
     * @mbg.generated
     */
    int deleteByExample(MessageGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.message_group
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer mesgGroupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.message_group
     *
     * @mbg.generated
     */
    int insert(MessageGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.message_group
     *
     * @mbg.generated
     */
    int insertSelective(MessageGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.message_group
     *
     * @mbg.generated
     */
    List<MessageGroup> selectByExampleWithRowbounds(MessageGroupExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.message_group
     *
     * @mbg.generated
     */
    List<MessageGroup> selectByExample(MessageGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.message_group
     *
     * @mbg.generated
     */
    MessageGroup selectByPrimaryKey(Integer mesgGroupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.message_group
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") MessageGroup record, @Param("example") MessageGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.message_group
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") MessageGroup record, @Param("example") MessageGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.message_group
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MessageGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.message_group
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MessageGroup record);
}