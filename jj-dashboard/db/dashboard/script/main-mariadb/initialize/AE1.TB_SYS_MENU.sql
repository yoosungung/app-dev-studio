/*
SET foreign_key_checks = 0;
DELETE FROM TB_SYS_MENU_AUTHOR;
DELETE FROM TB_SYS_MENU;
SET foreign_key_checks = 1;
*/

-- 시스템 - 메뉴
INSERT INTO TB_SYS_MENU                     VALUES ('000000001000000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', NULL                , 1, '대시보드', 1, '/supercompetence/SuperCompetence/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001001000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000001000000SM0', 2, '교양교과 핵심역량', 1, '/supercompetence/SuperCompetence/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001002000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000001000000SM0', 2, '성적', 2, '/score/Score/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001003000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000001000000SM0', 2, '선도학생 커리어 패스', 3, '/careerpath/CareerPath/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001004000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000001000000SM0', 2, '키워드 분석 워드클라우드', 4, '/wordcloud/WordCloud/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001005000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000001000000SM0', 2, '전국 출신 지역분포', 5, '/spread/Spread/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001006000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000001000000SM0', 2, '입학 데이터', 6, '/entrance/Entrance/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001007000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000001000000SM0', 2, '직업추천', 7, '/recommendjob/RecommendJob/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001008000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000001000000SM0', 2, '입학분석1/2', 8, '/ipsy/Ipsy1/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001009000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000001000000SM0', 2, '입학분석2/2', 9, '/ipsy/Ipsy2/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000001010000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000001000000SM0', 2, '학습분석', 10, '/analysis/Analysis/', '1');

INSERT INTO TB_SYS_MENU                     VALUES ('000000009000000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', NULL                , 1, '시스템관리', 3, '/admin/appmanage/personmanage/PersonManage/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000009001000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000009000000SM0', 2, '사용자 관리', 1, '/admin/appmanage/personmanage/PersonManage/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000009002000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000009000000SM0', 2, '코드 관리', 2, '/admin/sysmanage/codemanage/CodeManage/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000009003000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000009000000SM0', 2, '메뉴 관리', 3, '/admin/sysmanage/menumanage/MenuManage/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000009004000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000009000000SM0', 2, '권한 관리', 4, '/admin/sysmanage/authormanage/AuthorityManage/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000009005000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000009000000SM0', 2, '프로그램 관리', 5, '/admin/sysmanage/resrcemanage/ResourceManage/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000009006000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000009000000SM0', 2, '에러 로그', 6, '/admin/logmanage/errorlog/ErrorLogManage/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000009007000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000009000000SM0', 2, '메일 로그', 7, '/admin/logmanage/emaillog/EmailLogManage/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000009008000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000009000000SM0', 2, '액션 로그', 8, '/admin/logmanage/actionlog/ActionLogManage/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000009009000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000009000000SM0', 2, '접속 로그', 9, '/admin/logmanage/loginlog/LoginLogManage/', '1');
INSERT INTO TB_SYS_MENU                     VALUES ('000000009010000SM0', '000000000000000SU0', SYSDATE(), NULL, NULL, 'MAIN', '000000009000000SM0', 2, '메뉴 로그', 10, '/admin/logmanage/menulog/MenuLogManage/', '1');


-- 시스템 - 메뉴별 권한(ROLE_ADMIN)
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000001000000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000001001000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000001002000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000001003000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000001004000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000001005000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000001006000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000001007000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000001008000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000001009000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000001010000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);

INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000009000000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000009001000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000009002000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000009003000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000009004000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000009005000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000009006000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000009007000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000009008000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000009009000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
INSERT INTO TB_SYS_MENU_AUTHOR              VALUES ('000000009010000SM0', '000000000000007SA0', '000000000000000SU0', SYSDATE(), NULL, NULL);
