package top.nebula.cmi.common.tag;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import top.nebula.cmi.CMI;

public class ModFluidTags {
	public static TagKey<Fluid> createTag(String namespace, String name) {
		return FluidTags.create(ResourceLocation.fromNamespaceAndPath(namespace, name));
	}

	public static TagKey<Fluid> createTag(String name) {
		return FluidTags.create(CMI.loadResource(name));
	}
}