package org.blue.helper.StringHelper.utils;

import org.blue.helper.StringHelper.common.constants.RegExConstant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtils {

    public static boolean match(String str,String regex){
        Pattern pattern=Pattern.compile(regex);
        System.out.println(pattern.toString());
        Matcher matcher=pattern.matcher(str);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(match("13409635026",RegExConstant.MOBILE));
    }
}
