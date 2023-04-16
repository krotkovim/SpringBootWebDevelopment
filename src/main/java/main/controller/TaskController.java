package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import main.model.Task;
import main.model.TaskRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class TaskController {


    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public ArrayList<Task> list() {
        Iterable<Task> taskIterable = taskRepository.findAll();

        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    @PostMapping(value = "/tasks", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public HttpStatus add(@RequestBody Task task) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        task.setCreationTime(currentDateTime);
        task.setDone(false);
        taskRepository.save(task);
        return HttpStatus.CREATED;
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(optionalTask.get(), HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Task taskToDelete = optionalTask.get();
        taskRepository.delete(taskToDelete);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PatchMapping(value = "/tasks/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateTask(@PathVariable int id, @RequestBody Task requiredTask) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Task taskToUpdate = optionalTask.get();
        taskToUpdate.setTitle(requiredTask.getTitle());
        taskToUpdate.setDescription(requiredTask.getDescription());
        taskToUpdate.setDone(requiredTask.isDone());


        taskRepository.save(taskToUpdate);


        return new ResponseEntity<>(HttpStatus.OK);
    }
}





