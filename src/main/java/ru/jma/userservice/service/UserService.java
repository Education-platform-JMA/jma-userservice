package ru.jma.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.jma.userservice.model.User;
import ru.jma.userservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Mono<User> addUser(User user) {
        return userRepository.save(user);
    }

    public Mono<User> updateUser(Long id, User user) {
        return getUserById(id).map(userToUpdate -> user.withId(userToUpdate.getId()))
                              .flatMap(userRepository::save);
    }

    public Mono<Void> deleteUserById(Long id) {
        return getUserById(id).flatMap(userRepository::delete);
    }

}
