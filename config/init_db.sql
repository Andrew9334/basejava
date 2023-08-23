CREATE TABLE resumes (
                        uuid      CHAR(36) PRIMARY KEY NOT NULL,
                        full_name TEXT                 NOT NULL
);

CREATE TABLE contact (
                         id          SERIAL,
                         resume_uuid CHAR(36) NOT NULL REFERENCES resumes (uuid) ON DELETE CASCADE,
                         type        TEXT     NOT NULL,
                         value       TEXT     NOT NULL
);
CREATE UNIQUE INDEX contact_uuid_type_index
    ON contact (resume_uuid, type);

CREATE TABLE section (
                         id          SERIAL PRIMARY KEY,
                         resume_uuid CHAR(36) NOT NULL REFERENCES resumes (uuid) ON DELETE CASCADE,
                         type        TEXT     NOT NULL,
                         content     TEXT     NOT NULL
);
CREATE UNIQUE INDEX section_idx
    ON section (resume_uuid, type);



-- -- auto-generated definition
-- create table resume
-- (
--     uuid      char(36) not null
--         constraint uuid
--             primary key,
--     full_name text     not null
-- );
--
-- alter table resume
--     owner to postgres;
--
-- -- auto-generated definition
-- create table contact
-- (
--     id          integer not null
--         constraint id
--             primary key,
--     type        text    not null,
--     value       text    not null,
--     resume_uuid char(36)
--         constraint contact_resume_uuid_fk
--             references resume
--             on delete cascade
-- );
--
-- alter table contact
--     owner to postgres;
--
