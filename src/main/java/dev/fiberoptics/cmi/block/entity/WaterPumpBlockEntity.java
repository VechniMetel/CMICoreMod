package dev.fiberoptics.cmi.block.entity;

import blusunrize.immersiveengineering.common.blocks.wooden.TreatedWoodStyles;
import blusunrize.immersiveengineering.common.register.IEBlocks;
import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import dev.fiberoptics.cmi.CMI;
import dev.fiberoptics.cmi.util.ModLang;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WaterPumpBlockEntity extends BlockEntity implements IHaveGoggleInformation {

    private static final Lazy<Fluid> SEA_WATER = Lazy.of(() -> BuiltInRegistries.FLUID.get(ResourceLocation.fromNamespaceAndPath(CMI.MODID,"sea_water")));

    private final IFluidHandler fluidHandler = new IFluidHandler() {

        @Override
        public int getTanks() {
            return 1;
        }

        @Override
        public @NotNull FluidStack getFluidInTank(int i) {
            if(testStructure()) {
                if(isOcean()) return new FluidStack(SEA_WATER.get(), Integer.MAX_VALUE);
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
            if(testStructure()) {
                if(isOcean()) {
                    if(fluidStack.getFluid() == SEA_WATER.get()) return fluidStack;
                }
                else if (fluidStack.getFluid() == Fluids.WATER) return fluidStack;
                return FluidStack.EMPTY;
            }
            return FluidStack.EMPTY;
        }

        @Override
        public @NotNull FluidStack drain(int i, FluidAction fluidAction) {
            if(testStructure()) {
                if(isOcean()) return new FluidStack(SEA_WATER.get(), i);
                return new FluidStack(Fluids.WATER, i);
            }
            return FluidStack.EMPTY;
        }
    };

    private boolean testStructure() {
        return level.getBlockState(getBlockPos().north()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).get()) &&
                level.getBlockState(getBlockPos().south()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).get()) &&
                level.getBlockState(getBlockPos().east()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).get()) &&
                level.getBlockState(getBlockPos().west()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).get()) &&
                level.getBlockState(getBlockPos().north().west()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).get()) &&
                level.getBlockState(getBlockPos().north().east()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).get()) &&
                level.getBlockState(getBlockPos().south().west()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).get()) &&
                level.getBlockState(getBlockPos().south().east()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_WOOD.get(TreatedWoodStyles.HORIZONTAL).get()) &&
                level.getBlockState(getBlockPos().above().north().west()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_FENCE.get()) &&
                level.getBlockState(getBlockPos().above().north().east()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_FENCE.get()) &&
                level.getBlockState(getBlockPos().above().south().west()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_FENCE.get()) &&
                level.getBlockState(getBlockPos().above().south().east()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_FENCE.get()) &&
                level.getBlockState(getBlockPos().above().above().north().west()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_FENCE.get()) &&
                level.getBlockState(getBlockPos().above().above().north().east()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_FENCE.get()) &&
                level.getBlockState(getBlockPos().above().above().south().west()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_FENCE.get()) &&
                level.getBlockState(getBlockPos().above().above().south().east()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_FENCE.get()) &&
                level.getBlockState(getBlockPos().above().above().above().north().west()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_SCAFFOLDING.get()) &&
                level.getBlockState(getBlockPos().above().above().above().north().east()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_SCAFFOLDING.get()) &&
                level.getBlockState(getBlockPos().above().above().above().south().west()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_SCAFFOLDING.get()) &&
                level.getBlockState(getBlockPos().above().above().above().south().east()).getBlock().equals(IEBlocks.WoodenDecoration.TREATED_SCAFFOLDING.get()) &&
                level.getBlockState(getBlockPos().above().above().above().north()).getBlock().equals(IEBlocks.TO_STAIRS.get(new ResourceLocation("immersiveengineering:treated_wood_horizontal")).get()) &&
                level.getBlockState(getBlockPos().above().above().above().south()).getBlock().equals(IEBlocks.TO_STAIRS.get(new ResourceLocation("immersiveengineering:treated_wood_horizontal")).get()) &&
                level.getBlockState(getBlockPos().above().above().above().west()).getBlock().equals(IEBlocks.TO_STAIRS.get(new ResourceLocation("immersiveengineering:treated_wood_horizontal")).get()) &&
                level.getBlockState(getBlockPos().above().above().above().east()).getBlock().equals(IEBlocks.TO_STAIRS.get(new ResourceLocation("immersiveengineering:treated_wood_horizontal")).get()) &&
                level.getBlockState(getBlockPos().above().above().above().north()).getValue(StairBlock.HALF).equals(Half.TOP) &&
                level.getBlockState(getBlockPos().above().above().above().south()).getValue(StairBlock.HALF).equals(Half.TOP) &&
                level.getBlockState(getBlockPos().above().above().above().west()).getValue(StairBlock.HALF).equals(Half.TOP) &&
                level.getBlockState(getBlockPos().above().above().above().east()).getValue(StairBlock.HALF).equals(Half.TOP) &&
                level.getBlockState(getBlockPos().above().above().above().north()).getValue(StairBlock.FACING).equals(Direction.NORTH) &&
                level.getBlockState(getBlockPos().above().above().above().south()).getValue(StairBlock.FACING).equals(Direction.SOUTH) &&
                level.getBlockState(getBlockPos().above().above().above().west()).getValue(StairBlock.FACING).equals(Direction.WEST) &&
                level.getBlockState(getBlockPos().above().above().above().east()).getValue(StairBlock.FACING).equals(Direction.EAST);
    }

    private boolean isOcean() {
        return this.level.getBiome(this.getBlockPos()).is(BiomeTags.IS_OCEAN) && this.getBlockPos().getY() == 62;
    }

    public WaterPumpBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityTypes.WATER_PUMP.get(), pPos, pBlockState);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.FLUID_HANDLER) {
            return LazyOptional.of(() -> fluidHandler).cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        if(testStructure()) {
            ModLang.builder().translate("tooltip.water_pump.functional").forGoggles(tooltip);
        } else {
            ModLang.builder().translate("tooltip.water_pump.non_functional").forGoggles(tooltip);
        }
        return true;
    }
}
