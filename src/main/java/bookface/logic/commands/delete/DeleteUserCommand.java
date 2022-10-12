package bookface.logic.commands.delete;

import static java.util.Objects.requireNonNull;

import java.util.List;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.logic.commands.CommandResult;
import bookface.logic.commands.exceptions.CommandException;
import bookface.model.Model;
import bookface.model.book.Book;
import bookface.model.person.Person;

/**
 * Deletes a user identified using it's displayed index from the user list.
 */
public class DeleteUserCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "user";

    public static final String MESSAGE_USAGE = DeleteCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": Deletes the user identified by the index number used in the displayed user list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + DeleteCommand.COMMAND_WORD + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted User: %1$s";

    private final Index targetIndex;

    public DeleteUserCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        for (Book book : personToDelete.getLoanedBooksSet()) {
            model.returnLoan(book);
        }
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteUserCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteUserCommand) other).targetIndex)); // state check
    }
}
