/**
 * SakuraUtils - Package: net.syamn.utils Created: 2012/12/29 6:55:47
 */
package net.syamn.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * ItemUtil (ItemUtil.java)
 * 
 * @author syam(syamn)
 */
public class ItemUtil {
    private final static List<Integer> possibleRepairs;
    static {
        possibleRepairs = new LinkedList<Integer>();
        for (int i = 256; i <= 259; i++) { // 3 Iron Tools, FlintSteel
            possibleRepairs.add(i);
        }
        for (int i = 267; i <= 279; i++) { // Iron Sword, Wood Tools, Stone
                                           // Tools, Diamond Tools
            possibleRepairs.add(i);
        }
        for (int i = 283; i <= 286; i++) { // Gold Tools
            possibleRepairs.add(i);
        }
        for (int i = 290; i <= 294; i++) { // Hoe
            possibleRepairs.add(i);
        }
        for (int i = 298; i <= 317; i++) { // Armors
            possibleRepairs.add(i);
        }
        possibleRepairs.add(Material.SHEARS.getId());
        possibleRepairs.add(Material.BOW.getId());
        possibleRepairs.add(Material.FISHING_ROD.getId());
        possibleRepairs.add(Material.CARROT_STICK.getId());
    }

    /**
     * アイテムIDが修理可能なものか返す
     * 
     * @param id
     * @return
     */
    public static boolean repairable(final int id) {
        return possibleRepairs.contains(id);
    }

    /**
     * 指定した個数分アイテムを減らして返す
     * 
     * @param itemStack
     *            対象のItemStackオブジェクト
     * @param amount
     *            減らす個数が 0 以下なら何もせずに返す
     * @return 減らした後のItemStackオブジェクト もし0なら new ItemStack(Material.AIR) を返す
     */
    public static ItemStack decrementItem(ItemStack itemStack, int amount) {
        if (amount <= 0) { return itemStack; }

        int nowAmount = itemStack.getAmount();
        if ((nowAmount - amount) <= 0) { return (new ItemStack(Material.AIR)); }

        itemStack.setAmount(nowAmount - amount);
        return itemStack;
    }

    /**
     * ブロックIDから読みやすい名前を返す
     * @param blockId
     * @return
     */
    public static String getReadableName(int blockId) {
        String materialName = Material.getMaterial(blockId).toString().toLowerCase(Locale.ENGLISH);
        int indexOfDash = materialName.indexOf('_');
        String readableName = "";
        int index = 0;
        while (indexOfDash != -1) {
            readableName = readableName.concat(materialName.substring(index, index + 1).toUpperCase() + materialName.substring(index + 1, indexOfDash) + " ");
            index = indexOfDash + 1;
            indexOfDash = materialName.indexOf('_', indexOfDash + 1);
        }
        readableName = readableName.concat(materialName.substring(index, index + 1).toUpperCase() + materialName.substring(index + 1, materialName.length()));
        return readableName;
    }
}
