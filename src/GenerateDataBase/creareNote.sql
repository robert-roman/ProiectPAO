-- Table: public.Note

-- DROP TABLE IF EXISTS public."Note";

CREATE TABLE IF NOT EXISTS public."Note"
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    materie integer NOT NULL,
    profesor integer NOT NULL,
    student integer NOT NULL,
    nota_materie integer NOT NULL,
    CONSTRAINT "Note_pkey" PRIMARY KEY (id),
    CONSTRAINT materie FOREIGN KEY (materie)
        REFERENCES public."Materii" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT profesor FOREIGN KEY (profesor)
        REFERENCES public."Profesori" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT student FOREIGN KEY (student)
        REFERENCES public."Studenti" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Note"
    OWNER to postgres;