package me.kryz.dedsacore.parsers;

import me.kryz.dedsacore.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.context.CommandContext;
import org.incendo.cloud.context.CommandInput;
import org.incendo.cloud.parser.ArgumentParseResult;
import org.incendo.cloud.parser.ArgumentParser;

public final class PlayerParser<C> implements ArgumentParser<C, Player> {

    @Override
    public @NonNull ArgumentParseResult<@NonNull Player> parse(@NonNull CommandContext<@NonNull C> commandContext, @NonNull CommandInput commandInput) {
        final String string = commandInput.peekString();
        final Player player = Bukkit.getPlayer(string);

        if (player == null) {
            return ArgumentParseResult.failure(new Exception(Message.get("argument.player_not_exist")));
        }

        commandInput.readString();
        return ArgumentParseResult.success(player);
    }

}
