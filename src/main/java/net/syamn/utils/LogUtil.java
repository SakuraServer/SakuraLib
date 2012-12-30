/**
 * SakuraUtils - Package: net.syamn.utils
 * Created: 2012/12/26 4:46:46
 */
package net.syamn.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;

/**
 * LogUtil (LogUtil.java)
 * @author syam(syamn)
 */
public class LogUtil {
    private static Logger logger;
    private static Level oldLevel;
    
    public static void init(final Plugin plugin){
        logger = plugin.getLogger();
        oldLevel = getLogLevel();
    }
    
    public static Level getLogLevel(){
        return logger.getLevel();
    }
    public static void setLogLevel(final Level level){
        oldLevel = getLogLevel();
        logger.setLevel(level);
    }
    public static void backLogLevel(){
        setLogLevel(oldLevel);
    }
    
    public static void log(final Level level, final String message){
        logger.log(level, message);
    }
    
    public static void fine(final String message){
        logger.fine(message);
    }
    
    public static void finer(final String message){
        logger.finer(message);
    }
    
    public static void finest(final String message){
        logger.finest(message);
    }
    
    public static void info(final String message){
        logger.info(message);
    }
    
    public static void warning(final String message){
        logger.warning(message);
    }
    
    public static void severe(final String message){
        logger.severe(message);
    }
}
