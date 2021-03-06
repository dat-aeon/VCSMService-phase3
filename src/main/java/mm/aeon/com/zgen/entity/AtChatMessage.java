package mm.aeon.com.zgen.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AtChatMessage {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.chat_message_id
     *
     * @mbg.generated
     */
    private Integer chatMessageId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.da_product_type_id
     *
     * @mbg.generated
     */
    private Integer daProductTypeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.customer_id
     *
     * @mbg.generated
     */
    private Integer customerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.brand_id
     *
     * @mbg.generated
     */
    private Integer brandId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.customer_location
     *
     * @mbg.generated
     */
    private String customerLocation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.message_content
     *
     * @mbg.generated
     */
    private String messageContent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.price
     *
     * @mbg.generated
     */
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.phone_no
     *
     * @mbg.generated
     */
    private String phoneNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.url_link
     *
     * @mbg.generated
     */
    private String urlLink;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.send_time
     *
     * @mbg.generated
     */
    private Date sendTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.agent_level_id
     *
     * @mbg.generated
     */
    private Integer agentLevelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.agent_name
     *
     * @mbg.generated
     */
    private String agentName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.call_count
     *
     * @mbg.generated
     */
    private Integer callCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.op_send_flag
     *
     * @mbg.generated
     */
    private Integer opSendFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.del_flag
     *
     * @mbg.generated
     */
    private Short delFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.at_chat_message.read_flag
     *
     * @mbg.generated
     */
    private Integer readFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.chat_message_id
     *
     * @return the value of vcs.at_chat_message.chat_message_id
     *
     * @mbg.generated
     */
    public Integer getChatMessageId() {
        return chatMessageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.chat_message_id
     *
     * @param chatMessageId the value for vcs.at_chat_message.chat_message_id
     *
     * @mbg.generated
     */
    public void setChatMessageId(Integer chatMessageId) {
        this.chatMessageId = chatMessageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.da_product_type_id
     *
     * @return the value of vcs.at_chat_message.da_product_type_id
     *
     * @mbg.generated
     */
    public Integer getDaProductTypeId() {
        return daProductTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.da_product_type_id
     *
     * @param daProductTypeId the value for vcs.at_chat_message.da_product_type_id
     *
     * @mbg.generated
     */
    public void setDaProductTypeId(Integer daProductTypeId) {
        this.daProductTypeId = daProductTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.customer_id
     *
     * @return the value of vcs.at_chat_message.customer_id
     *
     * @mbg.generated
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.customer_id
     *
     * @param customerId the value for vcs.at_chat_message.customer_id
     *
     * @mbg.generated
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.brand_id
     *
     * @return the value of vcs.at_chat_message.brand_id
     *
     * @mbg.generated
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.brand_id
     *
     * @param brandId the value for vcs.at_chat_message.brand_id
     *
     * @mbg.generated
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.customer_location
     *
     * @return the value of vcs.at_chat_message.customer_location
     *
     * @mbg.generated
     */
    public String getCustomerLocation() {
        return customerLocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.customer_location
     *
     * @param customerLocation the value for vcs.at_chat_message.customer_location
     *
     * @mbg.generated
     */
    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.message_content
     *
     * @return the value of vcs.at_chat_message.message_content
     *
     * @mbg.generated
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.message_content
     *
     * @param messageContent the value for vcs.at_chat_message.message_content
     *
     * @mbg.generated
     */
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.price
     *
     * @return the value of vcs.at_chat_message.price
     *
     * @mbg.generated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.price
     *
     * @param price the value for vcs.at_chat_message.price
     *
     * @mbg.generated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.phone_no
     *
     * @return the value of vcs.at_chat_message.phone_no
     *
     * @mbg.generated
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.phone_no
     *
     * @param phoneNo the value for vcs.at_chat_message.phone_no
     *
     * @mbg.generated
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.url_link
     *
     * @return the value of vcs.at_chat_message.url_link
     *
     * @mbg.generated
     */
    public String getUrlLink() {
        return urlLink;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.url_link
     *
     * @param urlLink the value for vcs.at_chat_message.url_link
     *
     * @mbg.generated
     */
    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.send_time
     *
     * @return the value of vcs.at_chat_message.send_time
     *
     * @mbg.generated
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.send_time
     *
     * @param sendTime the value for vcs.at_chat_message.send_time
     *
     * @mbg.generated
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.agent_level_id
     *
     * @return the value of vcs.at_chat_message.agent_level_id
     *
     * @mbg.generated
     */
    public Integer getAgentLevelId() {
        return agentLevelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.agent_level_id
     *
     * @param agentLevelId the value for vcs.at_chat_message.agent_level_id
     *
     * @mbg.generated
     */
    public void setAgentLevelId(Integer agentLevelId) {
        this.agentLevelId = agentLevelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.agent_name
     *
     * @return the value of vcs.at_chat_message.agent_name
     *
     * @mbg.generated
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.agent_name
     *
     * @param agentName the value for vcs.at_chat_message.agent_name
     *
     * @mbg.generated
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.call_count
     *
     * @return the value of vcs.at_chat_message.call_count
     *
     * @mbg.generated
     */
    public Integer getCallCount() {
        return callCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.call_count
     *
     * @param callCount the value for vcs.at_chat_message.call_count
     *
     * @mbg.generated
     */
    public void setCallCount(Integer callCount) {
        this.callCount = callCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.op_send_flag
     *
     * @return the value of vcs.at_chat_message.op_send_flag
     *
     * @mbg.generated
     */
    public Integer getOpSendFlag() {
        return opSendFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.op_send_flag
     *
     * @param opSendFlag the value for vcs.at_chat_message.op_send_flag
     *
     * @mbg.generated
     */
    public void setOpSendFlag(Integer opSendFlag) {
        this.opSendFlag = opSendFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.del_flag
     *
     * @return the value of vcs.at_chat_message.del_flag
     *
     * @mbg.generated
     */
    public Short getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.del_flag
     *
     * @param delFlag the value for vcs.at_chat_message.del_flag
     *
     * @mbg.generated
     */
    public void setDelFlag(Short delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.at_chat_message.read_flag
     *
     * @return the value of vcs.at_chat_message.read_flag
     *
     * @mbg.generated
     */
    public Integer getReadFlag() {
        return readFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.at_chat_message.read_flag
     *
     * @param readFlag the value for vcs.at_chat_message.read_flag
     *
     * @mbg.generated
     */
    public void setReadFlag(Integer readFlag) {
        this.readFlag = readFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_chat_message
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", chatMessageId=").append(chatMessageId);
        sb.append(", daProductTypeId=").append(daProductTypeId);
        sb.append(", customerId=").append(customerId);
        sb.append(", brandId=").append(brandId);
        sb.append(", customerLocation=").append(customerLocation);
        sb.append(", messageContent=").append(messageContent);
        sb.append(", price=").append(price);
        sb.append(", phoneNo=").append(phoneNo);
        sb.append(", urlLink=").append(urlLink);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", agentLevelId=").append(agentLevelId);
        sb.append(", agentName=").append(agentName);
        sb.append(", callCount=").append(callCount);
        sb.append(", opSendFlag=").append(opSendFlag);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", readFlag=").append(readFlag);
        sb.append("]");
        return sb.toString();
    }
}