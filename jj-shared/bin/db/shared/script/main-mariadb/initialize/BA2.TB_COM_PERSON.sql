-- 공통 - 사람
INSERT INTO TB_COM_PERSON                   VALUES ('000000000000001CP0', '000000000000000SU0', SYSDATE(), NULL, NULL, '1', 'a@e-mail.com', NULL, NULL, NULL, NULL, '관리자', 'Admin', '保管人', NULL, 'C', '2000-01-10', NULL, '수석', NULL, NULL, '010-111-2222', '000000010000000CF0', NULL);
INSERT INTO TB_COM_PERSON                   VALUES ('000000000000002CP0', '000000000000000SU0', SYSDATE(), NULL, NULL, '2', 'b@e-mail.com', NULL, NULL, NULL, NULL, '고주몽', 'Go Ju-mong', NULL, NULL, 'H', '2005-04-16', NULL, '책임', NULL, NULL, '010-111-3333', NULL, NULL);
INSERT INTO TB_COM_PERSON                   VALUES ('000000000000003CP0', '000000000000000SU0', SYSDATE(), NULL, NULL, '3', 'c@e-mail.com', NULL, NULL, NULL, NULL, '홍길동', 'Hong Gil-dong', '洪吉東', NULL, 'T', '2010-06-21', '2012-12-21', '과장', NULL, NULL, '010-111-4444', NULL, NULL);

-- 공통 - 사람별 사용자
INSERT INTO TB_COM_PERSON_USER              VALUES ('000000000000001PU0', '000000000000000SU0', SYSDATE(), NULL, NULL, '000000000000001CP0', '000000000000001SU0');
INSERT INTO TB_COM_PERSON_USER              VALUES ('000000000000002PU0', '000000000000000SU0', SYSDATE(), NULL, NULL, '000000000000002CP0', '000000000000002SU0');
INSERT INTO TB_COM_PERSON_USER              VALUES ('000000000000003PU0', '000000000000000SU0', SYSDATE(), NULL, NULL, '000000000000003CP0', '000000000000003SU0');
