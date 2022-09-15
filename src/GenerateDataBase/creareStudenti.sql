-- Table: public.Studenti

-- DROP TABLE IF EXISTS public."Studenti";

CREATE TABLE IF NOT EXISTS public."Studenti"
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    nume character varying(40) COLLATE pg_catalog."default" NOT NULL,
    prenume character varying(40) COLLATE pg_catalog."default" NOT NULL,
    adresa character varying(40) COLLATE pg_catalog."default" NOT NULL,
    specializare character varying(40) COLLATE pg_catalog."default" NOT NULL,
    an integer NOT NULL,
    grupa integer NOT NULL,
    tip_studiu character varying COLLATE pg_catalog."default" NOT NULL,
    nota_corespondenta double precision NOT NULL,
    CONSTRAINT "Student_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Studenti"
    OWNER to postgres;