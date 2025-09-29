import java.time.LocalTime;

public class TaskFactory {
    public static Task create(String description, LocalTime start, LocalTime end, String priority) {
        return new Task(description, start, end, priority);
    }
}
