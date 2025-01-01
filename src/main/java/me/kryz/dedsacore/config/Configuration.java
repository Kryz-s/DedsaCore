package me.kryz.dedsacore.config;

import org.bukkit.plugin.Plugin;

import java.util.List;

public final class Configuration {
    private final JsonConfigFile configFile;

    public Configuration(final Plugin plugin) {
        this.configFile = new JsonConfigFile(plugin, "config.json");
    }

    public String getString(final String path){
        return this.configFile.getString(path);
    }

    public List<String> getListString(final String path){
        return this.configFile.getStringList(path);
    }

    public JsonConfigFile getConfigFile() {
        return configFile;
    }

    public void load(){
        this.configFile.reloadKeys();
    }
}
