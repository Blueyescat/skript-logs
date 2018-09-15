package me.blueyescat.skriptlogs.util;

import org.apache.logging.log4j.core.LogEvent;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author Blueyescat
 */
public class LogEvt extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private LogEvent logEvent;
	private boolean cancelled;

	public LogEvt(final LogEvent e) {
		logEvent = e;
	}

	public LogEvent getLogEvent() {
		return logEvent;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean b) {
		cancelled = b;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
