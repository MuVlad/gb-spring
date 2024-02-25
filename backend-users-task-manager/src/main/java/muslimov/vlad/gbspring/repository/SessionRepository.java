package muslimov.vlad.gbspring.repository;

import muslimov.vlad.gbspring.model.Session;
import muslimov.vlad.gbspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByUserName(String username);

    Optional<Session> save(User user);
}
