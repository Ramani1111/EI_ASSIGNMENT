import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("HH:mm");
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("ScheduleLogger");
        logger.setLevel(Level.INFO);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        logger.addHandler(ch);

        ScheduleManager mgr = ScheduleManager.getInstance();
        mgr.addObserver(new ConflictObserver());

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        System.out.println("Astronaut Daily Schedule Organizer - Console"); 
        System.out.println("Type 'help' to see commands.");

        while (running) {
            System.out.print("cmd> "); String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split(" ", 2);
            String cmd = parts[0].toLowerCase();
            try {
                switch(cmd) {
                    case "help": printHelp(); break;
                    case "add": // add Description|HH:mm|HH:mm|Priority
                        if (parts.length<2) { System.out.println("Usage: add Description|start|end|priority"); break; }
                        String[] p = parts[1].split("\|"); 
                        if (p.length<4) { System.out.println("Usage: add Description|start|end|priority"); break; }
                        String desc = p[0].trim();
                        LocalTime start = parseTime(p[1].trim());
                        LocalTime end = parseTime(p[2].trim());
                        String priority = p[3].trim();
                        if (!start.isBefore(end)) { System.out.println("Error: start must be before end."); break; }
                        Task t = TaskFactory.create(desc, start, end, priority);
                        if (mgr.addTask(t)) System.out.println("Task added successfully."); else System.out.println("Error: task conflicts with existing task."); 
                        break;
                    case "remove": // remove id
                        if (parts.length<2) { System.out.println("Usage: remove id"); break; }
                        int id = Integer.parseInt(parts[1].trim());
                        if (mgr.removeTask(id)) System.out.println("Task removed successfully."); else System.out.println("Error: Task not found."); 
                        break;
                    case "list": 
                        List<Task> tasks = mgr.listTasks();
                        if (tasks.isEmpty()) System.out.println("No tasks scheduled for the day."); 
                        else for (Task tt : tasks) System.out.println(tt);
                        break;
                    case "exit": running = false; break;
                    default: System.out.println("Unknown command. Type 'help'."); break;
                }
            } catch (DateTimeParseException dte) {
                System.out.println("Error: Invalid time format. Use HH:mm."); logger.warning(dte.toString());
            } catch (NumberFormatException nfe) {
                System.out.println("Error: Invalid number format."); logger.warning(nfe.toString());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage()); logger.severe(e.toString());
            }
        }
        System.out.println("Exiting. Bye."); sc.close();
    }

    private static LocalTime parseTime(String s) {
        return LocalTime.parse(s, F);
    }

    private static void printHelp() {
        System.out.println("Commands:");
        System.out.println("  add Description|HH:mm|HH:mm|Priority   - Add a new task"); 
        System.out.println("  remove id                             - Remove task by id"); 
        System.out.println("  list                                  - List tasks sorted by start time"); 
        System.out.println("  help                                  - Show this help"); 
        System.out.println("  exit                                  - Exit application"); 
    }
}
