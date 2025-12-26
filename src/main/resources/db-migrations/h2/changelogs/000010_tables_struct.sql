--changeset igor-dev:1
CREATE TABLE public.users
(
    id            uuid DEFAULT gen_random_uuid() NOT NULL,
    "name"        varchar(100)                   NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uname_uniq UNIQUE("name")
);
--rollback drop table users;

--changeset igor-dev:2
CREATE TABLE public.messages
(
    id      uuid             DEFAULT gen_random_uuid() NOT NULL,
    user_id uuid NULL,
    content varchar(500),
    CONSTRAINT messages_pkey PRIMARY KEY (id),
    CONSTRAINT user_fkey     FOREIGN KEY (user_id) REFERENCES public.users (id)
);
--rollback drop table messages;
