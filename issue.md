이슈


1. 시간측정해주는 Annotation 적용시 의도한 경로로 return 하지 못하고 reuqest 된 url의 경로와 이름을 가진 템플릿을 찾아가는 현상 발생

   -> 해결해야함

2. EnumType 저장시 (conn=631) Incorrect integer value 에러가 발생하면서 저장이 안되는 현상

   -> hibernate ddl=create로 생성시 별도의 설정이 없으면 db에 Int type으로 설정되는현상. 직접 varchar로 수정하였음.

3. 회원가입 로직 작성중 Service에서 id,pw가 일치하면 객체를 반환하고 그렇지 않으면 null을 반환하도록 하였는데, 항상 null값이 반환되는 이슈

   -> 시간측정하는 어노테이션을 AOP로 적용하면서 service객체의 리턴값은 proxy. 실제 객체가 아니여서 null값이 반환되는것으로 추정됨. 해당 어노테이션 삭제하여 실행하니 의도대로 로직이 수행됨.

       -> pratice1. 후에 리팩토링할 각오로 Service계층을 interface와 구현계층으로 나눈 후 InvocationHandler를 사용하여 컨트롤러에서 프록시를 조회하였으나, 위와 같은 현상 발생
    
       -> pratice1-1. 수행하던 중 java.lang.ClassCastException 발생. => InvocationHandler는 Obejct 타입을 반환하기 때문에 자유롭게 캐스팅 하였으나, interface를 구현한 proxy 이기때문에 interface 외에 다른 타입으로 변환에 있어 Exception을 겪었음.//https://stackoverflow.com/questions/16047829/proxy-cannot-be-cast-to-class

       -> pratice2. aop 작성을 잘못 하였음. 단순히 시간 log만 찍는 logic이라 void 타입으로 작성하였으나, Object타입으로 변환 후 joinPoint.proceed()값을 리턴해주니 정상적으로 작동함


4. Interceptor 적용 후, 특정 url에서 의도와는. 'localhost에서 리다렉션한 횟수가 너무 많습니다.' 이라는 뷰템플릿이 나오는 현상

    -> 특정 조건을 만족하지 못하였을때 요청하는 url경로가 다시한번 interceptor를 호출하였고, 호출된 intereptor는 조건을 만족시키지 못하므로 다시 interceptor를 호출하여 무한히 redirect 되는 현상. excludePatterns에 경로를 추가하여 해결하였음

5. 세션정보를 쿠키로 등록하여 매번 파라미터로 넘기지 않고 필요한 컨트롤러 메소드에서 호출하려고 하였으나, 의도와 다르게 빈값이 파라미터로 넘어가고, 컨트롤러 메소드에서 해당 파라미터가 null 로 찍히는 현상 발생

   -> 회원 업데이트 로직에서 누락된 로직이 있었음. 누락된 컬럼이 파라미터로 넘어가는 현상을 확인하였음. 후에 비슷한 현상이 생기면 세션이나 쿠키쪽을 연구하기보단, 비지니스 로직을 우선 검토해야겠다고 생각함