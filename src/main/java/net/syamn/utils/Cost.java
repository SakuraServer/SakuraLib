/**
 * SakuraLib - Package: net.syamn.utils
 * Created: 2013/01/09 15:36:39
 */
package net.syamn.utils;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Cost (Cost.java)
 * @author syam(syamn)
 */
public class Cost {
    private CostType type;
    private int itemId;
    private byte itemData;
    private double amount;
    
    /**
     * コンストラクタ CostType.MONEY
     * @param amount
     */
    public Cost(double amount){
        this(CostType.MONEY, 0, null, amount);
    }
    
    /**
     * コンストラクタ
     * @param type
     * @param itemId
     * @param itemData
     * @param amount
     */
    public Cost(CostType type, int itemId, Byte itemData, double amount){
        this.type = type;
        this.itemId = itemId;
        this.itemData = itemData;
        this.amount = amount;
    }
    
    public CostType getType(){
        return type;
    }
    public int getItemId(){
        return itemId;
    }
    public Byte getItemData(){
        return itemData;
    }
    public double getAmount(){
        return this.amount;
    }
    
    /**
     * 支払えるかどうか返す
     * @param sender
     * @return
     */
    public boolean isPayable(CommandSender sender){
        if (!(sender instanceof Player)){
            return true;
        }
        if (getAmount() <= 0){
            return true;
        }
        
        Player player = (Player) sender;
        
        switch (getType()){
            // money - economy
            case MONEY:
                Economy economy = SakuraLib.getEconomy();
                return (economy == null || !economy.isEnabled()) ? false : economy.has(player.getName(), getAmount());
            // item
            case ITEM:
                throw new NotImplementedException("Item payment method is not implemented!");
            // undefined
            default: 
                throw new NotImplementedException("Undefined CostType!");
        }
    }
    
    /**
     * 支払いを行う
     * @param sender
     */
    public void pay(CommandSender sender){
        if (!(sender instanceof Player)){
            return;
        }
        if (getAmount() == 0.0D){
            return;
        }
        
        Player player = (Player) sender;
        
        switch (getType()){
            // money - economy
            case MONEY:
                Economy economy = SakuraLib.getEconomy();
                if (economy == null || !economy.isEnabled()){
                    throw new IllegalStateException("Attempt to pay with economy money, but no economy plugin available!");
                }
                EconomyResponse res;
                if (getAmount() > 0.0D){
                    res = economy.withdrawPlayer(player.getName(), getAmount());
                }else{
                    res = economy.depositPlayer(player.getName(), -getAmount());
                }
                if (!res.transactionSuccess()){
                    throw new IllegalStateException("Economy error: " + res.errorMessage);
                }
                break;
            // item
            case ITEM:
                throw new NotImplementedException("Item payment method is not implemented!");
            // undefined
            default: 
                throw new NotImplementedException("Undefined CostType!");
        }
    }
    
    /**
     * 支払い方法の列挙
     * CostType (Cost.java)
     * @author syam(syamn)
     */
    public enum CostType{
        MONEY,
        ITEM,
        ;
    }
}
