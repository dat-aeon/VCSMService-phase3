package mm.aeon.com.zgen.entity;

import java.util.Date;

public class PlMessageInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.pl_message_info.pl_message_id
     *
     * @mbg.generated
     */
    private Integer plMessageId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.pl_message_info.message_content
     *
     * @mbg.generated
     */
    private String messageContent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.pl_message_info.message_type
     *
     * @mbg.generated
     */
    private Short messageType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.pl_message_info.send_time
     *
     * @mbg.generated
     */
    private Date sendTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.pl_message_info.sender
     *
     * @mbg.generated
     */
    private String sender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.pl_message_info.op_send_flag
     *
     * @mbg.generated
     */
    private Short opSendFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.pl_message_info.read_flag
     *
     * @mbg.generated
     */
    private Short readFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.pl_message_info.read_time
     *
     * @mbg.generated
     */
    private Date readTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.pl_message_info.pl_message_id
     *
     * @return the value of vcs.pl_message_info.pl_message_id
     *
     * @mbg.generated
     */
    public Integer getPlMessageId() {
        return plMessageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.pl_message_info.pl_message_id
     *
     * @param plMessageId the value for vcs.pl_message_info.pl_message_id
     *
     * @mbg.generated
     */
    public void setPlMessageId(Integer plMessageId) {
        this.plMessageId = plMessageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.pl_message_info.message_content
     *
     * @return the value of vcs.pl_message_info.message_content
     *
     * @mbg.generated
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.pl_message_info.message_content
     *
     * @param messageContent the value for vcs.pl_message_info.message_content
     *
     * @mbg.generated
     */
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.pl_message_info.message_type
     *
     * @return the value of vcs.pl_message_info.message_type
     *
     * @mbg.generated
     */
    public Short getMessageType() {
        return messageType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.pl_message_info.message_type
     *
     * @param messageType the value for vcs.pl_message_info.message_type
     *
     * @mbg.generated
     */
    public void setMessageType(Short messageType) {
        this.messageType = messageType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.pl_message_info.send_time
     *
     * @return the value of vcs.pl_message_info.send_time
     *
     * @mbg.generated
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.pl_message_info.send_time
     *
     * @param sendTime the value for vcs.pl_message_info.send_time
     *
     * @mbg.generated
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.pl_message_info.sender
     *
     * @return the value of vcs.pl_message_info.sender
     *
     * @mbg.generated
     */
    public String getSender() {
        return sender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.pl_message_info.sender
     *
     * @param sender the value for vcs.pl_message_info.sender
     *
     * @mbg.generated
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.pl_message_info.op_send_flag
     *
     * @return the value of vcs.pl_message_info.op_send_flag
     *
     * @mbg.generated
     */
    public Short getOpSendFlag() {
        return opSendFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.pl_message_info.op_send_flag
     *
     * @param opSendFlag the value for vcs.pl_message_info.op_send_flag
     *
     * @mbg.generated
     */
    public void setOpSendFlag(Short opSendFlag) {
        this.opSendFlag = opSendFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.pl_message_info.read_flag
     *
     * @return the value of vcs.pl_message_info.read_flag
     *
     * @mbg.generated
     */
    public Short getReadFlag() {
        return readFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.pl_message_info.read_flag
     *
     * @param readFlag the value for vcs.pl_message_info.read_flag
     *
     * @mbg.generated
     */
    public void setReadFlag(Short readFlag) {
        this.readFlag = readFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.pl_message_info.read_time
     *
     * @return the value of vcs.pl_message_info.read_time
     *
     * @mbg.generated
     */
    public Date getReadTime() {
        return readTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.pl_message_info.read_time
     *
     * @param readTime the value for vcs.pl_message_info.read_time
     *
     * @mbg.generated
     */
    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.pl_message_info
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", plMessageId=").append(plMessageId);
        sb.append(", messageContent=").append(messageContent);
        sb.append(", messageType=").append(messageType);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", sender=").append(sender);
        sb.append(", opSendFlag=").append(opSendFlag);
        sb.append(", readFlag=").append(readFlag);
        sb.append(", readTime=").append(readTime);
        sb.append("]");
        return sb.toString();
    }
}