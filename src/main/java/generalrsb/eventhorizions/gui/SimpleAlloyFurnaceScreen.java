package generalrsb.eventhorizions.gui;

import generalrsb.eventhorizions.EventHorizions;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;

public class SimpleAlloyFurnaceScreen extends AbstractContainerScreen<SimpleAlloyFurnaceContainerMenu> {
    public static final ResourceLocation BACKGROUND = new ResourceLocation(EventHorizions.MODID,"textures/gui/alloy_furnace_ui.png");

    private int leftPos, topPos;

    int imageOffsetX = 3;

    @Override
    protected void init() {
        super.init();

        imageWidth = 176;
        imageHeight=164;

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        if(this.minecraft == null) return;
    }


    public SimpleAlloyFurnaceScreen(SimpleAlloyFurnaceContainerMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float PartialTick, int pMouseX, int pMouseY) {


        guiGraphics.blit(BACKGROUND,leftPos+imageOffsetX,topPos,0,0,imageWidth,imageHeight);
    }
}
