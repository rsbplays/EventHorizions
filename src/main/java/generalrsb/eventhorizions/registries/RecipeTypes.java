package generalrsb.eventhorizions.registries;

import generalrsb.eventhorizions.EventHorizions;
import generalrsb.eventhorizions.recipes.AlloyFurnaceRecipe;
import generalrsb.eventhorizions.recipes.GrindstoneRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeTypes {
    public static DeferredRegister<RecipeType<?>> RECIPETYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, EventHorizions.MODID);

    public static final RegistryObject<RecipeType<GrindstoneRecipe>> GRINDSTONE_RECIPE_TYPE= RECIPETYPES.register(GrindstoneRecipe.RecipeID,()->GrindstoneRecipe.Type.INSTANCE);
    public static final RegistryObject<RecipeType<AlloyFurnaceRecipe>> ALLOY_FURNACE_RECIPE_TYPE= RECIPETYPES.register(AlloyFurnaceRecipe.RecipeID,()->AlloyFurnaceRecipe.Type.INSTANCE);

}
