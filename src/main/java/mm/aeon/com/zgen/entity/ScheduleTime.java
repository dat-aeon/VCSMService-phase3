package mm.aeon.com.zgen.entity;

public class ScheduleTime {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.schedule_time.schedule_time_id
     *
     * @mbg.generated
     */
    private Integer scheduleTimeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vcs.schedule_time.duration_hour
     *
     * @mbg.generated
     */
    private Integer durationHour;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.schedule_time.schedule_time_id
     *
     * @return the value of vcs.schedule_time.schedule_time_id
     *
     * @mbg.generated
     */
    public Integer getScheduleTimeId() {
        return scheduleTimeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.schedule_time.schedule_time_id
     *
     * @param scheduleTimeId the value for vcs.schedule_time.schedule_time_id
     *
     * @mbg.generated
     */
    public void setScheduleTimeId(Integer scheduleTimeId) {
        this.scheduleTimeId = scheduleTimeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vcs.schedule_time.duration_hour
     *
     * @return the value of vcs.schedule_time.duration_hour
     *
     * @mbg.generated
     */
    public Integer getDurationHour() {
        return durationHour;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vcs.schedule_time.duration_hour
     *
     * @param durationHour the value for vcs.schedule_time.duration_hour
     *
     * @mbg.generated
     */
    public void setDurationHour(Integer durationHour) {
        this.durationHour = durationHour;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.schedule_time
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", scheduleTimeId=").append(scheduleTimeId);
        sb.append(", durationHour=").append(durationHour);
        sb.append("]");
        return sb.toString();
    }
}