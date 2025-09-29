import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task implements Comparable<Task> {
    private static int COUNTER = 1;
    private final int id;
    private String description;
    private LocalTime start;
    private LocalTime end;
    private String priority;
    private boolean completed = false;

    public Task(String description, LocalTime start, LocalTime end, String priority) {
        this.id = COUNTER++;
        this.description = description;
        this.start = start;
        this.end = end;
        this.priority = priority;
    }

    public int getId(){ return id; }
    public String getDescription(){ return description; }
    public LocalTime getStart(){ return start; }
    public LocalTime getEnd(){ return end; }
    public String getPriority(){ return priority; }
    public boolean isCompleted(){ return completed; }
    public void setCompleted(boolean c){ completed = c; }

    public String toString(){
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("%d) %s - %s: %s [%s]%s", id, start.format(f), end.format(f), description, priority, completed?" (done)":"");
    }

    public int compareTo(Task other){ return this.start.compareTo(other.start); }
}
