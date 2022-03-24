INSERT INTO "user" (id, username, email, password)
VALUES
    (1, 'admin', 'admin@email.com', '$2a$10$Sgd4ap5QSBaFbPSgeEJVoeCnLHvwVU2Vhjxg4uisAYx0sJXhWmoee'),
    (2, 'student', null, '$2a$10$Sgd4ap5QSBaFbPSgeEJVoeCnLHvwVU2Vhjxg4uisAYx0sJXhWmoee'),
    (3, 'children', 'children@email.com', '$2a$10$Sgd4ap5QSBaFbPSgeEJVoeCnLHvwVU2Vhjxg4uisAYx0sJXhWmoee'),
    (4, 'teacher', 'teacher@email.com', '$2a$10$Sgd4ap5QSBaFbPSgeEJVoeCnLHvwVU2Vhjxg4uisAYx0sJXhWmoee'),
    (5, 'staff', null, '$2a$10$Sgd4ap5QSBaFbPSgeEJVoeCnLHvwVU2Vhjxg4uisAYx0sJXhWmoee');
INSERT INTO "role" (id, name, description)
VALUES
    (1, 'ROLE_ADMIN', 'Manage all system'),
    (2, 'ROLE_STUDENT', 'May be parent or children of system'),
    (3, 'INHERIT_PARENT', 'For children can use some feature of parent'),
    (4, 'ROLE_TEACHER', 'Be teacher of the system'),
    (5, 'ROLE_STAFF', 'Be staff of the system');
INSERT INTO "users_roles" (user_id, role_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 2),
    (3, 3),
    (4, 4),
    (5, 5);