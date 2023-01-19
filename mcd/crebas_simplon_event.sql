/*==============================================================*/
/* Nom de SGBD :  PostgreSQL 8                                  */
/* Date de création :  19/01/2023 11:24:12                      */
/*==============================================================*/


drop index EVENEMENT_PK;

drop table EVENEMENT;

drop index R_PARTICIPANT_EVENEMENT_FK;

drop index PARTICIPANT_PK;

drop table PARTICIPANT;

/*==============================================================*/
/* Table : EVENEMENT                                            */
/*==============================================================*/
create table EVENEMENT (
   ID_EVENEMENT         SERIAL               not null,
   DATE_EVENEMENT       DATE                 null,
   LIB_EVENEMENT        VARCHAR(250)         null,
   constraint PK_EVENEMENT primary key (ID_EVENEMENT)
);

/*==============================================================*/
/* Index : EVENEMENT_PK                                         */
/*==============================================================*/
create unique index EVENEMENT_PK on EVENEMENT (
ID_EVENEMENT
);

/*==============================================================*/
/* Table : PARTICIPANT                                          */
/*==============================================================*/
create table PARTICIPANT (
   ID_PARTICIPANT       SERIAL               not null,
   ID_EVENEMENT         INT4                 not null,
   NOM_PARTICIPANT      VARCHAR(50)          null,
   PRENOM_PARTICIPANT   VARCHAR(150)         null,
   TEL_PARTICIPANT      VARCHAR(20)          null,
   EMAIL_PARTICIPANT    VARCHAR(100)         null,
   constraint PK_PARTICIPANT primary key (ID_PARTICIPANT)
);

/*==============================================================*/
/* Index : PARTICIPANT_PK                                       */
/*==============================================================*/
create unique index PARTICIPANT_PK on PARTICIPANT (
ID_PARTICIPANT
);

/*==============================================================*/
/* Index : R_PARTICIPANT_EVENEMENT_FK                           */
/*==============================================================*/
create  index R_PARTICIPANT_EVENEMENT_FK on PARTICIPANT (
ID_EVENEMENT
);

alter table PARTICIPANT
   add constraint FK_PARTICIP_R_PARTICI_EVENEMEN foreign key (ID_EVENEMENT)
      references EVENEMENT (ID_EVENEMENT)
      on delete restrict on update restrict;

