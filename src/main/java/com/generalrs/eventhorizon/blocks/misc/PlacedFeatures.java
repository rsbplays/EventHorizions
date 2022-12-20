package com.generalrs.eventhorizon.blocks.misc;

import com.generalrs.eventhorizon.EventHorizon;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.List;

public class PlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, EventHorizon.MODID);

    public void register(IEventBus bus){
        PLACED_FEATURES.register(bus);
        PLACED_FEATURES.register("titanium_end_ore",()->new PlacedFeature(GenerateOres.TitaniumGenRegOBJ.getHolder().get(),commonOrePlacement(2,HeightRangePlacement.triangle(
                VerticalAnchor.absolute(24),
                VerticalAnchor.belowTop(50)
        ))));
    }

    private static List<PlacementModifier> commonOrePlacement(int countPerChunk, PlacementModifier height){
        return orePlacement(CountPlacement.of(countPerChunk),height);
    }
    private static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier height){
        return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }

}
