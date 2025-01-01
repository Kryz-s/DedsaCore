package me.kryz.dedsacore.commands;

import me.kryz.dedsacore.DedsaCore;
import me.kryz.dedsacore.enums.ArmorEnum;
import me.kryz.dedsacore.enums.ItemPlugin;
import me.kryz.dedsacore.interfaces.ArmorInterface;
import me.kryz.dedsacore.utils.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.annotations.AnnotationParser;
import org.incendo.cloud.annotations.Argument;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.suggestion.Suggestions;
import org.incendo.cloud.context.CommandContext;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public final class PluginCommands {

    private final DedsaCore dedsaCore;

    public PluginCommands(DedsaCore dedsaCore, CommandManager<CommandSender> manager) {
        this.dedsaCore = dedsaCore;
        final AnnotationParser<CommandSender> commandAnnotationParser = new AnnotationParser<>(manager, CommandSender.class);
        commandAnnotationParser.parse(this);
    }

    @Command("ded|dedsafio mute-chat")
    public void muteChat(final CommandSender sender){
        final boolean current = this.dedsaCore.getMuteChatChecker().isMuteChat();
        this.dedsaCore.getMuteChatChecker().setMuteCheck(!current);
        Message.send(sender, "command.mute_chat", !current);
    }

    @Command("ded|dedsafio items <items_plugin>")
    public void items(final Player player,
                      final @Argument(value = "items_plugin", suggestions = "items_plugin") @NotNull String item){
        try {
            final ItemPlugin itemPlugin = ItemPlugin.valueOf(item.toUpperCase());
            player.getInventory().addItem(this.dedsaCore.getItemManager().itemPlugin(itemPlugin));
        } catch (final IllegalArgumentException e){
            final String s = "redstone_amulet, taco, chef_hat, chef_outfit, black_hat, imploding_crossbow, exploding_crossbow, blacksmith_outfit, magic_milk, walking_stick, great_hammer";
            Message.send(player, "command.invalid_argument", s);
        }
    }

    @Command("ded|dedsafio reload")
    public void reload(final CommandSender sender){
        this.dedsaCore.reloadPlugin();
        Message.send(sender, "command.reload_success");
    }

    @Command("ded|dedsafio armor <armor_plugin> <armor_parts>")
    public void armor(final Player player,
                            final @Argument(value = "armor_plugin",suggestions = "armor_plugin") @NotNull String item,
                            final @Argument(value = "armor_parts", suggestions = "armor_parts") String part) {
        ArmorEnum armorEnum;
        try {
            armorEnum = ArmorEnum.valueOf(item.toUpperCase());
        } catch (final IllegalArgumentException e) {
            Message.send(player, "command.invalid_argument", "blood, redstone, chef");
            return;
        }

        final ArmorInterface armorManager = this.dedsaCore.getArmorManager().getArmorInterface(armorEnum);
        if(!"helmet,chestplate,leggings,boots".contains(part)){
            Message.send(player,"command.invalid_argument", "helmet, chestplate, leggings, boots");
            return;
        }
        final PlayerInventory inventory = player.getInventory();

        switch (part.toLowerCase()){
            case "helmet" -> inventory.addItem(armorManager.getHelmet());
            case "chestplate" -> inventory.addItem(armorManager.getChestplate());
            case "leggings" -> inventory.addItem(armorManager.getLeggings());
            case "boots" ->inventory.addItem(armorManager.getBoots());
        }
    }

    @Suggestions("items_plugin")
    public List<String> items(final CommandContext<CommandSender> ctx, final String input) {
        return List.of(
                "redstone_amulet",
                "taco",
                "chef_hat",
                "chef_outfit",
                "black_hat",
                "imploding_crossbow",
                "exploding_crossbow",
                "blacksmith_outfit",
                "magic_milk",
                "walking_stick",
                "great_hammer"
        );
    }
    @Suggestions("armor_plugin")
    public List<String> armor(final CommandContext<CommandSender> ctx, final String input) {
        return List.of("blood",
                "redstone");
    }
    @Suggestions("armor_parts")
    public List<String> armorParts(final CommandContext<CommandSender> ctx, final String input) {
        return List.of("helmet",
                "chestplate",
                "leggings",
                "boots");
    }
}
