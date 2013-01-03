/**
 * SakuraUtils - Package: net.syamn.utils
 * Created: 2012/12/23 16:05:41
 */
package net.syamn.utils;

import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Util (Util.java)
 * @author syam(syamn)
 */
public class Util {
    /**
     * プレイヤーにメッセージを送信する
     * @param sender
     * @param message
     */
    public static void message(CommandSender sender, String msg) {
        if (sender != null && msg != null) {
            sender.sendMessage(coloring(msg));
        }
    }
    
    /**
     * プレイヤーにメッセージを送信する
     * @param sender
     * @param message
     */
    public static void message(String name, String msg){
        message(Bukkit.getPlayerExact(name), msg);
    }
    
    /**
     * メッセージを全員に送信する
     * @param msg
     * @param sendConsole
     */
    public static void broadcastMessage(String msg, boolean sendConsole){
        msg = coloring(msg);
        if (sendConsole){
            Bukkit.broadcastMessage(msg);
        }else{
            for (final Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(msg);
            }
        }
    }
    
    /**
     * メッセージを全員に送信する
     * @param msg
     */
    public static void broadcastMessage(String msg){
        broadcastMessage(msg, false);
    }
    
    /**
     * カラーリングせずにメッセージを送信する
     * @param sender
     * @param msg
     */
    public static void rawMessage(CommandSender sender, String msg){
        if (sender != null && msg != null){
            sender.sendMessage(msg);
        }
    }
    
    /**
     * カラーリングせずにメッセージを全員に送信する
     * @param msg
     * @param sendConsole
     */
    public static void broadcastRawMessage(String msg, boolean sendConsole){
        if (sendConsole){
            Bukkit.broadcastMessage(msg);
        }else{
            for (final Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(msg);
            }
        }
    }
    
    /**
     * カラーリングせずにメッセージを全員に送信する
     * @param msg
     */
    public static void broadcastRawMessage(String msg){
        broadcastRawMessage(msg, false);
    }
    
    /**
     * メッセージをカラーリングする
     * @param str
     * @return
     */
    public static String coloring(String str){
        return str.replaceAll("&([0-9a-fk-or])", "\u00A7$1");
    }
    
    /**
     * メッセージのカラーリングを解除する
     * @param str
     * @return
     */
    public static String unColoring(String str){
        return str.replaceAll("\u00A7([0-9a-fk-or])", "&$1");
    }
    
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
