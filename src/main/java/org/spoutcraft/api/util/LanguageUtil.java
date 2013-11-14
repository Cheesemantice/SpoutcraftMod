package org.spoutcraft.api.util;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LanguageUtil {
	public static void add(String raw, String display) {
		LanguageRegistry.instance().addStringLocalization(raw, "en_US", display);
		LanguageRegistry.instance().addStringLocalization(raw, "en_GB", display);
		LanguageRegistry.instance().addStringLocalization(raw, "en_CA", display);
		LanguageRegistry.instance().addStringLocalization(raw, "en_AU", display);
		LanguageRegistry.instance().addStringLocalization(raw, "en_PT", display);
		LanguageRegistry.instance().addStringLocalization(raw, "es_ES", display);
		LanguageRegistry.instance().addStringLocalization(raw, "en_MX", display);
		LanguageRegistry.instance().addStringLocalization(raw, "fr_FR", display);
	}

	public static void name(Object obj, String name) {
		LanguageRegistry.addName(obj, name);
	}
}
