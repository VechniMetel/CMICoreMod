package top.nebula.cmi.compat.jei.category.multiblock;

import blusunrize.immersiveengineering.common.blocks.wooden.TreatedWoodStyles;
import blusunrize.immersiveengineering.common.register.IEBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import top.nebula.cmi.common.register.ModBlocks;

public class WaterPumpMultiblock extends AnimatedKinetics {
	private static final Lazy<Block> STAIRS = Lazy.of(() -> {
		return ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse("immersiveengineering:stairs_treated_wood_horizontal"));
	});

	@Override
	public void draw(@NotNull GuiGraphics graphics, int xOffset, int yOffset) {
		PoseStack matrixStack = graphics.pose();
		matrixStack.pushPose();
		matrixStack.translate(xOffset, yOffset, 100.0F);
		matrixStack.mulPose(Axis.XP.rotationDegrees(-15.5F));
		matrixStack.mulPose(Axis.YP.rotationDegrees(22.5F));

		int scale = 15;
		this.blockElement(ModBlocks.WATER_PUMP.get().defaultBlockState())
				.atLocal(0.0F, 4.0F, 0.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
				.atLocal(1.0F, 4.0F, -1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
				.atLocal(1.0F, 4.0F, 0.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
				.atLocal(1.0F, 4.0F, 1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
				.atLocal(0.0F, 4.0F, -1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
				.atLocal(0.0F, 4.0F, 1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
				.atLocal(-1.0F, 4.0F, -1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
				.atLocal(-1.0F, 4.0F, 0.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
				.atLocal(-1.0F, 4.0F, 1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
				.atLocal(1.0F, 3.0F, 1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
				.atLocal(1.0F, 3.0F, -1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
				.atLocal(-1.0F, 3.0F, 1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
				.atLocal(-1.0F, 3.0F, -1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
				.atLocal(1.0F, 2.0F, 1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
				.atLocal(1.0F, 2.0F, -1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
				.atLocal(-1.0F, 2.0F, 1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
				.atLocal(-1.0F, 2.0F, -1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_SCAFFOLDING.get().defaultBlockState())
				.atLocal(-1.0F, 1.0F, -1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_SCAFFOLDING.get().defaultBlockState())
				.atLocal(-1.0F, 1.0F, 1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_SCAFFOLDING.get().defaultBlockState())
				.atLocal(1.0F, 1.0F, -1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(IEBlocks.WoodenDecoration.TREATED_SCAFFOLDING.get().defaultBlockState())
				.atLocal(1.0F, 1.0F, 1.0F)
				.scale(scale)
				.render(graphics);
		this.blockElement(STAIRS.get().defaultBlockState())
				.atLocal(1.0F, 1.0F, 1.0F)
				.scale(scale)
				.render(graphics);
	}
}
