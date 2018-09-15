package me.blueyescat.skriptlogs.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.bukkit.Bukkit;

public class LogAppender extends AbstractAppender {

	public LogAppender() {
		super("skript-logs", null, null, false);
		Logger rootLogger = (Logger) LogManager.getRootLogger();
		rootLogger.addAppender(this);
	}

	@Override
	public void append(LogEvent e) {
		LogEvt logEvent = new LogEvt(e, e.getMessage());
		Bukkit.getServer().getPluginManager().callEvent(logEvent);
	}

}
