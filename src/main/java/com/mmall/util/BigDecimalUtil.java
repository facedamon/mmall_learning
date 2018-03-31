package com.mmall.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author facedamon facedamon
 * @create 2018/3/31
 */
public class BigDecimalUtil {
    private BigDecimalUtil(){}

    public static BigDecimal add(double one,double two){
        BigDecimal v1 = new BigDecimal(Double.toString(one));
        BigDecimal v2 = new BigDecimal(Double.toString(two));
        return v1.add(v2);
    }

    public static BigDecimal sub(double one,double two){
        BigDecimal v1 = new BigDecimal(Double.toString(one));
        BigDecimal v2 = new BigDecimal(Double.toString(two));
        return v1.subtract(v2);
    }

    public static BigDecimal mul(double one,double two){
        BigDecimal v1 = new BigDecimal(Double.toString(one));
        BigDecimal v2 = new BigDecimal(Double.toString(two));
        return v1.multiply(v2);
    }

    public static BigDecimal div(double one,double two){
        BigDecimal v1 = new BigDecimal(Double.toString(one));
        BigDecimal v2 = new BigDecimal(Double.toString(two));
        return v1.divide(v2,2, RoundingMode.HALF_UP);
    }
}
