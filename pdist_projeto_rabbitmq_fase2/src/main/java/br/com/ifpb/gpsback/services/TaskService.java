package br.com.ifpb.gpsback.services;

import br.com.ifpb.gpsback.model.Task;
import br.com.ifpb.gpsback.model.User;
import br.com.ifpb.gpsback.repository.TaskRepository;
import br.com.ifpb.gpsback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public void create(Task task) {
        try{
            taskRepository.save(task);
        }
        catch (Exception e){
            System.out.println("Something went wrong on create task.");
        };
    }

    public void delete(long idtask) {
        try{
            if (taskRepository.getById(idtask) != null) {
                taskRepository.deleteById(idtask);
            }
        }
        catch (Exception e){
            System.out.println("Something went wrong on delete task.");
        };
    }

    public List<Task> findAll() {
        try{
            return taskRepository.findAll();
        }
        catch (Exception e){
            System.out.println("Something went wrong on list all task.");
        };
        return  null;
    }

    public Task findById(long idtask) {
        try{
            return taskRepository.findById(idtask).get();
        }
        catch (Exception e){
            System.out.println("Something went wrong  on fin task by id.");
        };
        return  null;
    }

    public void update(long idtask, Task task) {
        try{
            List<Task> tasks = taskRepository.findAll();
            Task taskEspecifica = tasks.stream().filter(t -> t.getId() == idtask).collect(Collectors.toList()).get(0);
            taskEspecifica.setDate(task.getDate());
            taskEspecifica.setDescription(task.getDescription());
            taskEspecifica.setStatus(task.getStatus());
            taskEspecifica.setTitle(task.getTitle());
            taskRepository.save(taskEspecifica);
        }
        catch (Exception e){
            System.out.println("Something went wrong on update task.");
        };
    }

    public void register(User user) {
        try{
            userRepository.save(user);
        }
        catch (Exception e){
            System.out.println("Something went wrong on create user.");
        };
    }

    public void deleteUser(long iduser) {
        try{
            userRepository.deleteById(iduser);
        }
        catch (Exception e){
            System.out.println("Something went wrong on delete user.");
        };
    }

    public void updateUser(long iduser, User user) {
        try{
            User userAux = userRepository.findById(iduser).get();
            userAux.setName(user.getName());
            userAux.setEmail(user.getEmail());
            userAux.setPassword(user.getPassword());
            userRepository.save(userAux);
        }
        catch (Exception e){
            System.out.println("Something went wrong on update user.");
        };
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(long iduser) {
        return userRepository.findById(iduser).get();
    }

    public User login(User user) {
        User userAux = userRepository.findByEmail(user.getEmail());
        if (userAux.getPassword().equals(user.getPassword())) {
            return  userAux;
        }
        return null;
    }
}
