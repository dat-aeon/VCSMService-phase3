package mm.aeon.com.zgen.entity;

public class DaUserDepartment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_user_department.da_user_department_id
     *
     * @mbg.generated
     */
    private Integer daUserDepartmentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_user_department.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_user_department.da_department_info_id
     *
     * @mbg.generated
     */
    private Integer daDepartmentInfoId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_user_department.da_user_department_id
     *
     * @return the value of vcs.da_user_department.da_user_department_id
     *
     * @mbg.generated
     */
    public Integer getDaUserDepartmentId() {
        return daUserDepartmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_user_department.da_user_department_id
     *
     * @param daUserDepartmentId the value for vcs.da_user_department.da_user_department_id
     *
     * @mbg.generated
     */
    public void setDaUserDepartmentId(Integer daUserDepartmentId) {
        this.daUserDepartmentId = daUserDepartmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_user_department.user_id
     *
     * @return the value of vcs.da_user_department.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_user_department.user_id
     *
     * @param userId the value for vcs.da_user_department.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_user_department.da_department_info_id
     *
     * @return the value of vcs.da_user_department.da_department_info_id
     *
     * @mbg.generated
     */
    public Integer getDaDepartmentInfoId() {
        return daDepartmentInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_user_department.da_department_info_id
     *
     * @param daDepartmentInfoId the value for vcs.da_user_department.da_department_info_id
     *
     * @mbg.generated
     */
    public void setDaDepartmentInfoId(Integer daDepartmentInfoId) {
        this.daDepartmentInfoId = daDepartmentInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.da_user_department
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", daUserDepartmentId=").append(daUserDepartmentId);
        sb.append(", userId=").append(userId);
        sb.append(", daDepartmentInfoId=").append(daDepartmentInfoId);
        sb.append("]");
        return sb.toString();
    }
}