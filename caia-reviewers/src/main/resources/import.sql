INSERT INTO conferences (name, slug, created_at) VALUES ('Conference 1', 'conference-1', NOW());
INSERT INTO conferences (name, slug, created_at) VALUES ('Conference 2', 'conference-2', NOW());

INSERT INTO reviewers (first_name, last_name, email, affiliation, created_at) VALUES ('John', 'Doe', 'cristian@email.com', 'Some Affiliation', NOW());

INSERT INTO invitations (user_id, conference_id, state, message, token, created_at) VALUES (1, 1, 'PENDING', 'This is a test invitation', 'token', NOW());
INSERT INTO invitations (user_id, conference_id, state, message, token, created_at) VALUES (1, 2, 'PENDING', 'This is a test invitation', 'token', NOW());
