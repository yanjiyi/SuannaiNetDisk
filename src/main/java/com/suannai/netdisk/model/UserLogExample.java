package com.suannai.netdisk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserLogExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("UserID is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("UserID is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("UserID =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("UserID <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("UserID >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("UserID >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("UserID <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("UserID <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("UserID in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("UserID not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("UserID between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("UserID not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andSuperuserIsNull() {
            addCriterion("SuperUser is null");
            return (Criteria) this;
        }

        public Criteria andSuperuserIsNotNull() {
            addCriterion("SuperUser is not null");
            return (Criteria) this;
        }

        public Criteria andSuperuserEqualTo(Boolean value) {
            addCriterion("SuperUser =", value, "superuser");
            return (Criteria) this;
        }

        public Criteria andSuperuserNotEqualTo(Boolean value) {
            addCriterion("SuperUser <>", value, "superuser");
            return (Criteria) this;
        }

        public Criteria andSuperuserGreaterThan(Boolean value) {
            addCriterion("SuperUser >", value, "superuser");
            return (Criteria) this;
        }

        public Criteria andSuperuserGreaterThanOrEqualTo(Boolean value) {
            addCriterion("SuperUser >=", value, "superuser");
            return (Criteria) this;
        }

        public Criteria andSuperuserLessThan(Boolean value) {
            addCriterion("SuperUser <", value, "superuser");
            return (Criteria) this;
        }

        public Criteria andSuperuserLessThanOrEqualTo(Boolean value) {
            addCriterion("SuperUser <=", value, "superuser");
            return (Criteria) this;
        }

        public Criteria andSuperuserIn(List<Boolean> values) {
            addCriterion("SuperUser in", values, "superuser");
            return (Criteria) this;
        }

        public Criteria andSuperuserNotIn(List<Boolean> values) {
            addCriterion("SuperUser not in", values, "superuser");
            return (Criteria) this;
        }

        public Criteria andSuperuserBetween(Boolean value1, Boolean value2) {
            addCriterion("SuperUser between", value1, value2, "superuser");
            return (Criteria) this;
        }

        public Criteria andSuperuserNotBetween(Boolean value1, Boolean value2) {
            addCriterion("SuperUser not between", value1, value2, "superuser");
            return (Criteria) this;
        }

        public Criteria andOperationdescriptionIsNull() {
            addCriterion("OperationDescription is null");
            return (Criteria) this;
        }

        public Criteria andOperationdescriptionIsNotNull() {
            addCriterion("OperationDescription is not null");
            return (Criteria) this;
        }

        public Criteria andOperationdescriptionEqualTo(String value) {
            addCriterion("OperationDescription =", value, "operationdescription");
            return (Criteria) this;
        }

        public Criteria andOperationdescriptionNotEqualTo(String value) {
            addCriterion("OperationDescription <>", value, "operationdescription");
            return (Criteria) this;
        }

        public Criteria andOperationdescriptionGreaterThan(String value) {
            addCriterion("OperationDescription >", value, "operationdescription");
            return (Criteria) this;
        }

        public Criteria andOperationdescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("OperationDescription >=", value, "operationdescription");
            return (Criteria) this;
        }

        public Criteria andOperationdescriptionLessThan(String value) {
            addCriterion("OperationDescription <", value, "operationdescription");
            return (Criteria) this;
        }

        public Criteria andOperationdescriptionLessThanOrEqualTo(String value) {
            addCriterion("OperationDescription <=", value, "operationdescription");
            return (Criteria) this;
        }

        public Criteria andOperationdescriptionLike(String value) {
            addCriterion("OperationDescription like", value, "operationdescription");
            return (Criteria) this;
        }

        public Criteria andOperationdescriptionNotLike(String value) {
            addCriterion("OperationDescription not like", value, "operationdescription");
            return (Criteria) this;
        }

        public Criteria andOperationdescriptionIn(List<String> values) {
            addCriterion("OperationDescription in", values, "operationdescription");
            return (Criteria) this;
        }

        public Criteria andOperationdescriptionNotIn(List<String> values) {
            addCriterion("OperationDescription not in", values, "operationdescription");
            return (Criteria) this;
        }

        public Criteria andOperationdescriptionBetween(String value1, String value2) {
            addCriterion("OperationDescription between", value1, value2, "operationdescription");
            return (Criteria) this;
        }

        public Criteria andOperationdescriptionNotBetween(String value1, String value2) {
            addCriterion("OperationDescription not between", value1, value2, "operationdescription");
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