delimiter //

DROP FUNCTION IF EXISTS FN_ISEMPTY
//

CREATE FUNCTION FN_ISEMPTY
/**
 * 오라클의 NVL 함수와 동일 기능 구현
 * 공백도 NULL로 처리한 결과 반환
 */
(
    I_STR1                          VARCHAR(4000)
  , I_STR2                          VARCHAR(4000)
)
RETURNS VARCHAR(500) CHARSET utf8
BEGIN

    IF I_STR1 IS NULL OR I_STR1 = '' THEN
        RETURN I_STR2;
    END IF;

    RETURN I_STR1;

END
//

delimiter ;
