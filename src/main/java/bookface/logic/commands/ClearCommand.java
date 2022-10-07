package bookface.logic.commands;

import static java.util.Objects.requireNonNull;

import bookface.model.BookFace;
import bookface.model.Model;

/**
 * Clears the user list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "User list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setBookFace(new BookFace());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
