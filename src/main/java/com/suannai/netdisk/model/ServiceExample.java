package com.suannai.netdisk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ServiceExample() {
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

        public Criteria andUserfilenameIsNull() {
            addCriterion("UserFileName is null");
            return (Criteria) this;
        }

        public Criteria andUserfilenameIsNotNull() {
            addCriterion("UserFileName is not null");
            return (Criteria) this;
        }

        public Criteria andUserfilenameEqualTo(String value) {
            addCriterion("UserFileName =", value, "userfilename");
            return (Criteria) this;
        }

        public Criteria andUserfilenameNotEqualTo(String value) {
            addCriterion("UserFileName <>", value, "userfilename");
            return (Criteria) this;
        }

        public Criteria andUserfilenameGreaterThan(String value) {
            addCriterion("UserFileName >", value, "userfilename");
            return (Criteria) this;
        }

        public Criteria andUserfilenameGreaterThanOrEqualTo(String value) {
            addCriterion("UserFileName >=", value, "userfilename");
            return (Criteria) this;
        }

        public Criteria andUserfilenameLessThan(String value) {
            addCriterion("UserFileName <", value, "userfilename");
            return (Criteria) this;
        }

        public Criteria andUserfilenameLessThanOrEqualTo(String value) {
            addCriterion("UserFileName <=", value, "userfilename");
            return (Criteria) this;
        }

        public Criteria andUserfilenameLike(String value) {
            addCriterion("UserFileName like", value, "userfilename");
            return (Criteria) this;
        }

        public Criteria andUserfilenameNotLike(String value) {
            addCriterion("UserFileName not like", value, "userfilename");
            return (Criteria) this;
        }

        public Criteria andUserfilenameIn(List<String> values) {
            addCriterion("UserFileName in", values, "userfilename");
            return (Criteria) this;
        }

        public Criteria andUserfilenameNotIn(List<String> values) {
            addCriterion("UserFileName not in", values, "userfilename");
            return (Criteria) this;
        }

        public Criteria andUserfilenameBetween(String value1, String value2) {
            addCriterion("UserFileName between", value1, value2, "userfilename");
            return (Criteria) this;
        }

        public Criteria andUserfilenameNotBetween(String value1, String value2) {
            addCriterion("UserFileName not between", value1, value2, "userfilename");
            return (Criteria) this;
        }

        public Criteria andSysfilerecordidIsNull() {
            addCriterion("SysFileRecordID is null");
            return (Criteria) this;
        }

        public Criteria andSysfilerecordidIsNotNull() {
            addCriterion("SysFileRecordID is not null");
            return (Criteria) this;
        }

        public Criteria andSysfilerecordidEqualTo(Integer value) {
            addCriterion("SysFileRecordID =", value, "sysfilerecordid");
            return (Criteria) this;
        }

        public Criteria andSysfilerecordidNotEqualTo(Integer value) {
            addCriterion("SysFileRecordID <>", value, "sysfilerecordid");
            return (Criteria) this;
        }

        public Criteria andSysfilerecordidGreaterThan(Integer value) {
            addCriterion("SysFileRecordID >", value, "sysfilerecordid");
            return (Criteria) this;
        }

        public Criteria andSysfilerecordidGreaterThanOrEqualTo(Integer value) {
            addCriterion("SysFileRecordID >=", value, "sysfilerecordid");
            return (Criteria) this;
        }

        public Criteria andSysfilerecordidLessThan(Integer value) {
            addCriterion("SysFileRecordID <", value, "sysfilerecordid");
            return (Criteria) this;
        }

        public Criteria andSysfilerecordidLessThanOrEqualTo(Integer value) {
            addCriterion("SysFileRecordID <=", value, "sysfilerecordid");
            return (Criteria) this;
        }

        public Criteria andSysfilerecordidIn(List<Integer> values) {
            addCriterion("SysFileRecordID in", values, "sysfilerecordid");
            return (Criteria) this;
        }

        public Criteria andSysfilerecordidNotIn(List<Integer> values) {
            addCriterion("SysFileRecordID not in", values, "sysfilerecordid");
            return (Criteria) this;
        }

        public Criteria andSysfilerecordidBetween(Integer value1, Integer value2) {
            addCriterion("SysFileRecordID between", value1, value2, "sysfilerecordid");
            return (Criteria) this;
        }

        public Criteria andSysfilerecordidNotBetween(Integer value1, Integer value2) {
            addCriterion("SysFileRecordID not between", value1, value2, "sysfilerecordid");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("Status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("Status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Boolean value) {
            addCriterion("Status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("Status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("Status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("Status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("Status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("Status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("Status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("Status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("Status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("Status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andUploaddateIsNull() {
            addCriterion("UploadDate is null");
            return (Criteria) this;
        }

        public Criteria andUploaddateIsNotNull() {
            addCriterion("UploadDate is not null");
            return (Criteria) this;
        }

        public Criteria andUploaddateEqualTo(Date value) {
            addCriterion("UploadDate =", value, "uploaddate");
            return (Criteria) this;
        }

        public Criteria andUploaddateNotEqualTo(Date value) {
            addCriterion("UploadDate <>", value, "uploaddate");
            return (Criteria) this;
        }

        public Criteria andUploaddateGreaterThan(Date value) {
            addCriterion("UploadDate >", value, "uploaddate");
            return (Criteria) this;
        }

        public Criteria andUploaddateGreaterThanOrEqualTo(Date value) {
            addCriterion("UploadDate >=", value, "uploaddate");
            return (Criteria) this;
        }

        public Criteria andUploaddateLessThan(Date value) {
            addCriterion("UploadDate <", value, "uploaddate");
            return (Criteria) this;
        }

        public Criteria andUploaddateLessThanOrEqualTo(Date value) {
            addCriterion("UploadDate <=", value, "uploaddate");
            return (Criteria) this;
        }

        public Criteria andUploaddateIn(List<Date> values) {
            addCriterion("UploadDate in", values, "uploaddate");
            return (Criteria) this;
        }

        public Criteria andUploaddateNotIn(List<Date> values) {
            addCriterion("UploadDate not in", values, "uploaddate");
            return (Criteria) this;
        }

        public Criteria andUploaddateBetween(Date value1, Date value2) {
            addCriterion("UploadDate between", value1, value2, "uploaddate");
            return (Criteria) this;
        }

        public Criteria andUploaddateNotBetween(Date value1, Date value2) {
            addCriterion("UploadDate not between", value1, value2, "uploaddate");
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