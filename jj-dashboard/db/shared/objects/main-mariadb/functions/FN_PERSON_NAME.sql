delimiter //

DROP FUNCTION IF EXISTS FN_PERSON_NAME
//

CREATE FUNCTION FN_PERSON_NAME
/**
 * 사람 이름 반환
 */
(
    I_PERSON_ID                     VARCHAR(18)
  , I_USER_LOCALE                   VARCHAR(5)
)
RETURNS VARCHAR(100) CHARSET utf8
BEGIN

    DECLARE R_VALUE                         VARCHAR(100);

    SELECT CASE SUBSTR(I_USER_LOCALE, 1, 2)
                WHEN 'ko' THEN TCP.KOREAN_NM
                WHEN 'zh' THEN TCP.CHCRT_NM
                ELSE TCP.ENGL_NM
           END
    INTO   R_VALUE
    FROM   TB_COM_PERSON TCP
    WHERE  TCP.PERSON_ID = I_PERSON_ID
    ;

    RETURN R_VALUE;

END
//

delimiter ;
