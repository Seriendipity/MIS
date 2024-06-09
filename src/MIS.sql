/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2024/5/24 19:19:24                           */
/*==============================================================*/


drop table if exists Class;

drop table if exists Course;

drop table if exists Evaluation;

drop table if exists Student;

drop table if exists Teacher;

drop table if exists sc;

drop table if exists teaching;

/*==============================================================*/
/* Table: Class                                                 */
/*==============================================================*/
create table Class
(
   ClassNo              char(6) not null,
   ClassName            varchar(20),
   ClassMajor           varchar(20),
   ClassDept            varchar(20),
   StudentNumber        int,
   primary key (ClassNo)
);

/*==============================================================*/
/* Table: Course                                                */
/*==============================================================*/
create table Course
(
   CourseNo             varchar(20) not null,
   CourseName           varchar(20) not null,
   CourseCredit         int,
   primary key (CourseNo)
);

/*==============================================================*/
/* Table: Evaluation                                            */
/*==============================================================*/
create table Evaluation
(
   StudentNo            varchar(8) not null,
   CourseNo             varchar(20) not null,
   TeacherNo            varchar(4) not null,
   EvaluationGrade      int,
   EvaluationComment    varchar(100),
   primary key (StudentNo, CourseNo, TeacherNo)
);

/*==============================================================*/
/* Table: Student                                               */
/*==============================================================*/
create table Student
(
   StudentNo            varchar(8) not null,
   ClassNo              char(6),
   StudentName          varchar(20) ,
   StudentBirthday      date,
   StudentSex           varchar(2),
   TotalCredit          int,
   PhoneNumber          varchar(11) ,
   StudentEmail         varchar(20) ,
   primary key (StudentNo)
);

/*==============================================================*/
/* Table: Teacher                                               */
/*==============================================================*/
create table Teacher
(
   TeacherNo            varchar(4) not null,
   TeacherName          varchar(20) ,
   TeacherSex           varchar(2),
   TeacherBirthday      date,
   TeacherTitle         varchar(5),
   TeacherEmail         varchar(20) ,
   primary key (TeacherNo)
);

/*==============================================================*/
/* Table: sc                                                    */
/*==============================================================*/
create table sc
(
   StudentNo            varchar(8) not null,
   CourseNo             varchar(20) not null,
   Grade                int,
   primary key (StudentNo, CourseNo)
);

/*==============================================================*/
/* Table: teaching                                              */
/*==============================================================*/
create table teaching
(
   CourseNo             varchar(20) not null,
   TeacherNo            varchar(4) not null,
   Language             varchar(2) not null,
   primary key (CourseNo, TeacherNo)
);

alter table Evaluation add constraint FK_Reference_6 foreign key (StudentNo)
      references Student (StudentNo) on delete restrict on update restrict;

alter table Evaluation add constraint FK_Reference_7 foreign key (CourseNo)
      references Course (CourseNo) on delete restrict on update restrict;

alter table Evaluation add constraint FK_Reference_8 foreign key (TeacherNo)
      references Teacher (TeacherNo) on delete restrict on update restrict;

alter table Student add constraint FK_belong foreign key (ClassNo)
      references Class (ClassNo) on delete restrict on update restrict;

alter table sc add constraint FK_sc foreign key (StudentNo)
      references Student (StudentNo) on delete restrict on update restrict;

alter table sc add constraint FK_sc2 foreign key (CourseNo)
      references Course (CourseNo) on delete restrict on update restrict;

alter table teaching add constraint FK_teaching foreign key (CourseNo)
      references Course (CourseNo) on delete restrict on update restrict;

alter table teaching add constraint FK_teaching2 foreign key (TeacherNo)
      references Teacher (TeacherNo) on delete restrict on update restrict;


alter table student add column password varchar(20) default 'Bjtu@123456';
alter table teacher add column password varchar(20) default 'Bjtu@teacher';

/*==============================================================*/
/* Table: exam                                                  */
/*==============================================================*/
create table exam
(
    cid varchar(3),
    ExamDate DATE,
    primary key (cid),
    FOREIGN KEY (cid) references teaching(cid)
);


alter table sc add column  cid varchar(3);
alter table teaching add column cid varchar(3) ;
alter table teaching add constraint unique (cid);

/*==============================================================*/
/* Table: Admin                                                 */
/*==============================================================*/
create table Admin(
  name varchar(10) primary key default 'admin@root',
  password varchar(20) default 'admin@password'
)

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User(
    UserName varchar(8) primary key ,
    password varchar(20)
)

    insert into class(classno, classname, classmajor, classdept, studentnumber)
