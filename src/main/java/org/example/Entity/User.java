package org.example.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "user", schema = "tables")
public class User {
    @Id
    private int id;
    private String name;
    private String lastname;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private int age;
    @Enumerated(EnumType.STRING)
    private Role role;
}
