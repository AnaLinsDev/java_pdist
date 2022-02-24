package br.com.ifpb.gpsback.services;

import br.com.ifpb.gpsback.model.Task;
import br.com.ifpb.gpsback.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void create(Task task) {
        taskRepository.save(task);
    }

    public void delete(long idtask) {
        if (taskRepository.getById(idtask) != null) {
            taskRepository.deleteById(idtask);
        }
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(long idtask) {
        return taskRepository.findById(idtask).get();
    }

    public Task update(long idtask, Task task) {
        List<Task> tasks = taskRepository.findAll();
        Task taskEspecifica = tasks.stream().filter(t -> t.getId() == idtask).collect(Collectors.toList()).get(0);
        taskEspecifica.setDate(task.getDate());
        taskEspecifica.setDescription(task.getDescription());
        taskEspecifica.setStatus(task.getStatus());
        taskEspecifica.setTitle(task.getTitle());
        taskRepository.save(taskEspecifica);
        return taskEspecifica;
    }
}
