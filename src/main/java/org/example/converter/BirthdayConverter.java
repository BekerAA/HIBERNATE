package org.example.converter;

import org.example.Entity.Birthday;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.util.Optional;

public class BirthdayConverter  implements AttributeConverter<Birthday, Date> {
    @Override
    public Date convertToDatabaseColumn(Birthday birthday) {
        return Optional.ofNullable(birthday)
                .map(Birthday::birthDate)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public Birthday convertToEntityAttribute(Date data) {
        return Optional.ofNullable(data)
                .map(Date::toLocalDate)
                .map(Birthday::new)
                .orElse(null);
    }
}
