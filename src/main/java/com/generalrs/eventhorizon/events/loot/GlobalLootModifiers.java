package com.generalrs.eventhorizon.events.loot;

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
        LOOT_MODIFIER_SERIALIZERS.register("drop_glowing_item", DropGlowingItem.CODEC);
        LOOT_MODIFIER_SERIALIZERS.register("additem_modifier", AddItem.CODEC);
    }

    @Override
    protected void start() {
        add("ender_dragon_drops",new DropGlowingItem(new LootItemCondition[]{
                LootTableIdCondition.builder(new ResourceLocation("entities/ender_dragon")).build()
        }, ItemRegister.dragon_scale));
        add("end_city_tresure_modifier",new AddItem(new LootItemCondition[]{
                LootTableIdCondition.builder(new ResourceLocation("chests/end_city_treasure")).build()
        }, ItemRegister.dragon_scale,0.25f));
    }
}
