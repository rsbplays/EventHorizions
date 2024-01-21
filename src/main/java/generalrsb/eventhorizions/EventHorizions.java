package generalrsb.eventhorizions;

import com.mojang.logging.LogUtils;
import generalrsb.eventhorizions.registries.*;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EventHorizions.MODID)
public class EventHorizions {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "evh";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "EventHorizions" namespace


    public EventHorizions() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::buildCreativeModeTabContents);

        // Register the Deferred Register to the mod event bus so blocks get registered
        EVHBlocks.BLOCKS.register(modEventBus);
        //Register the Items
        EVHItems.ITEMS.register(modEventBus);
        //Register Blockentities
        EVHBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        MenuTypes.MENU_TYPES.register(modEventBus);

        RecipeTypes.RECIPETYPES.register(modEventBus);
        RecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts

    }

    @SubscribeEvent
    public void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event){

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            for (String key: EVHItems.getAllItemIDs()) {
                EVHItems.ItemEntry item = EVHItems.getItem(key);
                event.accept(item.Item.get());
            }
        }
    }

}
