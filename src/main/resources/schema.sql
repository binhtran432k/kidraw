-- Drop all table
DROP TABLE IF EXISTS "users_roles";
DROP TABLE IF EXISTS "users_contests";
DROP TABLE IF EXISTS "users_courses";
DROP TABLE IF EXISTS "user";
DROP TABLE IF EXISTS "role";
DROP TABLE IF EXISTS "contest";
DROP TABLE IF EXISTS "course";
DROP TABLE IF EXISTS "art_level";
DROP TABLE IF EXISTS "art_type";
DROP TABLE IF EXISTS "class";

-- Concreate tables
CREATE TABLE "user" (
    "id" bigint NOT NULL,
    "email" varchar(255) UNIQUE,
    "username" varchar(255) UNIQUE NOT NULL,
    "password" varchar(255) NOT NULL,
    "address" varchar(255),
    "date_of_birth" date,
    "first_name" varchar(255),
    "last_name" varchar(255),
    "phone" varchar(255),
    "sex" char(1),
    "create_time" timestamp NOT NULL DEFAULT now(),
    "enabled" boolean NOT NULL DEFAULT true,
    "locked" boolean NOT NULL DEFAULT false,
    PRIMARY KEY ("id")
);

CREATE TABLE "role" (
    "id" bigint NOT NULL,
    "name" varchar(255) UNIQUE NOT NULL,
    "description" text,
    PRIMARY KEY ("id")
);

CREATE TABLE "art_level" (
    "id" bigint NOT NULL,
    "name" varchar(255) UNIQUE NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "art_type" (
    "id" bigint NOT NULL,
    "name" varchar(255) UNIQUE NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "class" (
    "id" bigint NOT NULL,
    "name" varchar(255) UNIQUE NOT NULL,
    "max_student" integer NOT NULL DEFAULT 0,
    PRIMARY KEY ("id")
);

CREATE TABLE "contest" (
    "id" bigint NOT NULL,
    "name" varchar(255) UNIQUE NOT NULL,
    "body" text NOT NULL,
    "registration_time" timestamp NOT NULL DEFAULT now(),
    "start_time" timestamp NOT NULL DEFAULT now(),
    "end_time" timestamp NOT NULL DEFAULT now(),
    "max_participant" integer NOT NULL DEFAULT 0,
    "visibility" boolean NOT NULL DEFAULT false,
    "art_level_id" bigint NOT NULL,
    "art_type_id" bigint NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "course" (
    "id" bigint NOT NULL,
    "name" varchar(255) UNIQUE NOT NULL,
    "description" text NOT NULL,
    "price" double precision NOT NULL DEFAULT 0.0,
    "max_student" integer NOT NULL DEFAULT 0,
    "visibility" boolean NOT NULL DEFAULT false,
    "art_level_id" bigint NOT NULL,
    "art_type_id" bigint NOT NULL,
    PRIMARY KEY ("id")
);

-- Many to many relation

CREATE TABLE "users_contests" (
    "user_id" bigint NOT NULL,
    "contest_id" bigint NOT NULL,
    PRIMARY KEY ("user_id", "contest_id")
);

CREATE TABLE "users_courses" (
    "user_id" bigint NOT NULL,
    "course_id" bigint NOT NULL,
    PRIMARY KEY ("user_id", "course_id")
);

CREATE TABLE "users_roles" (
    "user_id" bigint NOT NULL,
    "role_id" bigint NOT NULL,
    PRIMARY KEY ("user_id", "role_id")
);

-- Foreign keys

ALTER TABLE "contest"
ADD CONSTRAINT "fk_art_level_contest"
FOREIGN KEY ("art_level_id")
REFERENCES "art_level";

ALTER TABLE "contest"
ADD CONSTRAINT "fk_art_type_contest"
FOREIGN KEY ("art_type_id")
REFERENCES "art_type";

ALTER TABLE "course"
ADD CONSTRAINT "fk_art_level_course"
FOREIGN KEY ("art_level_id")
REFERENCES "art_level";

ALTER TABLE "course"
ADD CONSTRAINT "fk_art_type_course"
FOREIGN KEY ("art_type_id")
REFERENCES "art_type";

ALTER TABLE "users_contests"
ADD CONSTRAINT "fk_user_users_contests"
FOREIGN KEY ("user_id")
REFERENCES "user";

ALTER TABLE "users_contests"
ADD CONSTRAINT "fk_contest_users_contests"
FOREIGN KEY ("contest_id")
REFERENCES "contest";

ALTER TABLE "users_courses"
ADD CONSTRAINT "fk_user_users_courses"
FOREIGN KEY ("user_id")
REFERENCES "user";

ALTER TABLE "users_courses"
ADD CONSTRAINT "fk_course_users_courses"
FOREIGN KEY ("course_id")
REFERENCES "course";

ALTER TABLE "users_roles"
ADD CONSTRAINT "fk_user_users_roles"
FOREIGN KEY ("user_id")
REFERENCES "user";

ALTER TABLE "users_roles"
ADD CONSTRAINT "fk_role_users_roles"
FOREIGN KEY ("role_id")
REFERENCES "role";