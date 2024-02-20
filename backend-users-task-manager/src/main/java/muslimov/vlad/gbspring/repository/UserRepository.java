package muslimov.vlad.gbspring.repository;

import muslimov.vlad.gbspring.exception.model.NotFoundException;
import muslimov.vlad.gbspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
            () -> new NotFoundException("Пользователь с ID:" + id + " не найден!")
        );
    }

    Optional<User> findByName(String name);
}
