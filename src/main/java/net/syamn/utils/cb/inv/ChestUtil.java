/**
 * SakuraLib - Package: net.syamn.utils.cb.inv
 * Created: 2013/02/05 6:10:58
 */
package net.syamn.utils.cb.inv;

import java.lang.reflect.Field;

import net.minecraft.server.v1_6_R1.Block;
import net.minecraft.server.v1_6_R1.EntityPlayer;
import net.minecraft.server.v1_6_R1.IInventory;
import net.minecraft.server.v1_6_R1.InventoryLargeChest;
import net.minecraft.server.v1_6_R1.Packet100OpenWindow;
import net.minecraft.server.v1_6_R1.TileEntityChest;
import net.minecraft.server.v1_6_R1.World;
import net.syamn.utils.Util;

import org.bukkit.craftbukkit.v1_6_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * ChestUtil (ChestUtil.java)
 * @author syam(syamn)
 */
public class ChestUtil {
    /**
     * 開くことができないチェストかどうか返す
     * @param bukkitPlayer
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static boolean isBlockedChest(Player bukkitPlayer, int x, int y, int z){
        EntityPlayer player = ((CraftPlayer) bukkitPlayer).getHandle(); // @reference net.minecraft.server.BlockChest
        World world = player.world;
        
        // check top block
        if (world.t(x, y + 1, z))
            return true;
        
        // check around top blocks for large chest
        if ((world.getTypeId(x - 1, y, z) == Block.CHEST.id) && (world.t(x - 1, y + 1, z)))
            return true;
        if ((world.getTypeId(x + 1, y, z) == Block.CHEST.id) && (world.t(x + 1, y + 1, z)))
            return true;
        if ((world.getTypeId(x, y, z - 1) == Block.CHEST.id) && (world.t(x, y + 1, z - 1)))
            return true;
        if ((world.getTypeId(x, y, z + 1) == Block.CHEST.id) && (world.t(x, y + 1, z + 1)))
            return true;
        
        return false;   
    }
    
    /**
     * チェストインベントリを開く
     * @param p
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static boolean openChestInventory(Player bukkitPlayer, int x, int y, int z, boolean doAnimate) {
        EntityPlayer player = ((CraftPlayer) bukkitPlayer).getHandle();
        World world = player.world;
        Object chest = (TileEntityChest) world.getTileEntity(x, y, z);
        if (chest == null)
            return false;
        
        final String label = "ラージチェスト";
        if (world.getTypeId(x - 1, y, z) == Block.CHEST.id)
            chest = new InventoryLargeChest(label, (TileEntityChest) world.getTileEntity(x - 1, y, z), (IInventory) chest);
        if (world.getTypeId(x + 1, y, z) == Block.CHEST.id)
            chest = new InventoryLargeChest(label, (IInventory) chest, (TileEntityChest) world.getTileEntity(x + 1, y, z));
        if (world.getTypeId(x, y, z - 1) == Block.CHEST.id)
            chest = new InventoryLargeChest(label, (TileEntityChest) world.getTileEntity(x, y, z - 1), (IInventory) chest);
        if (world.getTypeId(x, y, z + 1) == Block.CHEST.id)
            chest = new InventoryLargeChest(label, (IInventory) chest, (TileEntityChest) world.getTileEntity(x, y, z + 1));
        
        if (doAnimate) {
            player.openContainer((IInventory) chest);
        }
        else {
            try {
                int id = 0;
                try {
                    Field windowID = player.getClass().getDeclaredField("containerCounter");
                    windowID.setAccessible(true);
                    id = windowID.getInt(player);
                    id = id % 100 + 1;
                    windowID.setInt(player, id);
                }
                catch (NoSuchFieldException ignore) {}
                
                // TODO temp fix, may broken on 1.5
                player.playerConnection.sendPacket(new Packet100OpenWindow(id, 0, ((IInventory) chest).getName(), ((IInventory) chest).getSize(), true));
                //player.playerConnection.sendPacket(new Packet100OpenWindow(id, 0, ((IInventory) chest).getName(), ((IInventory) chest).getSize()));
                player.activeContainer = new CBContainerChest(player.inventory, ((IInventory) chest));
                player.activeContainer.windowId = id;
                player.activeContainer.addSlotListener(player);
                return false;
            } catch (Exception ex) {
                ex.printStackTrace();
                Util.message(bukkitPlayer, "&cError while sending chest inventory without animation");
            }
        }
        
        return true;
    }
}
