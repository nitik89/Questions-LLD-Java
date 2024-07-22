package taskManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class TaskManager {
    private static TaskManager instance;
    private final Map<String,Task>tasks;
    private final Map<String,List<Task>>userTasks;

    private TaskManager(){
        tasks=new ConcurrentHashMap<>();
        userTasks=new ConcurrentHashMap<>();
    }
    public static synchronized TaskManager getInstance(){
        if(instance==null){
            instance=new TaskManager();
        }
        return instance;
    }

    public void addTask(Task task){
        tasks.put(task.getId(),task);
        assignTask(task,task.getAssignedUser());
    }
    public Task getTask(String taskId){
        return tasks.get(taskId);
    }

    public void assignTask(Task task,User user){
         userTasks.computeIfAbsent(user.getId(), k -> new CopyOnWriteArrayList<>()).add(task);
    }
    
    public void updateTask(Task updatedTask){
        Task existingTask = tasks.get(updatedTask.getId());

        if(existingTask != null){
            synchronized (existingTask) {
                existingTask.setTitle(updatedTask.getTitle());
                existingTask.setDescription(updatedTask.getDescription());
                existingTask.setDueDate(updatedTask.getDueDate());
                existingTask.setPriority(updatedTask.getPriority());
                existingTask.setStatus(updatedTask.getStatus());
                User previousUser = existingTask.getAssignedUser();
                User newUser = updatedTask.getAssignedUser();
                if(!previousUser.equals(newUser)){
                    unassignTaskFromUser(previousUser, existingTask);
                    assignTask(existingTask, newUser);
                }
                
            }
        }
    }

    public void deleteTask(String taskId){
        Task task=tasks.remove(taskId);
        if(task!=null){
            unassignTaskFromUser(task.getAssignedUser(),task);
        }
    }

    public List<Task> searchTasks(String keyword){
        List<Task> results=new CopyOnWriteArrayList<>();
        for(Task task:tasks.values()){
            if(task.getTitle().contains(keyword) || task.getDescription().contains(keyword)){
            
                results.add(task);
            }
        }
        return results;
    }

    public List<Task> filterTasks(TaskStatus status,Date startDate,Date endDate,int priority){
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getStatus() == status &&
                    task.getDueDate().compareTo(startDate) >= 0 &&
                    task.getDueDate().compareTo(endDate) <= 0 &&
                    task.getPriority() == priority) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    private void unassignTaskFromUser(User user, Task task){
        List<Task> userTasks=this.userTasks.get(user.getId());
        if(userTasks!=null){
            userTasks.remove(task);
        }
    }

    public void markTaskCompleted(String taskId){
        Task task=tasks.get(taskId);
        if(task!=null){
            synchronized (task) {
                task.setStatus(TaskStatus.COMPLETED);
            }
        }
    }
    public List<Task> getTaskHistory(User user){
        return userTasks.getOrDefault(user.getId(), new CopyOnWriteArrayList<>());
    }





}
