package com.fund.api.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class BigDecimalUtil {
    private static final int DEF_DIV_SCALE = 4;
    private static final int BALANCE_SCALE = 2;

    /**
     * 乘
     */
    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
        if (v1 == null) {
            v1 = BigDecimal.ZERO;
        }

        if (v2 == null) {
            v2 = BigDecimal.ZERO;
        }
        return v1.multiply(v2).setScale(DEF_DIV_SCALE, RoundingMode.HALF_UP);
    }

    /**
     * 除法
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
        if (v1 == null) {
            v1 = BigDecimal.ZERO;
        }

        if (v2 == null) {
            v2 = BigDecimal.ZERO;
        }
        return div(v1, v2, DEF_DIV_SCALE);
    }


    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }

        if (v1 == null) {
            v1 = BigDecimal.ZERO;
        }

        if (v2 == null) {
            v2 = BigDecimal.ZERO;
        }

        return v1.divide(v2, scale, RoundingMode.HALF_UP);
    }
}