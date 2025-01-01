package me.kryz.dedsacore.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.kryz.dedsacore.DedsaCore;
import me.kryz.dedsacore.utils.Message;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class MuteChatListener implements Listener {

    private final DedsaCore dedsaCore;

    public MuteChatListener(DedsaCore dedsaCore) {
        this.dedsaCore = dedsaCore;
    }

    @EventHandler
    public void onChat(final AsyncChatEvent event){
        if(!this.dedsaCore.getMuteChatChecker().isMuteChat()) return;
        Message.send(event.getPlayer(), "chat.mute_message");
        event.setCancelled(true);
    }
}
