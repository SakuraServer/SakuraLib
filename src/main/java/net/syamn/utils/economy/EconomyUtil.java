/**
 * SakuraLib - Package: net.syamn.utils.economy
 * Created: 2013/01/10 1:50:47
 */
package net.syamn.utils.economy;

import java.util.List;

import net.syamn.utils.SakuraLib;
import net.syamn.utils.exception.utils.EconomyException;

import org.bukkit.entity.Player;

/**
 * EconomyUtil (EconomyUtil.java)
 * @author syam(syamn)
 */
public class EconomyUtil {
    /**
     * 経済システム名を返す
     * @return
     */
    public static String getEconomyName(){
        if (!SakuraLib.isEconomyEnabled()){
            throw new EconomyException("Economy plugin does not available!");
        }
        return SakuraLib.getEconomy().getName();
    }
    
    /**
     * 指定したプレイヤーの所持金に加える
     * @param playerName
     * @param amount
     * @return
     */
    public static boolean addMoney(String playerName, double amount){
        if (!SakuraLib.isEconomyEnabled()){
            throw new EconomyException("Economy plugin does not available!");
        }
        if (amount < 0D){
            return false;
        }
        if (amount == 0D){
            return true;
        }
        return SakuraLib.getEconomy().depositPlayer(playerName, amount).transactionSuccess();
    }
    public static boolean addMoney(Player player, double amount){
        return addMoney(player.getName(), amount);
    }
    
    /**
     * 指定したプレイヤーの所持金を引く
     * @param playerName
     * @param amount
     * @return
     */
    public static boolean takeMoney(String playerName, double amount){
        if (!SakuraLib.isEconomyEnabled()){
            throw new EconomyException("Economy plugin does not available!");
        }
        if (amount < 0D){
            return false;
        }
        if (amount == 0D){
            return true;
        }
        return SakuraLib.getEconomy().withdrawPlayer(playerName, amount).transactionSuccess();
    }
    public static boolean takeMoney(Player player, double amount){
        return takeMoney(player.getName(), amount);
    }
    
    /**
     * 指定したプレイヤーがお金を持っているか返す
     * @param playerName
     * @param amount
     * @return
     */
    public static boolean hasMoney(String playerName, double amount){
        if (!SakuraLib.isEconomyEnabled()){
            throw new EconomyException("Economy plugin does not available!");
        }
        if (amount < 0D){
            return false;
        }
        if (amount == 0D){
            return true;
        }
        return SakuraLib.getEconomy().has(playerName, amount);
    }
    public static boolean hasMoney(Player player, double amount){
        return takeMoney(player.getName(), amount);
    }
    
    /**
     * 指定したアカウントの所持金を返す
     * @param accName
     * @return
     */
    public static double getBalance(String accName){
        if (!SakuraLib.isEconomyEnabled()){
            throw new EconomyException("Economy plugin does not available!");
        }
        return SakuraLib.getEconomy().getBalance(accName);
    }
    public static double getBalance(Player player){
        return getBalance(player.getName());
    }
    
    /**
     * 指定したアカウントが存在するか返す
     * @param name
     * @return
     */
    public static boolean hasAccount(String name){
        if (!SakuraLib.isEconomyEnabled()){
            throw new EconomyException("Economy plugin does not available!");
        }
        return SakuraLib.getEconomy().hasAccount(name);
    }
    
    public static boolean createPlayerAccount(String playerName){
        if (!SakuraLib.isEconomyEnabled()){
            throw new EconomyException("Economy plugin does not available!");
        }
        return SakuraLib.getEconomy().createPlayerAccount(playerName);
    }
    
    /**
     * 銀行アカウントをサポートしているか返す
     * @return
     */
    public static boolean hasBankSupport(){
        if (!SakuraLib.isEconomyEnabled()){
            return false;
        }
        return SakuraLib.getEconomy().hasBankSupport();
    }
    
