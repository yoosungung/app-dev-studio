-- 시스템 - 코드 - 재직상태
INSERT INTO TB_SYS_TITLE                    VALUES ('COM_CODE_GROUP_NM.000000001000000CG0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '재직상태');
INSERT INTO TB_SYS_CODE_GROUP               VALUES ('000000006000000CG0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'HFFC_STTUS', 'COM_CODE_GROUP_NM.000000001000000CG0', NULL, '1');

INSERT INTO TB_SYS_TITLE                    VALUES ('COM_CODE_VALUE_NM.000000001000001CV0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '재직');
INSERT INTO TB_SYS_CODE_VALUE               VALUES ('000000006000001CV0', '000000000000000SU0', SYSDATE(), NULL, NULL, '000000006000000CG0', 'C', 'COM_CODE_VALUE_NM.000000001000001CV0', NULL, 1, '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('COM_CODE_VALUE_NM.000000001000002CV0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '휴직');
INSERT INTO TB_SYS_CODE_VALUE               VALUES ('000000006000002CV0', '000000000000000SU0', SYSDATE(), NULL, NULL, '000000006000000CG0', 'H', 'COM_CODE_VALUE_NM.000000001000002CV0', NULL, 2, '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('COM_CODE_VALUE_NM.000000001000003CV0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '퇴직');
INSERT INTO TB_SYS_CODE_VALUE               VALUES ('000000006000003CV0', '000000000000000SU0', SYSDATE(), NULL, NULL, '000000006000000CG0', 'T', 'COM_CODE_VALUE_NM.000000001000003CV0', NULL, 3, '1');
