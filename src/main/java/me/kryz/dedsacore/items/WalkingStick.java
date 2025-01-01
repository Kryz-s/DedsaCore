package me.kryz.dedsacore.items;

import me.kryz.dedsacore.config.ItemsJson;
import me.kryz.dedsacore.interfaces.ItemPluginInterface;
import me.kryz.dedsacore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class WalkingStick implements ItemPluginInterface {

    private final ItemsJson json;

    public WalkingStick(ItemsJson json) {
        this.json = json;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemBuilder(Material.GOLDEN_SWORD)
                .setCustomModelData(110)
                .setDisplayName(this.json.getJsonItemName("walking_stick"))
                .build();
    }
}
