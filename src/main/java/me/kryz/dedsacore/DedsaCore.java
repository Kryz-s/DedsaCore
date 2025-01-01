package me.kryz.dedsacore;

import me.kryz.dedsacore.listeners.MuteChatListener;
import me.kryz.dedsacore.managers.ArmorManager;
import me.kryz.dedsacore.commands.MyCommandManager;
import me.kryz.dedsacore.commands.PluginCommands;
import me.kryz.dedsacore.config.Configuration;
import me.kryz.dedsacore.config.ItemsJson;
import me.kryz.dedsacore.config.Language;
import me.kryz.dedsacore.managers.ItemManager;
import me.kryz.dedsacore.other.MuteChatChecker;
import me.kryz.dedsacore.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.exception.InvalidSyntaxException;
import org.incendo.cloud.exception.handling.ExceptionContext;
import org.incendo.cloud.exception.handling.ExceptionHandler;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.LegacyPaperCommandManager;


public final class DedsaCore extends JavaPlugin {

    private Configuration configuration;
    private ItemsJson itemsJson;
    private Language language;
    private MuteChatChecker muteChatChecker;

    private ArmorManager armorManager;
    private ItemManager itemManager;

    private CommandManager<CommandSender> manager;

    private static final @NonNull ExecutionCoordinator<CommandSender> executionCoordinatorFunction =
            ExecutionCoordinator.simpleCoordinator();

    @Override
    public void onEnable() {
        this.muteChatChecker = new MuteChatChecker();

        this.itemsJson = new ItemsJson(this);
        this.language = new Language(this);
        this.configuration = new Configuration(this);

        this.manager = LegacyPaperCommandManager.createNative(this,
                executionCoordinatorFunction);

        final PluginCommands pluginCommands = new PluginCommands(this, manager);
        this.armorManager = new ArmorManager(itemsJson);
        this.itemManager = new ItemManager(itemsJson);

        Message.set(new Message(language));
        new MyCommandManager(manager);
        this.testExceptions();
        this.setListeners();
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public ArmorManager getArmorManager() {
        return this.armorManager;
    }

    public ItemManager getItemManager() {
        return this.itemManager;
    }

    public MuteChatChecker getMuteChatChecker(){
        return this.muteChatChecker;
    }

    public void reloadPlugin(){
        this.itemsJson.load();
        this.configuration.load();
        this.language.load();
    }

    public void setListeners(){
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new MuteChatListener(this), this);
    }

    private void testExceptions(){
        this.manager.exceptionController().registerHandler(InvalidSyntaxException.class, new ExceptionHandler<>() {
            @Override
            public void handle(@NonNull ExceptionContext<CommandSender, InvalidSyntaxException> context) throws Throwable {
                Message.send(context.context().sender(), "command.invalid_syntax", context.exception().correctSyntax());
            }
        });
    }
}
