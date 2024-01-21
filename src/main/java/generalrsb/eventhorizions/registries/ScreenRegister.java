package generalrsb.eventhorizions.registries;

import generalrsb.eventhorizions.EventHorizions;
import generalrsb.eventhorizions.gui.SimpleAlloyFurnaceContainerMenu;
import generalrsb.eventhorizions.gui.SimpleAlloyFurnaceScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EventHorizions.MODID,bus=Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ScreenRegister {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        event.enqueueWork( ()->{
            MenuScreens.register(MenuTypes.SIMPLE_ALLOY_FURNACE_MENU.get(), SimpleAlloyFurnaceScreen::new);
        });
    }
}
