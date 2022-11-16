DROP VIEW IF EXISTS VW_SURVEY_CMMN_RSPNS_CO;

CREATE VIEW VW_SURVEY_CMMN_RSPNS_CO AS
SELECT TSCR.SURVEY_QESTNR_ID
     , TSCR.SURVEY_CODE
     , TSCR.CODE_VALUE
     , COUNT(*) AS RSPNS_CO
FROM   (SELECT TSQP.SURVEY_QESTNR_ID
             , 'SEXDSTN' AS SURVEY_CODE
             , TSCR.SEXDSTN AS CODE_VALUE
        FROM   TB_SURVEY_QESTNR_PERSON TSQP
               INNER JOIN TB_SURVEY_CMMN_RSPNS TSCR ON TSCR.SURVEY_ID = TSQP.SURVEY_ID
               INNER JOIN SURVEY S ON S.ID = TSQP.SURVEY_ID AND S.STATUS = 'S'
        UNION ALL
        SELECT TSQP.SURVEY_QESTNR_ID
             , 'AREA' AS SURVEY_CODE
             , TSCR.AREA AS CODE_VALUE
        FROM   TB_SURVEY_QESTNR_PERSON TSQP
               INNER JOIN TB_SURVEY_CMMN_RSPNS TSCR ON TSCR.SURVEY_ID = TSQP.SURVEY_ID
               INNER JOIN SURVEY S ON S.ID = TSQP.SURVEY_ID AND S.STATUS = 'S'
        UNION ALL
        SELECT TSQP.SURVEY_QESTNR_ID
             , 'GRADE' AS SURVEY_CODE
             , TSCR.GRADE AS CODE_VALUE
        FROM   TB_SURVEY_QESTNR_PERSON TSQP
               INNER JOIN TB_SURVEY_CMMN_RSPNS TSCR ON TSCR.SURVEY_ID = TSQP.SURVEY_ID
               INNER JOIN SURVEY S ON S.ID = TSQP.SURVEY_ID AND S.STATUS = 'S'
        UNION ALL
        SELECT TSQP.SURVEY_QESTNR_ID
             , 'CGHMY' AS SURVEY_CODE
             , TSCR.CGHMY AS CODE_VALUE
        FROM   TB_SURVEY_QESTNR_PERSON TSQP
               INNER JOIN TB_SURVEY_CMMN_RSPNS TSCR ON TSCR.SURVEY_ID = TSQP.SURVEY_ID
               INNER JOIN SURVEY S ON S.ID = TSQP.SURVEY_ID AND S.STATUS = 'S'
        UNION ALL
        SELECT TSQP.SURVEY_QESTNR_ID
             , 'SUBJCT' AS SURVEY_CODE
             , TSCR.SUBJCT AS CODE_VALUE
        FROM   TB_SURVEY_QESTNR_PERSON TSQP
               INNER JOIN TB_SURVEY_CMMN_RSPNS TSCR ON TSCR.SURVEY_ID = TSQP.SURVEY_ID
               INNER JOIN SURVEY S ON S.ID = TSQP.SURVEY_ID AND S.STATUS = 'S'
        UNION ALL
        SELECT TSQP.SURVEY_QESTNR_ID
             , 'STSFDG_01' AS SURVEY_CODE
             , TSCR.STSFDG_01 AS CODE_VALUE
        FROM   TB_SURVEY_QESTNR_PERSON TSQP
               INNER JOIN TB_SURVEY_CMMN_RSPNS TSCR ON TSCR.SURVEY_ID = TSQP.SURVEY_ID
               INNER JOIN SURVEY S ON S.ID = TSQP.SURVEY_ID AND S.STATUS = 'S'
        UNION ALL
        SELECT TSQP.SURVEY_QESTNR_ID
             , 'STSFDG_02' AS SURVEY_CODE
             , TSCR.STSFDG_02 AS CODE_VALUE
        FROM   TB_SURVEY_QESTNR_PERSON TSQP
               INNER JOIN TB_SURVEY_CMMN_RSPNS TSCR ON TSCR.SURVEY_ID = TSQP.SURVEY_ID
               INNER JOIN SURVEY S ON S.ID = TSQP.SURVEY_ID AND S.STATUS = 'S'
        UNION ALL
        SELECT TSQP.SURVEY_QESTNR_ID
             , 'STSFDG_03' AS SURVEY_CODE
             , TSCR.STSFDG_03 AS CODE_VALUE
        FROM   TB_SURVEY_QESTNR_PERSON TSQP
               INNER JOIN TB_SURVEY_CMMN_RSPNS TSCR ON TSCR.SURVEY_ID = TSQP.SURVEY_ID
               INNER JOIN SURVEY S ON S.ID = TSQP.SURVEY_ID AND S.STATUS = 'S'
       ) TSCR
WHERE  TSCR.CODE_VALUE IS NOT NULL
GROUP BY TSCR.SURVEY_QESTNR_ID, TSCR.SURVEY_CODE, TSCR.CODE_VALUE
;
