package me.kryz.dedsacore.armor;

import me.kryz.dedsacore.config.ItemsJson;
import me.kryz.dedsacore.interfaces.ArmorInterface;
import me.kryz.dedsacore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class BloodArmor implements ArmorInterface {

    private final ItemsJson itemsJson;
    private static final String helmet = "blood_helmet";
    private static final String chestplate = "blood_chestplate";
    private static final String leggings = "blood_leggings";
    private static final String boots = "blood_boots";

    public BloodArmor(ItemsJson itemsJson) {
        this.itemsJson = itemsJson;
    }

    @Override
    public ItemStack getHelmet() {
        return new ItemBuilder(Material.NETHERITE_HELMET)
                .addStringTag(helmet)
                .setCustomModelData(110)
                .setDisplayName(itemsJson.getJsonItemName(helmet))
                .setLore(itemsJson.getJsonItemLore(helmet))
                .build();
    }

    @Override
    public ItemStack getChestplate() {
        return new ItemBuilder(Material.NETHERITE_CHESTPLATE)
                .setCustomModelData(111)
                .setDisplayName(itemsJson.getJsonItemName(chestplate))
                .setLore(itemsJson.getJsonItemLore(chestplate))
                .addStringTag(chestplate)
                .build();
    }

    @Override
    public ItemStack getLeggings() {
        return new ItemBuilder(Material.NETHERITE_LEGGINGS)
                .setCustomModelData(112)
                .setDisplayName(itemsJson.getJsonItemName(leggings))
                .setLore(itemsJson.getJsonItemLore(leggings))
                .addStringTag(leggings)
                .build();
    }

    @Override
    public ItemStack getBoots() {
        return new ItemBuilder(Material.NETHERITE_BOOTS)
                .setCustomModelData(113)
                .setDisplayName(itemsJson.getJsonItemName(boots))
                .setLore(itemsJson.getJsonItemLore(boots))
                .addStringTag(boots)
                .build();
    }

    @Override
    public boolean isArmor(final ItemStack itemStack) {
        final String tag =  ItemBuilder.getItemValueTag(itemStack);
        return tag.equals(helmet) || tag.equals(chestplate) || tag.equals(leggings) || tag.equals(boots);
    }
}
