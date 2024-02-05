package muslimov.vlad.gbspring.service;

import lombok.RequiredArgsConstructor;
import muslimov.vlad.gbspring.dto.TaskCreateDto;
import muslimov.vlad.gbspring.dto.TaskDto;
import muslimov.vlad.gbspring.mapper.TaskMapper;
import muslimov.vlad.gbspring.mapper.UserMapper;
import muslimov.vlad.gbspring.model.Task;
import muslimov.vlad.gbspring.model.TaskStatus;
import muslimov.vlad.gbspring.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    TaskRepository taskRepository;
    TaskMapper taskMapper;
    UserService userService;
    UserMapper userMapper;

    public List<TaskDto> getTasks() {
        return taskRepository
            .findAll()
            .stream()
            .map(taskMapper::toDto)
            .toList();
    }

    public List<TaskDto> getTasksByStatus(TaskStatus taskStatus) {
        return taskRepository
            .findTaskByStatus(taskStatus)
            .stream()
            .map(taskMapper::toDto)
            .toList();
    }

    public TaskDto getTask(Long id) {
        return taskMapper.toDto(taskRepository.findByIdOrThrow(id));
    }

    public TaskDto createTask(TaskCreateDto task) {
        return taskMapper.toDto(taskRepository.save(taskMapper.toEntity(task)));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskDto editTaskStatus(Long id, TaskStatus taskStatus) {
        Task task = taskRepository.findByIdOrThrow(id);
        task.setStatus(taskStatus);
        return taskMapper.toDto(task);
    }

    public TaskDto assignUser(Long taskId, Long userId) {
        final var task = taskRepository.findByIdOrThrow(taskId);
        final var user = userService.getUserById(userId);
        task.setUser(userMapper.toEntity(user));
        return taskMapper.toDto(taskRepository.save(task));
    }
}
