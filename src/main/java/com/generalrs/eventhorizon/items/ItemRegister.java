package com.generalrs.eventhorizon.items;

import com.generalrs.eventhorizon.EventHorizon;
import com.generalrs.eventhorizon.blocks.BlocksRegister;
import com.generalrs.eventhorizon.items.advanced.TitaniumOre;
import com.generalrs.eventhorizon.items.materials.titanium.TitaniumTier;
import com.generalrs.eventhorizon.items.sets.dragonscale.DragonScaleArmour;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegister {
    FMLJavaModLoadingContext ctx;
    public static DeferredRegister<Item> itemsRegister = DeferredRegister.create(ForgeRegistries.ITEMS, EventHorizon.MODID);

    public static Item dragon_scale;
    public static RegistryObject<Item> TITANIUM_INGOT = itemsRegister.register("titanium_ingot",()->{return new ItemBase(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS),"titanium_ingot");});
    public static RegistryObject<Item> TITANIUM_PICKAXE = itemsRegister.register("titanium_pickaxe",()->{return new PickaxeItem(new TitaniumTier(),1,-2.1F,new Item.Properties().tab(CreativeModeTab.TAB_TOOLS));});
    public ItemRegister(FMLJavaModLoadingContext ctx){
        this.ctx=ctx;
    }

    public void register(){
        registerItems();
        itemsRegister.register(ctx.getModEventBus());
    }

    public void registerItems(){

        //End items
        //Ender Dragon items
        itemsRegister.register("ender_dragon_scale",()->{
            dragon_scale=new ItemBase(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS),"ender_dragon_scale");
            return dragon_scale;
        });

        itemsRegister.register("enderscale_chestplate",()->{
            Item.Properties properties = new Item.Properties();
            properties.tab(CreativeModeTab.TAB_MISC);
            DragonScaleArmour item = new DragonScaleArmour(DragonScaleArmour.getMaterial1(), EquipmentSlot.CHEST,properties.tab(CreativeModeTab.TAB_COMBAT));
            return item;
        });

        itemsRegister.register("enderscale_legs",()->{
            Item.Properties properties = new Item.Properties();
            properties.tab(CreativeModeTab.TAB_MISC);
            DragonScaleArmour item = new DragonScaleArmour(DragonScaleArmour.getMaterial1(), EquipmentSlot.LEGS,properties.tab(CreativeModeTab.TAB_COMBAT));
            return item;
        });

        itemsRegister.register("enderscale_boots",()->{
            Item.Properties properties = new Item.Properties();
            properties.tab(CreativeModeTab.TAB_MISC);
            DragonScaleArmour item = new DragonScaleArmour(DragonScaleArmour.getMaterial1(), EquipmentSlot.FEET,properties.tab(CreativeModeTab.TAB_COMBAT));
            return item;
        });

        itemsRegister.register("enderscale_helmet",()->{
            Item.Properties properties = new Item.Properties();
            properties.tab(CreativeModeTab.TAB_MISC);
            DragonScaleArmour item = new DragonScaleArmour(DragonScaleArmour.getMaterial1(), EquipmentSlot.HEAD,properties.tab(CreativeModeTab.TAB_COMBAT));
            return item;
        });

        itemsRegister.register("titanium_ore",()->{
            Item.Properties properties = new Item.Properties();
            return new TitaniumOre(BlocksRegister.TITANIUM_ORE.get(),properties.tab(CreativeModeTab.TAB_BUILDING_BLOCKS));
        });

    }
    public void LootTableDataGen(){

    }
}
