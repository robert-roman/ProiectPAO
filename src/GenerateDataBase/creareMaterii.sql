-- Table: public.Materii

-- DROP TABLE IF EXISTS public."Materii";

CREATE TABLE IF NOT EXISTS public."Materii"
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    denumire character varying(40) COLLATE pg_catalog."default" NOT NULL,
    nr_credite integer NOT NULL,
    CONSTRAINT "Materie_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Materii"
    OWNER to postgres;