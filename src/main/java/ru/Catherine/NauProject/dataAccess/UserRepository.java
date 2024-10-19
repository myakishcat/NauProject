package ru.Catherine.NauProject.dataAccess;

import org.springframework.data.repository.CrudRepository;
import ru.Catherine.NauProject.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
