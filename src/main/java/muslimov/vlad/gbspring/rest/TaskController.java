package muslimov.vlad.gbspring.rest;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Получение списка задач")
    public HttpEntity<List<TaskDto>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/status")
    @Operation(summary = "Получение списка задач по статусу")
    public HttpEntity<List<TaskDto>> getTasksByStatus(@RequestParam TaskStatus taskStatus) {
        return ResponseEntity.ok(taskService.getTasksByStatus(taskStatus));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение задачи по id")
    public HttpEntity<TaskDto> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PostMapping
    @Operation(summary = "Создание новой задачи")
    public HttpEntity<TaskDto> createTask(@RequestBody TaskCreateDto task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование статуса задачи")
    public HttpEntity<TaskDto> editTaskStatus(@PathVariable Long id, @RequestParam TaskStatus taskStatus) {
        return ResponseEntity.ok(taskService.editTaskStatus(id, taskStatus));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление задачи")
    public HttpEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}/users/{userId}")
    @Operation(summary = "Назначение исполнителя задачи")
    public HttpEntity<TaskDto> assignUserToTask(
        @PathVariable Long taskId,
        @PathVariable Long userId
    ) {
        return ResponseEntity.ok(taskService.assignUser(taskId, userId));
    }
}