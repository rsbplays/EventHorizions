package com.generalrs.eventhorizon;

import com.generalrs.eventhorizon.blocks.BlockTagProvider;
import com.generalrs.eventhorizon.blocks.BlocksRegister;
import com.generalrs.eventhorizon.blocks.misc.PlacedFeatures;
import com.generalrs.eventhorizon.events.ArmorPointsEvent;
import com.generalrs.eventhorizon.blocks.misc.GenerateOres;
import com.generalrs.eventhorizon.events.loot.GlobalLootModifiers;
import com.generalrs.eventhorizon.items.ItemRegister;
import com.generalrs.eventhorizon.items.materials.titanium.TitaniumTier;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EventHorizon.MODID)
public class EventHorizon
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "eventhorizon";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static ItemRegister itemRegistry;
    public static BlocksRegister blocksRegister;
    public static GenerateOres generateOres;
    public static PlacedFeatures placedFeatures;

    public EventHorizon()

    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::onGatherData);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ArmorPointsEvent());

        GlobalLootModifiers.register(modEventBus);
        //Blocks registered first so that block items can be registered in the ItemRegister class
        blocksRegister = new BlocksRegister();
        blocksRegister.register(modEventBus);

        itemRegistry = new ItemRegister(FMLJavaModLoadingContext.get());
        itemRegistry.register();

        generateOres = new GenerateOres();
        generateOres.register(modEventBus);

        placedFeatures = new PlacedFeatures();
        placedFeatures.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

    }
    private void onGatherData(GatherDataEvent event){
        event.getGenerator().addProvider(event.includeServer(), new GlobalLootModifiers(event.getGenerator(),EventHorizon.MODID));
        event.getGenerator().addProvider(event.includeServer(),new BlockTagProvider(event.getGenerator(),MODID, event.getExistingFileHelper()));
        System.out.println("triggered");
    }

}
