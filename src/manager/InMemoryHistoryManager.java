package manager;
import tasks.*;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> history = new ArrayList<>(10);

    @Override
    public void add(Task task) {
        if (task == null) return;
        Task snapshot = snapshotOf(task);
        if (history.size() == 10) history.remove(0);
        history.add(snapshot);
    }

    @Override
    public List<Task> getHistory() {
        return List.copyOf(history);
    }

    private Task snapshotOf(Task t) {
        if (t instanceof Subtask s) return new Subtask(s);
        if (t instanceof Epic e) return new Epic(e);
        return new Task(t);
    }
}
