delimiter //

DROP FUNCTION IF EXISTS FN_TITLE
//

CREATE FUNCTION FN_TITLE
/**
 * 타이틀 내용 반환
 */
(
    I_TITLE_CODE                    VARCHAR(100)
  , I_USER_LOCALE                   VARCHAR(5)
  , I_DEFAULT_LOCALE                VARCHAR(5)
  , I_DEFAULT_TEXT                  VARCHAR(500)
)
RETURNS VARCHAR(500) CHARSET utf8
BEGIN

    DECLARE R_VALUE                         VARCHAR(500);

    SELECT TITLE_CN
    INTO   R_VALUE
    FROM   TB_SYS_TITLE
    WHERE  TITLE_CODE = I_TITLE_CODE
    AND    TITLE_LOCALE = I_USER_LOCALE
    ;

    IF R_VALUE IS NOT NULL AND R_VALUE <> '' THEN
        RETURN R_VALUE;
    END IF;

    IF I_USER_LOCALE <> I_DEFAULT_LOCALE THEN
        RETURN FN_TITLE(I_TITLE_CODE, I_DEFAULT_LOCALE, I_DEFAULT_LOCALE, I_DEFAULT_TEXT);
    END IF;

    RETURN IFNULL(I_DEFAULT_TEXT, I_TITLE_CODE);

END
//

delimiter ;
