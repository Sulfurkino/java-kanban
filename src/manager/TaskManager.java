package manager;

import tasks.*;

import java.util.List;

public interface TaskManager {
    int addNewTask(Task task);

    int addNewEpic(Epic epic);

    int addNewSubtask(Subtask subtask);

    Task getTask(int id);

    Epic getEpic(int id);

    Subtask getSubtask(int id);

    List<Task> getTasks();

    List<Epic> getEpics();

    List<Subtask> getSubtasks();

    List<Subtask> getSubtasksOfEpic(int epicId);

    void updateTaskStatus(int id, ProgressStatus status);

    void deleteTask(int id);

    void deleteEpic(int id);

    void deleteSubtask(int id);

    int updateTask(Task task);

    int updateEpic(Epic epic);

    int updateSubtask(Subtask subtask);

    List<Task> getHistory();
}

