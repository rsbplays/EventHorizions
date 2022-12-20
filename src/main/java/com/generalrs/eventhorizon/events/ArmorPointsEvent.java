package com.generalrs.eventhorizon.events;

import com.generalrs.eventhorizon.items.EventHorizionsArmor;
import com.generalrs.eventhorizon.items.sets.EventHorizionsArmourMaterial;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ArmorPointsEvent {


    @SubscribeEvent
    public void LivingEntityHurt(LivingDamageEvent e){
        float totaldefence=0;
        for (ItemStack item :e.getEntity().getArmorSlots()){
            if (item.getItem() instanceof EventHorizionsArmor){
                ArmorItem aItem = ((ArmorItem) item.getItem());
                totaldefence += ((EventHorizionsArmourMaterial)aItem.getMaterial()).getAddedArmourReduction(LivingEntity.getEquipmentSlotForItem(item));
            }
        }
        if (totaldefence!=0) {
            e.setAmount(e.getAmount() * (1f - totaldefence / (totaldefence + 50)));
        }
    }

}
