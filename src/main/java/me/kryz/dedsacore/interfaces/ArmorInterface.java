package me.kryz.dedsacore.interfaces;

import org.bukkit.inventory.ItemStack;

public interface ArmorInterface {

    ItemStack getHelmet();
    ItemStack getChestplate();
    ItemStack getLeggings();
    ItemStack getBoots();
    boolean isArmor(final ItemStack itemStack);
}
