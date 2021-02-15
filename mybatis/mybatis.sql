create table member3 (
id varchar2(20) primary key,
email varchar2(30),
password varchar2(30),
name varchar2(30),
fileName varchar2(50),
del char(1) default 'n',
regdate date
);

create table memberphoto (
num number(10) primary key,
id varchar2(20) references member3(id),
fileName varchar2(50)
);
--그림 여러개 올리려면 테이블 하나 더 만들어야함
--하나의 아이디에 사진 여러개 저장

create sequence memberphoto_seq; 
--포토 primary key에 1증가


--펑션은 디벨로퍼에서 만들고 왔다. 38p
create or replace FUNCTION get_seq
	RETURN NUMBER
IS
BEGIN
	RETURN memberphoto_seq.nextval;
END;
/
--이거해봤자 에러니까 디벨로퍼감

select *from member3;
select *from memberphoto;

