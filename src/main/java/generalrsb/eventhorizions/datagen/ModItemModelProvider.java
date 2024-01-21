package generalrsb.eventhorizions.datagen;

import generalrsb.eventhorizions.EventHorizions;
import generalrsb.eventhorizions.registries.EVHBlocks;
import generalrsb.eventhorizions.registries.EVHItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import static generalrsb.eventhorizions.registries.EVHItems.ItemEntry;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output,  ExistingFileHelper existingFileHelper) {
        super(output, EventHorizions.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (String key: EVHItems.getAllItemIDs()){

            ItemEntry item = EVHItems.getItem(key);
            switch (item.modelType) {
                case Simple:
                    simpleItem(item.Item);
                    break;
                case HandHeld:
                    handHeldItem(item.Item);
                    break;
                case BlockItem:
                    blockItem(item.Item);
                    break;

            }
        }

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(EventHorizions.MODID,"item/"+item.getId().getPath()));
    }

    private ItemModelBuilder handHeldItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(EventHorizions.MODID,"item/"+item.getId().getPath()));
    }
    private ItemModelBuilder blockItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation(EventHorizions.MODID,"block/"+item.getId().getPath()));
    }
}
