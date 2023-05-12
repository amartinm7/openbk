CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks
(
    uuid        uuid                     NOT NULL,
    name        VARCHAR(100)             NOT NULL,
    description VARCHAR(100)             NOT NULL,
    priority    INT                      NOT NULL,
    created_at  timestamp with time zone NOT NULL DEFAULT (now()):: timestamp(0) without time zone,
    modified_at timestamp with time zone NOT NULL DEFAULT (now()):: timestamp(0) without time zone
);

ALTER TABLE tasks
    ADD CONSTRAINT pk_tasks UNIQUE (uuid);

CREATE INDEX IF NOT EXISTS idx_tasks_uuid ON tasks (uuid);