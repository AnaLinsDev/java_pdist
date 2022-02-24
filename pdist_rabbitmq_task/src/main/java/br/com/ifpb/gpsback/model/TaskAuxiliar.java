package br.com.ifpb.gpsback.model;

import java.io.Serializable;

public class TaskAuxiliar implements Serializable {

    private String method;
    private Task task;
    private long id;

    public TaskAuxiliar(String method) {
        this.method = method;
    }

    public TaskAuxiliar(String method, long id) {
        this.method = method;
        this.id = id;
    }

    public TaskAuxiliar(String method, Task task) {
        this.method = method;
        this.task = task;
    }

    public TaskAuxiliar(String method, Task task, long id) {
        this.method = method;
        this.task = task;
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
