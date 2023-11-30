package org.users.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users_table")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Size(min = 3, message = "Username is too short.")
    @Size(max = 20, message = "Username is too long.")
    String username;

    @Size(min = 1, message = "Name is too short.")
    @Size(max = 20, message = "Name is too long.")
    String name;

    @Size(min = 1, message = "Surname is too short.")
    @Size(max = 30, message = "Surname is too long.")
    String surname;
}
