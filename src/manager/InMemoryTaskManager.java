package manager;

import tasks.*;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {

    private int nextId = 1;
    private final Map<Integer, Task> taskMap = new HashMap<>();
    private final Map<Integer, Epic> epicMap = new HashMap<>();
    private final Map<Integer, Subtask> subtaskMap = new HashMap<>();
    private final HistoryManager history;

    public InMemoryTaskManager(HistoryManager history) {
        this.history = Objects.requireNonNull(history, "history");
    }

    private int generateId() {
        return nextId++;
    }

    @Override
    public int addNewTask(Task task) {
        int id = generateId();
        task.setId(id);
        taskMap.put(id, task);
        return id;
    }

    @Override
    public int addNewEpic(Epic epic) {
        int id = generateId();
        epic.setId(id);
        epicMap.put(id, epic);
        return id;
    }

    @Override
    public int addNewSubtask(Subtask subtask) {
        if (subtask.getEpicId() == subtask.getId())
            throw new IllegalArgumentException("Subtask не может ссылаться на себя как на эпик.");
        Epic epic = epicMap.get(subtask.getEpicId());
        if (epic == null)
            throw new IllegalArgumentException("Эпик не найден: " + subtask.getEpicId());
        int id = generateId();
        subtask.setId(id);
        subtaskMap.put(id, subtask);
        epic.addSubtask(subtask);
        updateEpicStatus(epic.getId());
        return id;
    }

    @Override
    public Task getTask(int id) {
        Task t = taskMap.get(id);
        if (t != null) history.add(t);
        return t;
    }

    @Override
    public Epic getEpic(int id) {
        Epic e = epicMap.get(id);
        if (e != null) history.add(e);
        return e;
    }

    @Override
    public Subtask getSubtask(int id) {
        Subtask s = subtaskMap.get(id);
        if (s != null) history.add(s);
        return s;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(taskMap.values());
    }

    @Override
    public List<Epic> getEpics() {
        return new ArrayList<>(epicMap.values());
    }

    @Override
    public List<Subtask> getSubtasks() {
        return new ArrayList<>(subtaskMap.values());
    }

    @Override
    public List<Subtask> getSubtasksOfEpic(int epicId) {
        Epic epic = epicMap.get(epicId);
        if (epic == null) return List.of();
        List<Subtask> list = new ArrayList<>();
        for (int sid : epic.getSubtaskIds()) {
            Subtask s = subtaskMap.get(sid);
            if (s != null) list.add(s);
        }
        return list;
    }

    @Override
    public void updateTaskStatus(int id, ProgressStatus status) {
        if (taskMap.containsKey(id)) {
            taskMap.get(id).setStatus(status);
        } else if (subtaskMap.containsKey(id)) {
            Subtask s = subtaskMap.get(id);
            s.setStatus(status);
            updateEpicStatus(s.getEpicId());
        } else {
            System.out.println("Задача не найдена");
        }
    }

    @Override
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
        System.out.println("Задача не найдена.");
    }

    @Override
    public void deleteEpic(int id) {
        Epic e = epicMap.remove(id);
        if (e != null) {
            for (int sid : e.getSubtaskIds()) subtaskMap.remove(sid);
        } else {
            System.out.println("Эпик не найден");
        }
    }

    @Override
    public void deleteSubtask(int id) {
        Subtask s = subtaskMap.remove(id);
        if (s != null) {
            Epic e = epicMap.get(s.getEpicId());
            if (e != null) {
                e.removeSubtask(id);
                updateEpicStatus(e.getId());
            }
        } else {
            System.out.println("Подзадача не найдена");
        }
    }

    private void updateEpicStatus(int epicId) {
        Epic e = epicMap.get(epicId);
        if (e == null) return;
        var subs = e.getSubtaskIds();
        if (subs.isEmpty()) {
            e.setStatus(ProgressStatus.NEW);
            return;
        }
        boolean allNew = true, allDone = true;
        for (int sid : subs) {
            ProgressStatus st = subtaskMap.get(sid).getStatus();
            if (st != ProgressStatus.NEW) allNew = false;
            if (st != ProgressStatus.DONE) allDone = false;
        }
        if (allDone) e.setStatus(ProgressStatus.DONE);
        else if (allNew) e.setStatus(ProgressStatus.NEW);
        else e.setStatus(ProgressStatus.IN_PROGRESS);
    }

    @Override
    public List<Task> getHistory() {
        return history.getHistory();
    }
}
