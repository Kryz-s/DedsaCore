package me.kryz.dedsacore.items;

import me.kryz.dedsacore.config.ItemsJson;
import me.kryz.dedsacore.interfaces.ItemPluginInterface;
import me.kryz.dedsacore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class GreatHammer implements ItemPluginInterface {

    private final ItemsJson json;

    public GreatHammer(ItemsJson json) {
        this.json = json;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemBuilder(Material.NETHERITE_SWORD)
                .setCustomModelData(110)
                .setDisplayName(this.json.getJsonItemName("great_hammer"))
                .addAtrribute(Attribute.ATTACK_DAMAGE,1.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND)
                .addAtrribute(Attribute.ATTACK_SPEED, -0.9, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND)
                .build();
    }
}
