package top.nebula.cmi;

import top.nebula.cmi.block.ModBlocks;
import top.nebula.cmi.block.entity.ModBlockEntityTypes;
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
        context.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, CMI.MODID + "/common.toml");

        ModBlocks.register(context.getModEventBus());
        ModItems.register(context.getModEventBus());
        ModBlockEntityTypes.register(context.getModEventBus());

        context.getModEventBus().addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new ModOverworldRegion(5));

            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, CMI.MODID,
                    ModSurfaceRuleData.makeRules()
            );
            SurfaceRuleManager.addToDefaultSurfaceRulesAtStage(SurfaceRuleManager.RuleCategory.OVERWORLD,
                    SurfaceRuleManager.RuleStage.AFTER_BEDROCK, 0, ModSurfaceRuleData.makeInjections()
            );
        });
    }
}