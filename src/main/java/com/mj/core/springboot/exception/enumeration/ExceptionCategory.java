package com.mj.core.springboot.exception.enumeration;

public enum ExceptionCategory {

    BUSINESS("BUSINESS", "010", "BE"),
    TECHNICAL("TECHNICAL", "030", "TE"),
    SECURITY("SECURITY", "040", "SE"),
    INTEGRATION("INTEGRATION", "050", "IE"),
    GENERAL("GENERAL", "060", "GE");

    private String abbreviation;
    private String code;
    private String name;

    ExceptionCategory(String name, String code, String abbreviation) {
        this.abbreviation = abbreviation;
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getCode() {
        return code;
    }
}
