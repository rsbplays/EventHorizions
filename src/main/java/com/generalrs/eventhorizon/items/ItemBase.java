package com.generalrs.eventhorizon.items;

import com.generalrs.eventhorizon.EventHorizon;
import net.minecraft.world.item.Item;

public class ItemBase extends Item {
    String ID;

    public ItemBase(Properties pProperties,String name) {
        super(pProperties);
        ID=name;
    }

    public String getID() {
        return ID;
    }
}
