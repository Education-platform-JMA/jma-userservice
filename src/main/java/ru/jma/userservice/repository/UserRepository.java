package ru.jma.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jma.userservice.model.User;

public interface UserRepository extends JpaRepository<User,Long> {}
