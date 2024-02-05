package muslimov.vlad.gbspring.repository;

import muslimov.vlad.gbspring.model.Task;
import muslimov.vlad.gbspring.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    default Task findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new RuntimeException("Задача с ID:" + id + " не найден!")
        );
    }

    List<Task> findTaskByStatus(TaskStatus status);
}
