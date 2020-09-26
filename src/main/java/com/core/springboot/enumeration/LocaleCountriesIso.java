package com.core.springboot.enumeration;

import com.core.springboot.exception.BusinessException;

import java.util.Arrays;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/29/19
 */

public enum LocaleCountriesIso {

    JORDAN(1, "JO"),
    QATAR(2, "QA");

    private int id;

    private String isoCode;

    LocaleCountriesIso(int id, String isoCode) {
        this.id = id;
        this.isoCode = isoCode;
    }

    public static String getIsoCode(final int countryId) {
        return Arrays.stream(values())
                .filter(c -> c.id == countryId)
                .findFirst()
                .orElseThrow(CountryNotSupportedException::new)
                .getIsoCode();
    }

    public static int getId(final String countryCode) {
        return Arrays.stream(values())
                .filter(c -> c.isoCode.equalsIgnoreCase(countryCode))
                .findFirst()
                .orElseThrow(CountryNotSupportedException::new)
                .getId();
    }

    public static boolean isSupported(final String countryCode) {
        return Arrays.stream(values()).anyMatch(country -> country.getIsoCode().equalsIgnoreCase(countryCode));
    }

    public static boolean isSupported(final int countryId) {
        return Arrays.stream(values()).anyMatch(country -> country.getId() == countryId);
    }

    public static boolean isNotSupported(final String countryCode) {
        return Arrays.stream(values()).noneMatch(country -> country.getIsoCode().equalsIgnoreCase(countryCode));
    }

    public static boolean isNotSupported(final int countryId) {
        return Arrays.stream(values()).noneMatch(country -> country.getId() == countryId);
    }

    private int getId() {
        return id;
    }

    private String getIsoCode() {
        return isoCode;
    }

    private static final class CountryNotSupportedException extends BusinessException {
    }
}
