package top.nebula.cmi.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class CommonConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> BURNING_FLUIDS = BUILDER
            .comment("A list of fluids that burns entities (just like lava)")
            .defineListAllowEmpty("burningFluids", List.of(), CommonConfig::validateString);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    private static boolean validateString(Object obj) {
        return obj instanceof String;
    }
}