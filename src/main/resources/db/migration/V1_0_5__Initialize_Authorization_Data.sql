INSERT INTO authorized_user(client_id, client_secret, authorized_grant_type, scope) VALUES ('client1', 'password12345', 'client_credentials,refresh_token', 'user,repo');
INSERT INTO authorized_user(client_id, client_secret, authorized_grant_type, scope) VALUES ('client2', 'password12345', 'password,refresh_token', 'user,repo');

INSERT INTO authorized_user_role(client_id, role) VALUES ('client1', 'ROLE_ADMIN');
INSERT INTO authorized_user_role(client_id, role) VALUES ('client1', 'ROLE_USER');
INSERT INTO authorized_user_role(client_id, role) VALUES ('client2', 'ROLE_USER');