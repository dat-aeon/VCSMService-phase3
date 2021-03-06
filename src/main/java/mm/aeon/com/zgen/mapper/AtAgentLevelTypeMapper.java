package mm.aeon.com.zgen.mapper;

import java.util.List;
import mm.aeon.com.zgen.entity.AtAgentLevelType;
import mm.aeon.com.zgen.entity.AtAgentLevelTypeExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AtAgentLevelTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level_type
     *
     * @mbg.generated
     */
    long countByExample(AtAgentLevelTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level_type
     *
     * @mbg.generated
     */
    int deleteByExample(AtAgentLevelTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level_type
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer atAgentLevelTypeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level_type
     *
     * @mbg.generated
     */
    int insert(AtAgentLevelType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level_type
     *
     * @mbg.generated
     */
    int insertSelective(AtAgentLevelType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level_type
     *
     * @mbg.generated
     */
    List<AtAgentLevelType> selectByExampleWithRowbounds(AtAgentLevelTypeExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level_type
     *
     * @mbg.generated
     */
    List<AtAgentLevelType> selectByExample(AtAgentLevelTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level_type
     *
     * @mbg.generated
     */
    AtAgentLevelType selectByPrimaryKey(Integer atAgentLevelTypeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level_type
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") AtAgentLevelType record, @Param("example") AtAgentLevelTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level_type
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") AtAgentLevelType record, @Param("example") AtAgentLevelTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level_type
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AtAgentLevelType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level_type
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AtAgentLevelType record);
}