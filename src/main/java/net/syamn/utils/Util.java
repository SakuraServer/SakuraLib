/**
 * SakuraUtils - Package: net.syamn.utils
 * Created: 2012/12/23 16:05:41
 */
package net.syamn.utils;

import java.text.NumberFormat;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.World;
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
        broadcastMessage(msg, true);
    }
    
    /**
     * メッセージをワールド内のプレイヤーに送信する
     * @param world
     * @param msg
     * @param sendConsole
     */
    public static void worldcastMessage(World world, String msg, boolean sendConsole){
        if (world == null || msg == null){
            return;
        }
        msg = coloring(msg);
        if (sendConsole){
            message(Bukkit.getConsoleSender(), "[Worldcast][" + world.getName() + "]:" + msg);
        }else{
            for (final Player player : world.getPlayers()){
                player.sendMessage(msg);
            }
        }
    }
    
    /**
     * メッセージをワールド内のプレイヤーに送信する
     * @param world
     * @param msg
     */
    public static void worldcastMessage(World world, String msg){
        worldcastMessage(world, msg, false);
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
     * メッセージをワールド内のプレイヤーに送信する
     * @param world
     * @param msg
     * @param sendConsole
     */
    public static void worldcastRawMessage(World world, String msg, boolean sendConsole){
        if (world == null || msg == null){
            return;
        }
        if (sendConsole){
            message(Bukkit.getConsoleSender(), "[Worldcast][" + world.getName() + "]:" + msg);
        }else{
            for (final Player player : world.getPlayers()){
                player.sendMessage(msg);
            }
        }
    }
    
    /**
     * メッセージをワールド内のプレイヤーに送信する
     * @param world
     * @param msg
     */
    public static void worldcastRawMessage(World world, String msg){
        worldcastRawMessage(world, msg, false);
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
     * パーセンテージを求める
     * @param num
     * @param total 全体値
     * @param precision 小数点以下の桁数 負数は丸めない
     * @return double パーセンテージ
     */
    public static double getPercent(int num, int total, int precision){
            double perc = ((double) num / total) * 100;

            // 丸める
            if (precision >= 0){
                NumberFormat format = NumberFormat.getInstance();
                format.setMaximumFractionDigits(precision);
                perc = Double.valueOf(format.format(perc));
            }
            
            return perc;
    }

    /**
     * コンソールからコマンドを実行する
     * @param command
     */
    public static void executeCommandOnConsole(String command){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
    }
}
