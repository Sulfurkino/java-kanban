package tasks;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Integer> subtaskIds = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description, ProgressStatus.NEW);
    }

    public Epic(int id, String name, String description) {
        super(id, name, description, ProgressStatus.NEW);
    }

    public Epic(Epic other) {
        super(other.getId(), other.getName(), other.getDescription(), other.getStatus());
        this.subtaskIds.addAll(other.subtaskIds);
    }

    public void addSubtask(Subtask subtask) {
        if (subtask.getId() == this.getId())
            throw new IllegalArgumentException("Эпик не может содержать себя как подзадачу.");
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
        return "Epic{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", status=" + getStatus() +
                ", subtaskIds=" + subtaskIds +
                '}';
    }
}
