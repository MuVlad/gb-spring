package muslimov.vlad.gbspring.dto;

import muslimov.vlad.gbspring.model.TaskStatus;

import java.time.LocalDateTime;

public record TaskDto(
        Long id,
        String name,
        String description,
        LocalDateTime createAt,
        TaskStatus status) {
}
