스프링 내것으로 만들기 프로젝트


이슈
1. jpa설정을 마치고 ddl-auto: update로 설정하였으나 ddl 문이 나가지 않는 현상 발생
 -> application.yml 설정을 jpa.hibernate.. 로 설정하였었음. spring.jpa.hibernate로 변경하니 정상작동
