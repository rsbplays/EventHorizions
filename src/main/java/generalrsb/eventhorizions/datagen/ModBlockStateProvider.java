package generalrsb.eventhorizions.datagen;

import generalrsb.eventhorizions.EventHorizions;
import generalrsb.eventhorizions.blocks.EVHBlock;
import generalrsb.eventhorizions.registries.EVHBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    private ResourceLocation block(String name){
        return new ResourceLocation(EventHorizions.MODID,"block/"+name);
    }
    private ResourceLocation block(RegistryObject<Block>loc ,String suffix){
        return new ResourceLocation(EventHorizions.MODID,"block/"+loc.getId().getPath()+suffix);
    }

    @Override
    protected void registerStatesAndModels() {
        for (String key: EVHBlocks.getAllBlockIDs()){
            EVHBlocks.BlockEntry blockEntry = EVHBlocks.getBlock(key);
            switch (blockEntry.modelType){
                case Simple:
                    simpleBlockWithItem(blockEntry.Block.get(),cubeAll(blockEntry.Block.get()));
            }
        }

        frontFacedBlock(EVHBlocks.SIMPLE_ALLOY_FURNACE,block(EVHBlocks.SIMPLE_ALLOY_FURNACE,"_front"),block(EVHBlocks.SIMPLE_ALLOY_FURNACE,"_side"),block(EVHBlocks.SIMPLE_ALLOY_FURNACE,"_top"));


    }
    public void frontFacedBlock(RegistryObject<Block> blockReg, ResourceLocation front,ResourceLocation sides,ResourceLocation topandbottom) {
        EVHBlock block = (EVHBlock) blockReg.get();

        ModelFile horizontal = models().cube(blockReg.getId().getPath(),
                topandbottom,topandbottom,front,sides,sides,sides);

        getVariantBuilder(block)
                .partialState().with(block.getAxis(), Direction.NORTH)
                .modelForState().modelFile(horizontal).addModel()
                .partialState().with(block.getAxis(), Direction.EAST)
                .modelForState().modelFile(horizontal).rotationY(90).addModel()
                .partialState().with(block.getAxis(), Direction.SOUTH)
                .modelForState().modelFile(horizontal).rotationY(180).addModel()
                .partialState().with(block.getAxis(), Direction.WEST)
                .modelForState().modelFile(horizontal).rotationY(270).addModel();
    }
}
