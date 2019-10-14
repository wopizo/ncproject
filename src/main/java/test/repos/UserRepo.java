package test.repos;

import org.springframework.data.repository.CrudRepository;
import test.domain.User;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Integer> {

    List<User> findByLogin(String login);

}
