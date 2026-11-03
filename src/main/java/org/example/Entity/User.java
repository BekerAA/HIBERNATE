package org.example.Entity;

import lombok.*;
import org.example.converter.BirthdayConverter;

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

    @Convert(converter = BirthdayConverter.class)
    @Column(name = "birth_date")
    private Birthday birthday;

    @Enumerated(EnumType.STRING)
    private Role role;
}
