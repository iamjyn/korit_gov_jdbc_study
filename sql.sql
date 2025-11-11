-- 기본적인 용어
-- 스키마(Schema) : 데이터베이스, 프로젝트 하나의 저장소
-- 테이블(Table) : 데이터를 저장하는 표 (회원정보, 게시물 등등)
-- 행(Row, Tuple) : 테이블에 들어가는 한 줄의 데이터
-- 카디널리티(Cardinality) : 튜플의 갯수 
-- 열(Column, Attribute) : 속성 하나 (id, 이름, 나이, 등등)
-- 차수(Degree) : 속성의 갯수

-- DDL, DML, DCL
-- DDL (데이터 정의어)
-- CREATE DATABASE : DB 생성
-- CREATE TABLE : 테이블 생성
-- ALTER TABLE : 테이블 수정 (컬럼 추가, 수정 등)
-- DROP TABLE : 테이블 삭제

-- DML (데이터 조작어)
-- INSERT INTO : 데이터 추가
-- SELECT : 데이터 조회

-- AI 컬럼 및 값은 생략가능(하지만 초기에는 다 쓰는 거 권장)
INSERT INTO user_tb
    (user_id, username, password, age, create_dt)
VALUES 
	(0, "김길동", "qwerty", null, NOW());
    
SELECT
	*
FROM
	user_tb
WHERE
	user_id in (1, 2, 3, 4);
-- WHERE () LIKE "%시작", "끝나는%" "%포함%"
-- WHERE () in (1, 2) => in 들어가는
-- and, or
-- %가 포함된 데이터 조회시 => "%\%%"user_tb

SELECT
	*
FROM
	user_tb
WHERE
	username LIKE "%길%"
    and age > 20
    or username LIKE "%동"
ORDER BY
	create_dt ASC;
-- ORDER BY () ASC => 오름차순 (디폴트)
-- ORDER BY () DESC => 내림차순

INSERT INTO todo_tb
	(todo_id, content, username, create_dt, update_dt)
VALUES
	(0, "커피마시기", "dong", NOW(), null);

-- 전체 조회 
SELECT
	*
FROM
	todo_tb;

-- 최신순 조회
SELECT
	*
FROM
	todo_tb
ORDER BY
	create_dt DESC;

-- 이름 조회
SELECT
	*
FROM
	todo_tb
WHERE
	todo_id = 3;
    
INSERT INTO post_tb
	(post_id, title, content, username, create_dt)
VALUES
	(0, "국내도서", "절창", "구병모", NOW());
    
SELECT
	*
FROM
	post_tb;