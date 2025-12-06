package top.nebula.cmi.common.recipe.accelerator;

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
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.nebula.cmi.CMI;

import java.util.Objects;

public class AcceleratorRecipe implements Recipe<SimpleContainer> {

	private final ResourceLocation id;
	private final Ingredient input;
	private final Block targetBlock;
	private final Block outputBlock;

	public AcceleratorRecipe(ResourceLocation id, Ingredient input, Block targetBlock, Block outputBlock) {
		this.id = id;
		this.input = input;
		this.targetBlock = targetBlock;
		this.outputBlock = outputBlock;
	}

	@Override
	public boolean matches(@NotNull SimpleContainer container, @NotNull Level level) {
		return input.test(container.getItem(0));
	}

	@Override
	public @NotNull ItemStack assemble(@NotNull SimpleContainer container, @NotNull RegistryAccess access) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}

	@Override
	public @NotNull ItemStack getResultItem(@NotNull RegistryAccess access) {
		return ItemStack.EMPTY;
	}

	@Override
	public @NotNull ResourceLocation getId() {
		return id;
	}

	@Override
	public @NotNull RecipeSerializer<?> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public @NotNull RecipeType<?> getType() {
		return Type.INSTANCE;
	}

	public Ingredient getInput() {
		return input;
	}

	public Block getTargetBlock() {
		return targetBlock;
	}

	public Block getOutputBlock() {
		return outputBlock;
	}

	public static class Type implements RecipeType<AcceleratorRecipe> {
		private Type() {
		}

		public static final Type INSTANCE = new Type();
		public static final String ID = "accelerator";
	}

	public static class Serializer implements RecipeSerializer<AcceleratorRecipe> {
		public static final Serializer INSTANCE = new Serializer();
		public static final ResourceLocation ID =
				ResourceLocation.fromNamespaceAndPath(CMI.MODID, "accelerator");

		@Override
		public @NotNull AcceleratorRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
			Ingredient input = Ingredient.fromJson(json.getAsJsonObject("input"));

			String targetName = json.get("target").getAsString();
			Block targetBlock = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(targetName));
			Objects.requireNonNull(targetBlock, "Unknown target block: " + targetName);

			String outputName = json.get("output").getAsString();
			Block outputBlock = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(outputName));
			Objects.requireNonNull(outputBlock, "Unknown output block: " + outputName);

			return new AcceleratorRecipe(id, input, targetBlock, outputBlock);
		}


		@Override
		public @Nullable AcceleratorRecipe fromNetwork(@NotNull ResourceLocation id, @NotNull FriendlyByteBuf buf) {
			Ingredient input = Ingredient.fromNetwork(buf);
			Block targetBlock = ForgeRegistries.BLOCKS.getValue(buf.readResourceLocation());
			Block outputBlock = ForgeRegistries.BLOCKS.getValue(buf.readResourceLocation());
			return new AcceleratorRecipe(id, input, targetBlock, outputBlock);
		}

		@Override
		public void toNetwork(@NotNull FriendlyByteBuf buf, @NotNull AcceleratorRecipe recipe) {
			recipe.input.toNetwork(buf);
			buf.writeResourceLocation(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(recipe.targetBlock)));
			buf.writeResourceLocation(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(recipe.outputBlock)));
		}

	}
}