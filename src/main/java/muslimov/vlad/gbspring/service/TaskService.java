package muslimov.vlad.gbspring.service;

import muslimov.vlad.gbspring.dto.TaskDto;
import muslimov.vlad.gbspring.model.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    private final Map<Long, Task> repository;
    private Long countId;

    public TaskService() {
        this.repository = new HashMap<>();
        this.countId = 1L;
    }

    public List<Task> getTasks() {
        return repository.values().stream().toList();
    }

    public Task createTask(TaskDto task) {
        Task newTask = Task
                .builder()
                .id(countId++)
                .name(task.name())
                .description(task.description())
                .createAt(LocalDateTime.now())
                .build();

        return repository.put(newTask.id(), newTask);
    }

    public Task getTask(Long id) {
        return repository.get(id);
    }
}
