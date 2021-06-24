package com.example.springboot.page.project;

import javax.persistence.AttributeConverter;
import java.util.Arrays;

public class ProgramAttributeConverter implements AttributeConverter<Program, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Program indexedEnum) {
        return indexedEnum.getValue();
    }

    @Override
    public Program convertToEntityAttribute(Integer integer) {
        return Arrays.stream(Program.values()).filter(x -> x.getValue() == integer).findFirst().orElse(null);
    }
}
