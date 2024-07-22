package taskManagement;

import java.util.Date;

public class Task {
    private final String id;
    private String title;
    private String description;
    private Date dueDate;
    private int priority;
    private TaskStatus status;
    private final User assignedUser;

    //constuctor
    public Task(String id, String title, String description, Date dueDate,int priority,User assignedUser){
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = 0;
        this.status = TaskStatus.PENDING;
        this.assignedUser = assignedUser;
    }

    //Getter and setter methods
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User getAssignedUser() {
        return assignedUser;
    }



}
