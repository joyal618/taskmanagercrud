package com.dexlock.javacrud.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Task {
        private UUID taskNo;
        private String taskTitle;
        public enum type{Feature,Subtask,Enhancement,Bug,Task}
        private type taskType;
        private String taskDescription;
        private String taskCriteria;
        private String taskStatus;
        private String taskStartDate;
        private String taskDueDate;
        private Float taskOriginalEstimate;
        private String taskAssignee;
        private String taskReporter;

    public void setTaskNo(UUID taskNo) {
        this.taskNo = taskNo;
    }

    public List<String> getFileLoc() {
        return fileLoc;
    }

    public void setFileLoc(List<String> fileLoc) {
        this.fileLoc = fileLoc;
    }

    private List<String> fileLoc;
        public Task(){
            this.taskNo = UUID.randomUUID();
        }

        public Task(String taskTitle, type taskType, String taskDescription, String taskCriteria,
                    String taskStatus, String taskStartDate, String taskDueDate, Float taskOriginalEstimate,
                    String taskAssignee, String taskReporter) {
        this.taskNo = UUID.randomUUID();
        this.taskTitle = taskTitle;
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskCriteria = taskCriteria;
        this.taskStatus = taskStatus;
        this.taskStartDate = taskStartDate;
        this.taskDueDate = taskDueDate;
        this.taskOriginalEstimate = taskOriginalEstimate;
        this.taskAssignee = taskAssignee;
        this.taskReporter = taskReporter;
    }

    public UUID getTaskNo() {
        return taskNo;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public type getTaskType() {
        return taskType;
    }

    public void setTaskType(type taskType) {
        this.taskType = taskType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskCriteria() {
        return taskCriteria;
    }

    public void setTaskCriteria(String taskCriteria) {
        this.taskCriteria = taskCriteria;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(String taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public String getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(String taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public Float getTaskOriginalEstimate() {
        return taskOriginalEstimate;
    }

    public void setTaskOriginalEstimate(Float taskOriginalEstimate) {
        this.taskOriginalEstimate = taskOriginalEstimate;
    }

    public String getTaskAssignee() {
        return taskAssignee;
    }

    public void setTaskAssignee(String taskAssignee) {
        this.taskAssignee = taskAssignee;
    }

    public String getTaskReporter() {
        return taskReporter;
    }

    public void setTaskReporter(String taskReporter) {
        this.taskReporter = taskReporter;
    }
}
