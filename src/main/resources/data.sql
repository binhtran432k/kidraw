INSERT INTO "user" (id, username, password)
VALUES
    (1, 'admin', '$2a$10$Sgd4ap5QSBaFbPSgeEJVoeCnLHvwVU2Vhjxg4uisAYx0sJXhWmoee'),
    (2, 'student', '$2a$10$Sgd4ap5QSBaFbPSgeEJVoeCnLHvwVU2Vhjxg4uisAYx0sJXhWmoee'),
    (3, 'children', '$2a$10$Sgd4ap5QSBaFbPSgeEJVoeCnLHvwVU2Vhjxg4uisAYx0sJXhWmoee'),
    (4, 'teacher', '$2a$10$Sgd4ap5QSBaFbPSgeEJVoeCnLHvwVU2Vhjxg4uisAYx0sJXhWmoee'),
    (5, 'staff', '$2a$10$Sgd4ap5QSBaFbPSgeEJVoeCnLHvwVU2Vhjxg4uisAYx0sJXhWmoee');
INSERT INTO "role" (id, name, description)
VALUES
    (1, 'ADMIN_USER', 'Manage all system'),
    (2, 'STUDENT_USER', 'May be parent or children of system'),
    (3, 'INHERIT_PARENT', 'For children can use some feature of parent'),
    (4, 'TEACHER_USER', 'Be teacher of the system'),
    (5, 'STAFF_USER', 'Be staff of the system');
INSERT INTO "users_roles" (user_id, role_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 2),
    (3, 3),
    (4, 4),
    (5, 5);