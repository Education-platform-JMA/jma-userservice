package ru.jma.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.jma.userservice.model.User;
import ru.jma.userservice.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    public Mono<User> update(Long id, User user) {
        return findById(id).map(animeToUpdate -> user.withId(animeToUpdate.getId()))
                           .flatMap(userRepository::save);
    }

    public Mono<Void> deleteById(Long id) {
        return findById(id).flatMap(userRepository::delete);
    }

}
