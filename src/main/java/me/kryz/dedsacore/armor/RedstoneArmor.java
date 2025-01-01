package me.kryz.dedsacore.armor;

import me.kryz.dedsacore.config.ItemsJson;
import me.kryz.dedsacore.interfaces.ArmorInterface;
import me.kryz.dedsacore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class RedstoneArmor implements ArmorInterface {

    private final ItemsJson itemsJson;
    private static final String helmet = "reinforced_redstone_helmet";
    private static final String chestplate = "reinforced_redstone_chestplate";
    private static final String leggings = "reinforced_redstone_leggings";
    private static final String boots = "reinforced_redstone_boots";

    public RedstoneArmor(ItemsJson itemsJson) {
        this.itemsJson = itemsJson;
    }

    @Override
    public ItemStack getHelmet() {
        return new ItemBuilder(Material.NETHERITE_HELMET)
                .setCustomModelData(114)
                .addStringTag(helmet)
                .setDisplayName(itemsJson.getJsonItemName(helmet))
                .setLore(itemsJson.getJsonItemLore(helmet))
                .build();
    }

    @Override
    public ItemStack getChestplate() {
        return new ItemBuilder(Material.NETHERITE_CHESTPLATE)
                .setCustomModelData(115)
                .addStringTag(chestplate)
                .setDisplayName(itemsJson.getJsonItemName(chestplate))
                .setLore(itemsJson.getJsonItemLore(chestplate))
                .build();
    }

    @Override
    public ItemStack getLeggings() {
        return new ItemBuilder(Material.NETHERITE_LEGGINGS)
                .setCustomModelData(116)
                .addStringTag(leggings)
                .setDisplayName(itemsJson.getJsonItemName(leggings))
                .setLore(itemsJson.getJsonItemLore(leggings))
                .build();
    }

    @Override
    public ItemStack getBoots() {
        return new ItemBuilder(Material.NETHERITE_BOOTS)
                .setCustomModelData(117)
                .addStringTag(boots)
                .setDisplayName(itemsJson.getJsonItemName(boots))
                .setLore(itemsJson.getJsonItemLore(boots))
                .build();
    }

    @Override
    public boolean isArmor(final ItemStack itemStack) {
        final String tag =  ItemBuilder.getItemValueTag(itemStack);
        return tag.equals(helmet) || tag.equals(chestplate) || tag.equals(leggings) || tag.equals(boots);
    }
}
