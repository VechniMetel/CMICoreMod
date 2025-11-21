package top.nebula.cmi.event;

import dev.latvian.mods.kubejs.script.PlatformWrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.time.LocalDate;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GetDateModifyTitle {
	private static boolean isOsNameContains(String name) {
		return System.getProperty("os.name").toLowerCase().contains(name);
	}

	private static void modifyTitle(String title) {
		Minecraft.getInstance().getWindow().setTitle(title);
	}

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		int month = LocalDate.now().getMonthValue();
		int day = LocalDate.now().getDayOfMonth();

		event.enqueueWork(() -> {
			if (PlatformWrapper.isClientEnvironment()) {
				if (!isOsNameContains("mac")) {
					if (month == 4 && day == 1) {
						modifyTitle("Create: Infinity Mechanism");
					} else {
						modifyTitle("Create: Mechanism and Innovation");
					}
				}
			}
		});
	}
}