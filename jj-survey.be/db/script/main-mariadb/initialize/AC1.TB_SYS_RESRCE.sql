-- 시스템 - 리소스
INSERT INTO TB_SYS_RESRCE                   VALUES ('000000000000001SR0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'URL', '/**/*.do', 1, '1');
INSERT INTO TB_SYS_RESRCE                   VALUES ('000000000000002SR0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'URL', '/admin/appmanage/**', 2, '1');
INSERT INTO TB_SYS_RESRCE                   VALUES ('000000000000003SR0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'URL', '/admin/sysmanage/**', 3, '1');
INSERT INTO TB_SYS_RESRCE                   VALUES ('000000000000004SR0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'URL', '/admin/logmanage/**', 4, '1');
INSERT INTO TB_SYS_RESRCE                   VALUES ('000000000000005SR0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'URL', '/admin/**', 5, '1');

-- 시스템 - 리소스별 권한
INSERT INTO TB_SYS_RESRCE_AUTHOR            VALUES ('000000000000001SR0', '000000000000001SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_RESRCE_AUTHOR            VALUES ('000000000000001SR0', '000000000000002SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_RESRCE_AUTHOR            VALUES ('000000000000001SR0', '000000000000003SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_RESRCE_AUTHOR            VALUES ('000000000000001SR0', '000000000000004SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_RESRCE_AUTHOR            VALUES ('000000000000001SR0', '000000000000005SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_RESRCE_AUTHOR            VALUES ('000000000000001SR0', '000000000000006SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_RESRCE_AUTHOR            VALUES ('000000000000001SR0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_RESRCE_AUTHOR            VALUES ('000000000000001SR0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_RESRCE_AUTHOR            VALUES ('000000000000002SR0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_RESRCE_AUTHOR            VALUES ('000000000000002SR0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_RESRCE_AUTHOR            VALUES ('000000000000003SR0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_RESRCE_AUTHOR            VALUES ('000000000000004SR0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_RESRCE_AUTHOR            VALUES ('000000000000005SR0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_RESRCE_AUTHOR            VALUES ('000000000000005SR0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
