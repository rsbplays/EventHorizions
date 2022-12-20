package com.generalrs.eventhorizon.blocks.advanced.blocks;

import com.generalrs.eventhorizon.blocks.advanced.machines.MachineBase;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class Crusher extends MachineBase {

    public Crusher() {
        super(BlockBehaviour.Properties.of(Material.STONE));
    }

    @Override
    public int getCapacity() {
        return 12000;
    }

    @Override
    public int getCurrent() {
        return 1000;
    }


}

