/**
 * SakuraLib - Package: net.syamn.utils.cb.inv
 * Created: 2013/02/05 1:15:10
 */
package net.syamn.utils.cb.inv;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import net.minecraft.server.v1_7_R4.PlayerInteractManager;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.craftbukkit.v1_7_R4.CraftOfflinePlayer;
import org.bukkit.entity.Player;

/**
 * OfflineInvManager (OfflineInvManager.java)
 */
public class OfflineInvManager {
    @Deprecated
    public Player loadPlayer(String name) {
        return loadPlayerByUUID(Bukkit.getOfflinePlayer(name).getUniqueId());
    }
    
    public Player loadPlayerByUUID(UUID uuid) {
        try {
            // Default player folder
            File playerdatafolder = new File(Bukkit.getWorlds().get(0).getWorldFolder(), "playerdata");
            if (!playerdatafolder.exists()) {
                return null;
            }
            
            String playeruuid = matchUser(Arrays.asList(playerdatafolder.listFiles()), uuid.toString());
            
            if (playeruuid == null) {
                return null;
            }
            
            GameProfile profile = ((CraftOfflinePlayer)Bukkit.getOfflinePlayer(uuid)).getProfile();
            
            MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
            
            // Create an entity to load the player data
            EntityPlayer entity = new EntityPlayer(server, server.getWorldServer(0), profile, new PlayerInteractManager(server.getWorldServer(0)));
            
            // Get the bukkit entity
            Player target = (entity == null) ? null : entity.getBukkitEntity();
            if (target != null) {
                // Load data
                target.loadData();
                // Return the entity
                return target;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    private static String matchUser(final Collection<File> container, final String search) {
        String found = null;
        if (search == null) {
            return found;
        }
        final String lowerSearch = search.toLowerCase();
        int delta = Integer.MAX_VALUE;
        for (final File file : container) {
            final String filename = file.getName();
            final String str = filename.substring(0, filename.length() - 4);
            if (!str.toLowerCase().startsWith(lowerSearch)) {
                continue;
            }
            final int curDelta = str.length() - lowerSearch.length();
            if (curDelta < delta) {
                found = str;
                delta = curDelta;
            }
            if (curDelta == 0) {
                break;
            }
            
        }
        return found;
    }
}
