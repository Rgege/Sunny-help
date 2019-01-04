package org.blue.helper.StringHelper.persistence.entity.model;

import java.sql.Timestamp;

public class OfflineMsg {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column offline_msg.id
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column offline_msg.send_id
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    private Long sendId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column offline_msg.target_id
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    private Long targetId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column offline_msg.message
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    private String message;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column offline_msg.send_time
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    private Timestamp sendTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column offline_msg.exp_time
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    private Timestamp expTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column offline_msg.id
     *
     * @return the value of offline_msg.id
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column offline_msg.id
     *
     * @param id the value for offline_msg.id
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column offline_msg.send_id
     *
     * @return the value of offline_msg.send_id
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    public Long getSendId() {
        return sendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column offline_msg.send_id
     *
     * @param sendId the value for offline_msg.send_id
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column offline_msg.target_id
     *
     * @return the value of offline_msg.target_id
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    public Long getTargetId() {
        return targetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column offline_msg.target_id
     *
     * @param targetId the value for offline_msg.target_id
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column offline_msg.message
     *
     * @return the value of offline_msg.message
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column offline_msg.message
     *
     * @param message the value for offline_msg.message
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column offline_msg.send_time
     *
     * @return the value of offline_msg.send_time
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    public Timestamp getSendTime() {
        return sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column offline_msg.send_time
     *
     * @param sendTime the value for offline_msg.send_time
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column offline_msg.exp_time
     *
     * @return the value of offline_msg.exp_time
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    public Timestamp getExpTime() {
        return expTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column offline_msg.exp_time
     *
     * @param expTime the value for offline_msg.exp_time
     *
     * @mbggenerated Fri Oct 12 16:04:46 CST 2018
     */
    public void setExpTime(Timestamp expTime) {
        this.expTime = expTime;
    }
}