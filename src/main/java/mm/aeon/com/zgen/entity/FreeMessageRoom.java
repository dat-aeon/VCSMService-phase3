package mm.aeon.com.zgen.entity;

import java.util.Date;

public class FreeMessageRoom {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.free_message_room.free_message_room_id
     *
     * @mbg.generated
     */
    private Integer freeMessageRoomId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.free_message_room.free_cust_room_info_id
     *
     * @mbg.generated
     */
    private Integer freeCustRoomInfoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.free_message_room.free_message_info_id
     *
     * @mbg.generated
     */
    private Integer freeMessageInfoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.free_message_room.created_time
     *
     * @mbg.generated
     */
    private Date createdTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.free_message_room.free_message_room_id
     *
     * @return the value of vcs.free_message_room.free_message_room_id
     *
     * @mbg.generated
     */
    public Integer getFreeMessageRoomId() {
        return freeMessageRoomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.free_message_room.free_message_room_id
     *
     * @param freeMessageRoomId the value for vcs.free_message_room.free_message_room_id
     *
     * @mbg.generated
     */
    public void setFreeMessageRoomId(Integer freeMessageRoomId) {
        this.freeMessageRoomId = freeMessageRoomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.free_message_room.free_cust_room_info_id
     *
     * @return the value of vcs.free_message_room.free_cust_room_info_id
     *
     * @mbg.generated
     */
    public Integer getFreeCustRoomInfoId() {
        return freeCustRoomInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.free_message_room.free_cust_room_info_id
     *
     * @param freeCustRoomInfoId the value for vcs.free_message_room.free_cust_room_info_id
     *
     * @mbg.generated
     */
    public void setFreeCustRoomInfoId(Integer freeCustRoomInfoId) {
        this.freeCustRoomInfoId = freeCustRoomInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.free_message_room.free_message_info_id
     *
     * @return the value of vcs.free_message_room.free_message_info_id
     *
     * @mbg.generated
     */
    public Integer getFreeMessageInfoId() {
        return freeMessageInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.free_message_room.free_message_info_id
     *
     * @param freeMessageInfoId the value for vcs.free_message_room.free_message_info_id
     *
     * @mbg.generated
     */
    public void setFreeMessageInfoId(Integer freeMessageInfoId) {
        this.freeMessageInfoId = freeMessageInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.free_message_room.created_time
     *
     * @return the value of vcs.free_message_room.created_time
     *
     * @mbg.generated
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.free_message_room.created_time
     *
     * @param createdTime the value for vcs.free_message_room.created_time
     *
     * @mbg.generated
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", freeMessageRoomId=").append(freeMessageRoomId);
        sb.append(", freeCustRoomInfoId=").append(freeCustRoomInfoId);
        sb.append(", freeMessageInfoId=").append(freeMessageInfoId);
        sb.append(", createdTime=").append(createdTime);
        sb.append("]");
        return sb.toString();
    }
}