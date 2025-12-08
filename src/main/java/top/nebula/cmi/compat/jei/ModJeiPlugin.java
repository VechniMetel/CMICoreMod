package top.nebula.cmi.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;
import top.nebula.cmi.CMI;
import top.nebula.cmi.common.recipe.accelerator.AcceleratorRecipe;
import top.nebula.cmi.compat.jei.category.AcceleratorCategory;

import java.util.List;

@JeiPlugin
public class ModJeiPlugin implements IModPlugin {
	@Override
	public @NotNull ResourceLocation getPluginUid() {
		return CMI.loadResource("jei_plugin");
	}

	@Override
	public void registerCategories(@NotNull IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new AcceleratorCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

		List<AcceleratorRecipe> acceleratorRecipe =
				recipeManager.getAllRecipesFor(AcceleratorRecipe.Type.INSTANCE);

		registration.addRecipes(AcceleratorCategory.ACCELERATOR_TYPE, acceleratorRecipe);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(
				new ItemStack(AcceleratorCategory.ACCELERATOR_Item.get()),
				AcceleratorCategory.ACCELERATOR_TYPE
		);
	}
}