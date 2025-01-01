package me.kryz.dedsacore.items;

import me.kryz.dedsacore.config.ItemsJson;
import me.kryz.dedsacore.interfaces.ItemPluginInterface;
import me.kryz.dedsacore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class ChefHat implements ItemPluginInterface {

    private final ItemsJson json;

    public ChefHat(ItemsJson json) {
        this.json = json;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemBuilder(Material.CARVED_PUMPKIN)
                .setCustomModelData(110)
                .setDisplayName(json.getJsonItemName("chef_hat"))
                .build();
    }
}
