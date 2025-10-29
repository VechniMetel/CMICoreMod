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
import vazkii.patchouli.api.IMultiblock;

import java.util.List;

public class WaterPumpBlockEntity extends BlockEntity implements IHaveGoggleInformation {

    private static final Lazy<Fluid> SEA_WATER = Lazy.of(() -> BuiltInRegistries.FLUID.get(ResourceLocation.fromNamespaceAndPath(CMI.MODID, "sea_water")));

    private final IFluidHandler fluidHandler = new IFluidHandler() {

        @Override
        public int getTanks() {
            return 1;
        }

        @Override
        public @NotNull FluidStack getFluidInTank(int i) {
            if (defineStructure()) {
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
            if (defineStructure()) {
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
            if (defineStructure()) {
                if (isOcean()) {
                    return new FluidStack(SEA_WATER.get(), i);
                }
                return new FluidStack(Fluids.WATER, i);
            }
            return FluidStack.EMPTY;
        }
    };

    private boolean defineStructure() {
        ResourceLocation stairs =
                ResourceLocation.parse("immersiveengineering:stairs_treated_wood_horizontal");
        IMultiblock defineStructure = new MultiblockStructureBuilder(new String[][]{
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
                .where('F', BuiltInRegistries.BLOCK.get(stairs)
                        .defaultBlockState()
                        .setValue(StairBlock.FACING, Direction.SOUTH)
                        .setValue(StairBlock.HALF, Half.TOP)
                        .setValue(StairBlock.SHAPE, StairsShape.STRAIGHT))
                // 西边楼梯(左边), 朝东
                .where('G', BuiltInRegistries.BLOCK.get(stairs)
                        .defaultBlockState()
                        .setValue(StairBlock.FACING, Direction.EAST)
                        .setValue(StairBlock.HALF, Half.TOP)
                        .setValue(StairBlock.SHAPE, StairsShape.STRAIGHT))
                // 东边楼梯(右边), 朝西
                .where('H', BuiltInRegistries.BLOCK.get(stairs)
                        .defaultBlockState()
                        .setValue(StairBlock.FACING, Direction.WEST)
                        .setValue(StairBlock.HALF, Half.TOP)
                        .setValue(StairBlock.SHAPE, StairsShape.STRAIGHT))
                // 南边楼梯(下方), 朝北
                .where('I', BuiltInRegistries.BLOCK.get(stairs)
                        .defaultBlockState()
                        .setValue(StairBlock.FACING, Direction.NORTH)
                        .setValue(StairBlock.HALF, Half.TOP)
                        .setValue(StairBlock.SHAPE, StairsShape.STRAIGHT))
                .build();

        defineStructure.validate(level, worldPosition);

        return false;
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
        if (defineStructure()) {
            ModLang.builder().translate("tooltip.water_pump.functional").forGoggles(tooltip);
        } else {
            ModLang.builder().translate("tooltip.water_pump.non_functional").forGoggles(tooltip);
        }
        return true;
    }
}