package tasks;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Integer> subtaskIds = new ArrayList<>();

    public Epic(int id, String name, String description) {
        super(id, name, description, ProgressStatus.NEW);
    }

    public void addSubtask(Subtask subtask) {
        subtaskIds.add(subtask.getId());
    }

    public void removeSubtask(int subtaskId) {
        subtaskIds.remove(Integer.valueOf(subtaskId));
    }

    public List<Integer> getSubtaskIds() {
        return List.copyOf(subtaskIds);
    }

    @Override
    public String toString() {
        return "tasks.Epic{" +
                "subtaskIds=" + subtaskIds +
                '}';
    }
}