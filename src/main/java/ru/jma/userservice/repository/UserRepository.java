package ru.jma.userservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.jma.userservice.model.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}
