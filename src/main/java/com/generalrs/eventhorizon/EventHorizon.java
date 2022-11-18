package com.generalrs.eventhorizon;

import com.generalrs.eventhorizon.events.ArmorPointsEvent;
import com.generalrs.eventhorizon.events.GlobalLootModifiers;
import com.generalrs.eventhorizon.items.ItemRegister;
import com.mojang.logging.LogUtils;
import net.minecraft.data.DataProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

    public EventHorizon()

    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onGatherData);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ArmorPointsEvent());

        GlobalLootModifiers.register(modEventBus);

        itemRegistry = new ItemRegister(FMLJavaModLoadingContext.get());
        itemRegistry.register();

        System.out.println("triggered1");
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {
        System.out.println("server start");
    }


    private void onGatherData(GatherDataEvent event){
        event.getGenerator().addProvider(event.includeServer(), new GlobalLootModifiers(event.getGenerator(),EventHorizon.MODID));
        System.out.println("triggered");
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code

        }
    }



}
