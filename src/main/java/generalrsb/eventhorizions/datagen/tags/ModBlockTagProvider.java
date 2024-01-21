package generalrsb.eventhorizions.datagen.tags;

import generalrsb.eventhorizions.EventHorizions;
import generalrsb.eventhorizions.blocks.EVHBlock;
import generalrsb.eventhorizions.registries.EVHBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends TagsProvider<Block>{


    public ModBlockTagProvider(PackOutput p_256596_, CompletableFuture<HolderLookup.Provider> p_256513_) {
        super(p_256596_, ForgeRegistries.BLOCKS.getRegistryKey(), p_256513_);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        tag(new TagKey<>(ForgeRegistries.BLOCKS.getRegistryKey(),new ResourceLocation(EventHorizions.MODID,"testtag")))
                .add(EVHBlocks.COBALT_BLOCK.getKey());

        AddAutomatedTags();
    }

    private void AddAutomatedTags(){
        TagAppender<Block> pickaxeTagAppender = tag(new TagKey<>(ForgeRegistries.BLOCKS.getRegistryKey(),new ResourceLocation("minecraft","mineable/pickaxe")));
        for (String key: EVHBlocks.getAllBlockIDs()){
            EVHBlocks.BlockEntry blockEntry = EVHBlocks.getBlock(key);
            EVHBlock block = (EVHBlock) blockEntry.Block.get();
            switch (block.toolType){
                case PICKAXE:
                    pickaxeTagAppender.add(blockEntry.Block.getKey());
            }
            if (block.getMinimumTierToMine()!=null){
                TagAppender<Block> tagAppender = tag(new TagKey<>(ForgeRegistries.BLOCKS.getRegistryKey(),block.getMinimumTierToMine().location()));
                tagAppender.add(blockEntry.Block.getKey());
            }
        }
    }


}
