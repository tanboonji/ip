public class ToDoCommand extends Command {

    public static final String COMMAND = "todo";

    private final String description;

    private ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute() throws DukeException {
        Task newTask = new ToDo(description);
        taskList.addTask(newTask);

        StringBuilder builder = new StringBuilder();
        builder.append("Got it. I've added this task:\n\t").append(newTask).append("\nNow you have ")
                .append(taskList.getSize());
        if (taskList.getSize() == 1) {
            builder.append(" task");
        } else {
            builder.append(" tasks");
        }
        return builder.toString();
    }

    @Override
    public boolean shouldSave() {
        return true;
    }

    public static ToDoCommand parseArguments(String input) {
        return new ToDoCommand(input);
    }
}