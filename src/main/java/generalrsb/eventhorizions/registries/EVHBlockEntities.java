package generalrsb.eventhorizions.registries;

import generalrsb.eventhorizions.EventHorizions;
import generalrsb.eventhorizions.blocks.blockentities.GrindstoneBlockEntity;
import generalrsb.eventhorizions.blocks.blockentities.SimpleAlloyFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EVHBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EventHorizions.MODID);

    public static final RegistryObject<BlockEntityType<GrindstoneBlockEntity>> GRINDSTONE_BLOCK_ENTITY = BLOCK_ENTITIES.register("grindstone", ()->{return BlockEntityType.Builder.of(GrindstoneBlockEntity::new, EVHBlocks.GRINDSTONE.get()).build(null);});
    public static final RegistryObject<BlockEntityType<SimpleAlloyFurnaceBlockEntity>> SIMPLE_ALLOY_FURNACE_ENTITY = BLOCK_ENTITIES.register("simple_alloy_furnace", ()->{return BlockEntityType.Builder.of(SimpleAlloyFurnaceBlockEntity::new, EVHBlocks.SIMPLE_ALLOY_FURNACE.get()).build(null);});
}
