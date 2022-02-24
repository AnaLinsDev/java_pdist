package br.com.ifpb.gpsback.services;

import br.com.ifpb.gpsback.model.Task;
import br.com.ifpb.gpsback.model.Usuario;
import br.com.ifpb.gpsback.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UsuarioService usuarioService;


    public Task create(long idusu, Task task) {

        Usuario usuario = usuarioService.findById(idusu).get(0);
        task.setUsuario(usuario);
        usuario.adicionarTask(task);
        taskRepository.save(task);
        usuarioService.update(idusu, usuario);

        return task;
    }

    public ResponseEntity delete(long idusu, long idtask) {

        Usuario usuario = usuarioService.findById(idusu).get(0);
        if (usuario instanceof Usuario) {
            usuario.removerTask(taskRepository.getById(idtask));
            taskRepository.delete(taskRepository.getById(idtask));
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<Task> findTasksByIdUser(long idusu) {
        return usuarioService.findById(idusu).get(0).getTasks();
    }

    public Task update(long idusu, long idtask,  Task task) {

        List<Task> tasksUsuario = usuarioService.findById(idusu).get(0).getTasks();
        Task taskEspecifica = null;
        for (Task taskUsu : tasksUsuario) {
            if (taskUsu.getId() == idtask) {
                taskEspecifica = taskUsu;
                taskEspecifica.setDate(task.getDate());
                taskEspecifica .setDescription(task.getDescription());
                taskEspecifica.setStatus(task.getStatus());
                taskEspecifica.setTitle(task.getTitle());
                taskRepository.save(taskEspecifica);
            }
        }
        return taskEspecifica;
    }


    public List<Task> findByIdUser_IdTask(long idusu, long idtask) {
        List<Task> task = usuarioService.findById(idusu).get(0).getTasks();
        List<Task> tasks = new ArrayList<>();
        task.forEach(t -> {
            if (t.getId() == idtask) {
                tasks.add(t);
            }
        });
        return tasks;
    }

}
