package me.blueyescat.skriptlogs.skript;

import org.apache.logging.log4j.spi.StandardLevel;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Converter;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.Converters;
import ch.njol.skript.util.EnumUtils;

/**
 * @author Blueyescat
 */
public class Types {

	static {
		final EnumUtils<StandardLevel> levels = new EnumUtils<>(StandardLevel.class, "log levels");
		Classes.registerClass(new ClassInfo<>(StandardLevel.class, "loglevel")
				.user("log ?levels?")
				.name("Log Level")
				.description("")
				.usage("info, warning, error, fatal, trace, debug, off, all")
				.examples("")
				.since("0.1.0")
				.defaultExpression(new EventValueExpression<>(StandardLevel.class))
				.parser(new Parser<StandardLevel>() {

					@Override
					@Nullable
					public StandardLevel parse(String s, ParseContext context) {
						return levels.parse(s);
					}

					@Override
					public String toString(StandardLevel o, int flags) {
						return levels.toString(o, flags);
					}

					@Override
					@SuppressWarnings("null")
					public String toVariableNameString(StandardLevel o) {
						return o.name();
					}

					@Override
					public String getVariableNamePattern() {
						return "\\S+";
					}

				}));

		// Log levels have a type just for enum values. They should act like strings.
		Converters.registerConverter(StandardLevel.class, String.class, (Converter<StandardLevel, String>) logLevel ->
				levels.toString(logLevel, 0)
		);

	}

}
