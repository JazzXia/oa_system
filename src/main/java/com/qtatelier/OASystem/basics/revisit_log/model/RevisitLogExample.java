package com.qtatelier.OASystem.basics.revisit_log.model;

import java.util.ArrayList;
import java.util.List;

public class RevisitLogExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    public RevisitLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
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
     * This method corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
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

        public Criteria andRevisitIdIsNull() {
            addCriterion("revisit_id is null");
            return (Criteria) this;
        }

        public Criteria andRevisitIdIsNotNull() {
            addCriterion("revisit_id is not null");
            return (Criteria) this;
        }

        public Criteria andRevisitIdEqualTo(String value) {
            addCriterion("revisit_id =", value, "revisitId");
            return (Criteria) this;
        }

        public Criteria andRevisitIdNotEqualTo(String value) {
            addCriterion("revisit_id <>", value, "revisitId");
            return (Criteria) this;
        }

        public Criteria andRevisitIdGreaterThan(String value) {
            addCriterion("revisit_id >", value, "revisitId");
            return (Criteria) this;
        }

        public Criteria andRevisitIdGreaterThanOrEqualTo(String value) {
            addCriterion("revisit_id >=", value, "revisitId");
            return (Criteria) this;
        }

        public Criteria andRevisitIdLessThan(String value) {
            addCriterion("revisit_id <", value, "revisitId");
            return (Criteria) this;
        }

        public Criteria andRevisitIdLessThanOrEqualTo(String value) {
            addCriterion("revisit_id <=", value, "revisitId");
            return (Criteria) this;
        }

        public Criteria andRevisitIdLike(String value) {
            addCriterion("revisit_id like", value, "revisitId");
            return (Criteria) this;
        }

        public Criteria andRevisitIdNotLike(String value) {
            addCriterion("revisit_id not like", value, "revisitId");
            return (Criteria) this;
        }

        public Criteria andRevisitIdIn(List<String> values) {
            addCriterion("revisit_id in", values, "revisitId");
            return (Criteria) this;
        }

        public Criteria andRevisitIdNotIn(List<String> values) {
            addCriterion("revisit_id not in", values, "revisitId");
            return (Criteria) this;
        }

        public Criteria andRevisitIdBetween(String value1, String value2) {
            addCriterion("revisit_id between", value1, value2, "revisitId");
            return (Criteria) this;
        }

        public Criteria andRevisitIdNotBetween(String value1, String value2) {
            addCriterion("revisit_id not between", value1, value2, "revisitId");
            return (Criteria) this;
        }

        public Criteria andRevisitLogIsNull() {
            addCriterion("revisit_log is null");
            return (Criteria) this;
        }

        public Criteria andRevisitLogIsNotNull() {
            addCriterion("revisit_log is not null");
            return (Criteria) this;
        }

        public Criteria andRevisitLogEqualTo(String value) {
            addCriterion("revisit_log =", value, "revisitLog");
            return (Criteria) this;
        }

        public Criteria andRevisitLogNotEqualTo(String value) {
            addCriterion("revisit_log <>", value, "revisitLog");
            return (Criteria) this;
        }

        public Criteria andRevisitLogGreaterThan(String value) {
            addCriterion("revisit_log >", value, "revisitLog");
            return (Criteria) this;
        }

        public Criteria andRevisitLogGreaterThanOrEqualTo(String value) {
            addCriterion("revisit_log >=", value, "revisitLog");
            return (Criteria) this;
        }

        public Criteria andRevisitLogLessThan(String value) {
            addCriterion("revisit_log <", value, "revisitLog");
            return (Criteria) this;
        }

        public Criteria andRevisitLogLessThanOrEqualTo(String value) {
            addCriterion("revisit_log <=", value, "revisitLog");
            return (Criteria) this;
        }

        public Criteria andRevisitLogLike(String value) {
            addCriterion("revisit_log like", value, "revisitLog");
            return (Criteria) this;
        }

        public Criteria andRevisitLogNotLike(String value) {
            addCriterion("revisit_log not like", value, "revisitLog");
            return (Criteria) this;
        }

        public Criteria andRevisitLogIn(List<String> values) {
            addCriterion("revisit_log in", values, "revisitLog");
            return (Criteria) this;
        }

        public Criteria andRevisitLogNotIn(List<String> values) {
            addCriterion("revisit_log not in", values, "revisitLog");
            return (Criteria) this;
        }

        public Criteria andRevisitLogBetween(String value1, String value2) {
            addCriterion("revisit_log between", value1, value2, "revisitLog");
            return (Criteria) this;
        }

        public Criteria andRevisitLogNotBetween(String value1, String value2) {
            addCriterion("revisit_log not between", value1, value2, "revisitLog");
            return (Criteria) this;
        }

        public Criteria andRevisitDateIsNull() {
            addCriterion("revisit_date is null");
            return (Criteria) this;
        }

        public Criteria andRevisitDateIsNotNull() {
            addCriterion("revisit_date is not null");
            return (Criteria) this;
        }

        public Criteria andRevisitDateEqualTo(String value) {
            addCriterion("revisit_date =", value, "revisitDate");
            return (Criteria) this;
        }

        public Criteria andRevisitDateNotEqualTo(String value) {
            addCriterion("revisit_date <>", value, "revisitDate");
            return (Criteria) this;
        }

        public Criteria andRevisitDateGreaterThan(String value) {
            addCriterion("revisit_date >", value, "revisitDate");
            return (Criteria) this;
        }

        public Criteria andRevisitDateGreaterThanOrEqualTo(String value) {
            addCriterion("revisit_date >=", value, "revisitDate");
            return (Criteria) this;
        }

        public Criteria andRevisitDateLessThan(String value) {
            addCriterion("revisit_date <", value, "revisitDate");
            return (Criteria) this;
        }

        public Criteria andRevisitDateLessThanOrEqualTo(String value) {
            addCriterion("revisit_date <=", value, "revisitDate");
            return (Criteria) this;
        }

        public Criteria andRevisitDateLike(String value) {
            addCriterion("revisit_date like", value, "revisitDate");
            return (Criteria) this;
        }

        public Criteria andRevisitDateNotLike(String value) {
            addCriterion("revisit_date not like", value, "revisitDate");
            return (Criteria) this;
        }

        public Criteria andRevisitDateIn(List<String> values) {
            addCriterion("revisit_date in", values, "revisitDate");
            return (Criteria) this;
        }

        public Criteria andRevisitDateNotIn(List<String> values) {
            addCriterion("revisit_date not in", values, "revisitDate");
            return (Criteria) this;
        }

        public Criteria andRevisitDateBetween(String value1, String value2) {
            addCriterion("revisit_date between", value1, value2, "revisitDate");
            return (Criteria) this;
        }

        public Criteria andRevisitDateNotBetween(String value1, String value2) {
            addCriterion("revisit_date not between", value1, value2, "revisitDate");
            return (Criteria) this;
        }

        public Criteria andRevisitTelIsNull() {
            addCriterion("revisit_tel is null");
            return (Criteria) this;
        }

        public Criteria andRevisitTelIsNotNull() {
            addCriterion("revisit_tel is not null");
            return (Criteria) this;
        }

        public Criteria andRevisitTelEqualTo(String value) {
            addCriterion("revisit_tel =", value, "revisitTel");
            return (Criteria) this;
        }

        public Criteria andRevisitTelNotEqualTo(String value) {
            addCriterion("revisit_tel <>", value, "revisitTel");
            return (Criteria) this;
        }

        public Criteria andRevisitTelGreaterThan(String value) {
            addCriterion("revisit_tel >", value, "revisitTel");
            return (Criteria) this;
        }

        public Criteria andRevisitTelGreaterThanOrEqualTo(String value) {
            addCriterion("revisit_tel >=", value, "revisitTel");
            return (Criteria) this;
        }

        public Criteria andRevisitTelLessThan(String value) {
            addCriterion("revisit_tel <", value, "revisitTel");
            return (Criteria) this;
        }

        public Criteria andRevisitTelLessThanOrEqualTo(String value) {
            addCriterion("revisit_tel <=", value, "revisitTel");
            return (Criteria) this;
        }

        public Criteria andRevisitTelLike(String value) {
            addCriterion("revisit_tel like", value, "revisitTel");
            return (Criteria) this;
        }

        public Criteria andRevisitTelNotLike(String value) {
            addCriterion("revisit_tel not like", value, "revisitTel");
            return (Criteria) this;
        }

        public Criteria andRevisitTelIn(List<String> values) {
            addCriterion("revisit_tel in", values, "revisitTel");
            return (Criteria) this;
        }

        public Criteria andRevisitTelNotIn(List<String> values) {
            addCriterion("revisit_tel not in", values, "revisitTel");
            return (Criteria) this;
        }

        public Criteria andRevisitTelBetween(String value1, String value2) {
            addCriterion("revisit_tel between", value1, value2, "revisitTel");
            return (Criteria) this;
        }

        public Criteria andRevisitTelNotBetween(String value1, String value2) {
            addCriterion("revisit_tel not between", value1, value2, "revisitTel");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table revisit_log
     *
     * @mbggenerated do_not_delete_during_merge Fri Jan 24 10:27:51 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table revisit_log
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
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