package me.blueyescat.skriptlogs.skript.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

import me.blueyescat.skriptlogs.util.LogEvt;

/**
 * @author Blueyescat
 */
@Name("Logger Name")
@Description({"Returns the name of the logger in a log event. None if the logger name doesn't exist.",
		"If a plugin logs a message using its plugin logger this will be name of the plugin. i.e. '[<Plugin>] <message>'.",
		"In this case the logged message won't contain this '[Plugin]' prefix. " +
		"So you can check if the logger name is a valid plugin somehow and merge it with the logged message."})
@Examples({"set {_loggerName} to the logger name"})
@Since("0.1.0")
public class ExprLoggerName extends SimpleExpression<String> {

	static {
		Skript.registerExpression(ExprLoggerName.class, String.class, ExpressionType.SIMPLE, "[the] logger name");
	}

	@Override
	public boolean init(final Expression<?>[] exprs, final int matchedPattern, final Kleenean isDelayed, final ParseResult parseResult) {
		return true;
	}

	@Override
	protected String[] get(final Event e) {
		String name = ((LogEvt) e).getLogEvent().getLoggerName();
		if (name.isEmpty())
			return null;
		return CollectionUtils.array(name);
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public String toString(final @Nullable Event e, final boolean debug) {
		return "logged message";
	}

}
