package mm.aeon.com.zgen.entity;

import java.util.Date;

public class CustomerCoupon {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.customer_coupon.customer_coupon_id
     *
     * @mbg.generated
     */
    private Integer customerCouponId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.customer_coupon.coupon_id
     *
     * @mbg.generated
     */
    private Integer couponId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.customer_coupon.customer_id
     *
     * @mbg.generated
     */
    private Integer customerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.customer_coupon.status
     *
     * @mbg.generated
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.customer_coupon.updated_by
     *
     * @mbg.generated
     */
    private String updatedBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.customer_coupon.updated_time
     *
     * @mbg.generated
     */
    private Date updatedTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.customer_coupon.customer_coupon_id
     *
     * @return the value of vcs.customer_coupon.customer_coupon_id
     *
     * @mbg.generated
     */
    public Integer getCustomerCouponId() {
        return customerCouponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.customer_coupon.customer_coupon_id
     *
     * @param customerCouponId the value for vcs.customer_coupon.customer_coupon_id
     *
     * @mbg.generated
     */
    public void setCustomerCouponId(Integer customerCouponId) {
        this.customerCouponId = customerCouponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.customer_coupon.coupon_id
     *
     * @return the value of vcs.customer_coupon.coupon_id
     *
     * @mbg.generated
     */
    public Integer getCouponId() {
        return couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.customer_coupon.coupon_id
     *
     * @param couponId the value for vcs.customer_coupon.coupon_id
     *
     * @mbg.generated
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.customer_coupon.customer_id
     *
     * @return the value of vcs.customer_coupon.customer_id
     *
     * @mbg.generated
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.customer_coupon.customer_id
     *
     * @param customerId the value for vcs.customer_coupon.customer_id
     *
     * @mbg.generated
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.customer_coupon.status
     *
     * @return the value of vcs.customer_coupon.status
     *
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.customer_coupon.status
     *
     * @param status the value for vcs.customer_coupon.status
     *
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.customer_coupon.updated_by
     *
     * @return the value of vcs.customer_coupon.updated_by
     *
     * @mbg.generated
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.customer_coupon.updated_by
     *
     * @param updatedBy the value for vcs.customer_coupon.updated_by
     *
     * @mbg.generated
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.customer_coupon.updated_time
     *
     * @return the value of vcs.customer_coupon.updated_time
     *
     * @mbg.generated
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.customer_coupon.updated_time
     *
     * @param updatedTime the value for vcs.customer_coupon.updated_time
     *
     * @mbg.generated
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_coupon
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", customerCouponId=").append(customerCouponId);
        sb.append(", couponId=").append(couponId);
        sb.append(", customerId=").append(customerId);
        sb.append(", status=").append(status);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append("]");
        return sb.toString();
    }
}