package mm.aeon.com.zgen.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FreeMessageRoomExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    public FreeMessageRoomExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andFreeMessageRoomIdIsNull() {
            addCriterion("free_message_room_id is null");
            return (Criteria) this;
        }

        public Criteria andFreeMessageRoomIdIsNotNull() {
            addCriterion("free_message_room_id is not null");
            return (Criteria) this;
        }

        public Criteria andFreeMessageRoomIdEqualTo(Integer value) {
            addCriterion("free_message_room_id =", value, "freeMessageRoomId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageRoomIdNotEqualTo(Integer value) {
            addCriterion("free_message_room_id <>", value, "freeMessageRoomId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageRoomIdGreaterThan(Integer value) {
            addCriterion("free_message_room_id >", value, "freeMessageRoomId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageRoomIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("free_message_room_id >=", value, "freeMessageRoomId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageRoomIdLessThan(Integer value) {
            addCriterion("free_message_room_id <", value, "freeMessageRoomId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageRoomIdLessThanOrEqualTo(Integer value) {
            addCriterion("free_message_room_id <=", value, "freeMessageRoomId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageRoomIdIn(List<Integer> values) {
            addCriterion("free_message_room_id in", values, "freeMessageRoomId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageRoomIdNotIn(List<Integer> values) {
            addCriterion("free_message_room_id not in", values, "freeMessageRoomId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageRoomIdBetween(Integer value1, Integer value2) {
            addCriterion("free_message_room_id between", value1, value2, "freeMessageRoomId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageRoomIdNotBetween(Integer value1, Integer value2) {
            addCriterion("free_message_room_id not between", value1, value2, "freeMessageRoomId");
            return (Criteria) this;
        }

        public Criteria andFreeCustRoomInfoIdIsNull() {
            addCriterion("free_cust_room_info_id is null");
            return (Criteria) this;
        }

        public Criteria andFreeCustRoomInfoIdIsNotNull() {
            addCriterion("free_cust_room_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andFreeCustRoomInfoIdEqualTo(Integer value) {
            addCriterion("free_cust_room_info_id =", value, "freeCustRoomInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeCustRoomInfoIdNotEqualTo(Integer value) {
            addCriterion("free_cust_room_info_id <>", value, "freeCustRoomInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeCustRoomInfoIdGreaterThan(Integer value) {
            addCriterion("free_cust_room_info_id >", value, "freeCustRoomInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeCustRoomInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("free_cust_room_info_id >=", value, "freeCustRoomInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeCustRoomInfoIdLessThan(Integer value) {
            addCriterion("free_cust_room_info_id <", value, "freeCustRoomInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeCustRoomInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("free_cust_room_info_id <=", value, "freeCustRoomInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeCustRoomInfoIdIn(List<Integer> values) {
            addCriterion("free_cust_room_info_id in", values, "freeCustRoomInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeCustRoomInfoIdNotIn(List<Integer> values) {
            addCriterion("free_cust_room_info_id not in", values, "freeCustRoomInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeCustRoomInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("free_cust_room_info_id between", value1, value2, "freeCustRoomInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeCustRoomInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("free_cust_room_info_id not between", value1, value2, "freeCustRoomInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageInfoIdIsNull() {
            addCriterion("free_message_info_id is null");
            return (Criteria) this;
        }

        public Criteria andFreeMessageInfoIdIsNotNull() {
            addCriterion("free_message_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andFreeMessageInfoIdEqualTo(Integer value) {
            addCriterion("free_message_info_id =", value, "freeMessageInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageInfoIdNotEqualTo(Integer value) {
            addCriterion("free_message_info_id <>", value, "freeMessageInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageInfoIdGreaterThan(Integer value) {
            addCriterion("free_message_info_id >", value, "freeMessageInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("free_message_info_id >=", value, "freeMessageInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageInfoIdLessThan(Integer value) {
            addCriterion("free_message_info_id <", value, "freeMessageInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("free_message_info_id <=", value, "freeMessageInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageInfoIdIn(List<Integer> values) {
            addCriterion("free_message_info_id in", values, "freeMessageInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageInfoIdNotIn(List<Integer> values) {
            addCriterion("free_message_info_id not in", values, "freeMessageInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("free_message_info_id between", value1, value2, "freeMessageInfoId");
            return (Criteria) this;
        }

        public Criteria andFreeMessageInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("free_message_info_id not between", value1, value2, "freeMessageInfoId");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table vcs.free_message_room
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}