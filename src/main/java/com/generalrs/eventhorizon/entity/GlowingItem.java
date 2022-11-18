package com.generalrs.eventhorizon.entity;

import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.border.WorldBorder;

public class GlowingItem extends ItemEntity {
    int ticks=0;
    boolean hovers = false;

    public GlowingItem(Level pLevel, double pPosX, double pPosY, double pPosZ, ItemStack pItemStack,Boolean hovers) {
        super(pLevel, pPosX, pPosY, pPosZ, pItemStack);
        this.hovers=hovers;
        setNoGravity(hovers);
    }

    @Override
    public void tick() {

        if (ticks%3==0) {
            getLevel().addParticle(ParticleTypes.EXPLOSION,true,position().x,position().y,position().z,0,1,0);
            getLevel().part
        }
        ticks++;
        super.tick();
        setOnGround(true);
    }





}
