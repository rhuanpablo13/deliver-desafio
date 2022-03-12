package com.deliver.utils;

public enum DatePattern {
    
    DDMMYYYY("dd/MM/yyyy"),
    YYYY_MM_DD("yyyy-MM-dd");

    private String pattern;

    private DatePattern(String pattern)  {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }
}