VALUES('RJ2201','软件2201','软件工程','人工智能',0),
      ('RJ2202','软件2202','软件工程','算法',0),
      ('RJ2203','软件2203','软件工程','前端',0),
      ('RJ2204','软件2204','软件工程','后端',0),
      ('RJ2205','软件2205','软件工程','Web开发',0),
      ('RJ2206','软件2206','软件工程','游戏开发',0);

insert into course(courseno, coursename, coursecredit)
VALUES ('00000001','数据库',4),
       ('00000002','算法',3),
       ('00000003','人工智能',4),
       ('00000004','离散数学',5),
       ('00000005','概率论',6),
       ('00000006','微积分',6),
       ('00000007','物理',4);

insert into teacher(teacherno, teachername, teachersex, teacherbirthday, teachertitle, teacheremail)
VALUES ('1001','张军','男','1978-09-04','教授','1001@bjtu.edu.cn'),
       ('1002','田静','女','1988-04-04','副教授','1002@bjtu.edu.cn'),
       ('1003','徐国立','男','1968-11-04','教授','1003@bjtu.edu.cn'),
       ('1004','赵薇','女','1977-04-21','讲师','1004@bjtu.edu.cn'),
       ('1005','王毅','男','1988-06-11','讲师','1005@bjtu.edu.cn'),
       ('1006','赵磊','男','1966-10-15','教授','1006@bjtu.edu.cn'),
       ('1007','孙悦','男','1984-05-11','讲师','1007@bjtu.edu.cn');

insert into teaching(courseno, teacherno, language, cid)
VALUES ('00000001','1001','中文','1'),
       ('00000001','1002','双语','2'),
       ('00000002','1002','英文','3'),
       ('00000003','1003','中文','4'),
       ('00000004','1004','中文','5'),
       ('00000004','1005','中文','6'),
       ('00000007','1007','双语','7'),
       ('00000005','1003','中文','8');

insert into student(studentno, classno, studentname, studentbirthday, studentsex, totalcredit, phonenumber, studentemail, password)
VALUES ('22901111','RJ2201','张伟','2004-03-08','男',0,'19021345421','22901111@bjtu.edu.cn','Bjtu@123456'),
       ('22902222','RJ2201','田曦薇','2003-06-07','女',0,'13853225432','22902222@bjtu.edu.cn','Bjtu@123456'),
       ('22903333','RJ2202','王威','2004-09-12','男',0,'13678294321','22903333@bjtu.edu.cn','Bjtu@123456'),
       ('22904444','RJ2203','蒙毅','2004-06-13','男',0,'15592385321','22904444@bjtu.edu.cn','Bjtu@123456'),
       ('22905555','RJ2204','孙奕东','2005-04-08','男',0,'1426345134','22905555@bjtu.edu.cn','Bjtu@123456'),
       ('22906666','RJ2205','宁静','2004-09-30','女',0,'18721343221','22906666@bjtu.edu.cn','Bjtu@123456'),
       ('22907777','RJ2206','李亦非','2004-05-13','女',0,'14321246211','22907777@bjtu.edu.cn','Bjtu@123456'),
       ('22908888','RJ2206','周文','2004-02-11','男',0,'17543673553','22908888@bjtu.edu.cn','Bjtu@123456'),
       ('22909999','RJ2202','任飞','2004-08-08','男',0,'18431362413','22909999@bjtu.edu.cn','Bjtu@123456'),
       ('22900000','RJ2201','朱凯','2004-01-04','男',0,'18124521345','22900000@bjtu.edu.cn','Bjtu@123456');

insert into user(username, password)
VALUES ('22901111','Bjtu@123456'),
       ('22902222','Bjtu@123456'),
       ('22903333','Bjtu@123456'),
       ('22904444','Bjtu@123456'),
       ('22905555','Bjtu@123456'),
       ('22906666','Bjtu@123456'),
       ('22907777','Bjtu@123456'),
       ('22908888','Bjtu@123456'),
       ('22909999','Bjtu@123456'),
       ('22900000','Bjtu@123456'),
       ('1001','Bjtu@teacher'),
       ('1002','Bjtu@teacher'),
       ('1003','Bjtu@teacher'),
       ('1004','Bjtu@teacher'),
       ('1005','Bjtu@teacher'),
       ('1006','Bjtu@teacher'),
       ('1007','Bjtu@teacher');
