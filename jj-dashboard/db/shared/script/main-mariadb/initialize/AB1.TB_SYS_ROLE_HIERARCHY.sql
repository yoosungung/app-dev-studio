-- 시스템 - 역할 계층
INSERT INTO TB_SYS_ROLE_HIERARCHY           VALUES ('00000000000002SRH0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'IS_AUTHENTICATED_ANONYMOUSLY', 'IS_AUTHENTICATED_REMEMBERED', 1);
INSERT INTO TB_SYS_ROLE_HIERARCHY           VALUES ('00000000000003SRH0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'IS_AUTHENTICATED_REMEMBERED', 'IS_AUTHENTICATED_FULLY', 2);
INSERT INTO TB_SYS_ROLE_HIERARCHY           VALUES ('00000000000004SRH0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'IS_AUTHENTICATED_FULLY', 'ROLE_RESTRICTED', 3);
INSERT INTO TB_SYS_ROLE_HIERARCHY           VALUES ('00000000000005SRH0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ROLE_RESTRICTED', 'ROLE_USER', 4);
INSERT INTO TB_SYS_ROLE_HIERARCHY           VALUES ('00000000000006SRH0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ROLE_USER', 'ROLE_ADMIN', 5);
INSERT INTO TB_SYS_ROLE_HIERARCHY           VALUES ('00000000000007SRH0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'IS_AUTHENTICATED_FULLY', 'ROLE_SUPER', 6);
