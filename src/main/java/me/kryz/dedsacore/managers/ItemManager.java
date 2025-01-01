package me.kryz.dedsacore.managers;

import me.kryz.dedsacore.config.ItemsJson;
import me.kryz.dedsacore.enums.ItemPlugin;
import me.kryz.dedsacore.interfaces.ItemPluginInterface;
import me.kryz.dedsacore.items.*;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public final class ItemManager {
    private final Map<ItemPlugin, ItemPluginInterface> itemPluginInterfaceMap;

    public ItemManager(final ItemsJson itemsJson) {
        this.itemPluginInterfaceMap = new HashMap<>();
        this.itemPluginInterfaceMap.put(ItemPlugin.REDSTONE_AMULET, new RedstoneAmulet(itemsJson));
        this.itemPluginInterfaceMap.put(ItemPlugin.TACO, new Taco(itemsJson));
        this.itemPluginInterfaceMap.put(ItemPlugin.CHEF_HAT, new ChefHat(itemsJson));
        this.itemPluginInterfaceMap.put(ItemPlugin.CHEF_OUTFIT, new ChefOutfit(itemsJson));
        this.itemPluginInterfaceMap.put(ItemPlugin.BLACK_HAT, new BlackHat(itemsJson));
        this.itemPluginInterfaceMap.put(ItemPlugin.EXPLODING_CROSSBOW, new ExplodingCrossbow(itemsJson));
        this.itemPluginInterfaceMap.put(ItemPlugin.IMPLODING_CROSSBOW, new ImplodingCrossbow(itemsJson));
        this.itemPluginInterfaceMap.put(ItemPlugin.BLACKSMITH_OUTFIT, new BlackSmithOutfit(itemsJson));
        this.itemPluginInterfaceMap.put(ItemPlugin.MAGIC_MILK, new MagicMilk(itemsJson));
        this.itemPluginInterfaceMap.put(ItemPlugin.WALKING_STICK, new WalkingStick(itemsJson));
        this.itemPluginInterfaceMap.put(ItemPlugin.GREAT_HAMMER, new GreatHammer(itemsJson));
    }

    public ItemPluginInterface itemInterface(final ItemPlugin itemEnum){
        return this.itemPluginInterfaceMap.get(itemEnum);
    }

    public ItemStack itemPlugin(final ItemPlugin itemPlugin){
        return this.itemInterface(itemPlugin).getItem();
    }
}
