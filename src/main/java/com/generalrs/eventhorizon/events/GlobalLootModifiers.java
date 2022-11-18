package com.generalrs.eventhorizon.events;

import com.generalrs.eventhorizon.EventHorizon;
import com.generalrs.eventhorizon.items.ItemRegister;
import com.mojang.serialization.Codec;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GlobalLootModifiers extends GlobalLootModifierProvider {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS= DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, EventHorizon.MODID);


    public GlobalLootModifiers(DataGenerator gen, String modid) {
        super(gen, modid);
    }

    public static void register(IEventBus bus){

        LOOT_MODIFIER_SERIALIZERS.register(bus);
        LOOT_MODIFIER_SERIALIZERS.register("ender_dragon_drops", DropGlowingItem.CODEC);

    }

    @Override
    protected void start() {

        add("ender_dragon_drops",new DropGlowingItem(new LootItemCondition[]{
                LootTableIdCondition.builder(new ResourceLocation("entities/ghast")).build()
        }, ItemRegister.dragon_scale));
    }
}
