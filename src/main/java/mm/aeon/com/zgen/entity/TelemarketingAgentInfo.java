package mm.aeon.com.zgen.entity;

public class TelemarketingAgentInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.telemarketing_agent_info.telemarketing_agent_id
     *
     * @mbg.generated
     */
    private Integer telemarketingAgentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.telemarketing_agent_info.agent_level_id
     *
     * @mbg.generated
     */
    private Integer agentLevelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.telemarketing_agent_info.agent_name
     *
     * @mbg.generated
     */
    private String agentName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.telemarketing_agent_info.call_count
     *
     * @mbg.generated
     */
    private Integer callCount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.telemarketing_agent_info.telemarketing_agent_id
     *
     * @return the value of vcs.telemarketing_agent_info.telemarketing_agent_id
     *
     * @mbg.generated
     */
    public Integer getTelemarketingAgentId() {
        return telemarketingAgentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.telemarketing_agent_info.telemarketing_agent_id
     *
     * @param telemarketingAgentId the value for vcs.telemarketing_agent_info.telemarketing_agent_id
     *
     * @mbg.generated
     */
    public void setTelemarketingAgentId(Integer telemarketingAgentId) {
        this.telemarketingAgentId = telemarketingAgentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.telemarketing_agent_info.agent_level_id
     *
     * @return the value of vcs.telemarketing_agent_info.agent_level_id
     *
     * @mbg.generated
     */
    public Integer getAgentLevelId() {
        return agentLevelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.telemarketing_agent_info.agent_level_id
     *
     * @param agentLevelId the value for vcs.telemarketing_agent_info.agent_level_id
     *
     * @mbg.generated
     */
    public void setAgentLevelId(Integer agentLevelId) {
        this.agentLevelId = agentLevelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.telemarketing_agent_info.agent_name
     *
     * @return the value of vcs.telemarketing_agent_info.agent_name
     *
     * @mbg.generated
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.telemarketing_agent_info.agent_name
     *
     * @param agentName the value for vcs.telemarketing_agent_info.agent_name
     *
     * @mbg.generated
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.telemarketing_agent_info.call_count
     *
     * @return the value of vcs.telemarketing_agent_info.call_count
     *
     * @mbg.generated
     */
    public Integer getCallCount() {
        return callCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.telemarketing_agent_info.call_count
     *
     * @param callCount the value for vcs.telemarketing_agent_info.call_count
     *
     * @mbg.generated
     */
    public void setCallCount(Integer callCount) {
        this.callCount = callCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.telemarketing_agent_info
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", telemarketingAgentId=").append(telemarketingAgentId);
        sb.append(", agentLevelId=").append(agentLevelId);
        sb.append(", agentName=").append(agentName);
        sb.append(", callCount=").append(callCount);
        sb.append("]");
        return sb.toString();
    }
}