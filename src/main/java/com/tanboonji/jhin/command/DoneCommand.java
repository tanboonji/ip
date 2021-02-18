package com.tanboonji.jhin.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tanboonji.jhin.exception.InvalidCommandArgumentException;
import com.tanboonji.jhin.exception.JhinException;
import com.tanboonji.jhin.model.Task;

/**
 * The DoneCommand class contains information to execute the "done" command.
 */
public class DoneCommand extends Command {

    private static final Pattern COMMAND_FORMAT = Pattern.compile("^(\\d+)$");
    private static final String INVALID_ARGUMENT_MESSAGE = "Sorry, the done command you entered is invalid.\n"
                    + "Please enter a valid done command in the following format:\n"
                    + "done <task number>";
    private static final String INVALID_TASK_NUMBER_MESSAGE_FORMAT =
            "Sorry, the task number '%s' you entered is invalid.\n"
                    + "Please enter a valid task number and try again.";
    private static final String TASK_ALREADY_DONE_MESSAGE =
            "Sorry, the task you entered is already done.\n"
                    + "Please enter a task that is not done and try again.";
    private static final String SUCCESS_MESSAGE_FORMAT = "Nice! I've marked this task as done:\n"
            + "%s";

    private final int taskIndex;

    private DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean shouldSaveData() {
        return true;
    }

    @Override
    public boolean shouldExitJhin() {
        return false;
    }

    @Override
    public String execute() throws JhinException {
        try {
            Task task = taskList.markAsDone(taskIndex);
            return String.format(SUCCESS_MESSAGE_FORMAT, task);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException(String.format(INVALID_TASK_NUMBER_MESSAGE_FORMAT, taskIndex));
        }
    }

    /**
     * Returns new done command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New done command.
     * @throws JhinException If user input is not an integer.
     */
    public static DoneCommand parseArguments(String arguments) throws JhinException {
        Matcher matcher = COMMAND_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new InvalidCommandArgumentException(INVALID_ARGUMENT_MESSAGE);
        }

        try {
            int taskIndex = Integer.parseInt(arguments) - 1;
            return new DoneCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentException(String.format(INVALID_TASK_NUMBER_MESSAGE_FORMAT, arguments));
        }
    }
}
