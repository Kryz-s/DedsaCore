package me.kryz.dedsacore.utils;

import net.minecraft.nbt.CompoundTag;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.function.Consumer;

public final class EntityBuilder {
    private final LivingEntity entity;

    public EntityBuilder(final LivingEntity entity){
        this.entity = entity;
    }

    public EntityBuilder editEntity(final LivingEntity livingEntity, Consumer<LivingEntity> editor){
        editor.accept(livingEntity);
        return this;
    }

    public EntityBuilder entityTag(final String tag){
        final net.minecraft.world.entity.Entity ent = ((CraftEntity)this.entity).getHandle();
        final CompoundTag compoundTag = new CompoundTag();
        ent.save(compoundTag);
        compoundTag.putString("dedsafio-entity-id", tag);
        ent.load(compoundTag);
        return this;
    }

    public static String entityPluginTag(final Entity entity){
        final net.minecraft.world.entity.Entity ent = ((CraftEntity)entity).getHandle();
        final CompoundTag compoundTag = new CompoundTag();
        ent.save(compoundTag);
        return compoundTag.getString("dedsafio-entity-id");
    }
}
