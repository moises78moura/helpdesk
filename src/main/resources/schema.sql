CREATE SCHEMA IF NOT EXISTS helpdesk AUTHORIZATION admin;

alter table if exists helpdesk.CHAMADO
drop constraint if exists FKaeg6624elvee1nkfqgyphdkw5;

alter table if exists helpdesk.CHAMADO
drop constraint if exists FK8utamt82wcwddu6f2veryd1jn;

drop table if exists helpdesk.CHAMADO cascade;

drop table if exists helpdesk.PESSOA cascade;

alter table if exists helpdesk.PERFIL
drop constraint if exists FK6ph1j4ud33f1422cqtm5wllv8;

drop table if exists helpdesk.PERFIL cascade;

create table helpdesk.CHAMADO (
                                  ID  serial not null,
                                  DATA_ABERTURA timestamp,
                                  DATA_FECHAMENTO timestamp,
                                  OBSERVACAO varchar(255),
                                  PRIORIDADE int4,
                                  STATUS int4,
                                  TITULO varchar(255),
                                  CLIENTE_ID int4,
                                  TECNICO_ID int4,
                                  primary key (ID)
);

create table helpdesk.PESSOA (
                                 DTYPE varchar(31) not null,
                                 ID  serial not null,
                                 CPF varchar(255),
                                 DATA_CRIACAO date,
                                 EMAIL varchar(255),
                                 NOME varchar(255),
                                 SENHA varchar(255),
                                 primary key (ID)
);

alter table if exists helpdesk.PESSOA
    add constraint UK_ev46at4g6c0mqx5rshnp7sbmo unique (CPF);

alter table if exists helpdesk.PESSOA
    add constraint UK_sxgoosnmyo039uxa5duvu6wtf unique (EMAIL);

create table helpdesk.PERFIL (
                                 Pessoa_ID int4 not null,
                                 perfis int4
);

alter table if exists helpdesk.CHAMADO
    add constraint FKaeg6624elvee1nkfqgyphdkw5
    foreign key (CLIENTE_ID)
    references helpdesk.PESSOA;

alter table if exists helpdesk.CHAMADO
    add constraint FK8utamt82wcwddu6f2veryd1jn
    foreign key (TECNICO_ID)
    references helpdesk.PESSOA;

alter table if exists helpdesk.PERFIL
    add constraint FK6ph1j4ud33f1422cqtm5wllv8
    foreign key (Pessoa_ID)
    references helpdesk.PESSOA;