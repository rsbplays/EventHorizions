package generalrsb.eventhorizions.registries;

import generalrsb.eventhorizions.EventHorizions;
import generalrsb.eventhorizions.blocks.machines.SimpleAlloyFurnace;
import generalrsb.eventhorizions.gui.SimpleAlloyFurnaceContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, EventHorizions.MODID);

    public static final RegistryObject<MenuType<SimpleAlloyFurnaceContainerMenu>> SIMPLE_ALLOY_FURNACE_MENU= MENU_TYPES.register("simple_alloy_furnace_menu",()->{
        return IForgeMenuType.create(SimpleAlloyFurnaceContainerMenu::new);
    });
}
