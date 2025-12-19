package top.nebula.cmi.compat.jei.category.multiblock;

import blusunrize.immersiveengineering.common.blocks.wooden.TreatedWoodStyles;
import blusunrize.immersiveengineering.common.register.IEBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import cpw.mods.util.Lazy;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import top.nebula.cmi.common.register.ModBlocks;

public class WaterPumpMulti extends AnimatedKinetics {

    @Override
    public void draw(@NotNull GuiGraphics graphics, int xOffset, int yOffset) {
        PoseStack matrixStack = graphics.pose();
        matrixStack.pushPose();
        matrixStack.translate((float) xOffset, (float) yOffset, 100.0F);
        matrixStack.mulPose(Axis.XP.rotationDegrees(-15.5F));
        matrixStack.mulPose(Axis.YP.rotationDegrees(22.5F));

        int scale = 15;
        this.blockElement(ModBlocks.WATER_PUMP.get().defaultBlockState())
                .atLocal((double) 0.0F, (double) 4.0F, (double) 0.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
                .atLocal((double) 1.0F, (double) 4.0F, (double) -1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
                .atLocal((double) 1.0F, (double) 4.0F, (double) 0.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
                .atLocal((double) 1.0F, (double) 4.0F, (double) 1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
                .atLocal((double) 0.0F, (double) 4.0F, (double) -1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
                .atLocal((double) 0.0F, (double) 4.0F, (double) 1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
                .atLocal((double) -1.0F, (double) 4.0F, (double) -1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
                .atLocal((double) -1.0F, (double) 4.0F, (double) 0.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).defaultBlockState())
                .atLocal((double) -1.0F, (double) 4.0F, (double) 1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
                .atLocal((double) 1.0F, (double) 3.0F, (double) 1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
                .atLocal((double) 1.0F, (double) 3.0F, (double) -1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
                .atLocal((double) -1.0F, (double) 3.0F, (double) 1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
                .atLocal((double) -1.0F, (double) 3.0F, (double) -1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
                .atLocal((double) 1.0F, (double) 2.0F, (double) 1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
                .atLocal((double) 1.0F, (double) 2.0F, (double) -1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
                .atLocal((double) -1.0F, (double) 2.0F, (double) 1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_FENCE.get().defaultBlockState())
                .atLocal((double) -1.0F, (double) 2.0F, (double) -1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_SCAFFOLDING.get().defaultBlockState())
                .atLocal((double) -1.0F, (double) 1.0F, (double) -1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_SCAFFOLDING.get().defaultBlockState())
                .atLocal((double) -1.0F, (double) 1.0F, (double) 1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_SCAFFOLDING.get().defaultBlockState())
                .atLocal((double) 1.0F, (double) 1.0F, (double) -1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.WoodenDecoration.TREATED_SCAFFOLDING.get().defaultBlockState())
                .atLocal((double) 1.0F, (double) 1.0F, (double) 1.0F)
                .scale((double) scale)
                .render(graphics);
        this.blockElement(IEBlocks.TO_SLAB.get(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL)).defaultBlockState());
    }
}
