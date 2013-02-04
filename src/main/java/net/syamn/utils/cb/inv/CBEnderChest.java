/**
 * SakuraLib - Package: net.syamn.utils.cb.inv
 * Created: 2013/02/05 2:47:11
 */
package net.syamn.utils.cb.inv;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.craftbukkit.v1_4_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_4_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_4_R1.inventory.CraftInventory;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import net.minecraft.server.v1_4_R1.EntityHuman;
import net.minecraft.server.v1_4_R1.InventoryEnderChest;
import net.minecraft.server.v1_4_R1.InventorySubcontainer;
import net.minecraft.server.v1_4_R1.ItemStack;

/**
 * CBEnderChest (CBEnderChest.java)
 */
public class CBEnderChest extends InventorySubcontainer {
    public static HashMap<String, CBEnderChest> chests = new HashMap<String, CBEnderChest>();
    
    public List<HumanEntity> transaction = new ArrayList<HumanEntity>();
    public boolean playerOnline = false;
    private CraftPlayer owner;
    private InventoryEnderChest enderChest;
    private int maxStack = MAX_STACK;
    private CraftInventory inventory = new CraftInventory(this);
    
    public CBEnderChest(Player p, Boolean online) {
        super(((CraftPlayer) p).getHandle().getEnderChest().getName(), ((CraftPlayer) p).getHandle().getEnderChest().getSize());
        CraftPlayer player = (CraftPlayer) p;
        this.enderChest = player.getHandle().getEnderChest();
        this.owner = player;
        this.items = enderChest.getContents();
    }
    
    public Inventory getBukkitInventory() {
        return inventory;
    }
    
    public void InventoryRemovalCheck() {
        if (transaction.isEmpty() && !playerOnline) {
            owner.saveData();
            chests.remove(owner.getName().toLowerCase());
        }
    }
    
    public void PlayerGoOnline(Player p) {
        if (!playerOnline) {
            try {
                InventoryEnderChest playerEnderChest = ((CraftPlayer) p).getHandle().getEnderChest();
                Field field = playerEnderChest.getClass().getField("items");
                field.setAccessible(true);
                field.set(playerEnderChest, this.items);
            } catch (Exception ignore) {}
            p.saveData();
            playerOnline = true;
        }
    }
    
    public void PlayerGoOffline() {
        playerOnline = false;
    }
    
    public ItemStack[] getContents() {
        return this.items;
    }
    
    public void onOpen(CraftHumanEntity who) {
        transaction.add(who);
    }
    
    public void onClose(CraftHumanEntity who) {
        transaction.remove(who);
    }
    
    public List<HumanEntity> getViewers() {
        return transaction;
    }
    
    public InventoryHolder getOwner() {
        return this.owner;
    }
    
    public void setMaxStackSize(int size) {
        maxStack = size;
    }
    
    public int getMaxStackSize() {
        return maxStack;
    }
    
    public boolean a(EntityHuman entityhuman) {
        return true;
    }
    
    public void startOpen() {
        
    }
    
    public void f() {
        
    }
    
    public void update() {
        enderChest.update();
    }
}
