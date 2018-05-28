# SQL常见操作 #
**一、数据库的创建：**

1、创建一个名称为demo的数据库：

	CREATE DATABASE demo

2、创建一个使用utf8字符集的demo2数据库：

	CREATE DATABASE demo2 CHARACTER SET utf8;

3、创建一个使用utf8字符集，并带比较规则的demo3数据库：

	CREATE DATABASE demo3 CHARACTER SET utf8 COLLATE utf8_bin;

**二、数据库的修改：**

修改mydb2字符集为gbk：

	ALTER DATABASE demo CHARACTER SET gbk;

**三、数据库的删除：**

删除数据库demo：

	DROP DATABASE demo;

**四、数据库查看：**

查看所有数据库：

	SHOW DATABASES;

查看数据库mydb1的字符集：

	SHOW CREATE DATABASE demo;

## 数据库中表操作的SQL练习 ##

**一、创建表**

创建一张员工表employee

    CREATE TABLE employee(
		id INT PRIMARY KEY AUTO_INCREMENT,
		NAME VARCHAR(15) NOT NULL,
		gender VARCHAR(20),
		birthday DATETIME,
		entry_date TIMESTAMP,
		job VARCHAR(10),
		salary DOUBLE,
		RESUME TEXT
		)

**二、删除表**

1、删除employee表

		DROP TABLE employee;

**三、数据表的结构的修改：**

1、在上面员工表的基本上增加一个image列。

	ALTER TABLE employee ADD image VARCHAR(20)

2、修改job列，使其长度为60。

	ALTER TABLE employee MODIFY job VARCHAR(60)

3、删除gender列。

	ALTER TABLE employee DROP gender

4、表名改为USER。

	RENAME TABLE employee TO USER

5、修改表的字符集为utf8

    ALTER TABLE USER CHARACTER SET utf8

6、列名NAME修改为username

	ALTER TABLE USER CHANGE NAME username VARCHAR(20)

**四、查看表结构**

1、查看数据库内的所有表

	SHOW TABLES;

2、查看employee的建表语句

	SHOW CREATE TABLE employee;

3、查看employee的表结构

	DESC employee;

## 表记录的操作 ##
**一、插入语句 ---INSERT**

1、向employee中插入三个员工信息，要求员工姓名分别是郑爽,唐嫣,杨幂

	INSERT INTO employee VALUES(NULL,'郑爽',NULL,NULL,NULL,NULL,NULL,NULL)
	INSERT INTO employee VALUES(NULL,'唐嫣',NULL,NULL,NULL,NULL,NULL,NULL)
	INSERT INTO employee VALUES(NULL,'杨幂',NULL,NULL,NULL,NULL,NULL,NULL)

**二、更新语句 ---UPDATE**

1、将所有员工薪水修改为5000元。
	
	UPDATE employee SET salary=5000

2、将姓名为’郑爽’的员工薪水修改为8000元。

	UPDATE employee SET salary = 8000 WHERE NAME = '郑爽'

3、将姓名为’唐嫣’的员工薪水修改为7000元,job改为'演员'。

	UPDATE employee SET salary = 7000,job = '演员' WHERE NAME = '唐嫣'

4、将杨幂的薪水在原有基础上增加2000元

	UPDATE employee SET salary = salary+2000 WHERE NAME = '杨幂'

**三、删除语句 ---DELETE**

1、删除表中名称为’郑爽’的记录。

	DELETE FROM employee WHERE NAME = '郑爽'

2、删除表中所有记录。

	DELETE FROM employee

## 四、查询语句 ---SELECT ##

> 准备数据

	CREATE TABLE exam(
		id INT PRIMARY KEY AUTO_INCREMENT,
		NAME VARCHAR(20) NOT NULL,
		chinese DOUBLE,
		math DOUBLE,
		english DOUBLE
	);
	INSERT INTO exam VALUES(NULL,'关羽',85,76,70);	
	INSERT INTO exam VALUES(NULL,'关羽',85,76,70);
	INSERT INTO exam VALUES(NULL,'张飞',70,75,70);
	INSERT INTO exam VALUES(NULL,'赵云',90,65,95);
	INSERT INTO exam VALUES(NULL,'刘备',97,50,50);
	INSERT INTO exam VALUES(NULL,'曹操',90,89,80);
	INSERT INTO exam VALUES(NULL,'司马懿',90,67,65);

1、查询表中所有学生的信息。

 	SELECT * FROM exam;

2、查询表中所有学生的姓名和对应的英语成绩。

	SELECT NAME,english FROM exam;

3、过滤表中重复数据

 	SELECT DISTINCT NAME FROM exam

4、在所有学生分数上加10分特长分。

	SELECT NAME,chinese+10 AS 语文,math+10 AS 数学,english + 10 AS 英语 FROM exam

5、统计每个学生的总分。

	SELECT NAME,chinese+math+english AS 总分 FROM exam

6、使用别名表示学生分数。

	SELECT id,NAME 名字, chinese 语文,math 数学, english 英语 FROM exam;

7、查询姓名为刘备的学生成绩

	SELECT * FROM exam WHERE NAME = '刘备'

8、查询英语分数在 80－90之间的同学。
	SELECT * FROM exam WHERE english BETWEEN 80 AND 90;

