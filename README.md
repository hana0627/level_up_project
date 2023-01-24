스프링 내것으로 만들기 프로젝트


이슈
1. jpa설정을 마치고 ddl-auto: update로 설정하였으나 ddl 문이 나가지 않는 현상 발생

 -> application.yml 설정을 jpa.hibernate.. 로 설정하였었음. spring.jpa.hibernate로 변경하니 정상작동


2. 시간측정해주는 Annotation 적용시 의도한 경로로 return 하지 못하고 reuqest 된 url의 경로와 이름을 가진 템플릿을 찾아가는 현상 발생

-> 해결해야함

3. EnumType 저장시 (conn=631) Incorrect integer value 에러가 발생하면서 저장이 안되는 현상

-> hibernate ddl=create로 생성시 별도의 설정이 없으면 db에 Int type으로 설정되는현상. 직접 varchar로 수정하였음.

4. 회원가입 로직 작성중 Service에서 id,pw가 일치하면 객체를 반환하고 그렇지 않으면 null을 반환하도록 하였는데, 항상 null값이 반환되는 이슈

-> 시간측정하는 어노테이션을 AOP로 적용하면서 service객체의 리턴값은 proxy. 실제 객체가 아니여서 null값이 반환되는것으로 추정됨. 해당 어노테이션 삭제하여 실행하니 의도대로 로직이 수행됨.  좀 더 공부하여 보완하기
