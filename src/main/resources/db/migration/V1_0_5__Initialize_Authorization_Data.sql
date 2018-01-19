-- client1/password12345
INSERT INTO authorized_user(client_id, client_secret) VALUES ('client1', 'password12345');
-- client2/password12345
INSERT INTO authorized_user(client_id, client_secret) VALUES ('client2', 'password12345');

INSERT INTO authorized_user_role(client_id, role) VALUES ('client1', 'ROLE_ADMIN');
INSERT INTO authorized_user_role(client_id, role) VALUES ('client1', 'ROLE_USER');
INSERT INTO authorized_user_role(client_id, role) VALUES ('client2', 'ROLE_USER');