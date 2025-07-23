import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager mgr = new TaskManager();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Название задачи:");
                    String tName = scanner.nextLine().trim();
                    System.out.print("Описание задачи:");
                    String tDesc = scanner.nextLine().trim();
                    mgr.addTask(new Task(tName, tDesc, ProgressStatus.NEW));
                    System.out.println("Задача создана.");
                    break;

                case "2":
                    System.out.print("Название эпика:");
                    String eName = scanner.nextLine().trim();
                    System.out.print("Описание эпика:");
                    String eDesc = scanner.nextLine().trim();
                    mgr.addEpic(new Epic(eName, eDesc));
                    System.out.println("Эпик создан");
                    break;

                case "3":
                    System.out.print("ID эпика:");
                    int parentId = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Название подзадачи: ");
                    String sName = scanner.nextLine().trim();
                    System.out.print("Описание подзадачи:");
                    String sDesc = scanner.nextLine().trim();
                    mgr.addSubtask(new Subtask(sName, sDesc, ProgressStatus.NEW, parentId));
                    break;

                case "4":
                    List<Task> tasks = mgr.getAllTasks();
                    for (Task task : tasks) {
                        System.out.println(task);
                    }
                    break;

                case "5":
                    List<Epic> epics = mgr.getAllEpics();
                    for (Epic epic : epics) {
                        System.out.println(epic);
                    }
                    break;

                case "6":
                    System.out.print("ID эпика:");
                    int epicId = Integer.parseInt(scanner.nextLine().trim());
                    List<Subtask> subs = mgr.getSubtasksOfEpic(epicId);
                    System.out.println(epicId);
                    for (Subtask sub : subs) {
                        System.out.println(sub);
                    }
                    break;

                case "7":
                    System.out.print("ID задачи: ");
                    int delId = Integer.parseInt(scanner.nextLine().trim());
                    mgr.deleteTask(delId);
                    System.out.println("Удаление выполнено.");
                    break;

                case "8":
                    System.out.print("ID задачи: ");
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

