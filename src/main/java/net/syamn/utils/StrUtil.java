/**
 * SakuraUtils - Package: net.syamn.utils Created: 2012/12/22 18:19:58
 */
package net.syamn.utils;

import java.util.Collection;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StrUtil (StrUtil.java)
 * 
 * @author syam(syamn)
 */
public class StrUtil {
    /**
     * コレクションをデリミタで結合して返す
     * @param s 結合するコレクション
     * @param delimiter デリミタ文字
     * @return 結合後の文字列
     */
    public static String join(Collection<?> s, String delimiter, int firstIndex) {
        if (s.size() == 0) return "";
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Object obj : s){
            if (i >= firstIndex){
                if (i > 0){
                    builder.append(delimiter);
                }
                builder.append(obj.toString());
            }
            ++i;
        }
        
        return builder.toString();
    }
    
    /**
     * 文字の配列をデリミタで結合して返す
     * @param s
     * @param delimiter
     * @param firstIndex
     * @return
     */
    public static String join(String[] s, String delimiter, int firstIndex){
        if (s.length == 0) return "";
        StringBuilder builder = new StringBuilder(s[firstIndex]);
        for (int i = firstIndex + 1; i < s.length; ++i){
            builder.append(delimiter).append(s[i]);
        }
        return builder.toString();
    }
    
    /**
     * オブジェクトの配列をデリミタで結合して返す
     * @param obj
     * @param delimiter
     * @param firstIndex
     * @return
     */
    public static String join(Object[] obj, String delimiter, int firstIndex){
        if (obj.length == 0) return "";
        StringBuilder builder = new StringBuilder(obj[firstIndex].toString());
        for (int i = firstIndex + 1; i < obj.length; ++i){
            builder.append(delimiter).append(obj[i].toString());
        }
        return builder.toString();
    }
    
    /**
     * コレクションをデリミタで結合して返す
     * @param s
     * @param delimiter
     * @return
     */
    public static String join(Collection<?> s, String delimiter){
        return join(s, delimiter, 0);
    }
    
    /**
     * 文字の配列をデリミタで結合して返す
     * @param s
     * @param delimiter
     * @return
     */
    public static String join(String[] s, String delimiter){
        return join(s, delimiter, 0);
    }

    /**
     * 列挙配列要素の中から一致する要素を返す
     * @param type 走査対象の列挙体配列
     * @param string 文字列
     * @return <T> T or null
     */
    public static <T extends Enum<T>> T isMatches(T[] type, String string){
        if (type == null || string == null)
            return null;
        
        string = string.toUpperCase(Locale.ENGLISH);
        for (T check : type){
            if (string.equals(check.name().toUpperCase(Locale.ENGLISH))){
                return check;
            }
        }
        
        return null;
    }

    /**
     * 文字列が配列の中に入っているか返す
     * @param target
     * @param check
     * @return
     */
    public static boolean isIn(String[] target, String check){
        check = check.trim();
        for (String c : target){
            if (check.equalsIgnoreCase(c.trim())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * 文字列が配列の中に入っているか返す
     * @param target
     * @param check
     * @return
     */
    public static boolean isInExact(String[] target, String check){
        for (String c : target){
            if (check.equals(c)){
                return true;
            }
        }
        return false;
    }

    /**
     * 文字列に含まれているキャラクタ数を返す
     * @param s
     * @param find
     * @return
     */
    public static int count(String s, char find){
        int c = 0;
        for (int i = 0; i < s.length(); ++i){
            if (s.charAt(i) == find){
                ++c;
            }
        }
        return c;
    }
    
    /**
     * キャラクタを指定した文字数まで重ねた文字列を返す
     * @param ch
     * @param len
     * @return
     */
    public static String repeat(char ch, int len){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; ++i){
            builder.append(ch);
        }
        return builder.toString();
    }

    /**
     * 文字列を指定した回数まで繰り返した文字列を返す
     * @param str
     * @param count
     * @return
     */
    public static String repeat(String str, int count){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++){
            builder.append(str);
        }
        return builder.toString();
    }

    /**
     * 文字列がIntegerにパースできるか返す
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    /**
     * 文字列がDoubleにパースできるか返す
     * @param str
     * @return
     */
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    /**
     * 文字列がFloatにパースできるか返す
     * @param str
     * @return
     */
    public static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    /**
     * 文字列がByteにパースできるか返す
     * @param str
     * @return
     */
    public static boolean isByte(String str){
        try{
            Byte.parseByte(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    /**
     * 文字列がLongにパースできるか返す
     * @param str
     * @return
     */
    public static boolean isLong(String str){
        try{
            Long.parseLong(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    /**
     * 文字列がShortにパースできるか返す
     * @param str
     * @return
     */
    public static boolean isShort(String str){
        try{
            Short.parseShort(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    /**
     * 文字列が有効なIPアドレスかどうか返す
     * @param str
     * @return
     */
    public static boolean isValidIP(String str){
        if (str == null) return false;
        Matcher matcher = Pattern.compile(IP_PATTERN).matcher(str);
        return matcher.matches();
    }
    private static final String IP_PATTERN = 
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
}
