package com.ganados.fitness.application.FitnessApplication.repositories.user;

import com.ganados.fitness.application.FitnessApplication.model.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(final String email);
}
