package generalrsb.eventhorizions.blocks.machines;

import generalrsb.eventhorizions.blocks.EVHBlock;
import generalrsb.eventhorizions.blocks.blockentities.GrindstoneBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class GrindStone extends EVHBlock implements EntityBlock {
    public GrindStone() {
        super(BlockBehaviour.Properties.of().noOcclusion().strength(2),true);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new GrindstoneBlockEntity(p_153215_, p_153216_);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_60550_) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        GrindstoneBlockEntity gr = (GrindstoneBlockEntity) level.getBlockEntity(pos);


        if (gr.getItemStackHandler().getStackInSlot(0) == ItemStack.EMPTY && gr.acceptItem(player.getItemInHand(InteractionHand.MAIN_HAND))) {
            ItemStack playersItem = player.getMainHandItem();
            if (playersItem.getCount() > 1) {
                playersItem.setCount(playersItem.getCount() - 1);
            } else {
                player.getInventory().removeItem(playersItem);
            }
            gr.getItemStackHandler().setStackInSlot(0, new ItemStack(playersItem.getItem(), 1));
        } else {
            if ((!gr.acceptItem(gr.getItemStackHandler().getStackInSlot(0)) && gr.getItemStackHandler().getStackInSlot(0) != ItemStack.EMPTY)) {
                ItemStack item = gr.getItemStackHandler().getStackInSlot(0);
                player.addItem(item);
                gr.getItemStackHandler().setStackInSlot(0, ItemStack.EMPTY);
            }else {
                gr.activate();
            }

        }

        if (!level.isClientSide()) {
            level.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
        }

        return InteractionResult.CONSUME;
    }


}
