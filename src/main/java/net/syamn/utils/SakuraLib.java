/**
 * SakuraLib - Package: net.syamn.utils
 * Created: 2013/01/09 16:37:59
 */
package net.syamn.utils;

import net.milkbowl.vault.economy.Economy;

/**
 * SakuraLib (SakuraLib.java)
 * @author syam(syamn)
 */
public class SakuraLib {
    private static Economy economy = null;
    
    /**
     * Economyを設定する
     * @param econ
     */
    public static void setEconomy(Economy econ){
        economy = econ;
    }
    
    /**
     * Economyを取得する
     */
    public static Economy getEconomy(){
        return economy;
    }
    
    /**
     * Economyが設定済みか返す
     * @return
     */
    public static boolean isSetEconomy(){
        return economy != null;
    }
}
