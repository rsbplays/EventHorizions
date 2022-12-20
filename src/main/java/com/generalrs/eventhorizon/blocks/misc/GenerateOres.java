package com.generalrs.eventhorizon.blocks.misc;

import com.generalrs.eventhorizon.EventHorizon;
import com.generalrs.eventhorizon.blocks.BlocksRegister;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;


public class GenerateOres {
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY,EventHorizon.MODID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> TitaniumGen = Suppliers.memoize(()-> List.of(OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), BlocksRegister.TITANIUM_ORE.get().defaultBlockState())));
    public static RegistryObject<ConfiguredFeature<?,?>> TitaniumGenRegOBJ;

    public void register(IEventBus eventBus){
        CONFIGURED_FEATURES.register(eventBus);
        TitaniumGenRegOBJ = CONFIGURED_FEATURES.register("titanium_end_ore",()-> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(TitaniumGen.get(),5)));
    }
}
