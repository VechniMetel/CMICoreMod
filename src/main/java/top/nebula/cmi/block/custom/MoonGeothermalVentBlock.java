package top.nebula.cmi.block.custom;

import com.github.alexmodguy.alexscaves.server.item.ACItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.nebula.cmi.block.ModBlockEntityTypes;
import top.nebula.cmi.block.entity.MoonGeothermalVentBlockEntity;

public class MoonGeothermalVentBlock extends BaseEntityBlock {
	public static final IntegerProperty SMOKE_TYPE = IntegerProperty.create("smoke_type", 0, 3);
	public static final BooleanProperty SPAWNING_PARTICLES = BooleanProperty.create("spawning_particles");

	public MoonGeothermalVentBlock() {
		super(BlockBehaviour.Properties.of()
				.mapColor(MapColor.STONE)
				.requiresCorrectToolForDrops()
				.strength(2F, 5.0F)
				.sound(SoundType.TUFF));
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(SMOKE_TYPE, Integer.valueOf(0))
				.setValue(SPAWNING_PARTICLES, true));
	}

	public BlockState getStateForPlacement(BlockPlaceContext context) {
		LevelAccessor levelaccessor = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		return this.defaultBlockState()
				.setValue(SMOKE_TYPE, getSmokeType(levelaccessor, blockpos))
				.setValue(SPAWNING_PARTICLES, isSpawningParticles(blockpos, levelaccessor));
	}

	public @NotNull BlockState updateShape(BlockState state, @NotNull Direction direction, @NotNull BlockState state1, @NotNull LevelAccessor accessor, @NotNull BlockPos pos, @NotNull BlockPos pos1) {
		return state.setValue(SMOKE_TYPE, getSmokeType(accessor, pos))
				.setValue(SPAWNING_PARTICLES, isSpawningParticles(pos, accessor));
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(SMOKE_TYPE, SPAWNING_PARTICLES);
	}

	public int getSmokeType(LevelAccessor level, BlockPos pos) {
		BlockState state = level.getBlockState(pos.below());
		if (state.getBlock() instanceof MoonGeothermalVentBlock) {
			return state.getValue(SMOKE_TYPE);
		}
		if (state.getFluidState().getFluidType() == Fluids.LAVA.getFluidType()) {
			return 3;
		} else if (state.getFluidState().is(FluidTags.WATER)) {
			return 1;
		} else if (state.getFluidState().is(FluidTags.LAVA)) {
			return 2;
		}
		return 0;
	}

	public boolean isSpawningParticles(BlockPos pos, LevelAccessor level) {
		BlockState above = level.getBlockState(pos.above());
		return (above.isAir() || !above.blocksMotion());
	}

	public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource source) {

	}

	public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
		ItemStack heldItem = player.getItemInHand(hand);
		if (heldItem.is(Items.GLASS_BOTTLE) && state.getValue(SMOKE_TYPE) == 3 && state.getValue(SPAWNING_PARTICLES)) {
			ItemStack bottle = new ItemStack(ACItemRegistry.RADON_BOTTLE.get());
			if (!player.addItem(bottle)) {
				player.drop(bottle, false);
			}
			if (!player.isCreative()) {
				heldItem.shrink(1);
			}
			player.playSound(SoundEvents.BOTTLE_FILL);
			return InteractionResult.SUCCESS;
		}
		return super.use(state, level, pos, player, hand, result);
	}

	@javax.annotation.Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
		if (level.isClientSide) {
			return state.getValue(SMOKE_TYPE) > 0 && state.getValue(SPAWNING_PARTICLES) ?
					createTickerHelper(type, ModBlockEntityTypes.MOON_GEO.get(),
							MoonGeothermalVentBlockEntity::particleTick) : null;
		} else {
			return null;
		}
	}

	public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
		return RenderShape.MODEL;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return new MoonGeothermalVentBlockEntity(pos, state);
	}
}