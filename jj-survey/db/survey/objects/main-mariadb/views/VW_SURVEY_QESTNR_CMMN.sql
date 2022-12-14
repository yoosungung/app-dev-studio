DROP VIEW IF EXISTS VW_SURVEY_QESTNR_CMMN;

CREATE VIEW VW_SURVEY_QESTNR_CMMN AS
SELECT TSQC.SURVEY_QESTNR_ID
     , 'CMMN' AS SURVEY_DIV
     , 'SEXDSTN' AS SURVEY_CODE
     , 'CMMN_SEXDSTN' AS CODE_GROUP
     , '성별' AS CODE_TITLE
     , 11 AS SORT_ORDR
FROM   TB_SURVEY_QESTNR_CMMN TSQC
WHERE  TSQC.SEXDSTN = '1'
UNION ALL
SELECT TSQC.SURVEY_QESTNR_ID
     , 'CMMN' AS SURVEY_DIV
     , 'AREA' AS SURVEY_CODE
     , 'CMMN_AREA' AS CODE_GROUP
     , '지역' AS CODE_TITLE
     , 12 AS SORT_ORDR
FROM   TB_SURVEY_QESTNR_CMMN TSQC
WHERE  TSQC.AREA = '1'
UNION ALL
SELECT TSQC.SURVEY_QESTNR_ID
     , 'CMMN' AS SURVEY_DIV
     , 'GRADE' AS SURVEY_CODE
     , 'CMMN_GRADE' AS CODE_GROUP
     , '학년' AS CODE_TITLE
     , 13 AS SORT_ORDR
FROM   TB_SURVEY_QESTNR_CMMN TSQC
WHERE  TSQC.GRADE = '1'
UNION ALL
SELECT TSQC.SURVEY_QESTNR_ID
     , 'CMMN' AS SURVEY_DIV
     , 'CGHMY' AS SURVEY_CODE
     , 'CMMN_CGHMY' AS CODE_GROUP
     , '소속단과대학' AS CODE_TITLE
     , 14 AS SORT_ORDR
FROM   TB_SURVEY_QESTNR_CMMN TSQC
WHERE  TSQC.CGHMY = '1'
UNION ALL
SELECT TSQC.SURVEY_QESTNR_ID
     , 'CMMN' AS SURVEY_DIV
     , 'SUBJCT' AS SURVEY_CODE
     , 'CMMN_SUBJCT' AS CODE_GROUP
     , '학과' AS CODE_TITLE
     , 15 AS SORT_ORDR
FROM   TB_SURVEY_QESTNR_CMMN TSQC
WHERE  TSQC.SUBJCT = '1'
UNION ALL
SELECT TSQC.SURVEY_QESTNR_ID
     , 'STSFDG' AS SURVEY_DIV
     , 'STSFDG_01' AS SURVEY_CODE
     , 'CMMN_STSFDG' AS CODE_GROUP
     , '전주대학교에 전반적으로 만족하십니까?' AS CODE_TITLE
     , 21 AS SORT_ORDR
FROM   TB_SURVEY_QESTNR_CMMN TSQC
WHERE  TSQC.STSFDG_01 = '1'
UNION ALL
SELECT TSQC.SURVEY_QESTNR_ID
     , 'STSFDG' AS SURVEY_DIV
     , 'STSFDG_02' AS SURVEY_CODE
     , 'CMMN_STSFDG' AS CODE_GROUP
     , '전주대학교의 교육에 대해 만족하십니까?' AS CODE_TITLE
     , 22 AS SORT_ORDR
FROM   TB_SURVEY_QESTNR_CMMN TSQC
WHERE  TSQC.STSFDG_02 = '1'
UNION ALL
SELECT TSQC.SURVEY_QESTNR_ID
     , 'STSFDG' AS SURVEY_DIV
     , 'STSFDG_03' AS SURVEY_CODE
     , 'CMMN_STSFDG' AS CODE_GROUP
     , '전주대학교의 교육환경에 대해 만족하십니까?' AS CODE_TITLE
     , 23 AS SORT_ORDR
FROM   TB_SURVEY_QESTNR_CMMN TSQC
WHERE  TSQC.STSFDG_03 = '1'
;
