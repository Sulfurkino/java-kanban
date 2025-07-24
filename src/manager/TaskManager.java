package manager;

import tasks.Epic;
import tasks.ProgressStatus;
import tasks.Subtask;
import tasks.Task;

import java.util.*;

public class TaskManager {
    private int nextId = 1;

    private int generateId() {
        return nextId++;
    }

    public Task getTask(int id) {
        return taskMap.get(id);
    }

    public Epic getEpic(int id) {
        return epicMap.get(id);
    }

    public Subtask getSubtask(int id) {
        return subtaskMap.get(id);
    }

    private final Map<Integer, Task> taskMap = new HashMap<>();
    private final Map<Integer, Epic> epicMap = new HashMap<>();
    private final Map<Integer, Subtask> subtaskMap = new HashMap<>();

    public Task createTask(String name, String description) {
        Task t = new Task(generateId(), name, description, ProgressStatus.NEW);
        addTask(t);
        return t;
    }

    public Epic createEpic(String name, String description) {
        Epic e = new Epic(generateId(), name, description);
        addEpic(e);
        return e;
    }

    public Subtask createSubtask(String name, String description,
                                 ProgressStatus status, int epicId) {
        Subtask s = new Subtask(generateId(), name, description, status, epicId);
        addSubtask(s);
        return s;
    }


    public void addTask(Task task) {
        taskMap.put(task.getId(), task);
    }


    public void addEpic(Epic epic) {
        epicMap.put(epic.getId(), epic);
    }

    public void addSubtask(Subtask subtask) {
        Epic epic = epicMap.get(subtask.getEpicId());
        if (epic == null) {
            System.out.println("Неверный id.");
            return;
        }
        subtaskMap.put(subtask.getId(), subtask);
        epic.addSubtask(subtask);
        updateEpicStatus(epic.getId());
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(taskMap.values());
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epicMap.values());
    }

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtaskMap.values());
    }

    public List<Subtask> getSubtasksOfEpic(int epicId) {
        Epic epic = epicMap.get(epicId);
        if (epic == null) return Collections.emptyList();
        List<Subtask> list = new ArrayList<>();
        for (int sid : epic.getSubtaskIds()) {
            list.add(subtaskMap.get(sid));
        }

        return list;
    }

    public void updateTaskStatus(int id, ProgressStatus status) {
        if (taskMap.containsKey(id)) {
            taskMap.get(id).setStatus(status);
        } else if (subtaskMap.containsKey(id)) {
            Subtask s = subtaskMap.get(id);
            s.setStatus(status);
            updateEpicStatus(s.getEpicId());
        }
        else {
            System.out.println("Задача не найден");

        }
    }

    public void deleteTask(int id) {
        if (taskMap.remove(id) != null) return;


        if (subtaskMap.containsKey(id)) {

            Subtask s = subtaskMap.remove(id);
            Epic e = epicMap.get(s.getEpicId());
            if (e != null) {
                e.removeSubtask(id);
                updateEpicStatus(e.getId());
            }

            return;
        }

        System.out.println(" задача не найдена.");
    }

    public void deleteEpic(int id) {
        Epic e = epicMap.remove(id);
        if (e != null) {
            for (int sid : e.getSubtaskIds()) {
                subtaskMap.remove(sid);
            }
        } else {
            System.out.println("задача не найдена");
        }
    }

    private void updateEpicStatus(int epicId) {
        Epic e = epicMap.get(epicId);
        if (e == null) return;
        List<Integer> subs = e.getSubtaskIds();
        if (subs.isEmpty()) {
            e.setStatus(ProgressStatus.NEW);
            return;
        }
        boolean allNew = true;
        boolean allDone = true;
        for (int sid : subs) {
            ProgressStatus st = subtaskMap.get(sid).getStatus();
            if (st != ProgressStatus.NEW) allNew = false;
            if (st != ProgressStatus.DONE) allDone = false;
        }
        if (allDone) e.setStatus(ProgressStatus.DONE);
        else if (allNew) e.setStatus(ProgressStatus.NEW);
        else e.setStatus(ProgressStatus.IN_PROGRESS);
    }
}