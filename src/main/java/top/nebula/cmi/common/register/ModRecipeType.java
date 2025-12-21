package top.nebula.cmi.common.register;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import top.nebula.cmi.CMI;
import top.nebula.cmi.common.recipe.accelerator.AcceleratorRecipe;
import top.nebula.cmi.common.recipe.waterpump.WaterPumpRecipe;

import java.util.function.Supplier;

public class ModRecipeType {
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES;

	public static final Supplier<RecipeType<AcceleratorRecipe>> ACCELERATOR;

	public static final Supplier<RecipeType<WaterPumpRecipe>> WATER_PUMP;

	static {
		RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, CMI.MODID);

		ACCELERATOR = RECIPE_TYPES.register("accelerator", () -> {
			return AcceleratorRecipe.Type.INSTANCE;
		});

		WATER_PUMP = RECIPE_TYPES.register("water_pump", () ->  {
			return WaterPumpRecipe.Type.INSTANCE;
		});
	}

	public static void register(IEventBus event) {
		RECIPE_TYPES.register(event);
	}
}