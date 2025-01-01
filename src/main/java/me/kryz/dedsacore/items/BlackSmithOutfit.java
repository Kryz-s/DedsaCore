package me.kryz.dedsacore.items;

import me.kryz.dedsacore.config.ItemsJson;
import me.kryz.dedsacore.interfaces.ItemPluginInterface;
import me.kryz.dedsacore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class BlackSmithOutfit implements ItemPluginInterface {

    private final ItemsJson json;

    public BlackSmithOutfit(ItemsJson json) {
        this.json = json;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemBuilder(Material.CHAINMAIL_LEGGINGS)
                .setCustomModelData(111)
                .setDisplayName(this.json.getJsonItemName("blacksmith_outfit"))
                .setUnbreakable(true)
                .build();
    }
}
