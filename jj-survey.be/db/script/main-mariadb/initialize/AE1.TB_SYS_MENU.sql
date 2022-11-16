-- 시스템 - 메뉴
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000001000001SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '게시판');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001000001SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', NULL                , 1, 'SYS_MENU_NM.000000001000001SM0', 1, '/bbsctt/BbsContentsPortal/NOTICE/viewList.do', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000001000002SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '공지사항');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001000002SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000001000001SM0', 2, 'SYS_MENU_NM.000000001000002SM0', 1, '/bbsctt/BbsContentsPortal/NOTICE/viewList.do', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000001000003SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, 'Q&A');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001000003SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000001000001SM0', 2, 'SYS_MENU_NM.000000001000003SM0', 2, '/bbsctt/BbsContentsPortal/QNA/viewList.do', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000001000004SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, 'FAQ');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001000004SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000001000001SM0', 2, 'SYS_MENU_NM.000000001000004SM0', 3, '/bbsctt/BbsContentsPortal/FAQ/viewList.do', '1');

INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000001000000SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '게시판');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001000000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', NULL                , 1, 'SYS_MENU_NM.000000001000000SM0', 1, NULL, '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000001001000SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '공지사항');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001001000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000001000000SM0', 2, 'SYS_MENU_NM.000000001001000SM0', 1, '/common/bbsctt/BbsContents/NOTICE/', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000001002000SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, 'Q&A');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001002000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000001000000SM0', 2, 'SYS_MENU_NM.000000001002000SM0', 2, '/common/bbsctt/BbsContents/QNA/', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000001003000SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, 'FAQ');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001003000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000001000000SM0', 2, 'SYS_MENU_NM.000000001003000SM0', 3, '/common/bbsctt/BbsContents/FAQ/', '1');

INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000002000000SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '어플리케이션 관리');
INSERT INTO TB_SYS_MENU                     VALUES ('000000002000000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', NULL                , 1, 'SYS_MENU_NM.000000002000000SM0', 1, NULL, '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000002001000SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '사용자/부서 관리');
INSERT INTO TB_SYS_MENU                     VALUES ('000000002001000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000002000000SM0', 2, 'SYS_MENU_NM.000000002001000SM0', 1, NULL, '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000002001001SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '사용자 관리');
INSERT INTO TB_SYS_MENU                     VALUES ('000000002001001SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000002001000SM0', 3, 'SYS_MENU_NM.000000002001001SM0', 1, '/admin/appmanage/usermanage/UserManage/', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000002001002SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '부서 관리');
INSERT INTO TB_SYS_MENU                     VALUES ('000000002001002SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000002001000SM0', 3, 'SYS_MENU_NM.000000002001002SM0', 2, '/admin/appmanage/deptmanage/DeptManage/', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000002002000SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '게시판 관리');
INSERT INTO TB_SYS_MENU                     VALUES ('000000002002000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000002000000SM0', 2, 'SYS_MENU_NM.000000002002000SM0', 2, NULL, '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000002002001SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '게시판 관리');
INSERT INTO TB_SYS_MENU                     VALUES ('000000002002001SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000002002000SM0', 3, 'SYS_MENU_NM.000000002002001SM0', 1, '/admin/appmanage/bbsmanage/BbsManage/', '1');

INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003000000SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '시스템 관리');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003000000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', NULL                , 1, 'SYS_MENU_NM.000000003000000SM0', 2, NULL, '1');

INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003001000SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '시스템 관리');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003001000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000003000000SM0', 2, 'SYS_MENU_NM.000000003001000SM0', 1, NULL, '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003001001SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '코드 관리');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003001001SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000003001000SM0', 3, 'SYS_MENU_NM.000000003001001SM0', 1, '/admin/sysmanage/codemanage/CodeManage/', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003001002SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '명칭 관리');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003001002SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000003001000SM0', 3, 'SYS_MENU_NM.000000003001002SM0', 2, '/admin/sysmanage/titlemanage/TitleManage/', '1');

INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003002000SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '메뉴/권한 관리');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003002000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000003000000SM0', 2, 'SYS_MENU_NM.000000003002000SM0', 2, NULL, '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003002001SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '메뉴 관리');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003002001SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000003002000SM0', 3, 'SYS_MENU_NM.000000003002001SM0', 1, '/admin/sysmanage/menumanage/MenuManage/', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003002002SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '권한 관리');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003002002SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000003002000SM0', 3, 'SYS_MENU_NM.000000003002002SM0', 2, '/admin/sysmanage/authormanage/AuthorityManage/', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003002003SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '리소스 관리');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003002003SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000003002000SM0', 3, 'SYS_MENU_NM.000000003002003SM0', 3, '/admin/sysmanage/resrcemanage/ResourceManage/', '1');

INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003003000SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '로그 조회');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003003000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000003000000SM0', 2, 'SYS_MENU_NM.000000003003000SM0', 3, NULL, '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003003001SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '에러 로그');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003003001SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000003003000SM0', 3, 'SYS_MENU_NM.000000003003001SM0', 1, '/admin/logmanage/errorlog/ErrorLogManage/', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003003002SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '스케줄러 로그');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003003002SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000003003000SM0', 3, 'SYS_MENU_NM.000000003003002SM0', 2, '/admin/logmanage/schdullog/SchedulerLogManage/', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003003003SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '메일 로그');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003003003SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000003003000SM0', 3, 'SYS_MENU_NM.000000003003003SM0', 3, '/admin/logmanage/emaillog/EmailLogManage/', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003003004SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '방문 로그');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003003004SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000003003000SM0', 3, 'SYS_MENU_NM.000000003003004SM0', 4, '/admin/logmanage/visitlog/VisitLogManage/', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003003005SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '액션 로그');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003003005SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000003003000SM0', 3, 'SYS_MENU_NM.000000003003005SM0', 5, '/admin/logmanage/actionlog/ActionLogManage/', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003003006SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '접속 로그');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003003006SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000003003000SM0', 3, 'SYS_MENU_NM.000000003003006SM0', 6, '/admin/logmanage/loginlog/LoginLogManage/', '1');
INSERT INTO TB_SYS_TITLE                    VALUES ('SYS_MENU_NM.000000003003007SM0', 'ko_KR', '000000000000000SU0', SYSDATE(), NULL, NULL, '메뉴 로그');
INSERT INTO TB_SYS_MENU                     VALUES ('000000003003007SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'ADMIN', '000000003003000SM0', 3, 'SYS_MENU_NM.000000003003007SM0', 7, '/admin/logmanage/menulog/MenuLogManage/', '1');

-- 시스템 - 메뉴별 권한(ROLE_ADMIN)
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000002000000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000002001000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000002001001SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000002001002SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000002002000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000002002001SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);

-- 시스템 - 메뉴별 권한(ROLE_SUPER)
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000002000000SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000002001000SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000002001001SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000002001002SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000002002000SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000002002001SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);

INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003000000SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003001000SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003001001SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003001002SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);

INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003002000SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003002001SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003002002SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003002003SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);

INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003003000SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003003001SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003003002SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003003003SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003003004SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003003005SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003003006SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000003003007SM0', '000000000000008SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
