package top.nebula.cmi.common.recipe.accelerator;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.nebula.cmi.CMI;

@SuppressWarnings("ALL")
@Mod.EventBusSubscriber(modid = CMI.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AcceleratorEvents {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		Level level = event.getLevel();
		Player player = event.getEntity();
		ItemStack item = player.getItemInHand(event.getHand());
		BlockPos pos = event.getPos();

		if (level.isClientSide())
			return;

		SimpleContainer container = new SimpleContainer(1);
		container.setItem(0, item);

		level.getRecipeManager()
				.getRecipeFor(AcceleratorRecipe.Type.INSTANCE, container, level)
				.ifPresent((recipe) -> {
					if (level.getBlockState(pos).getBlock() != recipe.getTargetBlock()) {
						return;
					}

					Block chosen = null;
					float roll = level.random.nextFloat();
					float sum = 0;

					for (AcceleratorRecipe.OutputEntry o : recipe.getOutputs()) {
						sum += o.chance;
						if (roll <= sum) {
							chosen = o.block;
							break;
						}
					}

					if (!player.isCreative()) {
						item.shrink(1);
					}

					if (chosen != null) {
						level.destroyBlock(pos, false);
						level.setBlock(pos, chosen.defaultBlockState(), 3);
					}

					event.setCanceled(true);
					event.setCancellationResult(InteractionResult.SUCCESS);
				});
	}

	// 按概率返回输出方块
	private static Block chooseOutput(AcceleratorRecipe recipe, RandomSource rand) {
		float roll = rand.nextFloat();
		float cumulative = 0;

		for (AcceleratorRecipe.OutputEntry entry : recipe.getOutputs()) {
			cumulative += entry.chance;
			if (roll <= cumulative) {
				return entry.block;
			}
		}
		return null;
	}
}