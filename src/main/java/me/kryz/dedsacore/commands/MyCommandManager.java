package me.kryz.dedsacore.commands;

import io.leangen.geantyref.TypeToken;
import me.kryz.dedsacore.parsers.PlayerParser;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.CommandManager;

public final class MyCommandManager{

    private final CommandManager<CommandSender> manager;

    public MyCommandManager(final CommandManager<CommandSender> commandManager){
        this.manager = commandManager;
        this.parsePlayerArgument();
    }

    public void parsePlayerArgument(){
        this.manager.parserRegistry().registerParserSupplier(
                TypeToken.get(Player.class),
                parserParameters -> new PlayerParser<>()
        );
    }
}
