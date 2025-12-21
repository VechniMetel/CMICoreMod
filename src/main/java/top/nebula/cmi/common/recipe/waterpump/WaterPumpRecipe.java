package top.nebula.cmi.common.recipe.waterpump;

import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.data.loadable.common.FluidStackLoadable;

public class WaterPumpRecipe implements Recipe<SimpleContainer> {
    public final ResourceLocation id;
    public final Ingredient input;
    public final FluidStack water;

    public WaterPumpRecipe(ResourceLocation id, Ingredient input,  FluidStack water) {
        this.id = id;
        this.input = input;
        this.water = water;
    }

    @Override
    public boolean matches(SimpleContainer container, Level pLevel) {
        return false;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<WaterPumpRecipe> {
        private Type() {}

        public static final WaterPumpRecipe.Type INSTANCE = new WaterPumpRecipe.Type();
    }

    public static class Serializer implements RecipeSerializer<WaterPumpRecipe> {
        public static final WaterPumpRecipe.Serializer INSTANCE = new WaterPumpRecipe.Serializer();

        @Override
        public @NotNull WaterPumpRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
            FluidStack stack = FluidStackLoadable.REQUIRED_STACK.deserialize(json.getAsJsonObject("water"));
            Ingredient ingredient  = Ingredient.fromJson(json.getAsJsonObject("input"));
            return new WaterPumpRecipe(id, ingredient, stack);
        }

        @Override
        public @Nullable WaterPumpRecipe fromNetwork(@NotNull ResourceLocation id, @NotNull FriendlyByteBuf buf) {
            FluidStack stack = FluidStack.readFromPacket(buf);
            Ingredient ingredient = Ingredient.fromNetwork(buf);
            return new WaterPumpRecipe(id, ingredient, stack);
        }

        @Override
        public void toNetwork(@NotNull FriendlyByteBuf buf, @NotNull WaterPumpRecipe recipe) {
            FluidStack.readFromPacket(buf);
            recipe.input.toNetwork(buf);
        }
    }
}