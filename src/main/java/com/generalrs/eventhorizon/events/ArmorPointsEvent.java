package com.generalrs.eventhorizon.events;

import com.generalrs.eventhorizon.items.sets.EventHorizionsArmour;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class ArmorPointsEvent {


    @SubscribeEvent
    public void LivingEntityHurt(LivingDamageEvent e){

        for (ItemStack item :e.getEntity().getArmorSlots()){
            if (item.getItem() instanceof EventHorizionsArmour){
                ArmorItem aItem = ((ArmorItem) item.getItem());
                int defenseForSlot = ((EventHorizionsArmour)aItem.getMaterial()).getAddedArmourReduction(aItem.getEquipmentSlot(item));
                e.setAmount(e.getAmount()*(1-e.getAmount()/(e.getAmount()+50)));
            }
        }

    }

}
