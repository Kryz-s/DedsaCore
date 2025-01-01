package me.kryz.dedsacore.items;

import me.kryz.dedsacore.config.ItemsJson;
import me.kryz.dedsacore.interfaces.ItemPluginInterface;
import me.kryz.dedsacore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class BlackHat implements ItemPluginInterface {

    private final ItemsJson json;

    public BlackHat(ItemsJson json) {
        this.json = json;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemBuilder(Material.CARVED_PUMPKIN)
                .setCustomModelData(111)
                .setDisplayName(this.json.getJsonItemName("black_hat"))
                .build();
    }
}
