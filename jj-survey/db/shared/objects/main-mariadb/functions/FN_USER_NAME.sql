delimiter //

DROP FUNCTION IF EXISTS FN_USER_NAME
//

CREATE FUNCTION FN_USER_NAME
/**
 * 사용자 이름 반환
 */
(
    I_USER_ID                       VARCHAR(18)
  , I_USER_LOCALE                   VARCHAR(5)
)
RETURNS VARCHAR(100) CHARSET utf8
BEGIN

    DECLARE R_VALUE                         VARCHAR(100);

    SELECT CASE WHEN TCPU.PERSON_ID IS NULL THEN
                TSU.LOGIN_NM
           ELSE
                FN_PERSON_NAME(TCPU.PERSON_ID, I_USER_LOCALE)
           END
    INTO   R_VALUE
    FROM   TB_SYS_USER TSU
           LEFT JOIN TB_COM_PERSON_USER TCPU ON TCPU.USER_ID = TSU.USER_ID
    WHERE  TSU.USER_ID = I_USER_ID
    ;

    RETURN R_VALUE;

END
//

delimiter ;
