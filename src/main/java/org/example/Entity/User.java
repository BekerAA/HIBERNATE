package org.example.Entity;

import lombok.*;
import org.example.Entity.NoEntity.Birthday;
import org.example.Entity.NoEntity.Role;
import org.example.converter.BirthdayConverter;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "tables")
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    @Embedded
    private PersonalInfo personalInfo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "company_id")
    private Integer companyId;
}
