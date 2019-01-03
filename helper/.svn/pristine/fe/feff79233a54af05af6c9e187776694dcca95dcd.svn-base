package org.blue.helper.StringHelper.utils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class DateUtil {

    /**
     * 字符串转java.sql.Date
     * @param date
     * @param format
     * @return
     */
    public static java.sql.Date formatToDate(String date,String format){
        java.sql.Date  sqlDate=null;
        try {
            if(date!=null && date.length()>0){
                java.util.Date  _date  =  new SimpleDateFormat(format).parse(date);
                sqlDate  =  new java.sql.Date(_date.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }

    /**
     * TimeStamp转字符串(ymd)
     * @param date
     * @param format
     * @return
     */
    public static String formatTimeStampToString(Timestamp date,String format){
        if(date==null) return "";
        SimpleDateFormat df = new SimpleDateFormat(format);
        String str = df.format(date);
        return str;
    }

    /**
     * 获取当前timestamp(仅精确到日期)
     * @param date
     * @return
     */
    public static Timestamp getCurrentTimeStampYMD(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String time = df.format(new Date());
        Timestamp ts = Timestamp.valueOf(time);
        return ts;
    }

    /**
     * 获取当前date(仅精确到日期)
     * @param date
     * @return
     */
    public static Date getCurrentDateYMD(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());
        return DateUtil.formatToDate(date, "yyyy-MM-dd");
    }


    public static Date timestampToDate(Timestamp tt){
        return new Date(tt.getTime());
    }

    /**
     * 判断日期是否有效
     * @param startDate,endDate,date
     */
    public static boolean isDateValidInRange(String startDate, String endDate, String date){
        Date startTime=DateUtil.formatToDate(startDate, "yyyy-MM-dd");
        Date endTime=DateUtil.formatToDate(endDate, "yyyy-MM-dd");
        Date currentDate=DateUtil.formatToDate(date, "yyyy-MM-dd");
        if (currentDate.compareTo(startTime)>=0 && currentDate.compareTo(endTime)<=0) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * 当前日期是否在开始日期结束日期范围之内
     * @param startDate,endDate
     */
    public static boolean isDateValid(String startDate, String endDate){
        Date startTime=DateUtil.formatToDate(startDate, "yyyy-MM-dd");
        Date endTime=DateUtil.formatToDate(endDate, "yyyy-MM-dd");
        Date currentDate=DateUtil.getCurrentDateYMD();
        if (currentDate.compareTo(startTime)>=0 && currentDate.compareTo(endTime)<=0) {
            return true;
        }else{
            return false;
        }
    }
    /**
     * 字符串转java.sql.Timestamp
     * @param date
     * @param format
     * @return
     */
    public static java.sql.Timestamp formatToTimestamp(String date,String format){
        java.sql.Timestamp timestamp=null;
        try {
            if(date!=null && date.length()>0){
                java.util.Date  _date  =  new SimpleDateFormat(format).parse(date);
                timestamp  =  new java.sql.Timestamp(_date.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    public static String formatToStr(java.sql.Date date,String format){
        String dateStr=null;
        if(date!=null){
            dateStr=new SimpleDateFormat(format).format(date);
        }
        return dateStr;
    }

    public static String formatToStr(Date date,String format){
        String dateStr=null;
        if(date!=null){
            dateStr=new SimpleDateFormat(format).format(date);
        }
        return dateStr;
    }

    public static String formatToStr(java.sql.Timestamp date,String format){
        String dateStr=null;
        if(date!=null){
            dateStr=new SimpleDateFormat(format).format(date);
        }
        return dateStr;
    }

    //日期格式字符串格式化
    public static String formatDate(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        if(StringUtils.isNotEmpty(time) && !"null".equalsIgnoreCase(time)){
            try {
                date = sdf.parse(time);
                time=sdf.format(date);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return time;
    }

    //日期格式字符串格式化
    public static String formatDateTime(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        if(StringUtils.isNotEmpty(time) && !"null".equalsIgnoreCase(time)){
            try {
                date = sdf.parse(time);
                time=sdf.format(date);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return time;
    }
    //计算日期相隔天数
    public static long dateDiff(String startTime, String endTime, String format) throws ParseException {
        //按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000*24*60*60;//一天的毫秒数
        long nh = 1000*60*60;//一小时的毫秒数
        long nm = 1000*60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数long diff;try {
        //获得两个时间的毫秒时间差异
        long diff =   sd.parse(startTime).getTime() -sd.parse(endTime).getTime();
        long day = diff/nd;//计算差多少天
        long hour = diff%nd/nh;//计算差多少小时
        long min = diff%nd%nh/nm;//计算差多少分钟
        long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果
        System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟"+sec+"秒。");
        return day;
    }

    //计算日期相隔毫秒
    public static long dateDiff(Date startTime, Date endTime) {
        //按照传入的格式生成一个simpledateformate对象
        long nd = 1000*24*60*60;//一天的毫秒数
        long nh = 1000*60*60;//一小时的毫秒数
        long nm = 1000*60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数long diff;try {
        //获得两个时间的毫秒时间差异
        if (endTime!=null && startTime !=null) {
            long diff =   endTime.getTime() -startTime.getTime();
            return diff;
        }else {
            return -1;
        }
    }

    //日期转化为字符串
    public static String getStrFromDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date);
        return str;
    }

    /**
     * 转换日期字符串为日期对象 yyyy-MM-dd
     * @param dateStr
     * @return
     */
    public static Date getDateFromStr(String dateStr){
        return DateUtil.formatToDate(dateStr, "yyyy-MM-dd");
    }

    /**
     * 获得指定日期(天)的前后日期 t为正表示后 负表示前
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static Date getSpecifiedDay(Date date, int d) {
        if(date == null) return null;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, d);
        return c.getTime();
    }

    /**
     * 获得指定日期(小时)的前后日期 t为正表示后 负表示前
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static Date getSpecifiedDayByHour(Date date, int h) {
        if(date == null) return null;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, h);
        return c.getTime();
    }
    /**
     * 获得指定日期(分钟)的前后日期 t为正表示后 负表示前
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static Date getSpecifiedDayByMinute(Date date, int m) {
        if(date == null) return null;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, m);
        return c.getTime();
    }
    /**
     * 获得指定日期(分钟)的前后日期 t为正表示后 负表示前
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static Date getSpecifiedDayBySecond(Date date, int s) {
        if(date == null) return null;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, s);
        return c.getTime();
    }
    /**
     * 返回中间没有分隔符的时间戳:yyyyMMddHHmmssSS
     * @return
     */
    public static String getPureCurrentTs() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = df.format(new Timestamp(System.currentTimeMillis()));
        return time;
    }

    /**
     * 获取当前timestamp(仅精确到日期)
     *
     * @param date
     * @return
     */
    public static Timestamp getCurrentTimeStampMillis() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        String time = df.format(new Date());
        Timestamp ts = Timestamp.valueOf(time);
        return ts;
    }

    /**
     * 切面日志，获取当前timestamp(仅精确到日期)
     * 原因：hh的时间格式，在12:59左右日志的时间会出现负数
     * @param date
     * @return
     */
    public static Timestamp getCurrentTimeStampMillisForAop() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String time = df.format(new Date());
        Timestamp ts = Timestamp.valueOf(time);
        return ts;
    }

    public static void main(String[] args) throws ParseException, InterruptedException  {
//        DateUtil t=new DateUtil();
//        System.out.println("t.getCurrentDateYMD()=>"+t.getCurrentTimeStampYMD().toString());
//        System.out.println("t.getCurrentDateYMD()=>"+t.getCurrentDateYMD().toString());
//        System.out.println(t.getDateFromStr("2015-11-06").getTime());
//        System.out.println(t.getDateFromStr("2015-11-07").getTime());
//		/*t.dateDiff("2014-12-15","2014-12-11","yyyy-MM-dd");
//		Date startTime=t.formatToDate("2015-6-19", "yyyy-MM-dd");
//		Date endTime=t.formatToDate("2015-6-19", "yyyy-MM-dd");
//		Date currentDate=t.getCurrentDateYMD();
//		System.out.println("currentDate=>"+currentDate.toString());
//		System.out.println("startTime=>"+startTime.toString());
//		System.out.println("endTime=>"+endTime.toString());
//		if (currentDate.compareTo(startTime)>=0 && currentDate.compareTo(endTime)<=0) {
//			System.out.println("时间范围有效");
//		}else{
//			System.out.println("时间范围无效");
//		}
//		System.out.println("date"+DateUtil.formatToDate("2014-09-23", "yyyy-MM-dd"));
//
//		startTime=new Date();
//		for(int i=0;i<100;i++){
//			Thread.currentThread().sleep((long) (Math.random()*100));
//			System.out.println(t.getPureCurrentTs());
//		}
//		endTime=new Date();
//		System.out.println(DateUtil.dateDiff(startTime, endTime)/1000);
//		PointsCalculator calculator=new PointsCalculator();
//		System.out.println(calculator.isBirthDayMatched("2", "4", "8", "2015"));
//		SimpleDateFormat df = new SimpleDateFormat("YYYYMMDDHHMISS");
//		String time = df.format(new Timestamp(System.currentTimeMillis()));
////		System.out.println(time);*/
//        Date d1=getDateFromStr("2018-09-18");
//        Date d2=getDateFromStr("2018-09-17");
//        Long l=dateDiff(d2,d1);
//        System.out.printf(l.toString());
        Date dt=getDateFromStr("2018-11-31");
        System.out.println(dt);
        String year=String.format("%tY", dt);

        String mon=String .format("%tm", dt);

        String day=String .format("%td", dt);

        System.out.println(year);

        System.out.println(mon);

        System.out.println(day);
    }

    public static String getYear(Date dt){
        return String.format("%tY", dt);
    }
    public static String getMonth(Date dt){
        return String.format("%tm", dt);
    }
    public static String getDay(Date dt){
        return String.format("%td", dt);
    }

    public static String getYear(){
        Date dt=new Date();
        return String.format("%tY", dt);
    }
    public static String getMonth(){
        Date dt=new Date();
        return String.format("%tm", dt);
    }
    public static String getDay(){
        Date dt=new Date();
        return String.format("%td", dt);
    }
    /**
     * 当前季度的开始时间，即2012-01-1 00:00:00
     *
     * @return
     */
    public static Date getCurrentQuarterStartTime() {
        SimpleDateFormat shortSdf = shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     *
     * @return
     */
    public static Date getCurrentQuarterEndTime() {
        SimpleDateFormat shortSdf = shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }
    /**
     * 获得某个月最大天数
     *
     * @param year 年份
     * @param month 月份 (1-12)
     * @return 某个月最大天数
     */
    public static int getMaxDayByYearMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     *
     * @return
     */
    public static boolean afterNow(Date time){
        Date now=new Date();
        Long diff=dateDiff(now,time);
        if(diff>=0){
            return true;
        }
        return false;
    }
}
