use ncs_erp;

select user(), database();

show tables;
desc department;

insert into department values 
(1,'영업',8),(2,'기획',10),(3, '개발',9),(4,'총무',7);

insert into title values 
(1, '사장'), (2, '부장'), (3, '과장'), (4, '대리'), (5, '사원');

INSERT INTO employee (empno,empname,title,manager,salary,dno) VALUES 
(4377,'이성래',1,NULL,5000000,2),
(3011,'이수민',2,4377,4000000,3),
(3426,'박영권',3,4377,3000000,1),
(1003,'조민희',3,4377,3000000,2),
(1365,'김상원',5,3426,1500000,1),
(2106,'김창섭',4,1003,2500000,2),
(3427,'최종철',4,3011,1500000,2);

select * from employee;
select * from title;
select * from department;