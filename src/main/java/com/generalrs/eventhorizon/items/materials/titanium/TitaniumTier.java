package com.generalrs.eventhorizon.items.materials.titanium;

import com.generalrs.eventhorizon.Tags;
import com.generalrs.eventhorizon.items.ItemRegister;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public class TitaniumTier implements Tier  {
    @Override
    public int getUses() {
        return 1800;
    }

    @Override
    public float getSpeed() {
        return 11F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 5F;
    }

    @Override
    public int getLevel() {
        return 5;
    }

    @Override
    public int getEnchantmentValue() {
        return 5;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(ItemRegister.TITANIUM_INGOT.get());
    }

    @Override
    public @Nullable TagKey<Block> getTag() {
        return Tags.needs_titanium_tool;
    }
}
