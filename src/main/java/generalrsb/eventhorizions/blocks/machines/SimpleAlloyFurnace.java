package generalrsb.eventhorizions.blocks.machines;

import generalrsb.eventhorizions.blocks.EVHBlock;
import generalrsb.eventhorizions.blocks.blockentities.GrindstoneBlockEntity;
import generalrsb.eventhorizions.blocks.blockentities.SimpleAlloyFurnaceBlockEntity;
import generalrsb.eventhorizions.gui.SimpleAlloyFurnaceContainerMenu;
import generalrsb.eventhorizions.registries.EVHBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.network.NetworkHooks;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleAlloyFurnace extends EVHBlock implements EntityBlock {
    public static final DirectionProperty AXIS = BlockStateProperties.HORIZONTAL_FACING;

    public SimpleAlloyFurnace() {
        super(Properties.of().noOcclusion().strength(2),true);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SimpleAlloyFurnaceBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == EVHBlockEntities.SIMPLE_ALLOY_FURNACE_ENTITY.get() ? SimpleAlloyFurnaceBlockEntity::tick : null;
    }

    @Override

    public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        SimpleAlloyFurnaceBlockEntity be = (SimpleAlloyFurnaceBlockEntity) level.getBlockEntity(pos);

        if (!level.isClientSide()) {
            level.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
            if (player instanceof ServerPlayer serverPlayer){
                // In some implementation
                NetworkHooks.openScreen(serverPlayer,be,pos);
                System.out.println("done");
            }

        }

        return InteractionResult.CONSUME_PARTIAL;
    }

    public BlockState getStateForPlacement(BlockPlaceContext p_55928_) {
        return this.defaultBlockState().setValue(AXIS, p_55928_.getHorizontalDirection().getOpposite());
    }

    @Override
    public DirectionProperty getAxis() {
        return AXIS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(AXIS);
    }

}
