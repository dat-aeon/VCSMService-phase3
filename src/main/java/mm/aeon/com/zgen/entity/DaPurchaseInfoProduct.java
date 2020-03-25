package mm.aeon.com.zgen.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DaPurchaseInfoProduct {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_purchase_info_product.da_purchase_info_product_id
     *
     * @mbg.generated
     */
    private Integer daPurchaseInfoProductId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_purchase_info_product.da_purchase_info_id
     *
     * @mbg.generated
     */
    private Integer daPurchaseInfoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_purchase_info_product.da_loan_type_id
     *
     * @mbg.generated
     */
    private Integer daLoanTypeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_purchase_info_product.da_product_type_id
     *
     * @mbg.generated
     */
    private Integer daProductTypeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_purchase_info_product.product_description
     *
     * @mbg.generated
     */
    private String productDescription;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_purchase_info_product.brand
     *
     * @mbg.generated
     */
    private String brand;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_purchase_info_product.model
     *
     * @mbg.generated
     */
    private String model;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_purchase_info_product.price
     *
     * @mbg.generated
     */
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_purchase_info_product.cash_down_amount
     *
     * @mbg.generated
     */
    private BigDecimal cashDownAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_purchase_info_product.created_time
     *
     * @mbg.generated
     */
    private Date createdTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_purchase_info_product.created_by
     *
     * @mbg.generated
     */
    private String createdBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_purchase_info_product.updated_time
     *
     * @mbg.generated
     */
    private Date updatedTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_purchase_info_product.updated_by
     *
     * @mbg.generated
     */
    private String updatedBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.da_purchase_info_product.del_flag
     *
     * @mbg.generated
     */
    private Boolean delFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_purchase_info_product.da_purchase_info_product_id
     *
     * @return the value of vcs.da_purchase_info_product.da_purchase_info_product_id
     *
     * @mbg.generated
     */
    public Integer getDaPurchaseInfoProductId() {
        return daPurchaseInfoProductId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_purchase_info_product.da_purchase_info_product_id
     *
     * @param daPurchaseInfoProductId the value for vcs.da_purchase_info_product.da_purchase_info_product_id
     *
     * @mbg.generated
     */
    public void setDaPurchaseInfoProductId(Integer daPurchaseInfoProductId) {
        this.daPurchaseInfoProductId = daPurchaseInfoProductId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_purchase_info_product.da_purchase_info_id
     *
     * @return the value of vcs.da_purchase_info_product.da_purchase_info_id
     *
     * @mbg.generated
     */
    public Integer getDaPurchaseInfoId() {
        return daPurchaseInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_purchase_info_product.da_purchase_info_id
     *
     * @param daPurchaseInfoId the value for vcs.da_purchase_info_product.da_purchase_info_id
     *
     * @mbg.generated
     */
    public void setDaPurchaseInfoId(Integer daPurchaseInfoId) {
        this.daPurchaseInfoId = daPurchaseInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_purchase_info_product.da_loan_type_id
     *
     * @return the value of vcs.da_purchase_info_product.da_loan_type_id
     *
     * @mbg.generated
     */
    public Integer getDaLoanTypeId() {
        return daLoanTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_purchase_info_product.da_loan_type_id
     *
     * @param daLoanTypeId the value for vcs.da_purchase_info_product.da_loan_type_id
     *
     * @mbg.generated
     */
    public void setDaLoanTypeId(Integer daLoanTypeId) {
        this.daLoanTypeId = daLoanTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_purchase_info_product.da_product_type_id
     *
     * @return the value of vcs.da_purchase_info_product.da_product_type_id
     *
     * @mbg.generated
     */
    public Integer getDaProductTypeId() {
        return daProductTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_purchase_info_product.da_product_type_id
     *
     * @param daProductTypeId the value for vcs.da_purchase_info_product.da_product_type_id
     *
     * @mbg.generated
     */
    public void setDaProductTypeId(Integer daProductTypeId) {
        this.daProductTypeId = daProductTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_purchase_info_product.product_description
     *
     * @return the value of vcs.da_purchase_info_product.product_description
     *
     * @mbg.generated
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_purchase_info_product.product_description
     *
     * @param productDescription the value for vcs.da_purchase_info_product.product_description
     *
     * @mbg.generated
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_purchase_info_product.brand
     *
     * @return the value of vcs.da_purchase_info_product.brand
     *
     * @mbg.generated
     */
    public String getBrand() {
        return brand;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_purchase_info_product.brand
     *
     * @param brand the value for vcs.da_purchase_info_product.brand
     *
     * @mbg.generated
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_purchase_info_product.model
     *
     * @return the value of vcs.da_purchase_info_product.model
     *
     * @mbg.generated
     */
    public String getModel() {
        return model;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_purchase_info_product.model
     *
     * @param model the value for vcs.da_purchase_info_product.model
     *
     * @mbg.generated
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_purchase_info_product.price
     *
     * @return the value of vcs.da_purchase_info_product.price
     *
     * @mbg.generated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_purchase_info_product.price
     *
     * @param price the value for vcs.da_purchase_info_product.price
     *
     * @mbg.generated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_purchase_info_product.cash_down_amount
     *
     * @return the value of vcs.da_purchase_info_product.cash_down_amount
     *
     * @mbg.generated
     */
    public BigDecimal getCashDownAmount() {
        return cashDownAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_purchase_info_product.cash_down_amount
     *
     * @param cashDownAmount the value for vcs.da_purchase_info_product.cash_down_amount
     *
     * @mbg.generated
     */
    public void setCashDownAmount(BigDecimal cashDownAmount) {
        this.cashDownAmount = cashDownAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_purchase_info_product.created_time
     *
     * @return the value of vcs.da_purchase_info_product.created_time
     *
     * @mbg.generated
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_purchase_info_product.created_time
     *
     * @param createdTime the value for vcs.da_purchase_info_product.created_time
     *
     * @mbg.generated
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_purchase_info_product.created_by
     *
     * @return the value of vcs.da_purchase_info_product.created_by
     *
     * @mbg.generated
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_purchase_info_product.created_by
     *
     * @param createdBy the value for vcs.da_purchase_info_product.created_by
     *
     * @mbg.generated
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_purchase_info_product.updated_time
     *
     * @return the value of vcs.da_purchase_info_product.updated_time
     *
     * @mbg.generated
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_purchase_info_product.updated_time
     *
     * @param updatedTime the value for vcs.da_purchase_info_product.updated_time
     *
     * @mbg.generated
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_purchase_info_product.updated_by
     *
     * @return the value of vcs.da_purchase_info_product.updated_by
     *
     * @mbg.generated
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_purchase_info_product.updated_by
     *
     * @param updatedBy the value for vcs.da_purchase_info_product.updated_by
     *
     * @mbg.generated
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.da_purchase_info_product.del_flag
     *
     * @return the value of vcs.da_purchase_info_product.del_flag
     *
     * @mbg.generated
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.da_purchase_info_product.del_flag
     *
     * @param delFlag the value for vcs.da_purchase_info_product.del_flag
     *
     * @mbg.generated
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.da_purchase_info_product
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", daPurchaseInfoProductId=").append(daPurchaseInfoProductId);
        sb.append(", daPurchaseInfoId=").append(daPurchaseInfoId);
        sb.append(", daLoanTypeId=").append(daLoanTypeId);
        sb.append(", daProductTypeId=").append(daProductTypeId);
        sb.append(", productDescription=").append(productDescription);
        sb.append(", brand=").append(brand);
        sb.append(", model=").append(model);
        sb.append(", price=").append(price);
        sb.append(", cashDownAmount=").append(cashDownAmount);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", delFlag=").append(delFlag);
        sb.append("]");
        return sb.toString();
    }
}