delimiter //

DROP FUNCTION IF EXISTS FN_CODE_NAME
//

CREATE FUNCTION FN_CODE_NAME
/**
 * 공통코드 이름 반환
 */
(
    I_CODE_GROUP                    VARCHAR(50)
  , I_CODE_VALUE                    VARCHAR(50)
  , I_USER_LOCALE                   VARCHAR(5)
  , I_DEFAULT_LOCALE                VARCHAR(5)
  , I_DEFAULT_TEXT                  VARCHAR(500)
)
RETURNS VARCHAR(500) CHARSET utf8
BEGIN

    DECLARE R_VALUE                         VARCHAR(500);

    SELECT FN_TITLE(TSCV.CODE_VALUE_NM_TITLE, I_USER_LOCALE, I_DEFAULT_LOCALE, I_DEFAULT_TEXT)
    INTO   R_VALUE
    FROM   TB_SYS_CODE_VALUE TSCV
           INNER JOIN TB_SYS_CODE_GROUP TSCG ON TSCG.CODE_GROUP_ID = TSCV.CODE_GROUP_ID
    WHERE  TSCG.CODE_GROUP = I_CODE_GROUP
    AND    TSCV.CODE_VALUE = I_CODE_VALUE
    ;

    IF R_VALUE IS NULL THEN
        SET R_VALUE = IFNULL(I_DEFAULT_TEXT, I_CODE_VALUE);
    END IF;

    RETURN R_VALUE;

END
//

delimiter ;