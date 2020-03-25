package mm.aeon.com.zgen.mapper;

import java.util.List;
import mm.aeon.com.zgen.entity.SecurityQuestion;
import mm.aeon.com.zgen.entity.SecurityQuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SecurityQuestionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.security_question
     *
     * @mbg.generated
     */
    long countByExample(SecurityQuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.security_question
     *
     * @mbg.generated
     */
    int deleteByExample(SecurityQuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.security_question
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer secQuesId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.security_question
     *
     * @mbg.generated
     */
    int insert(SecurityQuestion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.security_question
     *
     * @mbg.generated
     */
    int insertSelective(SecurityQuestion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.security_question
     *
     * @mbg.generated
     */
    List<SecurityQuestion> selectByExampleWithRowbounds(SecurityQuestionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.security_question
     *
     * @mbg.generated
     */
    List<SecurityQuestion> selectByExample(SecurityQuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.security_question
     *
     * @mbg.generated
     */
    SecurityQuestion selectByPrimaryKey(Integer secQuesId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.security_question
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SecurityQuestion record, @Param("example") SecurityQuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.security_question
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SecurityQuestion record, @Param("example") SecurityQuestionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.security_question
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SecurityQuestion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.security_question
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SecurityQuestion record);
}