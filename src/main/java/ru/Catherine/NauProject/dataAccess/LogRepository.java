package ru.Catherine.NauProject.dataAccess;

import org.springframework.data.repository.CrudRepository;
import ru.Catherine.NauProject.entity.BookConditionLog;

public interface LogRepository extends CrudRepository<BookConditionLog, Long> {
}
