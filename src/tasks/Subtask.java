package tasks;

public class Subtask extends Task {
    private final int epicId;

    public Subtask(String name, String description, ProgressStatus status, int epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public Subtask(int id, String name, String description, ProgressStatus status, int epicId) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    public Subtask(Subtask other) {
        this(other.getId(), other.getName(), other.getDescription(), other.getStatus(), other.getEpicId());
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", status=" + getStatus() +
                ", epicId=" + epicId +
                '}';
    }
}
