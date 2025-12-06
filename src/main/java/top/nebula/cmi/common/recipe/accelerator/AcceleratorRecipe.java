package top.nebula.cmi.common.recipe.accelerator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
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
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.nebula.cmi.CMI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AcceleratorRecipe implements Recipe<SimpleContainer> {
	private final ResourceLocation id;
	private final Ingredient input;
	private final Block targetBlock;
	private final List<OutputEntry> outputs;

	// 单个输出项(包含方块 + 概率)
	public static class OutputEntry {
		public final Block block;
		public final float chance;

		public OutputEntry(Block block, float chance) {
			this.block = block;
			this.chance = chance;
		}
	}

	public AcceleratorRecipe(ResourceLocation id, Ingredient input, Block targetBlock, List<OutputEntry> outputs) {
		this.id = id;
		this.input = input;
		this.targetBlock = targetBlock;
		this.outputs = outputs;
	}

	public Ingredient getInput() {
		return input;
	}

	public Block getTargetBlock() {
		return targetBlock;
	}

	public List<OutputEntry> getOutputs() {
		return outputs;
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
	public boolean canCraftInDimensions(int w, int h) {
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

	// RecipeType
	public static class Type implements RecipeType<AcceleratorRecipe> {
		public static final Type INSTANCE = new Type();
		public static final String ID = "accelerator";
	}

	// Serializer
	public static class Serializer implements RecipeSerializer<AcceleratorRecipe> {

		public static final Serializer INSTANCE = new Serializer();
		public static final ResourceLocation ID =
				ResourceLocation.fromNamespaceAndPath(CMI.MODID, "accelerator");

		@Override
		public @NotNull AcceleratorRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {

			// input 可为 object 或 array
			Ingredient input;
			if (json.get("input").isJsonArray()) {
				input = Ingredient.fromJson(json.getAsJsonArray("input"));
			} else {
				input = Ingredient.fromJson(json.getAsJsonObject("input"));
			}

			// target
			String targetName = json.get("target").getAsString();
			Block targetBlock = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(targetName));
			Objects.requireNonNull(targetBlock, "Unknown target block: " + targetName);

			// output: [{ id:"xxx", chance:0.5 }, ...]
			List<OutputEntry> outputs = new ArrayList<>();
			JsonArray arr = GsonHelper.getAsJsonArray(json, "output");

			for (JsonElement el : arr) {
				JsonObject obj = el.getAsJsonObject();
				String blockID = obj.get("id").getAsString();
				float chance = GsonHelper.getAsFloat(obj, "chance", 1.0f);

				Block b = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(blockID));
				Objects.requireNonNull(b, "Unknown output block: " + blockID);

				outputs.add(new OutputEntry(b, chance));
			}

			return new AcceleratorRecipe(id, input, targetBlock, outputs);
		}


		@Override
		public @Nullable AcceleratorRecipe fromNetwork(@NotNull ResourceLocation id, @NotNull FriendlyByteBuf buf) {
			Ingredient input = Ingredient.fromNetwork(buf);

			Block target = ForgeRegistries.BLOCKS.getValue(buf.readResourceLocation());
			Objects.requireNonNull(target);

			// 读取输出数量
			int size = buf.readInt();
			List<OutputEntry> outputs = new ArrayList<>();

			for (int i = 0; i < size; i++) {
				Block block = ForgeRegistries.BLOCKS.getValue(buf.readResourceLocation());
				float chance = buf.readFloat();
				outputs.add(new OutputEntry(block, chance));
			}

			return new AcceleratorRecipe(id, input, target, outputs);
		}

		@Override
		public void toNetwork(@NotNull FriendlyByteBuf buf, @NotNull AcceleratorRecipe recipe) {
			recipe.input.toNetwork(buf);

			// target
			buf.writeResourceLocation(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(recipe.targetBlock)));

			// outputs
			buf.writeInt(recipe.outputs.size());
			for (OutputEntry e : recipe.outputs) {
				buf.writeResourceLocation(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(e.block)));
				buf.writeFloat(e.chance);
			}
		}
	}
}