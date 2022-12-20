package com.generalrs.eventhorizon.entity;

import com.generalrs.eventhorizon.EventHorizon;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Marker;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.border.WorldBorder;
import org.apache.logging.log4j.LogManager;
import org.codehaus.plexus.util.cli.Commandline;


import java.util.logging.Logger;

public class GlowingItem extends ItemEntity {
    int ticks=0;
    boolean hovers = false;
    org.apache.logging.log4j.Logger logger = LogManager.getLogger(EventHorizon.MODID);

    public GlowingItem(Level pLevel, double pPosX, double pPosY, double pPosZ, ItemStack pItemStack,Boolean hovers) {
        super(pLevel, pPosX, pPosY, pPosZ, pItemStack);
        this.hovers=hovers;
        setNoGravity(hovers);
    }

    @Override
    public void tick() {
        if (ticks%3==0) {
            if (!level.isClientSide){
                ServerLevel lvl = (ServerLevel) level;
                lvl.sendParticles(ParticleTypes.FLAME,getX(),getY(),getZ(),15,0.4,0.8,0.4,1);
            }
        }
        ticks++;
        super.tick();
    }

}
