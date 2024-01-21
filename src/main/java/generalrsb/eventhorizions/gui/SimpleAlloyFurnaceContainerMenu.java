package generalrsb.eventhorizions.gui;

import generalrsb.eventhorizions.EventHorizions;
import generalrsb.eventhorizions.blocks.blockentities.SimpleAlloyFurnaceBlockEntity;
import generalrsb.eventhorizions.registries.EVHBlocks;
import generalrsb.eventhorizions.registries.MenuTypes;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

public class SimpleAlloyFurnaceContainerMenu extends AbstractContainerMenu {
    SimpleAlloyFurnaceBlockEntity be;
    ContainerLevelAccess levelAccessor;

    public SimpleAlloyFurnaceContainerMenu(int containerId, Inventory playerInventory, SimpleAlloyFurnaceBlockEntity be) {
        super(MenuTypes.SIMPLE_ALLOY_FURNACE_MENU.get(), containerId);
        this.be = be;

        levelAccessor = ContainerLevelAccess.create(be.getLevel(),be.getBlockPos());
        createPlayerInventory(playerInventory);

        addSlot(new SlotItemHandler( be.getItemOptional().resolve().get(), 0, 49, 18));
        addSlot(new SlotItemHandler( be.getItemOptional().resolve().get(), 1, 69, 18));

    }
    public SimpleAlloyFurnaceContainerMenu(int containerId, Inventory playerInventory, FriendlyByteBuf addition){
        this(containerId,playerInventory, (SimpleAlloyFurnaceBlockEntity) playerInventory.player.level().getBlockEntity(addition.readBlockPos()));
    }

    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        return ItemStack.EMPTY;
    }

    public void createPlayerInventory(Inventory playerInventory){
        //CreateHotbar
        for (int column = 0; column<9;column++){
            addSlot(new Slot( playerInventory, column, 11+(column*18), 143));
        }
        for (int row = 0; row<3;row++){
            for (int column = 0; column<9;column++){
                addSlot(new Slot( playerInventory, 9 + column+(row*9), 11+(column*18), 85+(row*18)));
            }
        }

    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return stillValid(levelAccessor,p_38874_, EVHBlocks.SIMPLE_ALLOY_FURNACE.get());
    }
}
