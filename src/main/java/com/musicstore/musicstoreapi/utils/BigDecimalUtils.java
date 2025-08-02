package com.musicstore.musicstoreapi.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {
    public static BigDecimal add(BigDecimal param1, BigDecimal param2) {
        if (param1 == null && param2 == null) return BigDecimal.ZERO;
        if (param1 == null) return param2;
        if (param2 == null) return param1;
        return param1.add(param2);
    }

    public static BigDecimal multiply(BigDecimal param1, BigDecimal param2) {
        if (param1 == null || param2 == null) return BigDecimal.ZERO;
        return param1.multiply(param2);
    }

    public static BigDecimal multiply(BigDecimal param1, Long param2) {
        return multiply(param1, BigDecimal.valueOf(param2));
    }

    public static BigDecimal multiply(BigDecimal param1, Integer param2) {
        return multiply(param1, BigDecimal.valueOf(param2));
    }

    public static BigDecimal multiply(Long param1, BigDecimal param2) {
        return multiply(BigDecimal.valueOf(param1), param2);
    }
    public static BigDecimal multiply(Integer param1, BigDecimal param2) {
        return multiply(BigDecimal.valueOf(param1), param2);
    }
}
