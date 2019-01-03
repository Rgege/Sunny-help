package org.blue.helper.test.file;

import javax.servlet.http.HttpServletRequest;

public class GlobInfo {

    public static String getQueryString(HttpServletRequest request,String key){
        String s="";
        if(request==null || key==null){
            return s;
        }
        s=request.getParameter(key);
        return s;
    }
}
