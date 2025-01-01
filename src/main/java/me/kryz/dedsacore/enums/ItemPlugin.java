package me.kryz.dedsacore.enums;

public enum ItemPlugin {
    REDSTONE_AMULET("redstone_amulet"),
    TACO("taco"),
    CHEF_HAT("chef_hat"),
    CHEF_OUTFIT("chef_outfit"),
    BLACK_HAT("black_hat"),
    IMPLODING_CROSSBOW("imploding_crossbow"),
    EXPLODING_CROSSBOW("exploding_crossbow"),
    BLACKSMITH_OUTFIT("blacksmith_outfit"),
    MAGIC_MILK("magic_milk"),
    WALKING_STICK("walking_stick"),
    GREAT_HAMMER("great_hammer");

    private final String item;

    ItemPlugin(String itemName){
        this.item = itemName;
    }
}
