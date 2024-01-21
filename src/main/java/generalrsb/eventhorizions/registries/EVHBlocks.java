package generalrsb.eventhorizions.registries;

import generalrsb.eventhorizions.EventHorizions;
import generalrsb.eventhorizions.blocks.EVHBlock;
import generalrsb.eventhorizions.blocks.EVHBlockModelType;
import generalrsb.eventhorizions.blocks.EVHOre;
import generalrsb.eventhorizions.blocks.machines.GrindStone;
import generalrsb.eventhorizions.blocks.machines.SimpleAlloyFurnace;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Set;
import java.util.function.Supplier;

import static generalrsb.eventhorizions.blocks.EVHBlockModelType.Custom;
import static generalrsb.eventhorizions.blocks.EVHBlockModelType.Simple;

public class EVHBlocks {
    private static final HashMap<String, BlockEntry> EVHBlockMap = new HashMap<String, BlockEntry>();

    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EventHorizions.MODID);

    public static RegistryObject<Block> COBALT_BLOCK = register("cobalt_block", ()->{return new EVHBlock(Block.Properties.copy(Blocks.IRON_BLOCK),true).pickaxeable().minimumTier(Tiers.IRON.getTag());},Simple );
    public static RegistryObject<Block> COPPER_BLOCK = register("steel_block", ()->{return new EVHBlock(Block.Properties.copy(Blocks.IRON_BLOCK),true).pickaxeable().minimumTier(Tiers.IRON.getTag());}, Simple);
    public static RegistryObject<Block> COBALT_ORE = register("cobalt_ore", ()->{return new EVHOre(12);},Simple);

    public static RegistryObject<Block> GRINDSTONE = register("grindstone", ()->{return new GrindStone().pickaxeable().minimumTier(Tiers.IRON.getTag()).dropsSelf();},Simple);
    public static RegistryObject<Block> SIMPLE_ALLOY_FURNACE = register("simple_alloy_furnace", ()->{return new SimpleAlloyFurnace().pickaxeable().minimumTier(Tiers.IRON.getTag()).dropsSelf();},Custom);

    private static RegistryObject<Block> register(String name, Supplier<Block> supplier,EVHBlockModelType modelType){
        RegistryObject<Block> currentBlock = BLOCKS.register(name,supplier);
        EVHBlockMap.put(name,new BlockEntry(currentBlock,modelType));
        return currentBlock;
    }

    public static Set<String> getAllBlockIDs(){
        return EVHBlockMap.keySet();
    }

    public static BlockEntry getBlock(String id){
        return EVHBlockMap.get(id);
    }

    public static class BlockEntry{
        public final RegistryObject<Block> Block;
        public final EVHBlockModelType modelType;

        public BlockEntry(RegistryObject<Block> block, EVHBlockModelType modelType) {
            this.Block = block;
            this.modelType=modelType;
        }
    }


}
