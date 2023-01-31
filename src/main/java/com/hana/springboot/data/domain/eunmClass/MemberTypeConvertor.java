package com.hana.springboot.data.domain.eunmClass;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter
public class MemberTypeConvertor implements AttributeConverter<MemberType,String> {
    @Override
    public String convertToDatabaseColumn(MemberType status) {
        if(status == null) {
            return null;
        }
        return status.getDbValue();
    }

    @Override
    public MemberType convertToEntityAttribute(String dbData) {
        return Arrays.stream(MemberType.values())
                .filter(s -> s.getDbValue().equals(dbData))
                .findFirst().orElseThrow();
    }
}
