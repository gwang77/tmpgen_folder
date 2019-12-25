package config;

import org.apache.commons.lang.StringUtils;

public class ParaData {
    String value = "";
    String split = "";

    public ParaData(String value) {
        this.value = StringUtils.isBlank(value)?"":value;
    }

    public ParaData(String value, String split) {
        this.value = StringUtils.isBlank(value)?"":value;
        this.split = StringUtils.isBlank(split)?"":split;
    }

    public String getValue() {
        return value;
    }

    public String getSplit() {
        return split;
    }

    public String getLowVal() {
        return value.toLowerCase();
    }

    public String getUpVal() {
        return value.toUpperCase();
    }

    public String getFirstUpVal() {
        String first = value.substring(0, 1);
        return value.replaceFirst(first, first.toUpperCase());
    }

    public String getFirstlowVal() {
        String first = value.substring(0, 1);
        return value.replaceFirst(first, first.toLowerCase());
    }

    public String toString() {
        return value;
    }
}
