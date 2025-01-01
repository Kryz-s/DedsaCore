package me.kryz.dedsacore.managers;

import me.kryz.dedsacore.armor.BloodArmor;
import me.kryz.dedsacore.armor.RedstoneArmor;
import me.kryz.dedsacore.config.ItemsJson;
import me.kryz.dedsacore.enums.ArmorEnum;
import me.kryz.dedsacore.interfaces.ArmorInterface;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class ArmorManager {

    private final Map<ArmorEnum, ArmorInterface> armorInterfaceMap;

    public ArmorManager(final ItemsJson json){
        this.armorInterfaceMap = new HashMap<>();
        this.armorInterfaceMap.put(ArmorEnum.BLOOD, new BloodArmor(json));
        this.armorInterfaceMap.put(ArmorEnum.REDSTONE, new RedstoneArmor(json));
    }

    @NotNull
    public ArmorInterface getArmorInterface(final ArmorEnum armorEnum){
        return this.armorInterfaceMap.get(armorEnum);
    }
}
