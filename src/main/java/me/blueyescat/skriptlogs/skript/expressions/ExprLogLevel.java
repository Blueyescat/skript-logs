package me.blueyescat.skriptlogs.skript.expressions;

import org.apache.logging.log4j.spi.StandardLevel;
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
@Name("Log Level")
@Description("Returns the level of the message in a log event.")
@Examples({"if the log level is error:"})
@Since("0.1.0")
public class ExprLogLevel extends SimpleExpression<StandardLevel> {

	static {
		Skript.registerExpression(ExprLogLevel.class, StandardLevel.class, ExpressionType.SIMPLE, "[the] log [message] level");
	}

	@Override
	public boolean init(final Expression<?>[] exprs, final int matchedPattern, final Kleenean isDelayed, final ParseResult parseResult) {
		return true;
	}

	@Override
	protected StandardLevel[] get(final Event e) {
		return CollectionUtils.array(((LogEvt) e).getLogEvent().getLevel().getStandardLevel());
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends StandardLevel> getReturnType() {
		return StandardLevel.class;
	}

	@Override
	public String toString(final @Nullable Event e, final boolean debug) {
		return "log level";
	}

}
