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
            // 문자열을 Date 객체로 파싱
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
        log.info("반갑습니다.");
        return null;
    }
}



        /*
        log.info("안녕하세요");
        log.info("text = {}" , date);
        //LocalDateTime parse = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.valueOf("yyyy-MM-dd"));
        TemporalAccessor parse = formatter.parse(date);

        System.out.println("확인 " + parse);
        return null;
         */