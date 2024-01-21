package generalrsb.eventhorizions.datagen;

import generalrsb.eventhorizions.blocks.EVHBlock;
import generalrsb.eventhorizions.registries.EVHBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    protected ModBlockLootTables(Set<Item> p_249153_, FeatureFlagSet p_251215_) {
        super(p_249153_, p_251215_);
    }

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        for (String key: EVHBlocks.getAllBlockIDs()){
            EVHBlocks.BlockEntry blockEntry = EVHBlocks.getBlock(key);
            EVHBlock block = (EVHBlock) blockEntry.Block.get();
            if (block.isDropsSelf()){
                dropSelf(block);
            }else{
                add(block,noDrop());
            }
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return EVHBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
