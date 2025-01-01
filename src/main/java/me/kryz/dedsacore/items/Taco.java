package me.kryz.dedsacore.items;

import me.kryz.dedsacore.config.ItemsJson;
import me.kryz.dedsacore.interfaces.ItemPluginInterface;
import me.kryz.dedsacore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class Taco implements ItemPluginInterface {

    private static final String taco = "taco";
    private final ItemsJson itemsJson;

    public Taco(ItemsJson itemsJson) {
        this.itemsJson = itemsJson;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemBuilder(Material.PAPER)
                .setCustomModelData(111)
                .setDisplayName(this.itemsJson.getJsonItemName(taco))
                .setLore(this.itemsJson.getJsonItemLore(taco))
                .addStringTag(taco)
                .build();
    }
}
