INSERT INTO user(user_name, password) VALUES ('admin', '$2a$10$C2TT9AEHQxbNfq27eg6aB.J1HAuJeC8UjjTR8fhNuxr8vzluihfnW');
INSERT INTO user(user_name, password) VALUES ('user', '$2a$10$sAHr3wIlH9InqzQqm6Pr.e5dTkCDG5L5NE0Dr8qzGQOhR044mH5I6');

INSERT INTO user_role(user_name, role) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_role(user_name, role) VALUES ('admin', 'ROLE_USER');
INSERT INTO user_role(user_name, role) VALUES ('user', 'ROLE_USER');