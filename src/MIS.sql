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
   StudentName          varchar(20) not null,
   StudentBirthday      date,
   StudentSex           varchar(2),
   TotalCredit          int,
   PhoneNumber          varchar(11) not null,
   StudentEmail         varchar(20) not null,
   primary key (StudentNo)
);

/*==============================================================*/
/* Table: Teacher                                               */
/*==============================================================*/
create table Teacher
(
   TeacherNo            varchar(4) not null,
   TeacherName          varchar(20) not null,
   TeacherSex           varchar(2),
   TeacherBirthday      date,
   TeacherTitle         varchar(5),
   TeacherEmail         varchar(20) not null,
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

