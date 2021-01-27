import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandParserTest {

    @Test
    void parse_toDoCommand_success() {
        String[] inputs = {"todo read book", " todo  read another book", "todo  more books "};
        for (String input: inputs) {
            assertTrue(CommandParser.parse(input).getClass().isAssignableFrom(ToDoCommand.class));
        }
    }

    @Test
    void parse_eventCommand_success() {
        String[] inputs = {"event book reading /at 01-02-2021 0000", " event  book reading /at 01/02/2021",
                "event  book reading /at 01.02.2021 2359 "};
        for (String input: inputs) {
            assertTrue(CommandParser.parse(input).getClass().isAssignableFrom(EventCommand.class));
        }
    }

    @Test
    void parse_listCommand_success() {
        String[] inputs = {"list", " list  all", "list  everything "};
        for (String input: inputs) {
            assertTrue(CommandParser.parse(input).getClass().isAssignableFrom(ListCommand.class));
        }
    }

    @Test
    void parse_doneCommand_success() {
        String[] inputs = {"done 0", " done  1", "done  2 "};
        for (String input: inputs) {
            assertTrue(CommandParser.parse(input).getClass().isAssignableFrom(DoneCommand.class));
        }
    }

    @Test
    void parse_errorHelpCommand_success() {
        String[] inputs = {"blah", "  unknown", "invalid  "};
        for (String input: inputs) {
            assertTrue(CommandParser.parse(input).getClass().isAssignableFrom(HelpCommand.class));
        }
    }

    @Test
    void parse_invalidToDoCommand_DukeException() {
        String[] inputs = {"todo", "  todo", "todo  "};
        for (String input: inputs) {
            assertTrue(CommandParser.parse(input).getClass().isAssignableFrom(InvalidCommand.class));
        }
    }

    @Test
    void parse_invalidEventCommand_DukeException() {
        String[] inputs = {"event", " event  book reading", "event  book reading /by 01.01.2021 0000 "};
        for (String input: inputs) {
            assertTrue(CommandParser.parse(input).getClass().isAssignableFrom(InvalidCommand.class));
        }
    }

    @Test
    void parse_invalidDoneCommand_DukeException() {
        String[] inputs = {"done", " done  one", "done  two "};
        for (String input: inputs) {
            assertTrue(CommandParser.parse(input).getClass().isAssignableFrom(InvalidCommand.class));
        }
    }
}