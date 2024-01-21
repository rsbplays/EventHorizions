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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.HashSet;

public class AlloyFurnaceRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public static HashSet<String> validKeys = new HashSet<>();

    public static final String RecipeID = "alloy_furnace_recipe";

    public AlloyFurnaceRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;

        for (Ingredient ingredient:recipeItems){
            for (ItemStack itemStack:ingredient.getItems()){
                if (!validKeys.contains(itemStack.getItem().builtInRegistryHolder().key().location().toString())){
                    validKeys.add(itemStack.getItem().builtInRegistryHolder().key().location().toString());
                    System.out.println(itemStack.getItem().builtInRegistryHolder().key().location().toString());
                }
            }
        }
    }

    @Override
    public boolean matches(SimpleContainer sc, Level p_44003_) {
        if (recipeItems.get(0).test(sc.getItem(0))||recipeItems.get(0).test(sc.getItem(1))){
            if (recipeItems.get(1).test(sc.getItem(0))||recipeItems.get(1).test(sc.getItem(1))) return true;
        }
        return false;
    }

    @Override
    public ItemStack assemble(SimpleContainer p_44001_, RegistryAccess p_267165_) {
        return output.copy();
    }

    public static boolean isValid(ItemStack itemStack){
        if (validKeys.contains(itemStack.getItem().builtInRegistryHolder().key().location().toString())){
            return true;
        }
        return false;
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


    public static class Type implements RecipeType<AlloyFurnaceRecipe> {
        private Type(){}
        public static final Type INSTANCE = new Type();
        public static final String ID = RecipeID;
    }

    public static class Serializer implements RecipeSerializer<AlloyFurnaceRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(EventHorizions.MODID,RecipeID);

        @Override
        public AlloyFurnaceRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(json,"output"))),GsonHelper.getAsInt(json,"amount"));
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            String ingredientID1 = GsonHelper.getAsString(json,"ingredient1");
            inputs.set(0,Ingredient.of(BuiltInRegistries.ITEM.getOptional(ResourceLocation.tryParse(ingredientID1)).orElseThrow(() -> {
                return new JsonSyntaxException("Unknown item '" + ingredientID1 + "'");
            })));
            String ingredientID2 = GsonHelper.getAsString(json,"ingredient2");
            inputs.set(1,Ingredient.of(BuiltInRegistries.ITEM.getOptional(ResourceLocation.tryParse(ingredientID2)).orElseThrow(() -> {
                return new JsonSyntaxException("Unknown item '" + ingredientID2 + "'");
            })));
            return new AlloyFurnaceRecipe(id, output, inputs);
        }

        @Override
        public AlloyFurnaceRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new AlloyFurnaceRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, AlloyFurnaceRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }

    }
}
