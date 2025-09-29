import java.time.LocalTime;
import java.util.*;
import java.util.logging.Logger;

public class ScheduleManager {
    private static ScheduleManager instance;
    private final List<Task> tasks = new ArrayList<>();
    private final List<TaskObserver> observers = new ArrayList<>();
    private final Logger logger = Logger.getLogger("ScheduleLogger");

    private ScheduleManager(){}
    public static synchronized ScheduleManager getInstance(){
        if (instance == null) instance = new ScheduleManager();
        return instance;
    }

    public void addObserver(TaskObserver o){ observers.add(o); }
    public void removeObserver(TaskObserver o){ observers.remove(o); }

    public synchronized boolean addTask(Task t){
        for (Task ex : tasks) {
            if (overlaps(ex, t)) {
                for (TaskObserver o : observers) o.onConflict(t, ex);
                return false;
            }
        }
        tasks.add(t);
        Collections.sort(tasks);
        for (TaskObserver o : observers) o.onTaskAdded(t);
        logger.info("Task added: " + t.getDescription());
        return true;
    }

    public synchronized boolean removeTask(int id){
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task t = it.next();
            if (t.getId() == id) {
                it.remove();
                for (TaskObserver o : observers) o.onTaskRemoved(id);
                logger.info("Task removed id=" + id);
                return true;
            }
        }
        return false;
    }

    public synchronized List<Task> listTasks(){ return new ArrayList<>(tasks); }

    private boolean overlaps(Task a, Task b) {
        return ! (a.getEnd().compareTo(b.getStart()) <= 0 || b.getEnd().compareTo(a.getStart()) <= 0);
    }
}
