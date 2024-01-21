package generalrsb.eventhorizions.blocks.blockentities;

import generalrsb.eventhorizions.recipes.GrindstoneRecipe;
import generalrsb.eventhorizions.registries.EVHBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Optional;

public class GrindstoneBlockEntity extends BlockEntity  {

    private boolean activated = false;
    private int progress = 0;
    private final int maxProgress = 10;

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(1);

    public GrindstoneBlockEntity( BlockPos p_155229_, BlockState p_155230_) {
        super(EVHBlockEntities.GRINDSTONE_BLOCK_ENTITY.get(), p_155229_, p_155230_);
    }

    public ItemStackHandler getItemStackHandler() {
        return itemStackHandler;
    }

    public void activate(){
        if (itemStackHandler.getStackInSlot(0)==ItemStack.EMPTY){
            return;
        }
        if (!acceptItem(itemStackHandler.getStackInSlot(0))){
            return;
        }

        activated = true;
        progress++;
        if (progress>=maxProgress){
            activated=false;
            progress=0;
            craftItem();
        }
    }

    public void craftItem(){
        SimpleContainer sc = new SimpleContainer(itemStackHandler.getSlots());
        for (int i=0; i<itemStackHandler.getSlots();i++){
            sc.setItem(i,itemStackHandler.getStackInSlot(i));
        }
        Optional<GrindstoneRecipe> match = this.getLevel().getRecipeManager().getRecipeFor(GrindstoneRecipe.Type.INSTANCE,sc,this.getLevel());
        match.ifPresent(grindstoneRecipe -> itemStackHandler.setStackInSlot(0, grindstoneRecipe.assemble(sc, level.registryAccess())));

    }
    public boolean acceptItem(ItemStack itemStack){

        SimpleContainer sc = new SimpleContainer(1);
        sc.addItem(itemStack);
        Optional<GrindstoneRecipe> match = this.getLevel().getRecipeManager().getRecipeFor(GrindstoneRecipe.Type.INSTANCE,sc,this.getLevel());
        return match.isPresent();
    }

    @Override
    public void load(CompoundTag p_155245_) {
        itemStackHandler.deserializeNBT(p_155245_.getCompound("inventory"));
        progress = p_155245_.getInt("progress");
        activated=false;
        if (progress!=0){
            activated = true;
        }
        super.load(p_155245_);
    }

    @Override
    protected void saveAdditional(CompoundTag p_187471_) {
        p_187471_.put("inventory",itemStackHandler.serializeNBT());
        p_187471_.putInt("progress",progress);
        super.saveAdditional(p_187471_);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        tag.put("inventory",itemStackHandler.serializeNBT());
        tag.putInt("progress",progress);
        return tag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        // Will get tag from #getUpdateTag
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public float getProgress(){
        return (float)progress/(float)maxProgress;
    }

}
