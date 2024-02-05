package muslimov.vlad.gbspring.mapper;

import muslimov.vlad.gbspring.dto.UserCreateDto;
import muslimov.vlad.gbspring.dto.UserDto;
import muslimov.vlad.gbspring.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {
    User toEntity(UserCreateDto userCreateDto);

    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}
