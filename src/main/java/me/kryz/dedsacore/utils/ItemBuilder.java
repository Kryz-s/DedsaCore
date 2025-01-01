package me.kryz.dedsacore.utils;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public final class ItemBuilder {
    private ItemStack itemStack;
    private ItemMeta itemMeta;
    private static final int version = Integer.parseInt(Bukkit.getServer().getVersion().replace(".",""));
    private static final NamespacedKey key = new NamespacedKey("dedsacore", "attribute");
    private static final String valueTag = "dedsafio-item-id";
    private static final List<String> itemTags = List.of("taco","redstone_amulet","imploding_crossbow","exploding_crossbow");

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemBuilder(Material material){
        this.itemStack = new ItemStack(material);
    }

    public ItemBuilder addStringTag(final String tag){
        final net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        CustomData.update(DataComponents.CUSTOM_DATA, nmsItem, compoundTag -> {
            compoundTag.putString(valueTag, tag);
        });
        this.itemStack = CraftItemStack.asBukkitCopy(nmsItem);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder setDisplayName(final String name){
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.setDisplayName(ColorUtils.legacyForItemFormat(name));
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    public ItemBuilder setUnbreakable(final boolean bool){
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.setUnbreakable(bool);
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder setLore(final List<String> lore){
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.setLore(lore);
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    public ItemBuilder setAmount(final int amount){
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder addAtrribute(final Attribute attribute, final double amount, final AttributeModifier.Operation operation, final EquipmentSlotGroup slot){
        this.itemMeta = this.itemStack.getItemMeta();

        final AttributeModifier modifier = new AttributeModifier(key, amount, operation, slot);
        this.itemMeta.addAttributeModifier(attribute, modifier);
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    public ItemBuilder setCustomModelData(final int modelData){
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.setCustomModelData(modelData);
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    public ItemStack build(){
        return this.itemStack;
    }

    public static boolean isPluginTag(final ItemStack stack){
        final net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(stack);
        final CustomData data = item.get(DataComponents.CUSTOM_DATA);
        if(data == null) return false;
        return data.contains(valueTag) || data.contains("dedsafio-entity-id");
    }
    public static String getItemStackTag(final ItemStack stack, final String valueTag){
        final net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(stack);
        final CustomData data = item.get(DataComponents.CUSTOM_DATA);
        if(data == null) return "null";
        return data.copyTag().getString(valueTag);
    }
    public static String getItemValueTag(final ItemStack stack){
        final net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(stack);
        final CustomData data = item.get(DataComponents.CUSTOM_DATA);
        if(data == null) return "null";
        return data.copyTag().getString(valueTag);
    }
}
