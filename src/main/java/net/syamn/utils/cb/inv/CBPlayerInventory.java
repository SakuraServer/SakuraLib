/**
 * SakuraLib - Package: net.syamn.utils.cb.inv
 * Created: 2013/02/05 1:01:57
 */
package net.syamn.utils.cb.inv;

import java.util.HashMap;

import net.minecraft.server.v1_5_R2.EntityHuman;
import net.minecraft.server.v1_5_R2.ItemStack;
import net.minecraft.server.v1_5_R2.PlayerInventory;

import org.bukkit.craftbukkit.v1_5_R2.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_5_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_5_R2.inventory.CraftInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * CBPlayerInventory (CBPlayerInventory.java)
 */
public class CBPlayerInventory extends PlayerInventory{
    public static HashMap<String, CBPlayerInventory> inventories = new HashMap<String, CBPlayerInventory>();
    
    CraftPlayer owner;
    public boolean playerOnline = false;
    private ItemStack[] extra = new ItemStack[5];
    private CraftInventory inventory = new CraftInventory(this);
    
    public CBPlayerInventory(Player p, Boolean online) {
        super(((CraftPlayer) p).getHandle());
        this.owner = ((CraftPlayer) p);
        this.playerOnline = online;
        this.items = player.inventory.items;
        this.armor = player.inventory.armor;
    }
    
    public Inventory getBukkitInventory() {
        return inventory;
    }
    
    public void InventoryRemovalCheck() {
        if (transaction.isEmpty() && !playerOnline) {
            owner.saveData();
            CBPlayerInventory.inventories.remove(owner.getName().toLowerCase());
        }
    }
    
    public void PlayerGoOnline(Player player) {
        if (!playerOnline) {
            CraftPlayer p = (CraftPlayer) player;
            p.getHandle().inventory.items = this.items;
            p.getHandle().inventory.armor = this.armor;
            p.saveData();
            playerOnline = true;
        }
    }
    
    public void PlayerGoOffline() {
        playerOnline = false;
    }
    
    @Override
    public void onClose(CraftHumanEntity who) {
        super.onClose(who);
        this.InventoryRemovalCheck();
    }
    
    @Override
    public ItemStack[] getContents() {
        ItemStack[] C = new ItemStack[getSize()];
        System.arraycopy(items, 0, C, 0, items.length);
        System.arraycopy(items, 0, C, items.length, armor.length);
        return C;
    }
    
    @Override
    public int getSize() {
        return super.getSize() + 5;
    }
    
    @Override
    public ItemStack getItem(int i) {
        ItemStack[] is = this.items;
        
        if (i >= is.length) {
            i -= is.length;
            is = this.armor;
        } else {
            i = getReversedItemSlotNum(i);
        }
        
        if (i >= is.length) {
            i -= is.length;
            is = this.extra;
        } else if (is == this.armor) {
            i = getReversedArmorSlotNum(i);
        }
        
        return is[i];
    }
    
    @Override
    public ItemStack splitStack(int i, int j) {
        ItemStack[] is = this.items;
        
        if (i >= is.length) {
            i -= is.length;
            is = this.armor;
        } else {
            i = getReversedItemSlotNum(i);
        }
        
        if (i >= is.length) {
            i -= is.length;
            is = this.extra;
        } else if (is == this.armor) {
            i = getReversedArmorSlotNum(i);
        }
        
        if (is[i] != null) {
            ItemStack itemstack;
            
            if (is[i].count <= j) {
                itemstack = is[i];
                is[i] = null;
                return itemstack;
            } else {
                itemstack = is[i].a(j);
                if (is[i].count == 0) {
                    is[i] = null;
                }
                return itemstack;
            }
        } else {
            return null;
        }
    }
    
    @Override
    public ItemStack splitWithoutUpdate(int i) {
        ItemStack[] is = this.items;
        
        if (i >= is.length) {
            i -= is.length;
            is = this.armor;
        } else {
            i = getReversedItemSlotNum(i);
        }
        
        if (i >= is.length) {
            i -= is.length;
            is = this.extra;
        } else if (is == this.armor) {
            i = getReversedArmorSlotNum(i);
        }
        
        if (is[i] != null) {
            ItemStack itemstack = is[i];
            
            is[i] = null;
            return itemstack;
        } else {
            return null;
        }
    }
    
    @Override
    public void setItem(int i, ItemStack itemstack) {
        ItemStack[] is = this.items;
        
        if (i >= is.length) {
            i -= is.length;
            is = this.armor;
        } else {
            i = getReversedItemSlotNum(i);
        }
        
        if (i >= is.length) {
            i -= is.length;
            is = this.extra;
        } else if (is == this.armor) {
            i = getReversedArmorSlotNum(i);
        }
        
        // Effects
        if (is == this.extra) {
            owner.getHandle().drop(itemstack);
            itemstack = null;
        }
        
        is[i] = itemstack;
        
        owner.getHandle().defaultContainer.b();
    }
    
    private int getReversedItemSlotNum(int i) {
        if (i >= 27)
            return i - 27;
        else
            return i + 9;
    }
    
    private int getReversedArmorSlotNum(int i) {
        if (i == 0)
            return 3;
        if (i == 1)
            return 2;
        if (i == 2)
            return 1;
        if (i == 3)
            return 0;
        else
            return i;
    }
    
    @Override
    public String getName() {
        if (player.name.length() > 16) {
            return player.name.substring(0, 16);
        }
        return player.name;
    }
    
    @Override
    public boolean a(EntityHuman entityhuman) {
        return true;
    }
}
