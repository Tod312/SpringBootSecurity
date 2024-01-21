insert into roles(name) values('ROLE_USER');
insert into roles(name) values('ROLE_ADMIN');

insert into users(login, password) values('user', 'user');
insert into users(login, password) values('admin', 'admin');

insert into users_roles(user_id, role_id) values(1,1);
insert into users_roles(user_id, role_id) values(2,2);
insert into users_roles(user_id, role_id) values(2,1);