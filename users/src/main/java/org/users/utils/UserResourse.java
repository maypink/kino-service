package org.users.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class UserResourse {

    UUID id;

    String username;

    String name;

    String surname;

}
