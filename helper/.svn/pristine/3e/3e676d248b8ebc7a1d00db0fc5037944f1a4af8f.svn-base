/**
 * @Probject Name: common-core
 * @Path: com.bailian.core.utilsNumberUtils.java
 * @Create By fanshunqing
 * @Create In 2014年10月22日 下午6:48:12
 * TODO
 */
package org.blue.helper.StringHelper.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;


public class NumberUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NumberUtils.class);
	
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("(-)?[0-9]+(.[0-9]+)?");
        return pattern.matcher(str).matches();
    }
    
    public static String formatDouble(Double number,String format){
    	DecimalFormat df=new DecimalFormat(format);
    	return df.format(number);
    }
    
    /**
     * 捕获所有异常并消化，防止非数字字符串解析导致程序崩溃
     * @param number
     * @return
     */
    public static synchronized double parseDouble(String number){
    	double parsed=0;
    	try {
    		parsed=Double.parseDouble(number);
		} catch (Exception e) {
			LOGGER.error("非法格式，不能转换成double类型=>"+number);
		}
    	return parsed;
    }
    
    /**
     * 捕获所有异常并消化，防止非数字字符串解析导致程序崩溃
     * @param number
     * @return
     */
    public static synchronized int parseInt(String number){
    	int parsed=0;
    	try {
    		parsed=Integer.parseInt(number);
		} catch (Exception e) {
			LOGGER.error("非法格式，不能转换成int类型=>"+number);
		}
    	return parsed;
    }
    
    /**
     * 格式化Decimal,小数点2位
     * @param args
     */
    public static synchronized double formatDecimal(double number){
    	BigDecimal bigDecimal=new BigDecimal(number);
    	return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 格式化Decimal,小数点指定位
     * @param args
     */
    public static synchronized double formatDecimal(double number,int scale){
    	BigDecimal bigDecimal=new BigDecimal(number);
    	return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 格式化Decimal,四舍五入取整
     * @param args
     */
    public static synchronized int formatInt(double number){
    	BigDecimal bigDecimal=new BigDecimal(number);
    	return bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }
    
    /**
     * 取整
     * @param value
     * @return
     */
    public static double roundingValue(Double value){
		Double roundValue=Math.floor(value);
		double roundingPoint=NumberUtils.formatDecimal(roundValue,0);
		return roundingPoint;
	}

	/**
	 * double 大于校验
	 * @param big
	 * @param small
	 * @return
	 */
	public static boolean bigD(double big,double small){
		boolean bigger=false;
		BigDecimal data1 = new BigDecimal(big);
		BigDecimal data2 = new BigDecimal(small);
		if (data1.compareTo(data2)>0){
			bigger = true;
		}
		return bigger;
	}
	/**
	 * double 大于校验等于
	 * @param big
	 * @param small
	 * @return
	 */
	public static boolean bigOrEqD(double big,double small){
		boolean biggerOrEq=false;
		BigDecimal data1 = new BigDecimal(big);
		BigDecimal data2 = new BigDecimal(small);
		if (data1.compareTo(data2)>=0){
			biggerOrEq = true;
		}
		return biggerOrEq;
	}
	/**
	 * double 小于校验
	 * @param big
	 * @param small
	 * @return
	 */
	public static boolean lessD(double small,double big){
		boolean less=false;
		BigDecimal data1 = new BigDecimal(big);
		BigDecimal data2 = new BigDecimal(small);
		if (data2.compareTo(data1)<0){
			less = true;
		}
		return less;
	}
	/**
	 * double 小于等于校验d
	 * @param big
	 * @param small
	 * @return
	 */
	public static boolean lessOrEqD(double small,double big){
		boolean lessOrEqD=false;
		BigDecimal data1 = new BigDecimal(big);
		BigDecimal data2 = new BigDecimal(small);
		if (data2.compareTo(data1)<=0){
			lessOrEqD = true;
		}
		return lessOrEqD;
	}
	/**
	 * double 开区间
	 * @param big
	 * @param small
	 * @return
	 */
	public static boolean openInterval(double left,double d,double right){
		boolean leftOpenInterval=false;
		BigDecimal leftd = new BigDecimal(left);
		BigDecimal dd = new BigDecimal(d);
		BigDecimal rightd = new BigDecimal(right);
		if (dd.compareTo(leftd)>0 && dd.compareTo(rightd)<0){
			leftOpenInterval = true;
		}
		return leftOpenInterval;
	}
	/**
	 * double 闭区间
	 * @param big
	 * @param small
	 * @return
	 */
	public static boolean closeInterval(double left,double d,double right){
		boolean leftOpenInterval=false;
		BigDecimal leftd = new BigDecimal(left);
		BigDecimal dd = new BigDecimal(d);
		BigDecimal rightd = new BigDecimal(right);
		if (dd.compareTo(leftd)>=0 && dd.compareTo(rightd)<=0){
			leftOpenInterval = true;
		}
		return leftOpenInterval;
	}
	/**
	 * double 左开的包含
	 * @param big
	 * @param small
	 * @return
	 */
	public static boolean leftOpenInterval(double left,double d,double right){
		boolean leftOpenInterval=false;
		BigDecimal leftd = new BigDecimal(left);
		BigDecimal dd = new BigDecimal(d);
		BigDecimal rightd = new BigDecimal(right);
		if (dd.compareTo(leftd)>0 && dd.compareTo(rightd)<=0){
			leftOpenInterval = true;
		}
		return leftOpenInterval;
	}
	/**
	 * double 右开的包含
	 * @param big
	 * @param small
	 * @return
	 */
	public static boolean rightOpenInterval(double left,double d,double right){
		boolean rightOpenBTD=false;
		BigDecimal leftd = new BigDecimal(left);
		BigDecimal dd = new BigDecimal(d);
		BigDecimal rightd = new BigDecimal(right);
		if (dd.compareTo(leftd)>=0 && dd.compareTo(rightd)<0){
			rightOpenBTD = true;
		}
		return rightOpenBTD;
	}
    public static void main(String[] args) {
		System.out.println(NumberUtils.formatDouble(12.0, "#.00"));
		double a=1.0;
		double b=3.0;
		double c=1/3;
		System.out.println(c);
		System.out.println(NumberUtils.formatDecimal(((double)1/3)*2));
		System.out.println(NumberUtils.formatDecimal(((double)2/3)*2));
		System.out.println("123.0 is number?"+NumberUtils.isNumeric("123.0"));
		System.out.println("123.000000 is number?"+NumberUtils.isNumeric("123.000000"));
		System.out.println("123 is number?"+NumberUtils.isNumeric("123"));
		System.out.println("1 is number?"+NumberUtils.isNumeric("1"));
		System.out.println("-1 is number?"+NumberUtils.isNumeric("-1"));
		System.out.println("space is number?"+NumberUtils.isNumeric(""));
		System.out.println("3.1 convert to int is=>"+NumberUtils.formatInt(3.134));
		System.out.println("3.5 convert to int is=>"+NumberUtils.formatInt(3.567));
	}
}