9、查询所有姓刘的学生成绩。

	SELECT * FROM exam WHERE NAME LIKE '刘%'

10、对数学成绩排序后输出。

	SELECT * FROM exam ORDER BY math DESC

11、统计一个班级共有多少学生

	SELECT COUNT(*) AS 总人数 FROM exam

12、统计总分大于250的人数有多少
	
	SELECT COUNT(*) FROM exam WHERE math+chinese+english > 250

13、统计一个班级数学总成绩

	SELECT SUM(math) FROM exam

14、统计一个班级语文、英语、数学各科的总成绩

	SELECT SUM(chinese) AS 语文 , SUM(math) AS 数学 , SUM(english) AS 英语 FROM exam

15、统计一个班级语文成绩平均分

	SELECT AVG(chinese) AS  语文平均分 FROM exam

16、求班级最高分和最低分（数值范围在统计中特别有用）

	SELECT MAX(chinese+math+english) AS 最高分,MIN(chinese+math+english) AS 最低分 FROM exam

> 准备数据

	CREATE TABLE orders(
	id INT,
	product VARCHAR(20),
	price FLOAT
	);

	INSERT INTO orders(id,product,price) VALUES(1,'电视',900);
	INSERT INTO orders(id,product,price) VALUES(2,'洗衣机',100);
	INSERT INTO orders(id,product,price) VALUES(3,'洗衣粉',90);
	INSERT INTO orders(id,product,price) VALUES(4,'桔子',9);
	INSERT INTO orders(id,product,price) VALUES(5,'洗衣粉',90);

1、查询购买了几类商品，并且每类总价大于90的商品

	SELECT product,price ,SUM(price) AS 总价 FROM orders GROUP BY product HAVING SUM(price) > 90

> 准备数据

	CREATE TABLE emp(
	empno INT,
	ename VARCHAR(50),
	job VARCHAR(50),
	mgr	INT,
	hiredate DATE,
	sal	DECIMAL(7,2),
	comm DECIMAL(7,2),
	deptno INT
	) 

	INSERT INTO emp VALUES(7369,'SMITH','CLERK',7902,'1980-12-17',800,NULL,20);
	INSERT INTO emp VALUES(7499,'ALLEN','SALESMAN',7698,'1981-02-20',1600,300,30);
	INSERT INTO emp VALUES(7521,'WARD','SALESMAN',7698,'1981-02-22',1250,500,30);
	INSERT INTO emp VALUES(7566,'JONES','MANAGER',7839,'1981-04-02',2975,NULL,20);
	INSERT INTO emp VALUES(7654,'MARTIN','SALESMAN',7698,'1981-09-28',1250,1400,30);
	INSERT INTO emp VALUES(7698,'BLAKE','MANAGER',7839,'1981-05-01',2850,NULL,30);
	INSERT INTO emp VALUES(7782,'CLARK','MANAGER',7839,'1981-06-09',2450,NULL,10);
	INSERT INTO emp VALUES(7788,'SCOTT','ANALYST',7566,'1987-04-19',3000,NULL,20);
	INSERT INTO emp VALUES(7839,'KING','PRESIDENT',NULL,'1981-11-17',5000,NULL,10);
	INSERT INTO emp VALUES(7844,'TURNER','SALESMAN',7698,'1981-09-08',1500,0,30);
	INSERT INTO emp VALUES(7876,'ADAMS','CLERK',7788,'1987-05-23',1100,NULL,20);
	INSERT INTO emp VALUES(7900,'JAMES','CLERK',7698,'1981-12-03',950,NULL,30);
	INSERT INTO emp VALUES(7902,'FORD','ANALYST',7566,'1981-12-03',3000,NULL,20);
	INSERT INTO emp VALUES(7934,'MILLER','CLERK',7782,'1982-01-23',1300,NULL,10);
	INSERT INTO emp VALUES(7981,'MILLER','CLERK',7788,'1992-01-23',2600,500,20);
	
	CREATE TABLE dept(
	deptno		INT,
	dname		VARCHAR(14),
	loc		VARCHAR(13)
	);

	INSERT INTO dept VALUES(10, 'ACCOUNTING', 'NEW YORK');
	INSERT INTO dept VALUES(20, 'RESEARCH', 'DALLAS');
	INSERT INTO dept VALUES(30, 'SALES', 'CHICAGO');
	INSERT INTO dept VALUES(40, 'OPERATIONS', 'BOSTON');

1、查询出高于10号部门的平均工资的员工信息

	SELECT * FROM emp WHERE sal > (SELECT AVG(sal) FROM emp WHERE deptno =10)

2、查询出比10号部门任何一个员工薪资高的员工信息

	SELECT * FROM emp WHERE sal > ANY(SELECT sal FROM emp WHERE deptno = 10) AND deptno != 10

3、查询出比20号部门所有员工薪资高的员工信息

	SELECT * FROM emp WHERE sal > ALL(SELECT sal FROM emp WHERE deptno = 20) AND deptno != 20

4、和10号部门同名同工作的员工信息

	SELECT * FROM emp WHERE (ename,job) IN (SELECT ename,job FROM emp WHERE deptno = 10) AND deptno !=10

5、获取员工的名字和部门的名字

	SELECT p.ename,d.dname FROM emp p ,dept d WHERE p.deptno = d.deptno



