package org.spoutcraft.mod.logger;

import cpw.mods.fml.common.FMLLog;
import org.spoutcraft.api.logger.AbstractLogger;

public class SpoutcraftLogger extends AbstractLogger {
	@Override
	public void init() {
		super.logger.setParent(FMLLog.getLogger());
	}
}
