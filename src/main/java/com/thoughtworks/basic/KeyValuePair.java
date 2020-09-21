package com.thoughtworks.basic;

import java.util.Objects;

public class KeyValuePair {
    private String key;
    private String value;

    public KeyValuePair(String key, String value){
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        KeyValuePair that = (KeyValuePair) object;
        return key.equals(that.key) &&
                value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
