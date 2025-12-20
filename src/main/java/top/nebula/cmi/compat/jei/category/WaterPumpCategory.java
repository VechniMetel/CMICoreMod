package top.nebula.cmi.compat.jei.category;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import top.nebula.cmi.CMI;
import top.nebula.cmi.common.recipe.waterpump.WaterPumpRecipe;
import top.nebula.cmi.common.register.ModBlocks;
import top.nebula.cmi.compat.jei.category.multiblock.WaterPumpMultiblock;

public class WaterPumpCategory implements IRecipeCategory<WaterPumpRecipe> {
	private final IDrawable background;
	private final IDrawable icon;
	private final WaterPumpMultiblock waterPump = new WaterPumpMultiblock();

	private static final Lazy<Fluid> SEA_WATER = Lazy.of(() -> {
		return ForgeRegistries.FLUIDS.getValue(CMI.loadResource("sea_water"));
	});

	public static final RecipeType<WaterPumpRecipe> WATER_PUMP_TYPE =
			new RecipeType<>(CMI.loadResource("water_pump"), WaterPumpRecipe.class);

	public WaterPumpCategory(IGuiHelper helper) {
		this.background = helper.createBlankDrawable(0, 0);
		this.icon = helper.createDrawableItemStack(ModBlocks.WATER_PUMP.get().asItem().getDefaultInstance());
	}

	@Override
	public @NotNull RecipeType<WaterPumpRecipe> getRecipeType() {
		return WATER_PUMP_TYPE;
	}

	@Override
	public @NotNull Component getTitle() {
		return Component.translatable("jei.category.cmi.water_pump");
	}

	@Override
	public @NotNull IDrawable getBackground() {
		return this.background;
	}

	@Override
	public @NotNull IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public int getWidth() {
		return 178;
	}

	@Override
	public int getHeight() {
		return 72;
	}

	@Override
	public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull WaterPumpRecipe recipe, @NotNull IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.OUTPUT, 140, 25)
				.setBackground(CreateRecipeCategory.getRenderedSlot(), -1, -1)
				.addFluidStack(SEA_WATER.get(), Integer.MAX_VALUE);
	}

	@Override // emmm...我没搞明白如何手动添加一个draw(大格子)
	public void draw(@NotNull WaterPumpRecipe recipe, @NotNull IRecipeSlotsView view, @NotNull GuiGraphics graphics, double mouseX, double mouseY) {
		AllGuiTextures.JEI_ARROW.render(graphics, 90, 30);
		this.waterPump.draw(graphics, 40, 5);

		PoseStack pose = graphics.pose();

		pose.popPose();
	}
}