package entity;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import enums.Status;

import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;


public class TaskManager {

    private List<Task> taskList = new ArrayList<>();
    private final Path pathFile = Path.of("C:/Users/Usuário/IdeaProjects/TaskTracker/tasks.json");
    private final Gson gson = Converters.registerAll(new GsonBuilder()).create();

    public TaskManager() {
        loadFile();
    }

    private void loadFile() {
        try {
            if (Files.deleteIfExists(pathFile)) {
                Files.createFile(pathFile);
            } else {
                Files.createFile(pathFile);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void addTask(String taskDescription) {
        try {
            Task task = new Task(taskDescription);
            taskList.add(task);
            String json = gson.toJson(getTaskList());
            updateFile(json);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTask(String id, String description) {
        List<Task> taskListUpdate = getTaskList();

        for (Task task : taskListUpdate) {
            if (task.getId().equals(Integer.valueOf(id))) {
                task.setUpdateDescription(description);
                break;
            }
        }
        String json = gson.toJson(taskListUpdate);
        updateFile(json);
    }

    public void deleteTask(String id) {
        List<Task> taskListUpdate = getTaskList();
        taskListUpdate.removeIf(task -> task.getId().equals(Integer.valueOf(id)));
        String json = gson.toJson(taskListUpdate);
        updateFile(json);
    }


    private void updateFile(String taskList) {
        try {
            Files.writeString(pathFile, taskList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateStatus(String status, String id) {
        List<Task> taskListUpdate = getTaskList();

        for (Task task : taskListUpdate) {
            if (task.getId().equals(Integer.valueOf(id))) {
                task.updateStatus(status);
                break;
            }
        }
        String json = gson.toJson(taskListUpdate);
        updateFile(json);
    }


    private List<Task> loadTasks() {
        try {
            Type listType = new TypeToken<ArrayList<Task>>() {
            }.getType();
            return gson.fromJson(Files.readString(pathFile), listType);
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    private void filterAndPrintTasks(Status status) {
        for (Task task : loadTasks()) {
            if (task.getStatus() == status) {
                System.out.println(task);
            }
        }
    }

    public void showAllTasks() {
        for (Task task : loadTasks()) {
            System.out.println(task.toString());
        }
    }

    public void showDoneTasks() {
        filterAndPrintTasks(Status.DONE);
    }

    public void showToDoTasks() {
        filterAndPrintTasks(Status.TO_DO);
    }

    public void showInProgressTasks() {
        filterAndPrintTasks(Status.IN_PROGRESS);
    }
}

