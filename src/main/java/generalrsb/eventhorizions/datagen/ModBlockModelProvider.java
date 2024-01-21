package generalrsb.eventhorizions.datagen;

import generalrsb.eventhorizions.EventHorizions;
import generalrsb.eventhorizions.registries.EVHBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockModelProvider extends BlockModelProvider {
    public ModBlockModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EventHorizions.MODID, exFileHelper);
    }


    @Override
    protected void registerModels() {
        for (String key: EVHBlocks.getAllBlockIDs()){
            EVHBlocks.BlockEntry blockEntry = EVHBlocks.getBlock(key);
            switch (blockEntry.modelType){
                case Simple:
                    cubeAll(blockEntry.Block.getId().getPath(),new ResourceLocation(EventHorizions.MODID,"block/"+blockEntry.Block.getId().getPath()));
            }
        }
    }
}
