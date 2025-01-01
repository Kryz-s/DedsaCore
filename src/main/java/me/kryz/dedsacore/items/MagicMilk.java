package me.kryz.dedsacore.items;

import me.kryz.dedsacore.config.ItemsJson;
import me.kryz.dedsacore.interfaces.ItemPluginInterface;
import me.kryz.dedsacore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class MagicMilk implements ItemPluginInterface {

    private final ItemsJson json;

    public MagicMilk(ItemsJson json) {
        this.json = json;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemBuilder(Material.MILK_BUCKET)
                .setDisplayName(this.json.getJsonItemName("magic_milk"))
                .setCustomModelData(110)
                .addStringTag("magic_milk")
                .build();
    }
}
