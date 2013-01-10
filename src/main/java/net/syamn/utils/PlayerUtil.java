/**
 * SakuraLib - Package: net.syamn.utils
 * Created: 2013/01/11 3:39:48
 */
package net.syamn.utils;

import java.util.regex.Pattern;

/**
 * PlayerUtil (PlayerUtil.java)
 * @author syam(syamn)
 */
public class PlayerUtil {
    /**
     * 指定した文字列がMinecraftのユーザ名として有効か返す
     * @param name
     * @return
     */
    public static boolean isValidName(final String name){
        if (name == null) return false;

        final String regex = "^[A-Za-z0-9_]{2,16}$";
        if (!Pattern.compile(regex).matcher(name).matches()){
            return false;
        }

        return true;
    }
}