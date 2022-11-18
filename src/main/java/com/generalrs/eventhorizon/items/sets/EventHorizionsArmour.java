package com.generalrs.eventhorizon.items.sets;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;

public interface EventHorizionsArmour extends ArmorMaterial {
    int getAddedArmourReduction(EquipmentSlot slot);
}
