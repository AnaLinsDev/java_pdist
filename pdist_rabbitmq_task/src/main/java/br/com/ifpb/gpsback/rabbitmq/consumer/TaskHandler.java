package br.com.ifpb.gpsback.rabbitmq.consumer;

import br.com.ifpb.gpsback.model.Task;
import br.com.ifpb.gpsback.model.TaskAuxiliar;
import br.com.ifpb.gpsback.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;


import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class TaskHandler {

    @Autowired
    private TaskService taskService;

    public void handleMessage(TaskAuxiliar taskaux ){

        if (Objects.equals(taskaux.getMethod(), "findAll")){
            this.findAll();
        }
        else if (Objects.equals(taskaux.getMethod(), "create")){
            this.create(taskaux.getTask());
        }
        else if (Objects.equals(taskaux.getMethod(), "update")){
            this.update(taskaux.getId(), taskaux.getTask());
        }
        else if (Objects.equals(taskaux.getMethod(), "delete")){
            this.delete(taskaux.getId());
        }

    }

    public void create(Task task) {
         taskService.create(task);
    }

    public void delete(long idtask) {
         taskService.delete(idtask);
    }

    public void findAll() {

        log.info(String.valueOf("\n\n----------------\n"));

        taskService.findAll().forEach(f -> log.info(String.valueOf(f.getTitle())));
    }

    public void  update(long idtask, Task task) {
         taskService.update(idtask, task);
    }

}


