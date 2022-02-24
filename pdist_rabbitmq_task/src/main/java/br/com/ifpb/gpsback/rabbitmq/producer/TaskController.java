package br.com.ifpb.gpsback.rabbitmq.producer;

import br.com.ifpb.gpsback.model.Task;
import br.com.ifpb.gpsback.model.TaskAuxiliar;
import br.com.ifpb.gpsback.rabbitmq.ConfigureRabbitMq;
import br.com.ifpb.gpsback.services.TaskService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/task")
public class TaskController {

    private final RabbitTemplate rabbitTemplate;

    public TaskController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Autowired
    private TaskService taskService;

    public String sendMessage(TaskAuxiliar taskAux) {

        rabbitTemplate.convertAndSend(ConfigureRabbitMq.EXCHANGE_NAME,
                "mike.springmessages", taskAux);
        return "We have sent a message! :" + "";
    }

    @PostMapping("/create")
    public void create(@RequestBody Task task) {
        TaskAuxiliar taskCr = new TaskAuxiliar("create", task);
        sendMessage(taskCr);
    }

    @DeleteMapping(path = {"/delete/{idtask}"})
    public void delete(@PathVariable long idtask) {
        TaskAuxiliar taskUp = new TaskAuxiliar("delete", idtask);
        sendMessage(taskUp);
    }

    @GetMapping(path = {"/"})
    public List<Task> findAll() {
        if (taskService.findAll().size() < 1) {
            for (int i = 0; i < 10; i++) {
                Task t = new Task("teste" + i, "description" + i, "TODO", "03/12/2021");
                taskService.create(t);
            }
        }
        return taskService.findAll();
    }

    @GetMapping(path= {"/{idtask}"})
    public Task findById(@PathVariable long idtask) {
        return taskService.findById(idtask);
    }

    @PutMapping(value = "/update/{idtask}")
    public Task update(@PathVariable long idtask, @RequestBody Task task) throws CloneNotSupportedException {
        return taskService.update(idtask, task);
    }
}
