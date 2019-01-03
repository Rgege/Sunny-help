package org.blue.helper.StringHelper.utils;

import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {
    /**
     *
     * @param str
     * @param prefix
     * @return
     */
    public static boolean isStartWith(String str, String prefix) {
        if (str.matches("(?i)" + prefix + ".*"))
            return true;
        else
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isStartWith("001253","001"));
        System.out.println(isStartWith("0001253","001"));
        System.out.println(isStartWith("002253","001"));
    }
}
