package com.generalrs.eventhorizon.items.sets.dragonscale;

import com.generalrs.eventhorizon.items.sets.EventHorizionsArmour;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class DragonScaleArmour extends ArmorItem {
    public DragonScaleArmour(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial,pSlot, pProperties);
    }

    public static DragonScaleMaterial getMaterial1(){
        return new DragonScaleMaterial();
    }
}
class DragonScaleMaterial implements EventHorizionsArmour {

    @Override
    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        if(pSlot==EquipmentSlot.FEET){
            return 600;
        }
        if(pSlot==EquipmentSlot.LEGS){
            return 900;
        }
        if(pSlot==EquipmentSlot.CHEST){
            return 980;
        }

        return 600;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot pSlot) {

        if(pSlot==EquipmentSlot.FEET){
            return 3;
        }
        if(pSlot==EquipmentSlot.LEGS){
            return 6;
        }
        if(pSlot==EquipmentSlot.CHEST){
            return 8;
        }

        return 3;

    }

    @Override
    public int getEnchantmentValue() {
        return 4;
    }

    @Override
    public SoundEvent getEquipSound() {
        return null;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public String getName() {
        return "eventhorizon:dragonscale";
    }

    @Override
    public float getToughness() {
        return 2;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }

    @Override
    public int getAddedArmourReduction(EquipmentSlot pSlot) {
        if(pSlot==EquipmentSlot.FEET){
            return 5;
        }
        if(pSlot==EquipmentSlot.LEGS){
            return 7;
        }
        if(pSlot==EquipmentSlot.CHEST){
            return 11;
        }

        return 4;
    }
}
