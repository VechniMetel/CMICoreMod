package top.nebula.cmi.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.nebula.cmi.Cmi;

@Mod.EventBusSubscriber(modid = Cmi.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerDrownInNether {
	@SubscribeEvent
	public static void onLivingTick(LivingEvent.LivingTickEvent event) {
		if (!(event.getEntity() instanceof Player player)) {
			return;
		}

		if (player.level().isClientSide()) {
			return;
		}

		// 是否在下界
		if (!player.level().dimension().equals(Level.NETHER)) {
			return;
		}

		if (player.isCreative() || player.isSpectator()) {
			return;
		}

		// 每20 tick 执行一次
		if (player.tickCount % 20 == 0) {
			int playerAir = player.getAirSupply();
			player.setAirSupply(playerAir - 10);
			// player.hurt(player.damageSources().drown(), 1.0F);
		}
	}
}