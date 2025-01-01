package me.kryz.dedsacore.listeners;

import me.kryz.dedsacore.DedsaCore;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class PlayerJoinListener implements Listener {
    private final DedsaCore dedsaCore;

    public PlayerJoinListener(final DedsaCore dedsaCore) {
        this.dedsaCore = dedsaCore;
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event){
        final Player player =  event.getPlayer();
    }
}
