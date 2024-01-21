package generalrsb.eventhorizions.blocks.blockentities;

import generalrsb.eventhorizions.gui.SimpleAlloyFurnaceContainerMenu;
import generalrsb.eventhorizions.recipes.AlloyFurnaceRecipe;
import generalrsb.eventhorizions.registries.EVHBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SimpleAlloyFurnaceBlockEntity extends BlockEntity implements MenuProvider {

    private boolean activated = false;
    private int progress = 0;
    private final int maxProgress = 15;
    private int remainingFuel = 0;

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(4);
    private final LazyOptional<ItemStackHandler> itemOptional = LazyOptional.of(()->this.itemStackHandler);

    public SimpleAlloyFurnaceBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(EVHBlockEntities.SIMPLE_ALLOY_FURNACE_ENTITY.get(), p_155229_, p_155230_);
    }

    public ItemStackHandler getItemStackHandler() {
        return itemStackHandler;
    }


    public boolean matches(){
        SimpleContainer sc = new SimpleContainer(itemStackHandler.getSlots());
        for (int i=0; i<itemStackHandler.getSlots();i++){
            sc.setItem(i,itemStackHandler.getStackInSlot(i));
        }
        Optional<AlloyFurnaceRecipe> match = this.getLevel().getRecipeManager().getRecipeFor(AlloyFurnaceRecipe.Type.INSTANCE,sc,this.getLevel());
        return match.isPresent();
    }

    public LazyOptional<ItemStackHandler> getItemOptional() {
        return itemOptional;
    }

    public boolean craftItem(){
        SimpleContainer sc = new SimpleContainer(itemStackHandler.getSlots());
        for (int i=0; i<itemStackHandler.getSlots();i++){
            sc.setItem(i,itemStackHandler.getStackInSlot(i));
        }
        Optional<AlloyFurnaceRecipe> match = this.getLevel().getRecipeManager().getRecipeFor(AlloyFurnaceRecipe.Type.INSTANCE,sc,this.getLevel());
        return match.isPresent();
    }

    public boolean acceptItem(ItemStack itemStack){
        return AlloyFurnaceRecipe.isValid(itemStack);
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

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        SimpleAlloyFurnaceBlockEntity furnace = (SimpleAlloyFurnaceBlockEntity) t;
        if (furnace.matches()){
            if (furnace.remainingFuel>0){
                if (furnace.maxProgress!=furnace.progress){
                    furnace.progress++;
                }else{
                }
            }else{
            }
        }else{
            furnace.progress=0;
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        return cap== ForgeCapabilities.ITEM_HANDLER ? itemOptional.cast() : super.getCapability(cap, side);

    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("fff");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
        return new SimpleAlloyFurnaceContainerMenu(p_39954_, p_39955_, this);
    }

}
