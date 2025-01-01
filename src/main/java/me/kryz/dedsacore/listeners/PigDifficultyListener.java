package me.kryz.dedsacore.listeners;

import me.kryz.dedsacore.DedsaCore;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import org.bukkit.craftbukkit.entity.CraftIronGolem;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class PigDifficultyListener implements Listener {

    private final DedsaCore dedsaCore;

    public PigDifficultyListener(DedsaCore dedsaCore) {
        this.dedsaCore = dedsaCore;
    }

    @EventHandler
    public void onSpawn(final CreatureSpawnEvent event){
        final LivingEntity entity = event.getEntity();
        if(!(entity instanceof IronGolem golem)) return;
        final CraftIronGolem craftIronGolem = (CraftIronGolem) golem;
        final net.minecraft.world.entity.animal.IronGolem ironGolem = craftIronGolem.getHandle();
        ironGolem.goalSelector.addGoal(0, new MeleeAttackGoal(ironGolem, 1.0D, true));
    }
}
