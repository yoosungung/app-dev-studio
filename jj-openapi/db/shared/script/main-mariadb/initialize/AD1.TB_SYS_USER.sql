-- 시스템 - 사용자
INSERT INTO TB_SYS_USER                     VALUES ('000000000000000SU0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'super', 'E2605A569B0E416A53B864CEBB67EAF5778F87F7A82948C191CE14344378F530', '1', 'ko_KR', NULL, NULL, NULL);
INSERT INTO TB_SYS_USER                     VALUES ('000000000000001SU0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'admin', 'E2605A569B0E416A53B864CEBB67EAF5778F87F7A82948C191CE14344378F530', '1', 'ko_KR', NULL, NULL, NULL);
INSERT INTO TB_SYS_USER                     VALUES ('000000000000002SU0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'user01', 'E2605A569B0E416A53B864CEBB67EAF5778F87F7A82948C191CE14344378F530', '1', 'ko_KR', NULL, NULL, NULL);
INSERT INTO TB_SYS_USER                     VALUES ('000000000000003SU0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'user02', 'E2605A569B0E416A53B864CEBB67EAF5778F87F7A82948C191CE14344378F530', '1', 'ko_KR', NULL, NULL, NULL);

-- 시스템 - 사용자별 권한
INSERT INTO TB_SYS_USER_AUTHOR              VALUES ('000000000000000SU0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_USER_AUTHOR              VALUES ('000000000000001SU0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
