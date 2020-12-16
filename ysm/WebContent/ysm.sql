CREATE SEQUENCE SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOMINVALUE;

CREATE TABLE board(
    no NUMBER(5,0) PRIMARY KEY,
    title VARCHAR2(30),
    writer VARCHAR2(10),
    regdate DATE,
    click NUMBER(5,0)
);

ALTER TABLE board add content VARCHAR2(1000);
ALTER TABLE board add FILENAME VARCHAR2(100);

CREATE TABLE MEMBER(
    id VARCHAR2(10) PRIMARY KEY,
    pwd VARCHAR2(50),
    nick VARCHAR2(10)
);

CREATE TABLE REPLY(
    RCODE NUMBER(5,0) PRIMARY KEY,
    NO NUMBER(5,0),
    REPLY VARCHAR2(500),
    regdate DATE,
    WRITER VARCHAR2(20)
);

--공지사항
CREATE TABLE Nboard(
    no NUMBER(5,0) PRIMARY KEY,
    title VARCHAR2(100),
    writer VARCHAR2(10),
    regdate DATE,
    click NUMBER(5,0),
    content VARCHAR2(2000),
    filename VARCHAR2(100)
);

--공지시항 번호 매기기
CREATE SEQUENCE nbSEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOMINVALUE;

--공지사항 번호 증가 오류 수정
select sequence_name, min_value, max_value, increment_by, last_number
from user_sequences;

select sequence_name, cache_size
from user_sequences
where sequence_name = 'nbSEQ';

alter sequence nbSEQ nocache;

--스페인게시판 번호 증가 오류 수정
select sequence_name, min_value, max_value, increment_by, last_number
from user_sequences;

select sequence_name, cache_size
from user_sequences
where sequence_name = 'SEQ';

alter sequence SEQ nocache;
