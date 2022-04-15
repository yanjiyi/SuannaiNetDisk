package com.suannai.netdisk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskExample() {
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

        public Criteria andTasktypeIsNull() {
            addCriterion("TaskType is null");
            return (Criteria) this;
        }

        public Criteria andTasktypeIsNotNull() {
            addCriterion("TaskType is not null");
            return (Criteria) this;
        }

        public Criteria andTasktypeEqualTo(Integer value) {
            addCriterion("TaskType =", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeNotEqualTo(Integer value) {
            addCriterion("TaskType <>", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeGreaterThan(Integer value) {
            addCriterion("TaskType >", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("TaskType >=", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeLessThan(Integer value) {
            addCriterion("TaskType <", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeLessThanOrEqualTo(Integer value) {
            addCriterion("TaskType <=", value, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeIn(List<Integer> values) {
            addCriterion("TaskType in", values, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeNotIn(List<Integer> values) {
            addCriterion("TaskType not in", values, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeBetween(Integer value1, Integer value2) {
            addCriterion("TaskType between", value1, value2, "tasktype");
            return (Criteria) this;
        }

        public Criteria andTasktypeNotBetween(Integer value1, Integer value2) {
            addCriterion("TaskType not between", value1, value2, "tasktype");
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

        public Criteria andTargetidIsNull() {
            addCriterion("TargetID is null");
            return (Criteria) this;
        }

        public Criteria andTargetidIsNotNull() {
            addCriterion("TargetID is not null");
            return (Criteria) this;
        }

        public Criteria andTargetidEqualTo(Integer value) {
            addCriterion("TargetID =", value, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidNotEqualTo(Integer value) {
            addCriterion("TargetID <>", value, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidGreaterThan(Integer value) {
            addCriterion("TargetID >", value, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidGreaterThanOrEqualTo(Integer value) {
            addCriterion("TargetID >=", value, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidLessThan(Integer value) {
            addCriterion("TargetID <", value, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidLessThanOrEqualTo(Integer value) {
            addCriterion("TargetID <=", value, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidIn(List<Integer> values) {
            addCriterion("TargetID in", values, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidNotIn(List<Integer> values) {
            addCriterion("TargetID not in", values, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidBetween(Integer value1, Integer value2) {
            addCriterion("TargetID between", value1, value2, "targetid");
            return (Criteria) this;
        }

        public Criteria andTargetidNotBetween(Integer value1, Integer value2) {
            addCriterion("TargetID not between", value1, value2, "targetid");
            return (Criteria) this;
        }

        public Criteria andTaskstatusIsNull() {
            addCriterion("TaskStatus is null");
            return (Criteria) this;
        }

        public Criteria andTaskstatusIsNotNull() {
            addCriterion("TaskStatus is not null");
            return (Criteria) this;
        }

        public Criteria andTaskstatusEqualTo(Boolean value) {
            addCriterion("TaskStatus =", value, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusNotEqualTo(Boolean value) {
            addCriterion("TaskStatus <>", value, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusGreaterThan(Boolean value) {
            addCriterion("TaskStatus >", value, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("TaskStatus >=", value, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusLessThan(Boolean value) {
            addCriterion("TaskStatus <", value, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusLessThanOrEqualTo(Boolean value) {
            addCriterion("TaskStatus <=", value, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusIn(List<Boolean> values) {
            addCriterion("TaskStatus in", values, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusNotIn(List<Boolean> values) {
            addCriterion("TaskStatus not in", values, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusBetween(Boolean value1, Boolean value2) {
            addCriterion("TaskStatus between", value1, value2, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andTaskstatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("TaskStatus not between", value1, value2, "taskstatus");
            return (Criteria) this;
        }

        public Criteria andAdditionalIsNull() {
            addCriterion("Additional is null");
            return (Criteria) this;
        }

        public Criteria andAdditionalIsNotNull() {
            addCriterion("Additional is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionalEqualTo(Integer value) {
            addCriterion("Additional =", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalNotEqualTo(Integer value) {
            addCriterion("Additional <>", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalGreaterThan(Integer value) {
            addCriterion("Additional >", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalGreaterThanOrEqualTo(Integer value) {
            addCriterion("Additional >=", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalLessThan(Integer value) {
            addCriterion("Additional <", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalLessThanOrEqualTo(Integer value) {
            addCriterion("Additional <=", value, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalIn(List<Integer> values) {
            addCriterion("Additional in", values, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalNotIn(List<Integer> values) {
            addCriterion("Additional not in", values, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalBetween(Integer value1, Integer value2) {
            addCriterion("Additional between", value1, value2, "additional");
            return (Criteria) this;
        }

        public Criteria andAdditionalNotBetween(Integer value1, Integer value2) {
            addCriterion("Additional not between", value1, value2, "additional");
            return (Criteria) this;
        }

        public Criteria andIdleIsNull() {
            addCriterion("Idle is null");
            return (Criteria) this;
        }

        public Criteria andIdleIsNotNull() {
            addCriterion("Idle is not null");
            return (Criteria) this;
        }

        public Criteria andIdleEqualTo(Boolean value) {
            addCriterion("Idle =", value, "idle");
            return (Criteria) this;
        }

        public Criteria andIdleNotEqualTo(Boolean value) {
            addCriterion("Idle <>", value, "idle");
            return (Criteria) this;
        }

        public Criteria andIdleGreaterThan(Boolean value) {
            addCriterion("Idle >", value, "idle");
            return (Criteria) this;
        }

        public Criteria andIdleGreaterThanOrEqualTo(Boolean value) {
            addCriterion("Idle >=", value, "idle");
            return (Criteria) this;
        }

        public Criteria andIdleLessThan(Boolean value) {
            addCriterion("Idle <", value, "idle");
            return (Criteria) this;
        }

        public Criteria andIdleLessThanOrEqualTo(Boolean value) {
            addCriterion("Idle <=", value, "idle");
            return (Criteria) this;
        }

        public Criteria andIdleIn(List<Boolean> values) {
            addCriterion("Idle in", values, "idle");
            return (Criteria) this;
        }

        public Criteria andIdleNotIn(List<Boolean> values) {
            addCriterion("Idle not in", values, "idle");
            return (Criteria) this;
        }

        public Criteria andIdleBetween(Boolean value1, Boolean value2) {
            addCriterion("Idle between", value1, value2, "idle");
            return (Criteria) this;
        }

        public Criteria andIdleNotBetween(Boolean value1, Boolean value2) {
            addCriterion("Idle not between", value1, value2, "idle");
            return (Criteria) this;
        }

        public Criteria andGidIsNull() {
            addCriterion("GID is null");
            return (Criteria) this;
        }

        public Criteria andGidIsNotNull() {
            addCriterion("GID is not null");
            return (Criteria) this;
        }

        public Criteria andGidEqualTo(String value) {
            addCriterion("GID =", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotEqualTo(String value) {
            addCriterion("GID <>", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidGreaterThan(String value) {
            addCriterion("GID >", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidGreaterThanOrEqualTo(String value) {
            addCriterion("GID >=", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLessThan(String value) {
            addCriterion("GID <", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLessThanOrEqualTo(String value) {
            addCriterion("GID <=", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLike(String value) {
            addCriterion("GID like", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotLike(String value) {
            addCriterion("GID not like", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidIn(List<String> values) {
            addCriterion("GID in", values, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotIn(List<String> values) {
            addCriterion("GID not in", values, "gid");
            return (Criteria) this;
        }

        public Criteria andGidBetween(String value1, String value2) {
            addCriterion("GID between", value1, value2, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotBetween(String value1, String value2) {
            addCriterion("GID not between", value1, value2, "gid");
            return (Criteria) this;
        }

        public Criteria andAria2idIsNull() {
            addCriterion("Aria2ID is null");
            return (Criteria) this;
        }

        public Criteria andAria2idIsNotNull() {
            addCriterion("Aria2ID is not null");
            return (Criteria) this;
        }

        public Criteria andAria2idEqualTo(String value) {
            addCriterion("Aria2ID =", value, "aria2id");
            return (Criteria) this;
        }

        public Criteria andAria2idNotEqualTo(String value) {
            addCriterion("Aria2ID <>", value, "aria2id");
            return (Criteria) this;
        }

        public Criteria andAria2idGreaterThan(String value) {
            addCriterion("Aria2ID >", value, "aria2id");
            return (Criteria) this;
        }

        public Criteria andAria2idGreaterThanOrEqualTo(String value) {
            addCriterion("Aria2ID >=", value, "aria2id");
            return (Criteria) this;
        }

        public Criteria andAria2idLessThan(String value) {
            addCriterion("Aria2ID <", value, "aria2id");
            return (Criteria) this;
        }

        public Criteria andAria2idLessThanOrEqualTo(String value) {
            addCriterion("Aria2ID <=", value, "aria2id");
            return (Criteria) this;
        }

        public Criteria andAria2idLike(String value) {
            addCriterion("Aria2ID like", value, "aria2id");
            return (Criteria) this;
        }

        public Criteria andAria2idNotLike(String value) {
            addCriterion("Aria2ID not like", value, "aria2id");
            return (Criteria) this;
        }

        public Criteria andAria2idIn(List<String> values) {
            addCriterion("Aria2ID in", values, "aria2id");
            return (Criteria) this;
        }

        public Criteria andAria2idNotIn(List<String> values) {
            addCriterion("Aria2ID not in", values, "aria2id");
            return (Criteria) this;
        }

        public Criteria andAria2idBetween(String value1, String value2) {
            addCriterion("Aria2ID between", value1, value2, "aria2id");
            return (Criteria) this;
        }

        public Criteria andAria2idNotBetween(String value1, String value2) {
            addCriterion("Aria2ID not between", value1, value2, "aria2id");
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