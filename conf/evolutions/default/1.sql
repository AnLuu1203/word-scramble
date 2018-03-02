# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table entries (
  word                          varchar(255)
);


# --- !Downs

drop table if exists entries;

