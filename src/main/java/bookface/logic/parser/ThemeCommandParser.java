package bookface.logic.parser;

import bookface.commons.core.Messages;
import bookface.logic.commands.ThemeCommand;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input argument for the theme command
 */
public class ThemeCommandParser implements Parseable<ThemeCommand> {
    @Override
    public ThemeCommand parse(String args) throws ParseException {
        try {
            String theme = ParserUtil.parseTheme(args);
            return new ThemeCommand(theme);
        } catch (ParseException ex) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE)
            );
        }
    }
}
