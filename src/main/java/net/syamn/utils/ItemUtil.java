/**
 * SakuraUtils - Package: net.syamn.utils
 * Created: 2012/12/29 6:55:47
 */
package net.syamn.utils;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Material;

/**
 * ItemUtil (ItemUtil.java)
 * @author syam(syamn)
 */
public class ItemUtil {
    private final static List<Integer> possibleRepairs;
    static{
        possibleRepairs = new LinkedList<Integer>();
        for (int i = 256; i <= 259; i++){ // 3 Iron Tools, FlintSteel
            possibleRepairs.add(i);
        }
        for (int i = 267; i <= 279; i++){ // Iron Sword, Wood Tools, Stone Tools, Diamond Tools
            possibleRepairs.add(i);
        }
        for (int i = 283; i <= 286; i++){ // Gold Tools
            possibleRepairs.add(i);
        }
        for (int i = 290; i <= 294; i++){ // Hoe
            possibleRepairs.add(i);
        }
        for (int i = 298; i <= 317; i++){ // Armors
            possibleRepairs.add(i);
        }
        possibleRepairs.add(Material.SHEARS.getId());
        possibleRepairs.add(Material.BOW.getId());
        possibleRepairs.add(Material.FISHING_ROD.getId());
        possibleRepairs.add(Material.CARROT_STICK.getId());
    }
    
    /**
     * アイテムIDが修理可能なものか返す
     * @param id
     * @return
     */
    public static boolean repairable(final int id){
        return possibleRepairs.contains(id);
    }
}
