package generalrsb.eventhorizions.registries;

import generalrsb.eventhorizions.EventHorizions;
import generalrsb.eventhorizions.recipes.AlloyFurnaceRecipe;
import generalrsb.eventhorizions.recipes.GrindstoneRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, EventHorizions.MODID);

    public static final RegistryObject<RecipeSerializer<GrindstoneRecipe>> GRINDSTONE_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(GrindstoneRecipe.RecipeID,()->GrindstoneRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<AlloyFurnaceRecipe>> ALLOY_FURNACE_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(AlloyFurnaceRecipe.RecipeID,()->AlloyFurnaceRecipe.Serializer.INSTANCE);
}
