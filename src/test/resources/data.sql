insert into role (id, name) values (1, 'LIBRARIAN');
insert into role (id, name) values (2, 'BORROWER');
insert into role (id, name) values (3, 'CASHIER');

insert into user (id, email, enabled, first_name, last_name, password, token_expired)
values (1, 'a_b@a.com', true, 'a', 'b', 'abcdef', false );
insert into user (id, email, enabled, first_name, last_name, password, token_expired)
values (2, 'c_d@a.com', true, 'c', 'd', 'cdefgh', false );
insert into user (id, email, enabled, first_name, last_name, password, token_expired)
values (3, 'e_f@a.com', true, 'e', 'f', 'efghij', false );

-- insert into users_roles (user_id, role_id) values (1, 3);
-- insert into users_roles (user_id, role_id) values (2, 1);
-- insert into users_roles (user_id, role_id) values (3, 2);