package mm.aeon.com.zgen.entity;

public class Roles {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column roles.ROLE_ID
     *
     * @mbg.generated
     */
    private Long roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column roles.ROLE
     *
     * @mbg.generated
     */
    private String role;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column roles.ROLE_ID
     *
     * @return the value of roles.ROLE_ID
     *
     * @mbg.generated
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column roles.ROLE_ID
     *
     * @param roleId the value for roles.ROLE_ID
     *
     * @mbg.generated
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column roles.ROLE
     *
     * @return the value of roles.ROLE
     *
     * @mbg.generated
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column roles.ROLE
     *
     * @param role the value for roles.ROLE
     *
     * @mbg.generated
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table roles
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleId=").append(roleId);
        sb.append(", role=").append(role);
        sb.append("]");
        return sb.toString();
    }
}