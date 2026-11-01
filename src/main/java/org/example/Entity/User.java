package org.example.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    private int id;
    private String name;
    private String lastname;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private int age;
}
