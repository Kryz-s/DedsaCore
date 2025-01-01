package me.kryz.dedsacore.config;

import net.kyori.adventure.text.Component;
import org.bukkit.plugin.Plugin;

import java.util.List;

public final class ItemsJson {
    private final JsonConfigFile configFile;

    public ItemsJson(final Plugin plugin) {
        this.configFile = new JsonConfigFile(plugin, "items.json");
    }

    public String getJsonItemName(final String item){
        return this.configFile.getString(item +"/name");
    }

    public List<String> getJsonItemLore(final String item){
        return this.configFile.getItemLoreComponent(item +"/lore");
    }

    public void load(){
        this.configFile.reloadKeys();
    }
}
