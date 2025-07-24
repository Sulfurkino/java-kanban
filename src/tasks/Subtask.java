package tasks;

public class Subtask extends Task {
    private final int epicId;

    // Теперь пять параметров вместо четырёх
    public Subtask(int id,
                   String name,
                   String description,
                   ProgressStatus status,
                   int epicId) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "tasks.Subtask{" +
                "epicId=" + epicId +
                '}';
    }
}
