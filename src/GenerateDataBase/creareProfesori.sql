-- Table: public.Profesori

-- DROP TABLE IF EXISTS public."Profesori";

CREATE TABLE IF NOT EXISTS public."Profesori"
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    nume character varying(40) COLLATE pg_catalog."default" NOT NULL,
    prenume character varying(40) COLLATE pg_catalog."default" NOT NULL,
    adresa character varying(40) COLLATE pg_catalog."default" NOT NULL,
    an_incepere_activitate integer NOT NULL,
    specializare character varying(40) COLLATE pg_catalog."default" NOT NULL,
    info_corespunzator integer NOT NULL,
    CONSTRAINT "Profesor_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Profesori"
    OWNER to postgres;