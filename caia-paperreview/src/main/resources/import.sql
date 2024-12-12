INSERT INTO conferences (name, slug, created_at) VALUES ('Conference 1', 'conference-1', NOW());

INSERT INTO authors (first_name, last_name, email, affiliation, created_at) VALUES ('John', 'Doe', 'cristian@email.com', 'Some Affiliation', NOW());

INSERT INTO papers (conference_id, owner_id, description, keys, state, title, file_name, created_at, updated_at) VALUES (1, 1, 'Description 1', 'Keys 1', 'WAITING_FOR_REVIEW', 'Title 1', '457b580f-8f2b-41e9-b4b2-7604b5cab698.pdf', NOW(), NOW());