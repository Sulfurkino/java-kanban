public class Subtask extends Task {
    private final int epicId;

    public Subtask(String name, String description, ProgressStatus status, int epicId) {

        super(name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "epicId=" + epicId +
                '}';
    }
}
