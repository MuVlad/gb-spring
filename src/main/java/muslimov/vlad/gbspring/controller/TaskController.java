package muslimov.vlad.gbspring.controller;

import lombok.RequiredArgsConstructor;
import muslimov.vlad.gbspring.dto.TaskDto;
import muslimov.vlad.gbspring.model.Task;
import muslimov.vlad.gbspring.service.TaskService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public HttpEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/{id}")
    public HttpEntity<Task> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }


    @PostMapping
    public HttpEntity<Task> createTask(@RequestBody TaskDto task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }
}
