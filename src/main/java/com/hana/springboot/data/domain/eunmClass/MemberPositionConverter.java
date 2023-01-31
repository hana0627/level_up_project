//package com.hana.springboot.data.domain.eunmClass;
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//import java.util.Arrays;
//
//@Converter
//public class MemberPositionConverter implements AttributeConverter<MemberPosition,String> {
//    @Override
//    public String convertToDatabaseColumn(MemberPosition position) {
//        if(position == null) {
//            return null;
//        }
//        return position.getDbValue();
//    }
//
//    @Override
//    public MemberPosition convertToEntityAttribute(String dbData) {
//        return Arrays.stream(MemberPosition.values())
//                .filter(s -> s.getDbValue().equals(dbData))
//                .findFirst().orElseThrow();
//    }
//}
