public interface TaskObserver {
    void onConflict(Task t, Task conflictingWith);
    void onTaskAdded(Task t);
    void onTaskRemoved(int taskId);
}
