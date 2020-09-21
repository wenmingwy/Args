package com.thoughtworks.basic;



public class Schema {
    private String flag;
    private Object defaultValue;
    private String valueType;

    public Schema(String flag, String valueType, Object defaultValue) {
        this.flag = flag;
        this.valueType = valueType;
        this.defaultValue = defaultValue;
    }

    public String getValueType() {
        return valueType;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }
}


