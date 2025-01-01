package me.kryz.dedsacore.config;

import org.bukkit.plugin.Plugin;

import java.util.List;

public final class Language {
    private final JsonConfigFile configFile;

    public Language(final Plugin plugin) {
        this.configFile = new JsonConfigFile(plugin, "lang.json");
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
