package me.kryz.dedsacore.items;

import me.kryz.dedsacore.DedsaCore;
import me.kryz.dedsacore.config.ItemsJson;
import me.kryz.dedsacore.interfaces.ItemPluginInterface;
import me.kryz.dedsacore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class RedstoneAmulet implements ItemPluginInterface {

    private static final String amulet = "redstone_amulet";
    private final ItemsJson itemsJson;

    public RedstoneAmulet(ItemsJson itemsJson) {
        this.itemsJson = itemsJson;
    }

    @Override
    public @NotNull ItemStack getItem() {
        // Redstone Amulet
        return new ItemBuilder(Material.PAPER)
                .setCustomModelData(110)
                .setLore(itemsJson.getJsonItemLore(amulet))
                .setDisplayName(itemsJson.getJsonItemName(amulet))
                .addStringTag(amulet)
                .build();
    }
}
