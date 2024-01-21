package generalrsb.eventhorizions.datagen;

import generalrsb.eventhorizions.EventHorizions;
import generalrsb.eventhorizions.datagen.tags.ModBlockTagProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EventHorizions.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(true,new ModBlockModelProvider(packOutput,existingFileHelper));
        generator.addProvider(true,new ModBlockTagProvider(packOutput,event.getLookupProvider()));
        generator.addProvider(true,ModLootTableProvider.create(packOutput));
        generator.addProvider(true, new ModBlockStateProvider(packOutput,EventHorizions.MODID,existingFileHelper));
        generator.addProvider(true,new ModItemModelProvider(packOutput,existingFileHelper));
    }
}
