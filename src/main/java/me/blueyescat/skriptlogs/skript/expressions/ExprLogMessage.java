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
@Name("Logged Message")
@Description("Returns the logged message in a log event.")
@Examples({"set {_message} to the logged message"})
@Since("0.1.0")
public class ExprLogMessage extends SimpleExpression<String> {

	static {
		Skript.registerExpression(ExprLogMessage.class, String.class, ExpressionType.SIMPLE, "[the] [log[ged]] message");
	}

	@Override
	public boolean init(final Expression<?>[] exprs, final int matchedPattern, final Kleenean isDelayed, final ParseResult parseResult) {
		return true;
	}

	@Override
	protected String[] get(final Event e) {
		return CollectionUtils.array(((LogEvt) e).getLogEvent().getMessage().getFormattedMessage());
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
