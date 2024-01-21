package generalrsb.eventhorizions.recipes;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import generalrsb.eventhorizions.EventHorizions;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class GrindstoneRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public static final String RecipeID = "grindstone_recipe";

    public GrindstoneRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleContainer sc, Level p_44003_) {
        return recipeItems.get(0).test(sc.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer p_44001_, RegistryAccess p_267165_) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return output.copy();
    }
    public ItemStack getResultItem(){
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }


    public static class Type implements RecipeType<GrindstoneRecipe> {
        private Type(){}
        public static final Type INSTANCE = new Type();
        public static final String ID = RecipeID;
    }

    public static class Serializer implements RecipeSerializer<GrindstoneRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(EventHorizions.MODID,RecipeID);

        @Override
        public GrindstoneRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(json,"output"))),GsonHelper.getAsInt(json,"amount"));

            String ingredientID = GsonHelper.getAsString(json,"ingredient");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            inputs.set(0,Ingredient.of(BuiltInRegistries.ITEM.getOptional(ResourceLocation.tryParse(ingredientID)).orElseThrow(() -> {
                return new JsonSyntaxException("Unknown item '" + ingredientID + "'");
            })));


            return new GrindstoneRecipe(id, output, inputs);
        }

        @Override
        public GrindstoneRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new GrindstoneRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, GrindstoneRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }

    }
}
