create table lessons (id bigint not null auto_increment, student_group varchar(120) not null, subject varchar(120) not null, teacher varchar(120) not null, room_id bigint, timeslot_id bigint, primary key (id)) engine=InnoDB;
create table rooms (id bigint not null auto_increment, name varchar(120) not null, primary key (id)) engine=InnoDB;
create table timeslots (id bigint not null auto_increment, day_of_week integer not null, end_time time not null, start_time time not null, primary key (id)) engine=InnoDB;
alter table lessons add constraint FKrjf1qatcgcki9lap3l1x3ap8l foreign key (room_id) references rooms (id);
alter table lessons add constraint FKaajqm1mc6we57i8otga3d4nbq foreign key (timeslot_id) references timeslots (id);
