package me.kryz.dedsacore.utils;

import me.kryz.dedsacore.config.JsonConfigFile;
import me.kryz.dedsacore.config.Language;
import org.bukkit.command.CommandSender;

import java.text.MessageFormat;
import java.util.Formatter;

public final class Message {
    private static Message instance;
    private final Language language;

    public Message(final Language language){
        this.language = language;
    }

    public static Message send(final CommandSender sender, final String key, final Object... formats){
        final JsonConfigFile file = instance.language.getConfigFile();
        if(file.isList(key)){
            for ( String str : file.getStringList(key)){
                str = instance.setFormats(str, formats);
                sender.sendMessage(ColorUtils.miniMessage(str));
            }
            return instance;
        }
        String m = file.getString(key);
        m = instance.setFormats(m, formats);
        sender.sendMessage(ColorUtils.miniMessage(m));
        return instance;
    }

    public static Message send(final CommandSender sender, final String key){
        final JsonConfigFile file = instance.language.getConfigFile();
        if(file.isList(key)){
            for ( String str : file.getStringList(key)){
                sender.sendMessage(ColorUtils.miniMessage(str));
            }
            return instance;
        }
        String m = file.getString(key);
        sender.sendMessage(ColorUtils.miniMessage(m));
        return instance;
    }

    public static String get(final String key){
        return instance.language.getConfigFile().getString(key);
    }

    public String setFormats(final String string, final Object... formats){
        return MessageFormat.format(string, formats);
    }

    public static void set(final Message inst){
        instance = inst;
    }
}
