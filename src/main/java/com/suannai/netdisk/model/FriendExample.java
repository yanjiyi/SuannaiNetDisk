package com.suannai.netdisk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FriendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FriendExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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
            addCriterion("Id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("Id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("Id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("Id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("Id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("Id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("Id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("Id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("Id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("Id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFrdidIsNull() {
            addCriterion("FrdID is null");
            return (Criteria) this;
        }

        public Criteria andFrdidIsNotNull() {
            addCriterion("FrdID is not null");
            return (Criteria) this;
        }

        public Criteria andFrdidEqualTo(Integer value) {
            addCriterion("FrdID =", value, "frdid");
            return (Criteria) this;
        }

        public Criteria andFrdidNotEqualTo(Integer value) {
            addCriterion("FrdID <>", value, "frdid");
            return (Criteria) this;
        }

        public Criteria andFrdidGreaterThan(Integer value) {
            addCriterion("FrdID >", value, "frdid");
            return (Criteria) this;
        }

        public Criteria andFrdidGreaterThanOrEqualTo(Integer value) {
            addCriterion("FrdID >=", value, "frdid");
            return (Criteria) this;
        }

        public Criteria andFrdidLessThan(Integer value) {
            addCriterion("FrdID <", value, "frdid");
            return (Criteria) this;
        }

        public Criteria andFrdidLessThanOrEqualTo(Integer value) {
            addCriterion("FrdID <=", value, "frdid");
            return (Criteria) this;
        }

        public Criteria andFrdidIn(List<Integer> values) {
            addCriterion("FrdID in", values, "frdid");
            return (Criteria) this;
        }

        public Criteria andFrdidNotIn(List<Integer> values) {
            addCriterion("FrdID not in", values, "frdid");
            return (Criteria) this;
        }

        public Criteria andFrdidBetween(Integer value1, Integer value2) {
            addCriterion("FrdID between", value1, value2, "frdid");
            return (Criteria) this;
        }

        public Criteria andFrdidNotBetween(Integer value1, Integer value2) {
            addCriterion("FrdID not between", value1, value2, "frdid");
            return (Criteria) this;
        }

        public Criteria andOwnnerIsNull() {
            addCriterion("Ownner is null");
            return (Criteria) this;
        }

        public Criteria andOwnnerIsNotNull() {
            addCriterion("Ownner is not null");
            return (Criteria) this;
        }

        public Criteria andOwnnerEqualTo(Integer value) {
            addCriterion("Ownner =", value, "ownner");
            return (Criteria) this;
        }

        public Criteria andOwnnerNotEqualTo(Integer value) {
            addCriterion("Ownner <>", value, "ownner");
            return (Criteria) this;
        }

        public Criteria andOwnnerGreaterThan(Integer value) {
            addCriterion("Ownner >", value, "ownner");
            return (Criteria) this;
        }

        public Criteria andOwnnerGreaterThanOrEqualTo(Integer value) {
            addCriterion("Ownner >=", value, "ownner");
            return (Criteria) this;
        }

        public Criteria andOwnnerLessThan(Integer value) {
            addCriterion("Ownner <", value, "ownner");
            return (Criteria) this;
        }

        public Criteria andOwnnerLessThanOrEqualTo(Integer value) {
            addCriterion("Ownner <=", value, "ownner");
            return (Criteria) this;
        }

        public Criteria andOwnnerIn(List<Integer> values) {
            addCriterion("Ownner in", values, "ownner");
            return (Criteria) this;
        }

        public Criteria andOwnnerNotIn(List<Integer> values) {
            addCriterion("Ownner not in", values, "ownner");
            return (Criteria) this;
        }

        public Criteria andOwnnerBetween(Integer value1, Integer value2) {
            addCriterion("Ownner between", value1, value2, "ownner");
            return (Criteria) this;
        }

        public Criteria andOwnnerNotBetween(Integer value1, Integer value2) {
            addCriterion("Ownner not between", value1, value2, "ownner");
            return (Criteria) this;
        }

        public Criteria andWhoIsNull() {
            addCriterion("Who is null");
            return (Criteria) this;
        }

        public Criteria andWhoIsNotNull() {
            addCriterion("Who is not null");
            return (Criteria) this;
        }

        public Criteria andWhoEqualTo(String value) {
            addCriterion("Who =", value, "who");
            return (Criteria) this;
        }

        public Criteria andWhoNotEqualTo(String value) {
            addCriterion("Who <>", value, "who");
            return (Criteria) this;
        }

        public Criteria andWhoGreaterThan(String value) {
            addCriterion("Who >", value, "who");
            return (Criteria) this;
        }

        public Criteria andWhoGreaterThanOrEqualTo(String value) {
            addCriterion("Who >=", value, "who");
            return (Criteria) this;
        }

        public Criteria andWhoLessThan(String value) {
            addCriterion("Who <", value, "who");
            return (Criteria) this;
        }

        public Criteria andWhoLessThanOrEqualTo(String value) {
            addCriterion("Who <=", value, "who");
            return (Criteria) this;
        }

        public Criteria andWhoLike(String value) {
            addCriterion("Who like", value, "who");
            return (Criteria) this;
        }

        public Criteria andWhoNotLike(String value) {
            addCriterion("Who not like", value, "who");
            return (Criteria) this;
        }

        public Criteria andWhoIn(List<String> values) {
            addCriterion("Who in", values, "who");
            return (Criteria) this;
        }

        public Criteria andWhoNotIn(List<String> values) {
            addCriterion("Who not in", values, "who");
            return (Criteria) this;
        }

        public Criteria andWhoBetween(String value1, String value2) {
            addCriterion("Who between", value1, value2, "who");
            return (Criteria) this;
        }

        public Criteria andWhoNotBetween(String value1, String value2) {
            addCriterion("Who not between", value1, value2, "who");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("Date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("Date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterion("Date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterion("Date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterion("Date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterion("Date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterion("Date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterion("Date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterion("Date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterion("Date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterion("Date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterion("Date not between", value1, value2, "date");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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