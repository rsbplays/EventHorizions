package com.generalrs.eventhorizon.blocks;

import com.generalrs.eventhorizon.Tags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class BlockTagProvider extends BlockTagsProvider {

    public BlockTagProvider(DataGenerator pGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        //tag(Tags.needs_titanium_tool)
        tag(Tags.needs_netherite_tool).add(BlocksRegister.TITANIUM_ORE.get());
    }
}
