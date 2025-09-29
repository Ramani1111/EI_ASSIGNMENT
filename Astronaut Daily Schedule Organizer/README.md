Ex2 - Astronaut Daily Schedule Organizer (plain Java, console app)

Features:
- Add task (description, start time HH:mm, end time HH:mm, priority)
- Remove task by id
- View tasks sorted by start time
- Overlap validation (no overlapping tasks)
- Singleton ScheduleManager, TaskFactory, Observer for conflicts
- Logging and basic exception handling

Compile/run:
javac src/*.java
java -cp src Main
