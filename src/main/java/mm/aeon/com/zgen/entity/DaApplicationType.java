package mm.aeon.com.zgen.entity;

public class DaApplicationType {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_application_type.da_application_type_id
     *
     * @mbg.generated
     */
    private Integer daApplicationTypeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_application_type.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_application_type.del_flag
     *
     * @mbg.generated
     */
    private Boolean delFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_application_type.da_application_type_id
     *
     * @return the value of vcs.da_application_type.da_application_type_id
     *
     * @mbg.generated
     */
    public Integer getDaApplicationTypeId() {
        return daApplicationTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_application_type.da_application_type_id
     *
     * @param daApplicationTypeId the value for vcs.da_application_type.da_application_type_id
     *
     * @mbg.generated
     */
    public void setDaApplicationTypeId(Integer daApplicationTypeId) {
        this.daApplicationTypeId = daApplicationTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_application_type.name
     *
     * @return the value of vcs.da_application_type.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_application_type.name
     *
     * @param name the value for vcs.da_application_type.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_application_type.del_flag
     *
     * @return the value of vcs.da_application_type.del_flag
     *
     * @mbg.generated
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_application_type.del_flag
     *
     * @param delFlag the value for vcs.da_application_type.del_flag
     *
     * @mbg.generated
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.da_application_type
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", daApplicationTypeId=").append(daApplicationTypeId);
        sb.append(", name=").append(name);
        sb.append(", delFlag=").append(delFlag);
        sb.append("]");
        return sb.toString();
    }
}