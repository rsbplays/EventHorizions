package com.generalrs.eventhorizon.blocks.advanced.blockentities;

import com.generalrs.eventhorizon.EventHorizon;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class MachineBlockEntity extends BlockEntity {
    protected ItemStack[] blockInventory;

    public MachineBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    public void intializeInventory(){
        Logger logger = LogManager.getLogger(EventHorizon.MODID);
        for (int i=0;i<50000000;i++){
            logger.error(this.getClass().getName() + " has not initialized an inventory, override method initializeIventory and decalre blockInventory");
        }
    }

    public  static void tick(Level level, BlockPos pos, BlockState state, MachineBlockEntity blockEntity) {

    }
}
