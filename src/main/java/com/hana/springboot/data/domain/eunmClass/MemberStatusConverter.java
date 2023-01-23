package com.hana.springboot.data.domain.eunmClass;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter
public class MemberStatusConverter implements AttributeConverter<MemberStatus,String> {
    @Override
    public String convertToDatabaseColumn(MemberStatus status) {
        if(status == null) {
            return null;
        }
        return status.getDbValue();
    }

    @Override
    public MemberStatus convertToEntityAttribute(String dbData) {
        return Arrays.stream(MemberStatus.values())
                .filter(s -> s.getDbValue().equals(dbData))
                .findFirst().orElseThrow();
    }
}
