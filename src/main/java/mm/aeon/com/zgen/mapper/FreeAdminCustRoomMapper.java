package mm.aeon.com.zgen.mapper;

import java.util.List;
import mm.aeon.com.zgen.entity.FreeAdminCustRoom;
import mm.aeon.com.zgen.entity.FreeAdminCustRoomExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FreeAdminCustRoomMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_admin_cust_room
     *
     * @mbg.generated
     */
    long countByExample(FreeAdminCustRoomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_admin_cust_room
     *
     * @mbg.generated
     */
    int deleteByExample(FreeAdminCustRoomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_admin_cust_room
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer freeAdminCustRoomId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_admin_cust_room
     *
     * @mbg.generated
     */
    int insert(FreeAdminCustRoom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_admin_cust_room
     *
     * @mbg.generated
     */
    int insertSelective(FreeAdminCustRoom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_admin_cust_room
     *
     * @mbg.generated
     */
    List<FreeAdminCustRoom> selectByExampleWithRowbounds(FreeAdminCustRoomExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_admin_cust_room
     *
     * @mbg.generated
     */
    List<FreeAdminCustRoom> selectByExample(FreeAdminCustRoomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_admin_cust_room
     *
     * @mbg.generated
     */
    FreeAdminCustRoom selectByPrimaryKey(Integer freeAdminCustRoomId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_admin_cust_room
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") FreeAdminCustRoom record, @Param("example") FreeAdminCustRoomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_admin_cust_room
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") FreeAdminCustRoom record, @Param("example") FreeAdminCustRoomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_admin_cust_room
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(FreeAdminCustRoom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_admin_cust_room
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FreeAdminCustRoom record);
}