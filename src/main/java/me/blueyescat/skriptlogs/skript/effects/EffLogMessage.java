package me.blueyescat.skriptlogs.skript.effects;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.StandardLevel;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

/**
 * @author Blueyescat
 */
@Name("Log Message")
@Description({"Logs a message with the given log level. The last pattern uses 'info' level."})
@Examples({"log a warning \"test\"",
		"log errors \"test\" and \"error\"",
		"send a fatal error \"test\" to the console",
		"print in \"test\""})
@Since("0.1.0")
public class EffLogMessage extends Effect {

	static {
		Skript.registerEffect(EffLogMessage.class,
				"(log|print [in]) %loglevel% [[with [the]] message[s]] %strings%",
				"send %loglevel% [[with [the]] message[s]] %strings% to [the] console",
				"print [in] %strings%");
	}

	@SuppressWarnings("null")
	private Expression<StandardLevel> logLevel;

	@SuppressWarnings("null")
	private Expression<String> messages;

	private boolean usedPrint;

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(final Expression<?>[] exprs, final int matchedPattern, final Kleenean isDelayed, final ParseResult parser) {
		usedPrint = matchedPattern == 2;
		if (!usedPrint)
			logLevel = (Expression<StandardLevel>) exprs[0];
		messages = (Expression<String>) exprs[usedPrint ? 0 : 1];
		return true;
	}

	@Override
	protected void execute(final Event e) {
		if ((logLevel == null && !usedPrint) || messages == null)
			return;
		Logger logger = LogManager.getRootLogger();
		Level level = usedPrint ? Level.INFO : Level.valueOf(logLevel.getSingle(e).toString());
		for (String message : messages.getArray(e))
			logger.log(level, message);
	}

	@Override
	public String toString(final @Nullable Event e, final boolean debug) {
		if (usedPrint)
			return "print " + messages.toString(e, debug);
		else
			return "log " + logLevel.toString(e, debug) + " message " + messages.toString(e, debug);
	}

}
