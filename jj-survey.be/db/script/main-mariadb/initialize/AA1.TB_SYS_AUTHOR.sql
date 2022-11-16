-- 시스템 - 권한 - ROLE_ANONYMOUS
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_AUTHOR_NM.000000000000001SA0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '익명 사용자');
INSERT INTO TB_SYS_AUTHOR                   VALUES ('000000000000001SA0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ROLE_ANONYMOUS', 'SYS_AUTHOR_NM.000000000000001SA0', NULL, 1, '1', '1', '0');

-- 시스템 - 권한 - IS_AUTHENTICATED_ANONYMOUSLY
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_AUTHOR_NM.000000000000002SA0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '(스프링시큐리티 내부사용)');
INSERT INTO TB_SYS_AUTHOR                   VALUES ('000000000000002SA0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'IS_AUTHENTICATED_ANONYMOUSLY', 'SYS_AUTHOR_NM.000000000000002SA0', '사용자가 익명 사용자인 경우 접근을 승인한다.', 2, '1', '1', '0');

-- 시스템 - 권한 - IS_AUTHENTICATED_FULLY
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_AUTHOR_NM.000000000000003SA0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '(스프링시큐리티 내부사용)');
INSERT INTO TB_SYS_AUTHOR                   VALUES ('000000000000003SA0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'IS_AUTHENTICATED_FULLY', 'SYS_AUTHOR_NM.000000000000003SA0', '새로운 사용자 아이디와 비밀번호가 입력된 경우 접근을 승인한다.', 3, '1', '1', '0');

-- 시스템 - 권한 - IS_AUTHENTICATED_REMEMBERED
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_AUTHOR_NM.000000000000004SA0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '(스프링시큐리티 내부사용)');
INSERT INTO TB_SYS_AUTHOR                   VALUES ('000000000000004SA0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'IS_AUTHENTICATED_REMEMBERED', 'SYS_AUTHOR_NM.000000000000004SA0', '사용자가 Remember Me 기능을 사용해 인증한 경우 접근을 승인한다.', 4, '1', '1', '0');

-- 시스템 - 권한 - ROLE_RESTRICTED
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_AUTHOR_NM.000000000000005SA0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '제한된 사용자');
INSERT INTO TB_SYS_AUTHOR                   VALUES ('000000000000005SA0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ROLE_RESTRICTED', 'SYS_AUTHOR_NM.000000000000005SA0', NULL, 5, '1', '1', '0');

-- 시스템 - 권한 - ROLE_USER
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_AUTHOR_NM.000000000000006SA0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '로그인 사용자');
INSERT INTO TB_SYS_AUTHOR                   VALUES ('000000000000006SA0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ROLE_USER', 'SYS_AUTHOR_NM.000000000000006SA0', NULL, 6, '1', '1', '0');

-- 시스템 - 권한 - ROLE_ADMIN
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_AUTHOR_NM.000000000000007SA0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '관리자');
INSERT INTO TB_SYS_AUTHOR                   VALUES ('000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ROLE_ADMIN', 'SYS_AUTHOR_NM.000000000000007SA0', NULL, 7, '1', '1', '1');

-- 시스템 - 권한 - ROLE_SUPER
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_AUTHOR_NM.000000000000008SA0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, 'Super Admin');
INSERT INTO TB_SYS_AUTHOR                   VALUES ('000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ROLE_SUPER', 'SYS_AUTHOR_NM.000000000000008SA0', NULL, 8, '1', '1', '1');