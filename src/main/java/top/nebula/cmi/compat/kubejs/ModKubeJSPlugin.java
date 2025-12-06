package top.nebula.cmi.compat.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import top.nebula.cmi.CMI;
import top.nebula.cmi.compat.kubejs.recipe.AcceleratorSchema;
import top.nebula.cmi.util.ModLang;
import top.nebula.cmi.util.MultiblockStructureBuilder;
import top.nebula.cmi.util.PropertyImmutableMap;

import java.time.LocalDateTime;

public class ModKubeJSPlugin extends KubeJSPlugin {
//	@Override
//	public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
//		event.namespace(CMI.MODID)
//				.register("accelerator", AcceleratorSchema.SCHEMA);
//	}

	public void registerBindings(BindingsEvent event) {
		super.registerBindings(event);

		event.add("CMICore", CMI.class);
		event.add("MultiblockStructureBuilder", MultiblockStructureBuilder.class);
		event.add("PropertyImmutableMap", PropertyImmutableMap.class);
		event.add("CMIModLang", ModLang.class);
		event.add("LocalDateTime", LocalDateTime.class);
	}
}