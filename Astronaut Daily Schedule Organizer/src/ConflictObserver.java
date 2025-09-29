import java.util.logging.Logger;

public class ConflictObserver implements TaskObserver {
    private final Logger logger = Logger.getLogger("ScheduleLogger");
    public void onConflict(Task t, Task conflictingWith) {
        logger.warning("Conflict detected when adding task '" + t.getDescription() + "' with existing task '" + conflictingWith.getDescription() + "'.");
        System.out.println("Observer: Conflict detected between '" + t.getDescription() + "' and '" + conflictingWith.getDescription() + "'.");
    }
    public void onTaskAdded(Task t) {
        logger.info("Task added: " + t.getDescription());
    }
    public void onTaskRemoved(int taskId) {
        logger.info("Task removed: id=" + taskId);
    }
}
