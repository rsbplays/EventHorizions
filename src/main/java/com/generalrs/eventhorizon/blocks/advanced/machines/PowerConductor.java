package com.generalrs.eventhorizon.blocks.advanced.machines;

public interface PowerConductor {
    int transferLimit();
    int currentLimitOnTick();
}
