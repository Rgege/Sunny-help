//package org.blue.helper.StringHelper.aop.interceptor;
//
//import org.apache.commons.lang3.StringUtils;
//import org.blue.helper.StringHelper.utils.BeanConverUtil;
//import org.blue.helper.core.util.RedisUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ChartRoomInterceptor implements HandlerInterceptor {
//
//    private static final Logger log = LoggerFactory.getLogger(ChartRoomInterceptor.class);
//    private static final String onlineUsers="ONLINE_USER_LIST_KEY";
//    @Autowired
//    private RedisUtil redisUtil;
//    /**
//     * 进入controller层之前拦截请求
//     * @param request
//     * @param response
//     * @param object
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
//        boolean flag=false;
//        HttpSession session = request.getSession();
//        if(request.getRequestURL().indexOf("/endpointChat") >-1){
//            List<String> users=redisUtil.getList(onlineUsers);
//            if(users!=null && !users.isEmpty()){
//                return redisUtil.pushList(onlineUsers, this.getParam("userId",request));
//            }else {
//                List<String> userList=new ArrayList<>();
//                userList.add(this.getParam("userId",request));
//                return redisUtil.newList(onlineUsers,userList);
//            }
//        }else {
//            flag=true;
//        }
//        return flag;
//
//    }
//
//    /**
//     * 解析请求参数
//     *
//     * @param key
//     * @return
//     */
//    private String getParam(String key,HttpServletRequest request) {
//        String paramV = "";
//        if (key == null || key == "") return paramV;
//        if (request != null) {
//            String qStr = request.getQueryString();
//            String[] params = qStr.split("&");
//            for (String param : params) {
//                int split = param.indexOf("=");
//                if (key.equals(param.substring(0, split))) {
//                    paramV = param.substring(split + 1, param.length());
//                    break;
//                }
//            }
//        }
//        return paramV.equals("undefined") ? "" : paramV;
//    }
//
////    @Override
////    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
////        log.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
////    }
////
////    @Override
////    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
////        log.info("---------------视图渲染之后的操作-------------------------0");
////    }
//
//
//}
