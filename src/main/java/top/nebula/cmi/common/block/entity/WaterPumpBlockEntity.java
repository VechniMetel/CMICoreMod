package top.nebula.cmi.common.block.entity;

import blusunrize.immersiveengineering.common.blocks.wooden.TreatedWoodStyles;
import blusunrize.immersiveengineering.common.register.IEBlocks;
import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import top.nebula.cmi.CMI;
import top.nebula.cmi.common.register.ModBlockEntityTypes;
import top.nebula.cmi.common.register.ModBlocks;
import top.nebula.cmi.util.ModLang;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.nebula.utils.multiblock.MultiblockStructureBuilder;
import top.nebula.utils.multiblock.PropertyImmutableMap;
import vazkii.patchouli.api.IMultiblock;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.List;

public class WaterPumpBlockEntity extends BlockEntity implements IHaveGoggleInformation {
	public WaterPumpBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntityTypes.WATER_PUMP.get(), pos, state);
	}

	private static final Lazy<Fluid> SEA_WATER = Lazy.of(() -> {
		return BuiltInRegistries.FLUID.get(CMI.loadResource("sea_water"));
	});

	private static final ResourceLocation STAIRS =
			ResourceLocation.parse("immersiveengineering:stairs_treated_wood_horizontal");

	private static final Lazy<IMultiblock> STRUCTURE = Lazy.of(() -> {
		return new MultiblockStructureBuilder(new String[][]{
				{
						// 四个角为脚手架, 四边为楼梯, 中心镂空
						"DFD",
						"G H",
						"DID"
				},
				{
						// 木栅栏
						"C C",
						"   ",
						"C C"
				},
				{
						// 木栅栏
						"C C",
						"   ",
						"C C"
				},
				{
						// 木板 + 水泵
						"AAA",
						"A0A",
						"AAA"
				}
		})
				.define('A', IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).get())
				.define('0', ModBlocks.WATER_PUMP.get())
				.define('C', IEBlocks.WoodenDecoration.TREATED_FENCE.get())
				.define('D', IEBlocks.WoodenDecoration.TREATED_SCAFFOLDING.get())
				.define(' ', PatchouliAPI.get().anyMatcher())
				// 北边楼梯(上方), 朝南
				.define('F', BuiltInRegistries.BLOCK.get(STAIRS), PropertyImmutableMap.create()
						.add(StairBlock.FACING, Direction.WEST)
						.add(StairBlock.HALF, Half.TOP)
						.add(StairBlock.SHAPE, StairsShape.STRAIGHT)
						.build())
				// 西边楼梯(左边), 朝东
				.define('G', BuiltInRegistries.BLOCK.get(STAIRS), PropertyImmutableMap.create()
						.add(StairBlock.FACING, Direction.NORTH)
						.add(StairBlock.HALF, Half.TOP)
						.add(StairBlock.SHAPE, StairsShape.STRAIGHT)
						.build())
				// 东边楼梯(右边), 朝西
				.define('H', BuiltInRegistries.BLOCK.get(STAIRS), PropertyImmutableMap.create()
						.add(StairBlock.FACING, Direction.SOUTH)
						.add(StairBlock.HALF, Half.TOP)
						.add(StairBlock.SHAPE, StairsShape.STRAIGHT)
						.build())
				// 南边楼梯(下方), 朝北
				.define('I', BuiltInRegistries.BLOCK.get(STAIRS), PropertyImmutableMap.create()
						.add(StairBlock.FACING, Direction.EAST)
						.add(StairBlock.HALF, Half.TOP)
						.add(StairBlock.SHAPE, StairsShape.STRAIGHT)
						.build())
				.build();
	});

	private final IFluidHandler fluidHandler = new IFluidHandler() {
		@Override
		public int getTanks() {
			return 1;
		}

		@Override
		public @NotNull FluidStack getFluidInTank(int amount) {
			if (isStructureValid()) {
				if (isOcean()) {
					return new FluidStack(SEA_WATER.get(), Integer.MAX_VALUE);
				}
				return new FluidStack(Fluids.WATER, Integer.MAX_VALUE);
			}
			return FluidStack.EMPTY;
		}

		@Override
		public int getTankCapacity(int amount) {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isFluidValid(int amount, @NotNull FluidStack fluidStack) {
			return false;
		}

		@Override
		public int fill(FluidStack fluidStack, FluidAction fluidAction) {
			return 0;
		}

		@Override
		public @NotNull FluidStack drain(FluidStack fluidStack, FluidAction fluidAction) {
			if (isStructureValid()) {
				if (isOcean()) {
					if (fluidStack.getFluid() == SEA_WATER.get()) {
						return fluidStack;
					}
				} else if (fluidStack.getFluid() == Fluids.WATER) {
					return fluidStack;
				}
				return FluidStack.EMPTY;
			}
			return FluidStack.EMPTY;
		}

		@Override
		public @NotNull FluidStack drain(int i, FluidAction fluidAction) {
			if (isStructureValid()) {
				if (isOcean()) {
					return new FluidStack(SEA_WATER.get(), i);
				}
				return new FluidStack(Fluids.WATER, i);
			}
			return FluidStack.EMPTY;
		}
	};

	private boolean isShowMultiblock = false;

	private boolean isShowMultiblock() {
		if (isStructureValid()) {
			isShowMultiblock = false;
		} else {
			isShowMultiblock = !isShowMultiblock;
		}
		return isShowMultiblock;
	}

	/**
	 * 显示结构
	 * 由于客户端渲染因为某些不可抗因素 需要Y轴下沉一格
	 */
	public void showMultiblock() {
		if (level != null && !level.isClientSide) {
			return;
		}
		if (isShowMultiblock()) {
			PatchouliAPI.get().showMultiblock(
					STRUCTURE.get(),
					Component.translatable("multiblock.building." + CMI.MODID + ".water_pump"),
					worldPosition.offset(0, -1, 0),
					Rotation.NONE
			);
		} else {
			// 清理掉 所有 显示结构
			PatchouliAPI.get().clearMultiblock();
		}
	}

	// 外部可调用的方法，判断结构是否完整
	public boolean isStructureValid() {
		return STRUCTURE.get().validate(level, worldPosition) != null;
	}

	private boolean isOcean() {
		if (this.level != null) {
			return this.level.getBiome(this.getBlockPos()).is(BiomeTags.IS_OCEAN) && this.getBlockPos().getY() == 62;
		}
		return false;
	}

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
		if (capability == ForgeCapabilities.FLUID_HANDLER) {
			return LazyOptional.of(() -> {
				return fluidHandler;
			}).cast();
		}
		return super.getCapability(capability, side);
	}

	@Override
	public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
		if (isStructureValid()) {
			ModLang.builder()
					.translate("tooltip.water_pump.functional")
					.forGoggles(tooltip);
		} else {
			ModLang.builder()
					.translate("tooltip.water_pump.non_functional")
					.forGoggles(tooltip);
		}
		return true;
	}
}