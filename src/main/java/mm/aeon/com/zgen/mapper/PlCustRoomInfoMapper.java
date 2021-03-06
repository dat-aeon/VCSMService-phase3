package mm.aeon.com.zgen.mapper;

import java.util.List;
import mm.aeon.com.zgen.entity.PlCustRoomInfo;
import mm.aeon.com.zgen.entity.PlCustRoomInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PlCustRoomInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_cust_room_info
     *
     * @mbg.generated
     */
    long countByExample(PlCustRoomInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_cust_room_info
     *
     * @mbg.generated
     */
    int deleteByExample(PlCustRoomInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_cust_room_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer plCustRoomId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_cust_room_info
     *
     * @mbg.generated
     */
    int insert(PlCustRoomInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_cust_room_info
     *
     * @mbg.generated
     */
    int insertSelective(PlCustRoomInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_cust_room_info
     *
     * @mbg.generated
     */
    List<PlCustRoomInfo> selectByExampleWithRowbounds(PlCustRoomInfoExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_cust_room_info
     *
     * @mbg.generated
     */
    List<PlCustRoomInfo> selectByExample(PlCustRoomInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_cust_room_info
     *
     * @mbg.generated
     */
    PlCustRoomInfo selectByPrimaryKey(Integer plCustRoomId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_cust_room_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") PlCustRoomInfo record, @Param("example") PlCustRoomInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_cust_room_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") PlCustRoomInfo record, @Param("example") PlCustRoomInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_cust_room_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PlCustRoomInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_cust_room_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PlCustRoomInfo record);
}