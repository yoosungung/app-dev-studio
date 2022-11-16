delimiter //

DROP FUNCTION IF EXISTS FN_UID
//

CREATE FUNCTION FN_UID
/**
 * UID 반환
 */
(
    I_TYPE                          VARCHAR(1)
)
RETURNS VARCHAR(18) CHARSET utf8
BEGIN

    DECLARE V_TYPE                          VARCHAR(1);
    DECLARE V_BASE_STR                      VARCHAR(36);
    DECLARE V_BASE_LEN                      TINYINT;
    DECLARE V_DATE_NUM                      BIGINT;
    DECLARE V_DATE_VAL                      VARCHAR(9);
    DECLARE V_SEQU_VAL                      VARCHAR(8);

    IF I_TYPE IS NOT NULL AND I_TYPE != '' THEN
        SET V_TYPE = I_TYPE;
    ELSE
        SET V_TYPE = '0';
    END IF;

    SET V_BASE_STR = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    SET V_BASE_LEN = LENGTH(V_BASE_STR);
    SET V_DATE_NUM = DATE_FORMAT(SYSDATE(), '%Y%m%d%H%i%s');
    SET V_DATE_VAL = '';

    WHILE V_DATE_NUM >= V_BASE_LEN DO
        SET V_DATE_VAL = CONCAT(SUBSTRING(V_BASE_STR, MOD(V_DATE_NUM, V_BASE_LEN) + 1, 1), V_DATE_VAL);
        SET V_DATE_NUM = TRUNCATE(V_DATE_NUM / V_BASE_LEN, 0);
    END WHILE;

    SET V_DATE_VAL = CONCAT(SUBSTRING(V_BASE_STR, V_DATE_NUM + 1, 1), V_DATE_VAL);

    IF V_TYPE = '1' THEN
        SET V_SEQU_VAL = LEFT(HEX(AES_ENCRYPT(SYSDATE(), RAND() * RAND())), 8);
    ELSE
        SET V_SEQU_VAL = UCASE(LEFT(REPLACE(UUID(), '-', ''), 8));
    END IF;

    RETURN CONCAT(V_DATE_VAL, V_SEQU_VAL, V_TYPE);

END
//

delimiter ;
