package com.hana.springboot.data.domain.baseEntity;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.UUID;


/**
 * 임의의 코드를 만들어주는 class
 * UUID로 작성하였으나 좀 더 의미있는 코드를 만들 수 있으면 더욱 좋을 것 같음
 * LocalDate 등..
 */
@Slf4j
public class CodeGenerator {


    public static String generateMemberCode() {
        String memberCode = null;
        memberCode = UUID.randomUUID().toString().replace("-","");
        memberCode = memberCode.substring(0,9);

        log.info("회원번호 확인 => {}", memberCode);
        //TODO 현재는 String 으로 반환하나
        //숫자가 아닌 문자값은 임의의 숫자로 반환하여 Long type으로 return 해주기
        return memberCode;

    }


    public static String generateProductCode(String memberCode) {
        String productCode = null;
        String memberIdent = memberCode.substring(0,4);
        productCode = UUID.randomUUID().toString().replace("-","");
        productCode = productCode.substring(0,4) + memberIdent;

        log.info("상품번호 확인 => {}", productCode);
        return productCode;

    }

}
