package muslimov.vlad.gbspring.mapper;

import muslimov.vlad.gbspring.dto.TaskCreateDto;
import muslimov.vlad.gbspring.dto.TaskDto;
import muslimov.vlad.gbspring.model.Task;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TaskMapper {
    Task toEntity(TaskCreateDto taskCreateDto);

    TaskDto toDto(Task task);
}
