package muslimov.vlad.gbspring.repository;

import muslimov.vlad.gbspring.model.User;
import muslimov.vlad.gbspring.model.UserCreateDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class UserRepository {
    private final Map<Long, User> repository = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public List<User> getAllUsers() {
        return repository.values().stream().toList();
    }

    public User getUser(Long id) {
        return repository.get(id);
    }

    public User createUser(UserCreateDto userCreateDto) {
        User newUser = User
                .builder()
                .id(idCounter.incrementAndGet())
                .name(userCreateDto.name())
                .email(userCreateDto.email())
                .build();

        repository.put(newUser.getId(), newUser);

        return newUser;
    }
}
