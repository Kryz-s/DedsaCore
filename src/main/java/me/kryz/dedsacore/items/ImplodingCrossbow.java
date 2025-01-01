package me.kryz.dedsacore.items;

import me.kryz.dedsacore.config.ItemsJson;
import me.kryz.dedsacore.interfaces.ItemPluginInterface;
import me.kryz.dedsacore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class ImplodingCrossbow implements ItemPluginInterface {

    private final ItemsJson json;
    private static final String icross = "imploding_crossbow";

    public ImplodingCrossbow(final ItemsJson json) {
        this.json = json;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemBuilder(Material.CROSSBOW)
                .setCustomModelData(110)
                .addStringTag(icross)
                .setDisplayName(this.json.getJsonItemName(icross))
                .setLore(this.json.getJsonItemLore(icross))
                .build();
    }
}
