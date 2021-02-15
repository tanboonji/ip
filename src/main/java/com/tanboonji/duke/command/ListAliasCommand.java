package com.tanboonji.duke.command;

/**
 * The ListAliasCommand class contains information to execute the "list" command.
 */
public class ListAliasCommand extends Command {

    /** String input to execute this command */
    public static final String COMMAND = "listalias";
    private static final String SUCCESS_MESSAGE = "Here are the your aliases:\n"
            + "%s";
    private static final String SUCCESS_EMPTY_ALIAS_MESSAGE = "You currently have 0 aliases.";

    /**
     * Default class constructor.
     */
    public ListAliasCommand() {
    }

    @Override
    public boolean shouldSaveData() {
        return false;
    }

    @Override
    public boolean shouldExitDuke() {
        return false;
    }

    @Override
    public String execute() {
        if (aliasMap.getSize() == 0) {
            return SUCCESS_EMPTY_ALIAS_MESSAGE;
        }

        return String.format(SUCCESS_MESSAGE, aliasMap);
    }
}
