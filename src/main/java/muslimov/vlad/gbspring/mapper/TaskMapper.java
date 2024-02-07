package muslimov.vlad.gbspring.mapper;

import muslimov.vlad.gbspring.dto.TaskCreateDto;
import muslimov.vlad.gbspring.dto.TaskDto;
import muslimov.vlad.gbspring.model.Task;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface TaskMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "status", ignore = true),
    })
    Task toEntity(TaskCreateDto taskCreateDto);

    TaskDto toDto(Task task);
}
