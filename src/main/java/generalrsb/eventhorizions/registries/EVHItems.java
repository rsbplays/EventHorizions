package generalrsb.eventhorizions.registries;

import generalrsb.eventhorizions.EventHorizions;
import generalrsb.eventhorizions.items.EVHDust;
import generalrsb.eventhorizions.items.EVHIngot;
import generalrsb.eventhorizions.items.EVHModelType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Set;
import java.util.function.Supplier;
import static generalrsb.eventhorizions.items.EVHModelType.*;

import static generalrsb.eventhorizions.items.EVHModelType.Simple;

public class EVHItems {
    private static final HashMap<String,ItemEntry> EVHItemMap = new HashMap<String, ItemEntry>();

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EventHorizions.MODID);

    public static final RegistryObject<Item> COBALT_INGOT = register("cobalt_ingot", EVHIngot::new,Simple);
    public static final RegistryObject<Item> STEEL_INGOT = register("steel_ingot", EVHIngot::new,Simple);
    public static final RegistryObject<Item> PULVERISED_COAL = register("pulverised_coal", EVHDust::new,Simple);

    //BlockItems
    public static final RegistryObject<Item> COBALT_ORE = register("cobalt_ore",()->new BlockItem(EVHBlocks.COBALT_ORE.get(),new Item.Properties()),BlockItem);
    public static final RegistryObject<Item> COBALT_BLOCK = register("cobalt_block", ()-> new BlockItem(EVHBlocks.COBALT_BLOCK.get(),new Item.Properties()),BlockItem);
    public static final RegistryObject<Item> STEEL_BLOCK = register("steel_block", ()-> new BlockItem(EVHBlocks.COPPER_BLOCK.get(),new Item.Properties()),BlockItem);
    public static final RegistryObject<Item> GRINDSTONE = register("grindstone", ()-> new BlockItem(EVHBlocks.GRINDSTONE.get(),new Item.Properties()),BlockItem);
    public static final RegistryObject<Item> SIMPLE_ALLOY_FURNACE = register("simple_alloy_furnace",()-> new BlockItem(EVHBlocks.SIMPLE_ALLOY_FURNACE.get(),new Item.Properties()),BlockItem);

    private static RegistryObject<Item> register(String name, Supplier<Item> supplier, EVHModelType type){
        RegistryObject<Item> currentItem = ITEMS.register(name,supplier);
        EVHItemMap.put(name,new ItemEntry(currentItem,type));
        return currentItem;
    }

    public static Set<String> getAllItemIDs(){
       return EVHItemMap.keySet();
    }

    public static ItemEntry getItem(String id){
        return EVHItemMap.get(id);
    }

    public static class ItemEntry{
        public final RegistryObject<Item> Item;
        public final EVHModelType modelType;

        public ItemEntry(RegistryObject<Item> item, EVHModelType modelType) {
            this.Item = item;
            this.modelType = modelType;
        }
    }

}
