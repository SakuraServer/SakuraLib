/**
 * SakuraLib - Package: net.syamn.utils
 * Created: 2013/01/08 17:17:54
 */
package net.syamn.utils;

import java.text.DateFormat;
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
     * 秒を読みやすい時間表記にして返す
     * @param sec 正の秒数
     * @return 変換後の文字列
     */
    public static String getReadableTimeBySecond(int sec) {
        if (sec < 0) return "0秒";
        if (sec < 60) return sec + "秒";
        if (sec % 60 == 0) return sec / 60 + "分";

        int m = sec / 60;
        int s = sec % 60;
        return m + "分" + s + "秒";
    }
    
    /**
     * 分を読みやすい時間表記にして返す
     * @param min 正の分数
     * @return 変換後の文字列
     */
    public static String getReadableTimeByMinute(int min){
        if (min < 0) return "0分";
        if (min < 60) return min + "分";
        if (min % 60 == 0) return min / 60 + "時";
    
        int h = min / 60;
        int m = min % 60;
        return h + "時間" + m + "分";
    }
}
