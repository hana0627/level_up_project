package com.hana.springboot.data.domain.eunmClass;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter
public class OrderStatusConvertor implements AttributeConverter<OrderStatus,String> {


    @Override
    public String convertToDatabaseColumn(OrderStatus status) {
        if(status == null) {
            return null;
        }
        return status.getDbValue();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String dbData) {
        return Arrays.stream(OrderStatus.values())
                .filter(s -> s.getDbValue().equals(dbData))
                .findFirst().orElseThrow();
    }
}