    /**
     * 銀行アカウントを作成する
     * @param bankName
     * @param ownerPlayer
     * @return
     */
    public static boolean createBank(String bankName, String ownerPlayer){
        if (!hasBankSupport()){
            throw new EconomyException("Economy plugin does not available or not supported bank!");
        }
        return SakuraLib.getEconomy().createBank(bankName, ownerPlayer).transactionSuccess();
    }
    public static boolean createBank(String bankName, Player ownerPlayer){
        return createBank(bankName, ownerPlayer.getName());
    }
    
    /**
     * 銀行アカウントを削除する
     * @param bankName
     * @param ownerPlayer
     * @return
     */
    public static boolean deleteBank(String bankName){
        if (!hasBankSupport()){
            throw new EconomyException("Economy plugin does not available or not supported bank!");
        }
        return SakuraLib.getEconomy().deleteBank(bankName).transactionSuccess();
    }
    
    /**
     * 銀行アカウントの所持金に加える
     * @param bankName
     * @param amount
     * @return
     */
    public static boolean addBankMoney(String bankName, double amount){
        if (!hasBankSupport()){
            throw new EconomyException("Economy plugin does not available or not supported bank!");
        }
        if (amount < 0D){
            return false;
        }
        if (amount == 0D){
            return true;
        }
        return SakuraLib.getEconomy().bankDeposit(bankName, amount).transactionSuccess();
    }
    
    /**
     * 銀行アカウントの所持金から引く
     * @param bankName
     * @param amount
     * @return
     */
    public static boolean takeBankMoney(String bankName, double amount){
        if (!hasBankSupport()){
            throw new EconomyException("Economy plugin does not available or not supported bank!");
        }
        if (amount < 0D){
            return false;
        }
        if (amount == 0D){
            return true;
        }
        return SakuraLib.getEconomy().bankWithdraw(bankName, amount).transactionSuccess();
    }
    
    /**
     * 銀行アカウントの所持金を返す
     * @param bankName
     * @return
     */
    public static double getBankBalance(String bankName){
        if (!hasBankSupport()){
            throw new EconomyException("Economy plugin does not available or not supported bank!");
        }
        return SakuraLib.getEconomy().bankBalance(bankName).balance;
    }
    
    /**
     * 銀行アカウントのオーナーかどうか返す
     * @param bankName
     * @param playerName
     * @return
     */
    public static boolean isBankOwner(String bankName, String playerName){
        if (!hasBankSupport()){
            throw new EconomyException("Economy plugin does not available or not supported bank!");
        }
        return SakuraLib.getEconomy().isBankOwner(bankName, playerName).transactionSuccess();
    }
    public static boolean isBankOwner(String bankName, Player player){
        return isBankOwner(bankName, player.getName());
    }
    
    /**
     * 銀行アカウントのメンバーかどうか返す
     * @param bankName
     * @param playerName
     * @return
     */
    public static boolean isBankMember(String bankName, String playerName){
        if (!hasBankSupport()){
            throw new EconomyException("Economy plugin does not available or not supported bank!");
        }
        return SakuraLib.getEconomy().isBankMember(bankName, playerName).transactionSuccess();
    }
    public static boolean isBankMember(String bankName, Player player){
        return isBankMember(bankName, player.getName());
    }
    
    /**
     * 銀行アカウントが指定した金額を持っているか返す
     * @param bankName
     * @param amount
     * @return
     */
    public static boolean hasBankMoney(String bankName, double amount){
        if (!hasBankSupport()){
            throw new EconomyException("Economy plugin does not available or not supported bank!");
        }
        if (amount < 0D){
            return false;
        }
        if (amount == 0D){
            return true;
        }
        return SakuraLib.getEconomy().bankHas(bankName, amount).transactionSuccess();
    }
    
    /**
     * 銀行アカウント一覧を返す
     * @return
     */
    public static List<String> getBanks(){
        if (!hasBankSupport()){
            throw new EconomyException("Economy plugin does not available or not supported bank!");
        }
        return SakuraLib.getEconomy().getBanks();
    }
}
