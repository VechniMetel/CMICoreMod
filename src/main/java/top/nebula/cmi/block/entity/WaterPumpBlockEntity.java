package top.nebula.cmi.block.entity;

import blusunrize.immersiveengineering.common.blocks.wooden.TreatedWoodStyles;
import blusunrize.immersiveengineering.common.register.IEBlocks;
import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import top.nebula.cmi.CMI;
import top.nebula.cmi.block.ModBlocks;
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
import top.nebula.cmi.util.MultiblockStructureBuilder;
import top.nebula.cmi.util.PropertyImmutableMap;
import vazkii.patchouli.api.IMultiblock;

import java.util.List;

public class WaterPumpBlockEntity extends BlockEntity implements IHaveGoggleInformation {

	private static final Lazy<Fluid> SEA_WATER = Lazy.of(() -> {
		return BuiltInRegistries.FLUID.get(ResourceLocation.fromNamespaceAndPath(CMI.MODID, "sea_water"));
	});

	private static final ResourceLocation STAIRS = ResourceLocation.parse("immersiveengineering:stairs_treated_wood_horizontal");

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
						// 木板 + 水泵
						"AAA",
						"A0A",
						"AAA"
				}
		})
				.where('A', IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).get())
				.where('0', ModBlocks.WATER_PUMP.get())
				.where('C', IEBlocks.WoodenDecoration.TREATED_FENCE.get())
				.where('D', IEBlocks.WoodenDecoration.TREATED_SCAFFOLDING.get())
				// 北边楼梯(上方), 朝南
//            .where('F', BuiltInRegistries.BLOCK.get(STRUCTURE_ID)
//                    .defaultBlockState()
//                    .setValue(StairBlock.FACING, Direction.SOUTH)
//                    .setValue(StairBlock.HALF, Half.TOP)
//                    .setValue(StairBlock.SHAPE, StairsShape.STRAIGHT))
				.where('F', BuiltInRegistries.BLOCK.get(STAIRS), PropertyImmutableMap.create()
						.add(StairBlock.FACING, Direction.SOUTH)
						.add(StairBlock.HALF, Half.TOP)
						.add(StairBlock.SHAPE, StairsShape.STRAIGHT)
						.build())
				// 西边楼梯(左边), 朝东
//            .where('G', BuiltInRegistries.BLOCK.get(STRUCTURE_ID)
//                    .defaultBlockState()
//                    .setValue(StairBlock.FACING, Direction.EAST)
//                    .setValue(StairBlock.HALF, Half.TOP)
//                    .setValue(StairBlock.SHAPE, StairsShape.STRAIGHT))
				.where('G', BuiltInRegistries.BLOCK.get(STAIRS), PropertyImmutableMap.create()
						.add(StairBlock.FACING, Direction.EAST)
						.add(StairBlock.HALF, Half.TOP)
						.add(StairBlock.SHAPE, StairsShape.STRAIGHT)
						.build())
				// 东边楼梯(右边), 朝西
//            .where('H', BuiltInRegistries.BLOCK.get(STRUCTURE_ID)
//                    .defaultBlockState()
//                    .setValue(StairBlock.FACING, Direction.WEST)
//                    .setValue(StairBlock.HALF, Half.TOP)
//                    .setValue(StairBlock.SHAPE, StairsShape.STRAIGHT))
				.where('H', BuiltInRegistries.BLOCK.get(STAIRS), PropertyImmutableMap.create()
						.add(StairBlock.FACING, Direction.WEST)
						.add(StairBlock.HALF, Half.TOP)
						.add(StairBlock.SHAPE, StairsShape.STRAIGHT)
						.build())
				// 南边楼梯(下方), 朝北
//            .where('I', BuiltInRegistries.BLOCK.get(STRUCTURE_ID)
//                    .defaultBlockState()
//                    .setValue(StairBlock.FACING, Direction.NORTH)
//                    .setValue(StairBlock.HALF, Half.TOP)
//                    .setValue(StairBlock.SHAPE, StairsShape.STRAIGHT))
				.where('I', BuiltInRegistries.BLOCK.get(STAIRS), PropertyImmutableMap.create()
						.add(StairBlock.FACING, Direction.NORTH)
						.add(StairBlock.HALF, Half.TOP)
						.add(StairBlock.SHAPE, StairsShape.STRAIGHT)
						.build())
				.build();
	});

	// 缓存结构状态
	private Boolean structureValid = null;

	private final IFluidHandler fluidHandler = new IFluidHandler() {

		@Override
		public int getTanks() {
			return 1;
		}

		@Override
		public @NotNull FluidStack getFluidInTank(int i) {
			if (isStructureValid()) {
				if (isOcean()) return new FluidStack(SEA_WATER.get(), Integer.MAX_VALUE);
				return new FluidStack(Fluids.WATER, Integer.MAX_VALUE);
			}
			return FluidStack.EMPTY;
		}

		@Override
		public int getTankCapacity(int i) {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isFluidValid(int i, @NotNull FluidStack fluidStack) {
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

	// 外部可调用的方法，判断结构是否完整
	public boolean isStructureValid() {
		// 第一次调用时刷新
		if (structureValid == null) {
			refreshStructureStatus();
		}
		return structureValid;
	}

	// 刷新结构状态
	public void refreshStructureStatus() {
		if (level == null) {
			structureValid = false;
			return;
		}
		structureValid = true;
		try {
			// validate可能是void，这里只是触发Patchouli内部检查
			STRUCTURE.get().validate(level, worldPosition);
			// 如果需要，你可以用Patchouli的 targets 来手动判断
		} catch (Exception e) {
			structureValid = false;
		}
	}

	private boolean isOcean() {
		if (this.level != null) {
			return this.level.getBiome(this.getBlockPos()).is(BiomeTags.IS_OCEAN) && this.getBlockPos().getY() == 62;
		}
		return false;
	}

	public WaterPumpBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntityTypes.WATER_PUMP.get(), pPos, pBlockState);
	}

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if (cap == ForgeCapabilities.FLUID_HANDLER) {
			return LazyOptional.of(() -> fluidHandler).cast();
		}
		return super.getCapability(cap, side);
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