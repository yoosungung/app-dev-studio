package kr.ac.jj.shared.infrastructure.user;

import java.io.Serializable;

/**
 * 익명 사용자
 */
public class AnonymousUser implements Serializable {

    private static final long serialVersionUID = -79140198866532247L;

    public static final String USER_ID = "ANONYMOUS-USER-000";
    public static final String PERSON_ID = "ANONYMOUS-PERSON-0";

    public String getUserId() {
        return USER_ID;
    }

    public String getPersonId() {
        return PERSON_ID;
    }

}
