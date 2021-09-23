package ru.jma.userservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@With
public class User {

    @Id
    private Long id;

    private String username;

    private String password;

}
