package com.hana.springboot.data.domain.eunmClass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum MemberPosition {
    X(0,"NULL"),
    STAFF(1,"사원"),
    SENIORSTAFF(2, "주임"),
    ASSISTANTMANAGER(3, "대리"),
    MANAGER(4, "과장"),
    DEPUTYGENERALMANAGER(5, "차장"),
    GENERALMANAGER(6, "부장"),
    DIRECTOR(7, "이사"),
    VICEPRESIDENT(8, "부사장"),
    CEO(9, "사장");

    private int numberValue;
    private String dbValue;
    /*
    Number      직급 한국어      직급 영어
      1         사원              Staff
      2         주임              Senior staff
      3         대리              Assistant manager
      4         과장              Manager
      5         차장              DEPUTY General Manager
      6         부장              General Manager
      7         이사              Director
      8         부사장             Vice president
      9         사장              CEO
     */

    MemberPosition(int numberValue, String dbValue) {
        this.numberValue = numberValue;
        this.dbValue = dbValue;
    }
}
