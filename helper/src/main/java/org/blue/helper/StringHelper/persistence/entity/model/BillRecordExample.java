package org.blue.helper.StringHelper.persistence.entity.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BillRecordExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
     */
    public BillRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
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
     * This method corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
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

        public Criteria andDIdIsNull() {
            addCriterion("d_id is null");
            return (Criteria) this;
        }

        public Criteria andDIdIsNotNull() {
            addCriterion("d_id is not null");
            return (Criteria) this;
        }

        public Criteria andDIdEqualTo(Long value) {
            addCriterion("d_id =", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdNotEqualTo(Long value) {
            addCriterion("d_id <>", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdGreaterThan(Long value) {
            addCriterion("d_id >", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdGreaterThanOrEqualTo(Long value) {
            addCriterion("d_id >=", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdLessThan(Long value) {
            addCriterion("d_id <", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdLessThanOrEqualTo(Long value) {
            addCriterion("d_id <=", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdIn(List<Long> values) {
            addCriterion("d_id in", values, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdNotIn(List<Long> values) {
            addCriterion("d_id not in", values, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdBetween(Long value1, Long value2) {
            addCriterion("d_id between", value1, value2, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdNotBetween(Long value1, Long value2) {
            addCriterion("d_id not between", value1, value2, "dId");
            return (Criteria) this;
        }

        public Criteria andRecordCodeIsNull() {
            addCriterion("record_code is null");
            return (Criteria) this;
        }

        public Criteria andRecordCodeIsNotNull() {
            addCriterion("record_code is not null");
            return (Criteria) this;
        }

        public Criteria andRecordCodeEqualTo(String value) {
            addCriterion("record_code =", value, "recordCode");
            return (Criteria) this;
        }

        public Criteria andRecordCodeNotEqualTo(String value) {
            addCriterion("record_code <>", value, "recordCode");
            return (Criteria) this;
        }

        public Criteria andRecordCodeGreaterThan(String value) {
            addCriterion("record_code >", value, "recordCode");
            return (Criteria) this;
        }

        public Criteria andRecordCodeGreaterThanOrEqualTo(String value) {
            addCriterion("record_code >=", value, "recordCode");
            return (Criteria) this;
        }

        public Criteria andRecordCodeLessThan(String value) {
            addCriterion("record_code <", value, "recordCode");
            return (Criteria) this;
        }

        public Criteria andRecordCodeLessThanOrEqualTo(String value) {
            addCriterion("record_code <=", value, "recordCode");
            return (Criteria) this;
        }

        public Criteria andRecordCodeLike(String value) {
            addCriterion("record_code like", value, "recordCode");
            return (Criteria) this;
        }

        public Criteria andRecordCodeNotLike(String value) {
            addCriterion("record_code not like", value, "recordCode");
            return (Criteria) this;
        }

        public Criteria andRecordCodeIn(List<String> values) {
            addCriterion("record_code in", values, "recordCode");
            return (Criteria) this;
        }

        public Criteria andRecordCodeNotIn(List<String> values) {
            addCriterion("record_code not in", values, "recordCode");
            return (Criteria) this;
        }

        public Criteria andRecordCodeBetween(String value1, String value2) {
            addCriterion("record_code between", value1, value2, "recordCode");
            return (Criteria) this;
        }

        public Criteria andRecordCodeNotBetween(String value1, String value2) {
            addCriterion("record_code not between", value1, value2, "recordCode");
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

        public Criteria andBillTypeIsNull() {
            addCriterion("bill_type is null");
            return (Criteria) this;
        }

        public Criteria andBillTypeIsNotNull() {
            addCriterion("bill_type is not null");
            return (Criteria) this;
        }

        public Criteria andBillTypeEqualTo(String value) {
            addCriterion("bill_type =", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotEqualTo(String value) {
            addCriterion("bill_type <>", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeGreaterThan(String value) {
            addCriterion("bill_type >", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeGreaterThanOrEqualTo(String value) {
            addCriterion("bill_type >=", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLessThan(String value) {
            addCriterion("bill_type <", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLessThanOrEqualTo(String value) {
            addCriterion("bill_type <=", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLike(String value) {
            addCriterion("bill_type like", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotLike(String value) {
            addCriterion("bill_type not like", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeIn(List<String> values) {
            addCriterion("bill_type in", values, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotIn(List<String> values) {
            addCriterion("bill_type not in", values, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeBetween(String value1, String value2) {
            addCriterion("bill_type between", value1, value2, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotBetween(String value1, String value2) {
            addCriterion("bill_type not between", value1, value2, "billType");
            return (Criteria) this;
        }

        public Criteria andBillMoneyIsNull() {
            addCriterion("bill_money is null");
            return (Criteria) this;
        }

        public Criteria andBillMoneyIsNotNull() {
            addCriterion("bill_money is not null");
            return (Criteria) this;
        }

        public Criteria andBillMoneyEqualTo(Double value) {
            addCriterion("bill_money =", value, "billMoney");
            return (Criteria) this;
        }

        public Criteria andBillMoneyNotEqualTo(Double value) {
            addCriterion("bill_money <>", value, "billMoney");
            return (Criteria) this;
        }

        public Criteria andBillMoneyGreaterThan(Double value) {
            addCriterion("bill_money >", value, "billMoney");
            return (Criteria) this;
        }

        public Criteria andBillMoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("bill_money >=", value, "billMoney");
            return (Criteria) this;
        }

        public Criteria andBillMoneyLessThan(Double value) {
            addCriterion("bill_money <", value, "billMoney");
            return (Criteria) this;
        }

        public Criteria andBillMoneyLessThanOrEqualTo(Double value) {
            addCriterion("bill_money <=", value, "billMoney");
            return (Criteria) this;
        }

        public Criteria andBillMoneyIn(List<Double> values) {
            addCriterion("bill_money in", values, "billMoney");
            return (Criteria) this;
        }

        public Criteria andBillMoneyNotIn(List<Double> values) {
            addCriterion("bill_money not in", values, "billMoney");
            return (Criteria) this;
        }

        public Criteria andBillMoneyBetween(Double value1, Double value2) {
            addCriterion("bill_money between", value1, value2, "billMoney");
            return (Criteria) this;
        }

        public Criteria andBillMoneyNotBetween(Double value1, Double value2) {
            addCriterion("bill_money not between", value1, value2, "billMoney");
            return (Criteria) this;
        }

        public Criteria andBillDepictIsNull() {
            addCriterion("bill_depict is null");
            return (Criteria) this;
        }

        public Criteria andBillDepictIsNotNull() {
            addCriterion("bill_depict is not null");
            return (Criteria) this;
        }

        public Criteria andBillDepictEqualTo(String value) {
            addCriterion("bill_depict =", value, "billDepict");
            return (Criteria) this;
        }

        public Criteria andBillDepictNotEqualTo(String value) {
            addCriterion("bill_depict <>", value, "billDepict");
            return (Criteria) this;
        }

        public Criteria andBillDepictGreaterThan(String value) {
            addCriterion("bill_depict >", value, "billDepict");
            return (Criteria) this;
        }

        public Criteria andBillDepictGreaterThanOrEqualTo(String value) {
            addCriterion("bill_depict >=", value, "billDepict");
            return (Criteria) this;
        }

        public Criteria andBillDepictLessThan(String value) {
            addCriterion("bill_depict <", value, "billDepict");
            return (Criteria) this;
        }

        public Criteria andBillDepictLessThanOrEqualTo(String value) {
            addCriterion("bill_depict <=", value, "billDepict");
            return (Criteria) this;
        }

        public Criteria andBillDepictLike(String value) {
            addCriterion("bill_depict like", value, "billDepict");
            return (Criteria) this;
        }

        public Criteria andBillDepictNotLike(String value) {
            addCriterion("bill_depict not like", value, "billDepict");
            return (Criteria) this;
        }

        public Criteria andBillDepictIn(List<String> values) {
            addCriterion("bill_depict in", values, "billDepict");
            return (Criteria) this;
        }

        public Criteria andBillDepictNotIn(List<String> values) {
            addCriterion("bill_depict not in", values, "billDepict");
            return (Criteria) this;
        }

        public Criteria andBillDepictBetween(String value1, String value2) {
            addCriterion("bill_depict between", value1, value2, "billDepict");
            return (Criteria) this;
        }

        public Criteria andBillDepictNotBetween(String value1, String value2) {
            addCriterion("bill_depict not between", value1, value2, "billDepict");
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
     * This class corresponds to the database table bill_record
     *
     * @mbggenerated do_not_delete_during_merge Wed Nov 21 14:48:23 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table bill_record
     *
     * @mbggenerated Wed Nov 21 14:48:23 CST 2018
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