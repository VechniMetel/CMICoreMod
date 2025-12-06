package top.nebula.cmi.common.register;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import top.nebula.cmi.CMI;
import top.nebula.cmi.common.recipe.accelerator.AcceleratorRecipe;

import java.util.function.Supplier;

public class ModRecipeType {
	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS;
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES;

	public static final Supplier<RecipeType<AcceleratorRecipe>> ACCELERATOR_RECIPE_TYPE;
	public static final Supplier<RecipeSerializer<AcceleratorRecipe>> ACCELERATOR_SERIALIZER;

	static {
		SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, CMI.MODID);
		RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, CMI.MODID);

		ACCELERATOR_RECIPE_TYPE = RECIPE_TYPES.register("accelerator", () -> {
			return AcceleratorRecipe.Type.INSTANCE;
		});

		ACCELERATOR_SERIALIZER = SERIALIZERS.register("accelerator", () -> {
			return AcceleratorRecipe.Serializer.INSTANCE;
		});
	}

	public static void register(IEventBus event) {
		SERIALIZERS.register(event);
		RECIPE_TYPES.register(event);
	}
}