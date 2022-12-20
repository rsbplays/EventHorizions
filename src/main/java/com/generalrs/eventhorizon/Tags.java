package com.generalrs.eventhorizon;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

public class Tags {
    public static TagKey<Block> needs_titanium_tool = BlockTags.create(new ResourceLocation("needs_titanium_tool"));
    public static TagKey<Block> needs_netherite_tool = Tiers.NETHERITE.getTag();

    public static TagKey<Block> create(ResourceLocation name) {
        return TagKey.create(ForgeRegistries.BLOCKS.getRegistryKey(), name);
    }
    private static TagKey<Block> tag(String name)
    {
        return ForgeRegistries.BLOCKS.tags().createTagKey(new ResourceLocation(EventHorizon.MODID,name));
    }
    private static TagKey<Block> tag(String name,String namespace)
    {
        return ForgeRegistries.BLOCKS.tags().createTagKey(new ResourceLocation(namespace,name));
    }
}
