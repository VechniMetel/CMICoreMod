package top.nebula.cmi.mixin;

import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(HeatCondition.class)
public abstract class HeatConditionMixin {
	@Shadow
	@Final
	@Mutable
	private static HeatCondition[] $VALUES;

	@Unique
	private static final HeatCondition cmi$PLAIN = cmi$addVariant("GRILLED", 16747520);

	@Invoker("<init>")
	public static HeatCondition cmi$invokeInit(String name, int id, int color) {
		throw new AssertionError();
	}

	@Unique
	private static HeatCondition cmi$addVariant(String name, int color) {
		ArrayList<HeatCondition> variants = null;
		if ($VALUES != null) {
			variants = new ArrayList<>(Arrays.asList($VALUES));
		}
		HeatCondition heatCondition = cmi$invokeInit(name, variants.get(variants.size() - 1).ordinal() + 1, color);
		variants.add(heatCondition);
		$VALUES = variants.toArray(new HeatCondition[0]);
		return heatCondition;
	}

	@Inject(method = "testBlazeBurner", at = @At("HEAD"), remap = false, cancellable = true)
	public void testBlazeBurner(BlazeBurnerBlock.HeatLevel level, CallbackInfoReturnable<Boolean> cir) {
		if ((HeatCondition) (Object) this == cmi$PLAIN) {
			cir.setReturnValue(level != BlazeBurnerBlock.HeatLevel.NONE && level != BlazeBurnerBlock.HeatLevel.SMOULDERING);
		}
	}

	@Inject(method = "visualizeAsBlazeBurner", at = @At("HEAD"), remap = false, cancellable = true)
	public void visualizeAsBlazeBurner(CallbackInfoReturnable<BlazeBurnerBlock.HeatLevel> cir) {
		if ((HeatCondition) (Object) this == cmi$PLAIN) {
			cir.setReturnValue(BlazeBurnerBlock.HeatLevel.valueOf("GRILLED"));
		}
	}
}