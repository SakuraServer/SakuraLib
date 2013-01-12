/**
 * SakuraLib - Package: net.syamn.utils
 * Created: 2013/01/12 16:39:37
 */
package net.syamn.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

/**
 * ParseUtil (ParseUtil.java)
 * @author syam(syamn)
 */
public class ParseUtil {
    /**
     * ブロック座標を文字列に変換する
     * @param block
     * @return
     */
    public static String blockToString(Block block){
        return block.getWorld().getName() + "," + block.getX() + "," + block.getY() + "," + block.getZ();
    }
    
    /**
     * ブロック座標文字列からブロックに変換する
     * @param str
     * @return
     */
    public static Block stringToBlock(String str){
        try{
            String s[] = str.split(",");
            // check elements
            if (s.length < 4){
                return null;
            }
            
            // first, parse xyz locations from last 3 element
            int x = Integer.parseInt(s[s.length - 3]);
            int y = Integer.parseInt(s[s.length - 2]);
            int z = Integer.parseInt(s[s.length - 1]);
            
            // next, parse world name
            StringBuilder wname = new StringBuilder(12);
            for (int i = 0; i < s.length - 3; i++){
                if (i != 0){
                    wname.append(",");
                }
                wname.append(s[i]);
            }
            
            // check world is exists
            World world = Bukkit.getServer().getWorld(wname.toString());
            if (world == null){
                return null;
            }
            return world.getBlockAt(x, y, z);
        }catch (Exception ex){
            return null;
        }
    }

    /**
     * 座標から文字列に変換する
     * @param loc
     * @return
     */
    public static String locationToString(Location loc){
        return loc.getWorld().getName() + "," + loc.getX() + "," + loc.getY() + "," + loc.getZ() + "," + loc.getYaw() + "," + loc.getPitch();
    }
    /**
     * 文字列から座標に変換する
     * @param str
     * @return
     */
    public static Location stringToLocation(String str){
        try{
            String s[] = str.split(",");
            // check element
            if (s.length < 6){
                return null;
            }
            
            // x, y, z, yaw, pitch
            double x = Double.parseDouble(s[s.length - 5]);
            double y = Double.parseDouble(s[s.length - 4]);
            double z = Double.parseDouble(s[s.length - 3]);
            float yaw = Float.parseFloat(s[s.length - 2]);
            float pitch = Float.parseFloat(s[s.length - 1]);
            
            // world
            StringBuilder wname = new StringBuilder(12);
            for (int i = 0; i < s.length - 5; i++){
                if (i != 0){
                    wname.append(",");
                }
                wname.append(s[i]);
            }
            
            // check world is exists
            World world = Bukkit.getServer().getWorld(wname.toString());
            if (world == null){
                return null;
            }
            
            return new Location(world, x, y, z, yaw, pitch);
        }catch (Exception ex){
            return null;
        }
    }
}
