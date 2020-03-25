package mm.aeon.com.zgen.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AtAgentLevelExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table vcs.at_agent_level
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table vcs.at_agent_level
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table vcs.at_agent_level
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level
     *
     * @mbg.generated
     */
    public AtAgentLevelExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level
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
     * This method corresponds to the database table vcs.at_agent_level
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
     * This method corresponds to the database table vcs.at_agent_level
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vcs.at_agent_level
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
     * This class corresponds to the database table vcs.at_agent_level
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

        public Criteria andAgentLevelIdIsNull() {
            addCriterion("agent_level_id is null");
            return (Criteria) this;
        }

        public Criteria andAgentLevelIdIsNotNull() {
            addCriterion("agent_level_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgentLevelIdEqualTo(Integer value) {
            addCriterion("agent_level_id =", value, "agentLevelId");
            return (Criteria) this;
        }

        public Criteria andAgentLevelIdNotEqualTo(Integer value) {
            addCriterion("agent_level_id <>", value, "agentLevelId");
            return (Criteria) this;
        }

        public Criteria andAgentLevelIdGreaterThan(Integer value) {
            addCriterion("agent_level_id >", value, "agentLevelId");
            return (Criteria) this;
        }

        public Criteria andAgentLevelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("agent_level_id >=", value, "agentLevelId");
            return (Criteria) this;
        }

        public Criteria andAgentLevelIdLessThan(Integer value) {
            addCriterion("agent_level_id <", value, "agentLevelId");
            return (Criteria) this;
        }

        public Criteria andAgentLevelIdLessThanOrEqualTo(Integer value) {
            addCriterion("agent_level_id <=", value, "agentLevelId");
            return (Criteria) this;
        }

        public Criteria andAgentLevelIdIn(List<Integer> values) {
            addCriterion("agent_level_id in", values, "agentLevelId");
            return (Criteria) this;
        }

        public Criteria andAgentLevelIdNotIn(List<Integer> values) {
            addCriterion("agent_level_id not in", values, "agentLevelId");
            return (Criteria) this;
        }

        public Criteria andAgentLevelIdBetween(Integer value1, Integer value2) {
            addCriterion("agent_level_id between", value1, value2, "agentLevelId");
            return (Criteria) this;
        }

        public Criteria andAgentLevelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("agent_level_id not between", value1, value2, "agentLevelId");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNameIsNull() {
            addCriterion("agent_level_name is null");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNameIsNotNull() {
            addCriterion("agent_level_name is not null");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNameEqualTo(String value) {
            addCriterion("agent_level_name =", value, "agentLevelName");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNameNotEqualTo(String value) {
            addCriterion("agent_level_name <>", value, "agentLevelName");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNameGreaterThan(String value) {
            addCriterion("agent_level_name >", value, "agentLevelName");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("agent_level_name >=", value, "agentLevelName");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNameLessThan(String value) {
            addCriterion("agent_level_name <", value, "agentLevelName");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNameLessThanOrEqualTo(String value) {
            addCriterion("agent_level_name <=", value, "agentLevelName");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNameLike(String value) {
            addCriterion("agent_level_name like", value, "agentLevelName");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNameNotLike(String value) {
            addCriterion("agent_level_name not like", value, "agentLevelName");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNameIn(List<String> values) {
            addCriterion("agent_level_name in", values, "agentLevelName");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNameNotIn(List<String> values) {
            addCriterion("agent_level_name not in", values, "agentLevelName");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNameBetween(String value1, String value2) {
            addCriterion("agent_level_name between", value1, value2, "agentLevelName");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNameNotBetween(String value1, String value2) {
            addCriterion("agent_level_name not between", value1, value2, "agentLevelName");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andAgentLevelCodeIsNull() {
            addCriterion("agent_level_code is null");
            return (Criteria) this;
        }

        public Criteria andAgentLevelCodeIsNotNull() {
            addCriterion("agent_level_code is not null");
            return (Criteria) this;
        }

        public Criteria andAgentLevelCodeEqualTo(String value) {
            addCriterion("agent_level_code =", value, "agentLevelCode");
            return (Criteria) this;
        }

        public Criteria andAgentLevelCodeNotEqualTo(String value) {
            addCriterion("agent_level_code <>", value, "agentLevelCode");
            return (Criteria) this;
        }

        public Criteria andAgentLevelCodeGreaterThan(String value) {
            addCriterion("agent_level_code >", value, "agentLevelCode");
            return (Criteria) this;
        }

        public Criteria andAgentLevelCodeGreaterThanOrEqualTo(String value) {
            addCriterion("agent_level_code >=", value, "agentLevelCode");
            return (Criteria) this;
        }

        public Criteria andAgentLevelCodeLessThan(String value) {
            addCriterion("agent_level_code <", value, "agentLevelCode");
            return (Criteria) this;
        }

        public Criteria andAgentLevelCodeLessThanOrEqualTo(String value) {
            addCriterion("agent_level_code <=", value, "agentLevelCode");
            return (Criteria) this;
        }

        public Criteria andAgentLevelCodeLike(String value) {
            addCriterion("agent_level_code like", value, "agentLevelCode");
            return (Criteria) this;
        }

        public Criteria andAgentLevelCodeNotLike(String value) {
            addCriterion("agent_level_code not like", value, "agentLevelCode");
            return (Criteria) this;
        }

        public Criteria andAgentLevelCodeIn(List<String> values) {
            addCriterion("agent_level_code in", values, "agentLevelCode");
            return (Criteria) this;
        }

        public Criteria andAgentLevelCodeNotIn(List<String> values) {
            addCriterion("agent_level_code not in", values, "agentLevelCode");
            return (Criteria) this;
        }

        public Criteria andAgentLevelCodeBetween(String value1, String value2) {
            addCriterion("agent_level_code between", value1, value2, "agentLevelCode");
            return (Criteria) this;
        }

        public Criteria andAgentLevelCodeNotBetween(String value1, String value2) {
            addCriterion("agent_level_code not between", value1, value2, "agentLevelCode");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNull() {
            addCriterion("created_by is null");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNotNull() {
            addCriterion("created_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedByEqualTo(String value) {
            addCriterion("created_by =", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotEqualTo(String value) {
            addCriterion("created_by <>", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThan(String value) {
            addCriterion("created_by >", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThanOrEqualTo(String value) {
            addCriterion("created_by >=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThan(String value) {
            addCriterion("created_by <", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThanOrEqualTo(String value) {
            addCriterion("created_by <=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLike(String value) {
            addCriterion("created_by like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotLike(String value) {
            addCriterion("created_by not like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByIn(List<String> values) {
            addCriterion("created_by in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotIn(List<String> values) {
            addCriterion("created_by not in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByBetween(String value1, String value2) {
            addCriterion("created_by between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotBetween(String value1, String value2) {
            addCriterion("created_by not between", value1, value2, "createdBy");
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

        public Criteria andUpdatedByIsNull() {
            addCriterion("updated_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedByIsNotNull() {
            addCriterion("updated_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedByEqualTo(String value) {
            addCriterion("updated_by =", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotEqualTo(String value) {
            addCriterion("updated_by <>", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByGreaterThan(String value) {
            addCriterion("updated_by >", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByGreaterThanOrEqualTo(String value) {
            addCriterion("updated_by >=", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLessThan(String value) {
            addCriterion("updated_by <", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLessThanOrEqualTo(String value) {
            addCriterion("updated_by <=", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLike(String value) {
            addCriterion("updated_by like", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotLike(String value) {
            addCriterion("updated_by not like", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByIn(List<String> values) {
            addCriterion("updated_by in", values, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotIn(List<String> values) {
            addCriterion("updated_by not in", values, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByBetween(String value1, String value2) {
            addCriterion("updated_by between", value1, value2, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotBetween(String value1, String value2) {
            addCriterion("updated_by not between", value1, value2, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(Short value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(Short value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(Short value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(Short value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(Short value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(Short value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<Short> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<Short> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(Short value1, Short value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(Short value1, Short value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andAtAgentLevelTypeIdIsNull() {
            addCriterion("at_agent_level_type_id is null");
            return (Criteria) this;
        }

        public Criteria andAtAgentLevelTypeIdIsNotNull() {
            addCriterion("at_agent_level_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andAtAgentLevelTypeIdEqualTo(Integer value) {
            addCriterion("at_agent_level_type_id =", value, "atAgentLevelTypeId");
            return (Criteria) this;
        }

        public Criteria andAtAgentLevelTypeIdNotEqualTo(Integer value) {
            addCriterion("at_agent_level_type_id <>", value, "atAgentLevelTypeId");
            return (Criteria) this;
        }

        public Criteria andAtAgentLevelTypeIdGreaterThan(Integer value) {
            addCriterion("at_agent_level_type_id >", value, "atAgentLevelTypeId");
            return (Criteria) this;
        }

        public Criteria andAtAgentLevelTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("at_agent_level_type_id >=", value, "atAgentLevelTypeId");
            return (Criteria) this;
        }

        public Criteria andAtAgentLevelTypeIdLessThan(Integer value) {
            addCriterion("at_agent_level_type_id <", value, "atAgentLevelTypeId");
            return (Criteria) this;
        }

        public Criteria andAtAgentLevelTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("at_agent_level_type_id <=", value, "atAgentLevelTypeId");
            return (Criteria) this;
        }

        public Criteria andAtAgentLevelTypeIdIn(List<Integer> values) {
            addCriterion("at_agent_level_type_id in", values, "atAgentLevelTypeId");
            return (Criteria) this;
        }

        public Criteria andAtAgentLevelTypeIdNotIn(List<Integer> values) {
            addCriterion("at_agent_level_type_id not in", values, "atAgentLevelTypeId");
            return (Criteria) this;
        }

        public Criteria andAtAgentLevelTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("at_agent_level_type_id between", value1, value2, "atAgentLevelTypeId");
            return (Criteria) this;
        }

        public Criteria andAtAgentLevelTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("at_agent_level_type_id not between", value1, value2, "atAgentLevelTypeId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table vcs.at_agent_level
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
     * This class corresponds to the database table vcs.at_agent_level
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