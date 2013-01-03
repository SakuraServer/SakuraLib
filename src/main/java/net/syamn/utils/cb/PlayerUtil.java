/**
 * SakuraLib - Package: net.syamn.utils.cb
 * Created: 2012/12/31 1:43:22
 */
package net.syamn.utils.cb;


import net.minecraft.server.v1_4_6.EntityPlayer;
import net.minecraft.server.v1_4_6.Packet;
import net.minecraft.server.v1_4_6.Packet17EntityLocationAction;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_4_6.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * PlayerUtil (PlayerUtil.java)
 * @author syam(syamn)
 */
public class PlayerUtil {
    /**
     * 指定したプレイヤーにパケットを送る
     * @param player
     * @param packet
     */
    public static void sendPacket(Player player, Packet packet){
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
    
    /**
     * オンラインプレイヤーにパケットを送る
     * @param player
     * @param packet
     */
    public static void sendPacketToOnline(Packet... packets){
        for (final Player player : Bukkit.getOnlinePlayers()){
            if (player == null || !player.isOnline()){
                continue;
            }
            for (Packet packet : packets){
                sendPacket(player, packet);
            }
        }
    }
    
    /**
     * 指定した座標近くのプレイヤーにパケットを送る
     * @param loc
     * @param packets
     * @param radius
     */
    public static void sendPacketNearby(Location loc, double radius, Packet... packets){
        radius *= radius;
        World world = loc.getWorld();
        for (final Player player : world.getPlayers()){
            if (loc.distanceSquared(player.getLocation()) <= radius){
                for (final Packet packet : packets){
                    sendPacket(player, packet);
                }
            }
        }
    }
    
    /**
     * 指定した座標近くのプレイヤーにパケットを送る
     * @param loc
     * @param packets
     * @param radius
     */
    public static void sendPacketNearby(Location loc, Packet... packets){
        sendPacketNearby(loc, 64, packets);
    }

    /**
     * プレイヤーをベッドに寝た見た目で表示させる
     * @param player
     * @param radius
     */
    @Deprecated
    public static void sendPlayerSleep(Player player, double radius){
        if (player.isSleeping()) return;
        final EntityPlayer ep = ((CraftPlayer) player).getHandle();
        
        Packet17EntityLocationAction packet =  new Packet17EntityLocationAction(ep, 0, (int) ep.locX, (int) ep.locY, (int) ep.locZ);
        sendPacketNearby(player.getLocation(), radius, packet);
    }
}
