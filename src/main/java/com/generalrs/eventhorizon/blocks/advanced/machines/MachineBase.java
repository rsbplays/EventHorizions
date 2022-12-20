package com.generalrs.eventhorizon.blocks.advanced.machines;

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;

public abstract class MachineBase extends Block implements PoweredBlock {
    public ArrayList<PowerGrid> connectedGrids = new ArrayList<>();

    public MachineBase(Properties pProperties) {
        super(pProperties);
    }
}

