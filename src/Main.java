import manager.Managers;
import manager.TaskManager;
import tasks.*;



public class Main {
    private static final TaskManager mgr = Managers.getDefault();

    public static void main(String[] args) {
        int t1Id = mgr.addNewTask(new Task("T1", "task 1", ProgressStatus.NEW));
        int t2Id = mgr.addNewTask(new Task("T2", "task 2", ProgressStatus.NEW));
        int e1Id = mgr.addNewEpic(new Epic("E1", "epic 1"));
        int s1Id = mgr.addNewSubtask(new Subtask("S1", "sub 1", ProgressStatus.NEW, e1Id));

        mgr.getTask(t1Id);
        mgr.getEpic(e1Id);
        mgr.getSubtask(s1Id);
        mgr.getTask(t2Id);

        printAllTasks(mgr);
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Epic epic : manager.getEpics()) {
            System.out.println(epic);
            for (Subtask task : manager.getSubtasksOfEpic(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Subtask subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }
        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}


