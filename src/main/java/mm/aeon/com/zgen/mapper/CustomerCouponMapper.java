package mm.aeon.com.zgen.mapper;

import java.util.List;
import mm.aeon.com.zgen.entity.CustomerCoupon;
import mm.aeon.com.zgen.entity.CustomerCouponExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CustomerCouponMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_coupon
     *
     * @mbg.generated
     */
    long countByExample(CustomerCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_coupon
     *
     * @mbg.generated
     */
    int deleteByExample(CustomerCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_coupon
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer customerCouponId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_coupon
     *
     * @mbg.generated
     */
    int insert(CustomerCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_coupon
     *
     * @mbg.generated
     */
    int insertSelective(CustomerCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_coupon
     *
     * @mbg.generated
     */
    List<CustomerCoupon> selectByExampleWithRowbounds(CustomerCouponExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_coupon
     *
     * @mbg.generated
     */
    List<CustomerCoupon> selectByExample(CustomerCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_coupon
     *
     * @mbg.generated
     */
    CustomerCoupon selectByPrimaryKey(Integer customerCouponId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_coupon
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CustomerCoupon record, @Param("example") CustomerCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_coupon
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CustomerCoupon record, @Param("example") CustomerCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_coupon
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CustomerCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.customer_coupon
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CustomerCoupon record);
}