public class Task {

    private final int id;
    private static int idNumber = 0;


    private String name;
    private String description;
    private ProgressStatus status;

    public Task(String name, String description, ProgressStatus status) {
        this.id = idNumber++;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getNextId() {
        return idNumber;
    }

    public static void setNextId(int nextId) {
        Task.idNumber = nextId;
    }

    public ProgressStatus getStatus() {
        return status;
    }

    public void setStatus(ProgressStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
