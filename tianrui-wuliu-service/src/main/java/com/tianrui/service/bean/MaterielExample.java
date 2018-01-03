package com.tianrui.service.bean;

import java.util.ArrayList;
import java.util.List;

public class MaterielExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MaterielExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMaterieNameIsNull() {
            addCriterion("materie_name is null");
            return (Criteria) this;
        }

        public Criteria andMaterieNameIsNotNull() {
            addCriterion("materie_name is not null");
            return (Criteria) this;
        }

        public Criteria andMaterieNameEqualTo(String value) {
            addCriterion("materie_name =", value, "materieName");
            return (Criteria) this;
        }

        public Criteria andMaterieNameNotEqualTo(String value) {
            addCriterion("materie_name <>", value, "materieName");
            return (Criteria) this;
        }

        public Criteria andMaterieNameGreaterThan(String value) {
            addCriterion("materie_name >", value, "materieName");
            return (Criteria) this;
        }

        public Criteria andMaterieNameGreaterThanOrEqualTo(String value) {
            addCriterion("materie_name >=", value, "materieName");
            return (Criteria) this;
        }

        public Criteria andMaterieNameLessThan(String value) {
            addCriterion("materie_name <", value, "materieName");
            return (Criteria) this;
        }

        public Criteria andMaterieNameLessThanOrEqualTo(String value) {
            addCriterion("materie_name <=", value, "materieName");
            return (Criteria) this;
        }

        public Criteria andMaterieNameLike(String value) {
            addCriterion("materie_name like", value, "materieName");
            return (Criteria) this;
        }

        public Criteria andMaterieNameNotLike(String value) {
            addCriterion("materie_name not like", value, "materieName");
            return (Criteria) this;
        }

        public Criteria andMaterieNameIn(List<String> values) {
            addCriterion("materie_name in", values, "materieName");
            return (Criteria) this;
        }

        public Criteria andMaterieNameNotIn(List<String> values) {
            addCriterion("materie_name not in", values, "materieName");
            return (Criteria) this;
        }

        public Criteria andMaterieNameBetween(String value1, String value2) {
            addCriterion("materie_name between", value1, value2, "materieName");
            return (Criteria) this;
        }

        public Criteria andMaterieNameNotBetween(String value1, String value2) {
            addCriterion("materie_name not between", value1, value2, "materieName");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Long value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Long value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Long value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Long value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Long value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Long value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Long> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Long> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Long value1, Long value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Long value1, Long value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("modifier like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("modifier not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierTimeIsNull() {
            addCriterion("modifier_time is null");
            return (Criteria) this;
        }

        public Criteria andModifierTimeIsNotNull() {
            addCriterion("modifier_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifierTimeEqualTo(Long value) {
            addCriterion("modifier_time =", value, "modifierTime");
            return (Criteria) this;
        }

        public Criteria andModifierTimeNotEqualTo(Long value) {
            addCriterion("modifier_time <>", value, "modifierTime");
            return (Criteria) this;
        }

        public Criteria andModifierTimeGreaterThan(Long value) {
            addCriterion("modifier_time >", value, "modifierTime");
            return (Criteria) this;
        }

        public Criteria andModifierTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("modifier_time >=", value, "modifierTime");
            return (Criteria) this;
        }

        public Criteria andModifierTimeLessThan(Long value) {
            addCriterion("modifier_time <", value, "modifierTime");
            return (Criteria) this;
        }

        public Criteria andModifierTimeLessThanOrEqualTo(Long value) {
            addCriterion("modifier_time <=", value, "modifierTime");
            return (Criteria) this;
        }

        public Criteria andModifierTimeIn(List<Long> values) {
            addCriterion("modifier_time in", values, "modifierTime");
            return (Criteria) this;
        }

        public Criteria andModifierTimeNotIn(List<Long> values) {
            addCriterion("modifier_time not in", values, "modifierTime");
            return (Criteria) this;
        }

        public Criteria andModifierTimeBetween(Long value1, Long value2) {
            addCriterion("modifier_time between", value1, value2, "modifierTime");
            return (Criteria) this;
        }

        public Criteria andModifierTimeNotBetween(Long value1, Long value2) {
            addCriterion("modifier_time not between", value1, value2, "modifierTime");
            return (Criteria) this;
        }

        public Criteria andMaterieStatusIsNull() {
            addCriterion("materie_status is null");
            return (Criteria) this;
        }

        public Criteria andMaterieStatusIsNotNull() {
            addCriterion("materie_status is not null");
            return (Criteria) this;
        }

        public Criteria andMaterieStatusEqualTo(String value) {
            addCriterion("materie_status =", value, "materieStatus");
            return (Criteria) this;
        }

        public Criteria andMaterieStatusNotEqualTo(String value) {
            addCriterion("materie_status <>", value, "materieStatus");
            return (Criteria) this;
        }

        public Criteria andMaterieStatusGreaterThan(String value) {
            addCriterion("materie_status >", value, "materieStatus");
            return (Criteria) this;
        }

        public Criteria andMaterieStatusGreaterThanOrEqualTo(String value) {
            addCriterion("materie_status >=", value, "materieStatus");
            return (Criteria) this;
        }

        public Criteria andMaterieStatusLessThan(String value) {
            addCriterion("materie_status <", value, "materieStatus");
            return (Criteria) this;
        }

        public Criteria andMaterieStatusLessThanOrEqualTo(String value) {
            addCriterion("materie_status <=", value, "materieStatus");
            return (Criteria) this;
        }

        public Criteria andMaterieStatusLike(String value) {
            addCriterion("materie_status like", value, "materieStatus");
            return (Criteria) this;
        }

        public Criteria andMaterieStatusNotLike(String value) {
            addCriterion("materie_status not like", value, "materieStatus");
            return (Criteria) this;
        }

        public Criteria andMaterieStatusIn(List<String> values) {
            addCriterion("materie_status in", values, "materieStatus");
            return (Criteria) this;
        }

        public Criteria andMaterieStatusNotIn(List<String> values) {
            addCriterion("materie_status not in", values, "materieStatus");
            return (Criteria) this;
        }

        public Criteria andMaterieStatusBetween(String value1, String value2) {
            addCriterion("materie_status between", value1, value2, "materieStatus");
            return (Criteria) this;
        }

        public Criteria andMaterieStatusNotBetween(String value1, String value2) {
            addCriterion("materie_status not between", value1, value2, "materieStatus");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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