package top.nebula.cmi;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import top.nebula.cmi.common.block.ModBlocks;
import top.nebula.cmi.common.block.ModBlockEntityTypes;
import top.nebula.cmi.common.item.ModItems;
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

	public static ResourceLocation loadResource(String path) {
		return ResourceLocation.fromNamespaceAndPath(MODID, path);
	}

	public CMI(FMLJavaModLoadingContext context) {
		IEventBus event = context.getModEventBus();

		context.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, MODID + "/common.toml");

		ModBlocks.register(event);
		ModBlockEntityTypes.register(event);
		ModItems.register(event);

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