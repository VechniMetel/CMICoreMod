package top.nebula.cmi.compat.jei;

import top.nebula.cmi.compat.jei.api.CmiGuiTexture;

public class CmiGuiTextures {
	public static final CmiGuiTexture WATER_PUMP_ARROW;

	static {
		WATER_PUMP_ARROW = addGuiTexture("water_pump_arrow", 1, 1, 61, 22);
	}

	public static CmiGuiTexture addGuiTexture(String path, int startX, int startY, int width, int height) {
		return new CmiGuiTexture(path, startX, startY, width, height);
	}

	public static CmiGuiTexture addGuiTexture(String path, int width, int height) {
		return new CmiGuiTexture(path, 0, 0, width, height);
	}
}