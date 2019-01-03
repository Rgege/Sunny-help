package org.blue.helper.StringHelper.persistence.entity.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BillRecordYearExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
     */
    public BillRecordYearExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
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
     * This method corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCreatTimeIsNull() {
            addCriterion("creat_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatTimeIsNotNull() {
            addCriterion("creat_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatTimeEqualTo(Timestamp value) {
            addCriterion("creat_time =", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeNotEqualTo(Timestamp value) {
            addCriterion("creat_time <>", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeGreaterThan(Timestamp value) {
            addCriterion("creat_time >", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("creat_time >=", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeLessThan(Timestamp value) {
            addCriterion("creat_time <", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("creat_time <=", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeIn(List<Timestamp> values) {
            addCriterion("creat_time in", values, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeNotIn(List<Timestamp> values) {
            addCriterion("creat_time not in", values, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("creat_time between", value1, value2, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("creat_time not between", value1, value2, "creatTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Timestamp value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Timestamp value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Timestamp value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Timestamp value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Timestamp> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Timestamp> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("year is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("year is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(String value) {
            addCriterion("year =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(String value) {
            addCriterion("year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(String value) {
            addCriterion("year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(String value) {
            addCriterion("year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(String value) {
            addCriterion("year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(String value) {
            addCriterion("year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLike(String value) {
            addCriterion("year like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotLike(String value) {
            addCriterion("year not like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<String> values) {
            addCriterion("year in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<String> values) {
            addCriterion("year not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(String value1, String value2) {
            addCriterion("year between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(String value1, String value2) {
            addCriterion("year not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearCountIsNull() {
            addCriterion("year_count is null");
            return (Criteria) this;
        }

        public Criteria andYearCountIsNotNull() {
            addCriterion("year_count is not null");
            return (Criteria) this;
        }

        public Criteria andYearCountEqualTo(Long value) {
            addCriterion("year_count =", value, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountNotEqualTo(Long value) {
            addCriterion("year_count <>", value, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountGreaterThan(Long value) {
            addCriterion("year_count >", value, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountGreaterThanOrEqualTo(Long value) {
            addCriterion("year_count >=", value, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountLessThan(Long value) {
            addCriterion("year_count <", value, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountLessThanOrEqualTo(Long value) {
            addCriterion("year_count <=", value, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountIn(List<Long> values) {
            addCriterion("year_count in", values, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountNotIn(List<Long> values) {
            addCriterion("year_count not in", values, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountBetween(Long value1, Long value2) {
            addCriterion("year_count between", value1, value2, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearCountNotBetween(Long value1, Long value2) {
            addCriterion("year_count not between", value1, value2, "yearCount");
            return (Criteria) this;
        }

        public Criteria andYearExpIsNull() {
            addCriterion("year_exp is null");
            return (Criteria) this;
        }

        public Criteria andYearExpIsNotNull() {
            addCriterion("year_exp is not null");
            return (Criteria) this;
        }

        public Criteria andYearExpEqualTo(Double value) {
            addCriterion("year_exp =", value, "yearExp");
            return (Criteria) this;
        }

        public Criteria andYearExpNotEqualTo(Double value) {
            addCriterion("year_exp <>", value, "yearExp");
            return (Criteria) this;
        }

        public Criteria andYearExpGreaterThan(Double value) {
            addCriterion("year_exp >", value, "yearExp");
            return (Criteria) this;
        }

        public Criteria andYearExpGreaterThanOrEqualTo(Double value) {
            addCriterion("year_exp >=", value, "yearExp");
            return (Criteria) this;
        }

        public Criteria andYearExpLessThan(Double value) {
            addCriterion("year_exp <", value, "yearExp");
            return (Criteria) this;
        }

        public Criteria andYearExpLessThanOrEqualTo(Double value) {
            addCriterion("year_exp <=", value, "yearExp");
            return (Criteria) this;
        }

        public Criteria andYearExpIn(List<Double> values) {
            addCriterion("year_exp in", values, "yearExp");
            return (Criteria) this;
        }

        public Criteria andYearExpNotIn(List<Double> values) {
            addCriterion("year_exp not in", values, "yearExp");
            return (Criteria) this;
        }

        public Criteria andYearExpBetween(Double value1, Double value2) {
            addCriterion("year_exp between", value1, value2, "yearExp");
            return (Criteria) this;
        }

        public Criteria andYearExpNotBetween(Double value1, Double value2) {
            addCriterion("year_exp not between", value1, value2, "yearExp");
            return (Criteria) this;
        }

        public Criteria andYearIncIsNull() {
            addCriterion("year_inc is null");
            return (Criteria) this;
        }

        public Criteria andYearIncIsNotNull() {
            addCriterion("year_inc is not null");
            return (Criteria) this;
        }

        public Criteria andYearIncEqualTo(Double value) {
            addCriterion("year_inc =", value, "yearInc");
            return (Criteria) this;
        }

        public Criteria andYearIncNotEqualTo(Double value) {
            addCriterion("year_inc <>", value, "yearInc");
            return (Criteria) this;
        }

        public Criteria andYearIncGreaterThan(Double value) {
            addCriterion("year_inc >", value, "yearInc");
            return (Criteria) this;
        }

        public Criteria andYearIncGreaterThanOrEqualTo(Double value) {
            addCriterion("year_inc >=", value, "yearInc");
            return (Criteria) this;
        }

        public Criteria andYearIncLessThan(Double value) {
            addCriterion("year_inc <", value, "yearInc");
            return (Criteria) this;
        }

        public Criteria andYearIncLessThanOrEqualTo(Double value) {
            addCriterion("year_inc <=", value, "yearInc");
            return (Criteria) this;
        }

        public Criteria andYearIncIn(List<Double> values) {
            addCriterion("year_inc in", values, "yearInc");
            return (Criteria) this;
        }

        public Criteria andYearIncNotIn(List<Double> values) {
            addCriterion("year_inc not in", values, "yearInc");
            return (Criteria) this;
        }

        public Criteria andYearIncBetween(Double value1, Double value2) {
            addCriterion("year_inc between", value1, value2, "yearInc");
            return (Criteria) this;
        }

        public Criteria andYearIncNotBetween(Double value1, Double value2) {
            addCriterion("year_inc not between", value1, value2, "yearInc");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bill_record_year
     *
     * @mbggenerated do_not_delete_during_merge Thu Nov 22 09:19:44 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bill_record_year
     *
     * @mbggenerated Thu Nov 22 09:19:44 CST 2018
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
