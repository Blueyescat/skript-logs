package me.blueyescat.skriptlogs.skript.events;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

import me.blueyescat.skriptlogs.util.LogEvt;

/**
 * @author Blueyescat
 */
public class EvtLog extends SkriptEvent {

	static {
		Skript.registerEvent("Log", EvtLog.class, LogEvt.class, "[console] log")
				.description("Called when a message is logged.")
				.examples("on log:")
				.since("0.1.0");
		EventValues.registerEventValue(LogEvt.class, String.class, new Getter<String, LogEvt>() {
			@Override
			public String get(LogEvt e) {
				return e.getLogEvent().getMessage().getFormattedMessage();
			}
		}, 0);
	}

	@Override
	public boolean init(final Literal<?>[] args, final int matchedPattern, final ParseResult parser) {
		return true;
	}

	@Override
	public boolean check(final Event e) {
		return e instanceof LogEvt;
	}

	@Override
	public String toString(final @Nullable Event e, final boolean debug) {
		return "log";
	}

}
