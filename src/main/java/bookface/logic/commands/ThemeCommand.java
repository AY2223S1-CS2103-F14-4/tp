package bookface.logic.commands;

import static java.util.Objects.requireNonNull;

import bookface.logic.commands.add.AddCommand;
import bookface.model.Model;
import bookface.ui.UiManager;

/**
 * Handles changing of theme colour for BookFace's UI.
 */
public class ThemeCommand extends Command {
    public static final String COMMAND_WORD = "theme";
    public static final String MESSAGE_SUCCESS = "Theme changed.";
    public static final String DARK_THEME = "dark";
    public static final String LIGHT_THEME = "light";
    public static final String MESSAGE_USAGE = Command.generateMessage(COMMAND_WORD,
            "Changes the theme of BookFace. Supported themes: dark, light",
            COMMAND_WORD + " " + LIGHT_THEME);
    public static final String THEME_REGEX = "([a-z]+)";

    public ThemeCommand(String theme) {
        UiManager.applyTheme(theme);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
