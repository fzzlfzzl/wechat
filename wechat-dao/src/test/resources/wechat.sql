create table AccessToken (id bigint not null auto_increment, accessToken varchar(255), timeout integer not null, updatetime bigint not null, primary key (id))
create table Admin (id bigint not null auto_increment, name varchar(255) not null, password varchar(255) not null, primary key (id))
create table Message (id bigint not null auto_increment, content varchar(255), createTime bigint not null, event varchar(255), eventKey varchar(255), fromUserName varchar(255), msgId varchar(255), msgType varchar(255), toUserName varchar(255), primary key (id))
create table Orders (id bigint not null auto_increment, address varchar(255), dishes varchar(255), name varchar(255), openid varchar(255), telephone varchar(255), primary key (id))
create table User (openId varchar(255) not null, address varchar(255), telephone varchar(255), primary key (openId))
alter table Admin drop constraint UK_neu4frbbxvyq4gvaji5uagnuo
alter table Admin add constraint UK_neu4frbbxvyq4gvaji5uagnuo  unique (name)
create index createTimeIndex on Message (createTime)
alter table Message add constraint FK_lxd4e1hxvg4dlgrbpvjavr3hs foreign key (fromUserName) references User (openId)
