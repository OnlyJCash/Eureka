insert into eureka_group (id, name, label, entityAllowed) values (1, 'administrator', 'Administrator', '*');

insert into eureka_user (id, username, password, activationDate) values (1, 'admin', '111dc4a0904b76154e2f6646bdc582de', current_timestamp);

insert into eureka_user_group values(1,1);

insert into eureka_auth_token (id, token, user_id) values (1, '123456789', 1);