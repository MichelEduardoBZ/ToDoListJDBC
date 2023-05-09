package com.michel.controller;

import com.michel.entities.Task;
import com.michel.taskDB.TaskDB;

import java.util.List;


public class TaskController {
    public TaskController() {
    }

    Long i = 0L;

    public void newTask(String nameTask, String descp) {
        TaskDB.save(new Task(nameTask, descp, i++));
    }

    public void deleteTask(Long id) {
        TaskDB.delete(id);
    }

    public void editTask(Long id, Integer editTaskNum, String element) {
        TaskDB.update(id, editTaskNum, element);
    }

    public List<Task> showView() {
        return TaskDB.selectAll();
    }

    public Integer validation(Long id) {
        return TaskDB.validationNull(id);
    }

    public List<Task> showViewId(Long id) {
        return TaskDB.selectId(id);
    }
}
