--Conferences
INSERT INTO conferences (name, description, slug, created_at) VALUES ('Conference 1', 'Description 1', 'conference-1', NOW());
INSERT INTO conferences (name, description, slug, created_at) VALUES ('Conference 2', 'Description 2', 'conference-2', NOW());
INSERT INTO conferences (name, description, slug, created_at) VALUES ('Conference 3', 'Description 3', 'conference-3', NOW());
INSERT INTO conferences (name, description, slug, created_at) VALUES ('Conference 4', 'Description 4', 'conference-4', NOW());
INSERT INTO conferences (name, description, slug, created_at) VALUES ('Conference 5', 'Description 5', 'conference-5', NOW());
-------------------

--Users-----------
INSERT INTO organizers (first_name, last_name, email, affiliation, created_at) VALUES ('John', 'Doe', 'cristian@email.com', 'Some Affiliation', NOW());
INSERT INTO organizers (first_name, last_name, email, affiliation, created_at) VALUES ('Alice', 'Smith', 'alice@email.com', 'Some Affiliation',  NOW());
------------------

--Members-----------
INSERT INTO members (organizer_id, conference_id, created_at) VALUES (1, 1, NOW());
INSERT INTO members (organizer_id, conference_id, created_at) VALUES (2, 2, NOW());
INSERT INTO members (organizer_id, conference_id, created_at) VALUES (2, 3, NOW());
--------------------