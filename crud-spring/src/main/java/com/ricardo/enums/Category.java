package com.ricardo.enums;

public enum Category {

    BACKEND("back-end"),
    FRONTEND("front-end");

    private String value;

    private Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Category{" +
                "value='" + value + '\'' +
                '}';
    }
}
