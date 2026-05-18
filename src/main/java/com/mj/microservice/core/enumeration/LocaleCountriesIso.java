package com.mj.microservice.core.enumeration;

import com.mj.microservice.core.exception.BusinessException;
import java.util.Arrays;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/29/19
 */

public enum LocaleCountriesIso {

    JORDAN(1, "JO"),
    QATAR(2, "QA");

    private final int id;

    private final String code;

    LocaleCountriesIso(final int id, final String code) {
        this.id = id;
        this.code = code;
    }

    public static String getIsoCode(final int countryId) {
        return Arrays.stream(values())
                .filter(c -> c.id == countryId)
                .findFirst()
                .orElseThrow(CountryNotSupportedException::new)
                .getCode();
    }

    public static int getId(final String countryCode) {
        return Arrays.stream(values())
                .filter(c -> c.code.equalsIgnoreCase(countryCode))
                .findFirst()
                .orElseThrow(CountryNotSupportedException::new)
                .getId();
    }

    public static boolean isSupported(final String countryCode) {
        return Arrays.stream(values())
                .map(LocaleCountriesIso::getCode)
                .anyMatch(code -> code.equalsIgnoreCase(countryCode));
    }

    public static boolean isSupported(final int countryId) {
        return Arrays.stream(values())
                .map(LocaleCountriesIso::getId)
                .anyMatch(id -> id == countryId);
    }

    public static boolean isNotSupported(final String countryCode) {
        return !LocaleCountriesIso.isSupported(countryCode);
    }

    public static boolean isNotSupported(final int countryId) {
        return !LocaleCountriesIso.isSupported(countryId);
    }

    private int getId() {
        return id;
    }

    private String getCode() {
        return code;
    }

    //==================================================================================================================
    private static final class CountryNotSupportedException extends BusinessException {}
}
