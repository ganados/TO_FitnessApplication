package com.ganados.fitness.application.FitnessApplication.model.user;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.ganados.fitness.application.FitnessApplication.model.training.base.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    private String firstname;
    private String lastname;

    @Column(name = "email")
    private String email;
    private String password;
    private String role;

    @Builder
    public User(final String firstname, final String lastname, final String email, final String password, final String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, email, password);
    }
}
