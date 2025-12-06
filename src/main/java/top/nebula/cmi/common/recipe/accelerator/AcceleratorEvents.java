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
import top.nebula.cmi.common.recipe.AcceleratorRecipe;

@SuppressWarnings("ALL")
@Mod.EventBusSubscriber(modid = CMI.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AcceleratorEvents {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		Level level = event.getLevel();
		Player player = event.getEntity();
		ItemStack item = player.getItemInHand(event.getHand());
		BlockPos pos = event.getPos();

		SimpleContainer container = new SimpleContainer(1);
		container.setItem(0, item);

		if (level.isClientSide()) {
			return;
		}

		level.getRecipeManager()
				.getRecipeFor(AcceleratorRecipe.Type.INSTANCE, container, level)
				.ifPresent((recipe) -> {
					if (level.getBlockState(pos).getBlock() == recipe.getTargetBlock()) {
						level.destroyBlock(pos, false);
						level.setBlock(pos, recipe.getOutputBlock().defaultBlockState(), 3);

						if (!player.isCreative()) {
							item.shrink(1);
						}

						event.setCanceled(true);
						event.setCancellationResult(InteractionResult.SUCCESS);
					}
				});
	}
}