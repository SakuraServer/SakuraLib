/**
 * SakuraLib - Package: net.syamn.utils
 * Created: 2013/01/08 17:17:54
 */
package net.syamn.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TimeUtil (TimeUtil.java)
 * @author syam(syamn)
 */
public class TimeUtil {
    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * Unixミリ秒からDateを返す
     * @param unixMillis
     * @return
     */
    public static Date getDateByUnixMillis(long unixMillis){
        return new Date(unixMillis);
    }
    
    /**
     * Unix秒からDateを返す
     * @param unixSeconds
     * @return
     */
    public static Date getDateByUnixSeconds(long unixSeconds){
        return getDateByUnixMillis(unixSeconds * 1000);
    }
    
    /**
     * 現在のUnixミリ秒を取得する
     * @return long unixMillis
     */
    public static Long getCurrentUnixMillis(){
        return System.currentTimeMillis();
    }
    
    /**
     * 現在のUnix秒を取得する
     * @return long unixSec
     */
    public static Long getCurrentUnixSec(){
        return System.currentTimeMillis() / 1000;
    }
    
    /**
     * DateからUnix秒を取得する
     * @param date
     * @return
     */
    public static Long getUnixMillisByDate(Date date){
        return date.getTime();
    }
    
    /**
     * DateからUnix秒を取得する
     * @param date
     * @return
     */
    public static Long getUnixSecByDate(Date date){
        return date.getTime() / 1000;
    }
    
    /**
     * 日時を任意のフォーマット形式の文字列で返す
     * @return
     */
    public static String getReadableTime(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }
    
    /**
     * 日時をデフォルト形式の文字列で返す
     * @return
     */
    public static String getReadableTime(Date date) {
        return getReadableTime(date, DATE_FORMAT);
    }
    
    /**
     * 日時文字列を指定のフォーマットからDateインスタンスに変換して返す
     * @param date
     * @param format
     * @return
     */
    public static Date parseByFormat(String date, String format){
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }
    
    /**
     * 秒を読みやすい時間表記にして返す
     * @param sec 正の秒数
     * @return 変換後の文字列
     */
    public static String getReadableTimeBySecond(int sec) {
        if (sec <= 0) return "0秒";
        
        final int s = sec % 60;
        final int m = (sec / 60) % 60;
        final int h = (sec / 3600) % 24; // 60 * 60
        final int d = (sec / 86400) % 7; // 60 * 60 * 24
        
        String format = "";
        if (s > 0){
            format = s + "秒";
        }
        if (m > 0){
            format = m + "分" + format;
        }
        if (h > 0){
            format = h + "時間" + format;
        }
        if (d > 0){
            format = d + "日" + format;
        }
        
        return format;
    }
    
    /**
     * 分を読みやすい時間表記にして返す
     * @param min 正の分数
     * @return 変換後の文字列
     */
    public static String getReadableTimeByMinute(int min){
        if (min <= 0) return "0分";
        
        final int m = min % 60;
        final int h = (min / 60) % 24;
        final int d = (min / 1440) % 7; // 60 * 24
        
        String format = "";
        if (m > 0){
            format = m + "分";
        }
        if (h > 0){
            format = h + "時間" + format;
        }
        if (d > 0){
            format = d + "日" + format;
        }
        
        return format;
    }
}
