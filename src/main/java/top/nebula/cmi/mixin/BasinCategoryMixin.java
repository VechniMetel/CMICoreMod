package top.nebula.cmi.mixin;

import com.simibubi.create.compat.jei.category.BasinCategory;
import com.simibubi.create.content.processing.basin.BasinRecipe;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.foundation.utility.Lang;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BasinCategory.class, remap = false)
public abstract class BasinCategoryMixin {
	@Inject(method = "setRecipe(Lmezz/jei/api/gui/builder/IRecipeLayoutBuilder;Lcom/simibubi/create/content/processing/basin/BasinRecipe;Lmezz/jei/api/recipe/IFocusGroup;)V",
			at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/processing/basin/BasinRecipe;getRequiredHeat()Lcom/simibubi/create/content/processing/recipe/HeatCondition;"),
			remap = false, cancellable = true)
	public void setRecipe(IRecipeLayoutBuilder builder, BasinRecipe recipe, IFocusGroup focuses, CallbackInfo ci) {
		if (recipe.getRequiredHeat() == HeatCondition.valueOf("GRILLED"))
			ci.cancel();
	}

	@Inject(method = "draw(Lcom/simibubi/create/content/processing/basin/BasinRecipe;Lmezz/jei/api/gui/ingredient/IRecipeSlotsView;Lnet/minecraft/client/gui/GuiGraphics;DD)V",
			at = @At("HEAD"), remap = false)
	public void draw(BasinRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY, CallbackInfo ci) {
		if (recipe.getRequiredHeat() == HeatCondition.valueOf("GRILLED")) {
			graphics.drawString(Minecraft.getInstance().font, Lang.translateDirect("cmi.recipe.tip.fire"), 9, 2, recipe.getRequiredHeat().getColor(), false);
		}
	}
}