package me.kryz.dedsacore.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public final class ColorUtils {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private static final LegacyComponentSerializer SERIALIZER = LegacyComponentSerializer.builder().hexCharacter('#').hexColors().useUnusualXRepeatedCharacterHexFormat().build();;

    public static Component miniMessage(final String message){
        return MINI_MESSAGE.deserialize(message);
    }

    public static String legacyForItemFormat(final String s){
        final Component component = MINI_MESSAGE.deserialize(s);

        return SERIALIZER.serialize(component);
    }
}
