package me.kryz.dedsacore.items;

import me.kryz.dedsacore.config.ItemsJson;
import me.kryz.dedsacore.interfaces.ItemPluginInterface;
import me.kryz.dedsacore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class ExplodingCrossbow implements ItemPluginInterface {

    private final ItemsJson json;
    private static final String eCross = "exploding_crossbow";

    public ExplodingCrossbow(ItemsJson json) {
        this.json = json;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemBuilder(Material.CROSSBOW)
                .setCustomModelData(111)
                .setDisplayName(this.json.getJsonItemName(eCross))
                .setLore(this.json.getJsonItemLore(eCross))
                .addStringTag(eCross)
                .build();
    }
}
