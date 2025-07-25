import manager.TaskManager;
import tasks.Epic;
import tasks.ProgressStatus;
import tasks.Subtask;
import tasks.Task;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager mgr = new TaskManager();

    public static void main(String[] args) {

        runTests();

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Название задачи:");
                    String tName = scanner.nextLine().trim();
                    System.out.print("Описание задачи:");
                    String tDesc = scanner.nextLine().trim();
                    Task task = mgr.createTask(tName, tDesc);
                    System.out.println("Задача создана, ID =" + task.getId());
                    break;

                case "2":
                    System.out.print("Название эпика:");
                    String eName = scanner.nextLine().trim();
                    System.out.print("Описание эпика:");
                    String eDesc = scanner.nextLine().trim();
                    Epic epic = mgr.createEpic(eName, eDesc);
                    System.out.println("Эпик создан, ID = " + epic.getId());
                    break;

                case "3":
                    System.out.print("ID эпика:");
                    int parentId = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Название подзадачи:");
                    String sName = scanner.nextLine().trim();
                    System.out.print("Описание подзадачи:");
                    String sDesc = scanner.nextLine().trim();
                    Subtask sub = mgr.createSubtask(sName, sDesc, ProgressStatus.NEW, parentId);
                    System.out.println("Подзадача создана, ID = " + sub.getId());
                    break;

                case "4":
                    List<Task> tasks = mgr.getAllTasks();
                    for (Task tas : tasks) {
                        System.out.println(tas);
                    }
                    break;

                case "5":
                    List<Epic> epics = mgr.getAllEpics();
                    for (Epic epi : epics) {
                        System.out.println(epi);
                    }
                    break;

                case "6":
                    System.out.print("ID эпика:");
                    int epicId = Integer.parseInt(scanner.nextLine().trim());
                    List<Subtask> subs = mgr.getSubtasksOfEpic(epicId);
                    for (Subtask su : subs) {
                        System.out.println(su);
                    }
                    break;

                case "7":
                    System.out.print("ID задачи:");
                    int delId = Integer.parseInt(scanner.nextLine().trim());
                    mgr.deleteTask(delId);
                    System.out.println("Удаление выполнено.");
                    break;

                case "8":
                    System.out.print("ID задачи:");
                    int updId = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Новый статус (NEW, IN_PROGRESS, DONE): ");
                    String st = scanner.nextLine().trim();
                    mgr.updateTaskStatus(updId, ProgressStatus.valueOf(st));

                    break;

                case "0":
                    System.out.println("Выход.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверная команда, попробуйте снова.");
            }
        }
    }

    private static void runTests() {
        TaskManager testMgr = new TaskManager();

        Task t1 = testMgr.createTask("task1", "dcscscds");
        Task t2 = testMgr.createTask("task2", "dcscscds");
        Epic e1 = testMgr.createEpic("task3", "dcscscds");
        Epic e2 = testMgr.createEpic("task4", "dcscscds");
        Subtask s1 = testMgr.createSubtask("task5", "dcscscds", ProgressStatus.NEW, e1.getId());
        Subtask s2 = testMgr.createSubtask("task6", "dcscscds", ProgressStatus.NEW, e1.getId());
        Subtask s3 = testMgr.createSubtask("task7", "dcscscds", ProgressStatus.NEW, e2.getId());

        for (Task t : testMgr.getAllTasks()) {
            System.out.println(t.getId());
            System.out.println(t.getName());
            System.out.println(t.getDescription());
            System.out.println(t.getStatus());
        }

        for (Epic e : testMgr.getAllEpics()) {
            System.out.println(e.getId());
            System.out.println(e.getName());
            System.out.println(e.getDescription());
            System.out.println(e.getStatus());
            System.out.println(e.getSubtaskIds());
        }

        for (Subtask s : testMgr.getSubtasksOfEpic(e1.getId())) {
            System.out.println(s.getId());
            System.out.println(s.getName());
            System.out.println(s.getDescription());
            System.out.println(s.getStatus());
            System.out.println(s.getEpicId());
        }

        for (Subtask s : testMgr.getSubtasksOfEpic(e2.getId())) {
            System.out.println(s.getId());
            System.out.println(s.getName());
            System.out.println(s.getDescription());
            System.out.println(s.getStatus());
            System.out.println(s.getEpicId());
        }

        testMgr.updateTaskStatus(t1.getId(), ProgressStatus.IN_PROGRESS);
        testMgr.updateTaskStatus(s1.getId(), ProgressStatus.DONE);
        testMgr.updateTaskStatus(s2.getId(), ProgressStatus.DONE);

        System.out.println(testMgr.getTask(t1.getId()).getStatus());
        System.out.println(testMgr.getEpic(e1.getId()).getStatus());

        testMgr.deleteTask(t2.getId());
        testMgr.deleteEpic(e2.getId());

        for (Task t : testMgr.getAllTasks()) {
            System.out.println(t.getId() + " " + t.getName() + " " + t.getStatus());
        }

        System.out.println("Remaining Epics:");
        for (Epic e : testMgr.getAllEpics()) {
            System.out.println(e.getId() + " " + e.getName() + " " + e.getStatus());
        }

        testMgr.deleteTask(t1.getId());
        testMgr.deleteEpic(e1.getId());

        System.out.println(testMgr.getAllTasks());
        System.out.println(testMgr.getAllEpics());
    }

    private static void printMenu() {
        System.out.println("Выбирете пункт в меню -");
        System.out.println("1 Создать задачу");
        System.out.println("2 Создать эпик");
        System.out.println("3 Создать подзадачу");
        System.out.println("4 Показать все задачи");
        System.out.println("5 Показать все эпики");
        System.out.println("6 Показать все подзадачи эпика");
        System.out.println("7 Удалить задачу (по ID)");
        System.out.println("8 Обновить статус задачи");
        System.out.println("0 Выход");
    }
}

