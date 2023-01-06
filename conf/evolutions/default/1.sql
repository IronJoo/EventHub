# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table category (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_category primary key (id)
);

create table company (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  bio                           varchar(255),
  constraint pk_company primary key (id)
);

create table event (
  id                            bigint auto_increment not null,
  title                         varchar(255),
  description                   varchar(1500),
  start_time                    datetime(6),
  end_time                      datetime(6),
  company_id                    bigint,
  category_id                   bigint,
  venue_id                      bigint,
  constraint pk_event primary key (id)
);

create table request (
  user_id                       bigint,
  company_id                    bigint,
  status                        integer,
  constraint uq_request_user_id unique (user_id),
  constraint uq_request_company_id unique (company_id)
);

create table role (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint uq_role_name unique (name),
  constraint pk_role primary key (id)
);

create table role_user (
  user_id                       bigint,
  role_id                       bigint,
  is_active                     TINYINT DEFAULT 0 not null
);

create table section (
  id                            bigint auto_increment not null,
  event_id                      bigint,
  name                          varchar(255),
  description                   varchar(255),
  capacity                      integer,
  price                         float,
  constraint pk_section primary key (id)
);

create table ticket (
  id                            bigint auto_increment not null,
  event_id                      bigint,
  section_id                    bigint,
  user_id                       bigint,
  constraint pk_ticket primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  first_name                    varchar(255),
  last_name                     varchar(255),
  email                         varchar(255),
  password                      varchar(255),
  status                        varchar(255),
  balance                       float,
  constraint pk_user primary key (id)
);

create table user_role (
  user_id                       bigint not null,
  role_id                       bigint not null,
  constraint pk_user_role primary key (user_id,role_id)
);

create table venue (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  address                       varchar(255),
  city                          varchar(255),
  constraint pk_venue primary key (id)
);

create index ix_event_company_id on event (company_id);
alter table event add constraint fk_event_company_id foreign key (company_id) references company (id) on delete restrict on update restrict;

create index ix_event_category_id on event (category_id);
alter table event add constraint fk_event_category_id foreign key (category_id) references category (id) on delete restrict on update restrict;

create index ix_event_venue_id on event (venue_id);
alter table event add constraint fk_event_venue_id foreign key (venue_id) references venue (id) on delete restrict on update restrict;

alter table request add constraint fk_request_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table request add constraint fk_request_company_id foreign key (company_id) references company (id) on delete restrict on update restrict;

create index ix_role_user_user_id on role_user (user_id);
alter table role_user add constraint fk_role_user_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;

create index ix_role_user_role_id on role_user (role_id);
alter table role_user add constraint fk_role_user_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;

create index ix_section_event_id on section (event_id);
alter table section add constraint fk_section_event_id foreign key (event_id) references event (id) on delete restrict on update restrict;

create index ix_ticket_event_id on ticket (event_id);
alter table ticket add constraint fk_ticket_event_id foreign key (event_id) references event (id) on delete restrict on update restrict;

create index ix_ticket_section_id on ticket (section_id);
alter table ticket add constraint fk_ticket_section_id foreign key (section_id) references section (id) on delete restrict on update restrict;

create index ix_ticket_user_id on ticket (user_id);
alter table ticket add constraint fk_ticket_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;

create index ix_user_role_user on user_role (user_id);
alter table user_role add constraint fk_user_role_user foreign key (user_id) references user (id) on delete restrict on update restrict;

create index ix_user_role_role on user_role (role_id);
alter table user_role add constraint fk_user_role_role foreign key (role_id) references role (id) on delete restrict on update restrict;


# --- !Downs

alter table event drop foreign key fk_event_company_id;
drop index ix_event_company_id on event;

alter table event drop foreign key fk_event_category_id;
drop index ix_event_category_id on event;

alter table event drop foreign key fk_event_venue_id;
drop index ix_event_venue_id on event;

alter table request drop foreign key fk_request_user_id;

alter table request drop foreign key fk_request_company_id;

alter table role_user drop foreign key fk_role_user_user_id;
drop index ix_role_user_user_id on role_user;

alter table role_user drop foreign key fk_role_user_role_id;
drop index ix_role_user_role_id on role_user;

alter table section drop foreign key fk_section_event_id;
drop index ix_section_event_id on section;

alter table ticket drop foreign key fk_ticket_event_id;
drop index ix_ticket_event_id on ticket;

alter table ticket drop foreign key fk_ticket_section_id;
drop index ix_ticket_section_id on ticket;

alter table ticket drop foreign key fk_ticket_user_id;
drop index ix_ticket_user_id on ticket;

alter table user_role drop foreign key fk_user_role_user;
drop index ix_user_role_user on user_role;

alter table user_role drop foreign key fk_user_role_role;
drop index ix_user_role_role on user_role;

drop table if exists category;

drop table if exists company;

drop table if exists event;

drop table if exists request;

drop table if exists role;

drop table if exists role_user;

drop table if exists section;

drop table if exists ticket;

drop table if exists user;

drop table if exists user_role;

drop table if exists venue;

