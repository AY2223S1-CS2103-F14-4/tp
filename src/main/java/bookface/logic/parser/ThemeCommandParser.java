package bookface.logic.parser;

import bookface.logic.commands.ThemeCommand;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input argument for the theme command
 */
public class ThemeCommandParser implements Parseable<ThemeCommand> {
    @Override
    public ThemeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        try {
            String theme = ParserUtil.parseTheme(trimmedArgs);
            return new ThemeCommand(theme);
        } catch (ParseException ex) {
            throw new ParseException(
                    ex.getMessage()
            );
        }
    }
}
