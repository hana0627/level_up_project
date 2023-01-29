package com.hana.springboot.global.formatter;

import com.querydsl.core.annotations.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

@Slf4j
public class DateTypeFormatter implements Formatter<LocalDateTime> {
    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {

        // SimpleDateFormat 생성
        String datePattern = "yyyy-MM-dd";
        //TODO String[] 으로 설정하여 패턴을 여러개 등록 후
        // 일치하는 Pattern 나올때까지 반복문 돌리는 방법도 있을것 같음
        SimpleDateFormat format = new SimpleDateFormat(datePattern);


        try {
            Date date = format.parse(text);

            LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            return localDateTime;
        } catch (ParseException e) {
            System.err.println("dateStr : " + text + ", datePattern:" + datePattern);
            e.printStackTrace();
        }
        return null;


    }

    @Override
    public String print(LocalDateTime object, Locale locale) {
        return null;
    }
}


