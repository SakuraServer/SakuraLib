/**
 * SakuraLib - Package: net.syamn.utils.cb
 * Created: 2012/12/31 1:43:22
 */
package net.syamn.utils.cb;

import net.minecraft.server.v1_7_R3.EntityHuman;
import net.minecraft.server.v1_7_R3.Packet;
import net.minecraft.server.v1_7_R3.PacketPlayOutBed;
import net.minecraft.server.v1_7_R3.PacketPlayOutNamedSoundEffect;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * PacketUtil (PacketUtil.java)
 * @author syam(syamn)
 */
public class PacketUtil {
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
     * @param packets
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
        final EntityHuman eh = ((CraftHumanEntity) player).getHandle();
        
        PacketPlayOutBed packet =  new PacketPlayOutBed(eh, (int) eh.locX, (int) eh.locY, (int) eh.locZ);
        sendPacketNearby(player.getLocation(), radius, packet);
    }

    /**
     * クライアントにシステムサウンドを再生させる
     * @param player
     * @param soundName
     * @param volume default 1.0
     * @param pitch 0-255 default 0
     */
    public static void playSound(Player player, String soundName, Float volume, Float pitch){
        Location ploc = player.getLocation();
        sendPacket(player, new PacketPlayOutNamedSoundEffect(soundName, ploc.getX(), ploc.getY(), ploc.getZ(), volume, pitch));
    }
    
    /**
     * クライアントにシステムサウンドを再生させる
     * @param player
     * @param soundName
     */
    public static void playSound(Player player, String soundName){
        playSound(player, soundName, 1.0F, 0F);
    }
}
