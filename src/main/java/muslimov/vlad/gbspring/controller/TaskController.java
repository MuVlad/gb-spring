package muslimov.vlad.gbspring.controller;

import lombok.RequiredArgsConstructor;
import muslimov.vlad.gbspring.dto.TaskCreateDto;
import muslimov.vlad.gbspring.dto.TaskDto;
import muslimov.vlad.gbspring.model.TaskStatus;
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
    public HttpEntity<List<TaskDto>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/status")
    public HttpEntity<List<TaskDto>> getTasksByStatus(@RequestParam TaskStatus taskStatus) {
        return ResponseEntity.ok(taskService.getTasksByStatus(taskStatus));
    }

    @GetMapping("/{id}")
    public HttpEntity<TaskDto> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PostMapping
    public HttpEntity<TaskDto> createTask(@RequestBody TaskCreateDto task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/{id}")
    public HttpEntity<TaskDto> editTaskStatus(@PathVariable Long id, @RequestParam TaskStatus taskStatus) {
        return ResponseEntity.ok(taskService.editTaskStatus(id, taskStatus));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}