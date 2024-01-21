package generalrsb.eventhorizions.registries;


import generalrsb.eventhorizions.EventHorizions;
import generalrsb.eventhorizions.renderer.GrindStoneRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EventHorizions.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Renderers {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(EVHBlockEntities.GRINDSTONE_BLOCK_ENTITY.get(),GrindStoneRenderer::new);


    }
}
