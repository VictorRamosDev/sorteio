CREATE TABLE PLAYER(
    id bigint auto_increment not null,
    name varchar(50) not null,
    pot_id bigint not null,
    team_id bigint null,

    primary key(id)
)engine=InnoDB default charset=utf8;

CREATE TABLE POT(
    id bigint auto_increment not null,
    level bigint not null,

    primary key(id),
    constraint uq_pot_level unique (level)
)engine=InnoDB default charset=utf8;

CREATE TABLE TEAM(
    id bigint auto_increment not null,
    team_name varchar(10),

    primary key(id)
)engine=InnoDB default charset=utf8;
