package org.example;

import org.example.Entity.NoEntity.Birthday;
import org.example.Entity.NoEntity.Role;
import org.example.Entity.User;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

class HibernateRunningTest {
    @Test
    public void testConnectHibernate(){

        User user = User.builder()
                .name("Larisa")
                .lastname("Acopin")
                .birthday( new Birthday (LocalDate.of(2004, 12, 21)))
                .role(Role.CREATED)
                .build();

        var sql ="""
                INSERT INTO
                %s
                (%s)
                values
                (%s)
                """;

        var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());

        Field[] fields = user.getClass().getDeclaredFields();
        var columnName = Arrays.stream(fields)
                .map( field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName())
                ).collect(Collectors.joining(", "));


        var columnValue = Arrays.stream(fields).map(field -> "?").collect(Collectors.joining(", "));

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tables", "andrey", "14555555");
            PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, columnName, columnValue)))
        {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                preparedStatement.setObject(i + 1, fields[i].get(user));
            }
            preparedStatement.executeUpdate();
        }catch (SQLException | IllegalAccessException e){
            throw new RuntimeException(e);
        }

    }


}