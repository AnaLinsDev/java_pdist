package br.com.ifpb.gpsback.controller;

import br.com.ifpb.gpsback.model.Task;
import br.com.ifpb.gpsback.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("adicionartask/{idusu}")
    public Task create(@PathVariable long idusu, @RequestBody Task task) {
        return taskService.create(idusu, task);
    }

    @DeleteMapping(path = { "{idusu}/removertask/{idtask}" })
    public ResponseEntity delete(@PathVariable long idusu, @PathVariable long idtask) {
        return taskService.delete(idusu,idtask);
    }


    @GetMapping(path = { "/{idusu}" })
    public List<Task> findTasksByIdUser(@PathVariable long idusu) {
        return taskService.findTasksByIdUser(idusu);
    }


    @PutMapping(value = "{idusu}/atualizartask/{idtask}")
    public Task update(@PathVariable long idusu, @PathVariable long idtask, @RequestBody Task task) {
        return taskService.update(idusu,idtask,task);
    }


    @GetMapping(path = { "/{idusu}/tasks/{idtask}" })
    public List<Task> findById2(@PathVariable long idusu, @PathVariable long idtask) {
        return taskService.findByIdUser_IdTask(idusu,idtask);
    }


}
