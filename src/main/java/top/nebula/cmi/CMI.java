package top.nebula.cmi;

import net.minecraftforge.eventbus.api.IEventBus;
import top.nebula.cmi.block.ModBlocks;
import top.nebula.cmi.block.ModBlockEntityTypes;
import top.nebula.cmi.item.ModItems;
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

	public CMI(FMLJavaModLoadingContext context) {
		IEventBus event = context.getModEventBus();

		context.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, CMI.MODID + "/common.toml");

		ModBlocks.register(event);
		ModItems.register(event);
		ModBlockEntityTypes.register(event);

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