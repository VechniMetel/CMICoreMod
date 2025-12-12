package top.nebula.cmi;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.nebula.cmi.common.register.*;
import top.nebula.cmi.worldgen.region.ModOverworldRegion;
import top.nebula.cmi.worldgen.surfacerule.ModSurfaceRuleData;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.nebula.cmi.config.CommonConfig;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@Mod(CMI.MODID)
public class CMI {
	public static final String MODID = "cmi";
	public static final String NAME = "CMI";
	public static final Logger LOGGER = LogManager.getLogger(NAME);

	/**
	 * 加载ResourceLocation资源
	 *
	 * @param path
	 * @return
	 */
	public static ResourceLocation loadResource(String path) {
		return ResourceLocation.fromNamespaceAndPath(MODID, path);
	}

	/**
	 * 使用图腾动画
	 *
	 * @param stack
	 */
	public static void useAnimation(ItemStack stack) {
		Minecraft.getInstance().gameRenderer.displayItemActivation(stack);
	}

	public CMI(FMLJavaModLoadingContext context) {
		IEventBus event = context.getModEventBus();

		context.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, MODID + "/common.toml");

		ModBlocks.register(event);
		ModBlockEntityTypes.register(event);
		ModItems.register(event);
		ModRecipeType.register(event);
		ModRecipeSerializer.register(event);

		event.addListener(this::commonSetup);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			Regions.register(new ModOverworldRegion(5));

			SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, CMI.MODID, ModSurfaceRuleData.makeRules());
			SurfaceRuleManager.addToDefaultSurfaceRulesAtStage(SurfaceRuleManager.RuleCategory.OVERWORLD, SurfaceRuleManager.RuleStage.AFTER_BEDROCK, 0, ModSurfaceRuleData.makeInjections());
		});
	}
}