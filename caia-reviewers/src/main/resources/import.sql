INSERT INTO invitations (user_id, conference_id, state, message, token, created_at) VALUES (1, 1, 'PENDING', 'This is a test invitation', 'token', NOW());
INSERT INTO invitations (user_id, conference_id, state, message, token, created_at) VALUES (1, 2, 'PENDING', 'This is a test invitation', 'token', NOW());

INSERT INTO reviewers (first_name, last_name, email, affiliation, created_at) VALUES ('John', 'Doe', 'cristian@email.com', 'Some Affiliation', NOW());