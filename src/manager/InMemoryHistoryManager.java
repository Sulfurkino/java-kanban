package manager;

import tasks.*;

import java.util.ArrayList;
import java.util.Collections;
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
        return Collections.unmodifiableList(new ArrayList<>(history));
    }

    private Task snapshotOf(Task task) {
        if (task instanceof Subtask) return new Subtask((Subtask) task);
        if (task instanceof Epic) return new Epic((Epic) task);
        return new Task(task);
    }
}

