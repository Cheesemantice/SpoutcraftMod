package org.spoutcraft.mod.logger;

import cpw.mods.fml.common.FMLLog;
import org.spoutcraft.api.logger.AbstractLogger;

import java.util.logging.Logger;

public class SpoutcraftLogger extends AbstractLogger {
    public SpoutcraftLogger(Logger logger) {
        super(logger);
    }

    @Override
	public void init() {
		super.logger.setParent(FMLLog.getLogger());
	}
}
