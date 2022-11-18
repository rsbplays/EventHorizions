package com.generalrs.eventhorizon.entity;

import com.generalrs.eventhorizon.EventHorizon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.swing.text.html.parser.Entity;

public class EntitiesRegister {
    DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EventHorizon.MODID);

    public void register(FMLJavaModLoadingContext ctx){
        ENTITIES.register(ctx.getModEventBus());

    }
}
