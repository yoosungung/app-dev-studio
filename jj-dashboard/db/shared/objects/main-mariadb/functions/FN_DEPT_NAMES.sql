delimiter //

DROP FUNCTION IF EXISTS FN_DEPT_NAMES
//

CREATE FUNCTION FN_DEPT_NAMES
/**
 * 부서 이름 반환 => 콤마(, )로 구분
 */
(
    I_PERSON_ID                     VARCHAR(18)
)
RETURNS VARCHAR(1000) CHARSET utf8
BEGIN

    DECLARE R_VALUE                         VARCHAR(1000);
    DECLARE V_DEPT_NM                       VARCHAR(300);

    DECLARE CUR_TCD_DONE                    INT DEFAULT 0;

    DECLARE CUR_TCD CURSOR FOR
    SELECT TCD.DEPT_NM
    FROM   TB_COM_PERSON_DEPT TCPD
           INNER JOIN TB_COM_DEPT TCD ON TCD.DEPT_ID = TCPD.DEPT_ID
    WHERE  TCPD.PERSON_ID = I_PERSON_ID
    ORDER BY DEPT_NM
    ;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET CUR_TCD_DONE = TRUE;

    SET R_VALUE = '';

    OPEN CUR_TCD;

    READ_LOOP: LOOP
        FETCH CUR_TCD INTO V_DEPT_NM;

        IF CUR_TCD_DONE THEN
            LEAVE READ_LOOP;
        END IF;
    
        IF LENGTH(R_VALUE) > 0 THEN
            SET R_VALUE = CONCAT(R_VALUE, ', ');
        END IF;

        SET R_VALUE = CONCAT(R_VALUE, V_DEPT_NM);
    END LOOP;

    CLOSE CUR_TCD;

    RETURN R_VALUE;

END
//

delimiter ;
