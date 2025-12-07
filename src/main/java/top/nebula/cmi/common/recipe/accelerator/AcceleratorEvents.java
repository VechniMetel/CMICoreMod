package top.nebula.cmi.common.recipe.accelerator;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.nebula.cmi.CMI;

@SuppressWarnings("ALL")
@Mod.EventBusSubscriber(modid = CMI.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AcceleratorEvents {
	public static final Lazy<Block> ACCELERATOR_BLOCK = Lazy.of(() -> {
		return BuiltInRegistries.BLOCK.get(CMI.loadResource("accelerator"));
	});

	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		Level level = event.getLevel();
		Player player = event.getEntity();
		ItemStack item = player.getItemInHand(event.getHand());
		BlockPos pos = event.getPos();
		BlockState state = level.getBlockState(pos);

		if (level.isClientSide()) {
			return;
		}

		if (!state.is(ACCELERATOR_BLOCK.get())) {
			return;
		}

		SimpleContainer container = new SimpleContainer(1);
		container.setItem(0, item);

		level.getRecipeManager()
				.getRecipeFor(AcceleratorRecipe.Type.INSTANCE, container, level)
				.ifPresent((recipe) -> {
					boolean foundTarget = false;

					for (int dx = -2; dx <= 2; dx++) {
						for (int dz = -2; dz <= 2; dz++) {
							BlockPos check = pos.offset(dx, 0, dz);
							if (level.getBlockState(check).is(recipe.targetBlock)) {
								foundTarget = true;
								break;
							}
						}
						if (foundTarget) {
							break;
						}
					}

					if (!foundTarget) {
						if (!player.isCreative()) {
							item.shrink(1);
						}
						event.setCanceled(true);
						event.setCancellationResult(InteractionResult.SUCCESS);
						return;
					}

					RandomSource random = level.getRandom();

					for (int dx = -2; dx <= 2; dx++) {
						for (int dz = -2; dz <= 2; dz++) {
							BlockPos targetPos = pos.offset(dx, 0, dz);

							if (level.getBlockState(targetPos).is(recipe.targetBlock)) {
								for (AcceleratorRecipe.OutputEntry out : recipe.outputs) {
									if (random.nextFloat() <= out.chance) {
										level.destroyBlock(targetPos, false);
										level.setBlock(targetPos, out.block.defaultBlockState(), 3);
										break;
									}
								}
							}
						}
					}

					if (!player.isCreative()) {
						item.shrink(1);
					}

					event.setCanceled(true);
					event.setCancellationResult(InteractionResult.SUCCESS);
				});
	}
}