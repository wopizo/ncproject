package test.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import test.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
