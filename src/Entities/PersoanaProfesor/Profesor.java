package Entities.PersoanaProfesor;

import java.util.Scanner;

public class Profesor extends Entities.Persoana implements Comparable<Profesor>
{
    protected Integer an_incepere_activitate;

    public Profesor(){}

    public Profesor(String nume, String prenume, String adresa, Integer an_incepere_activitate) {
        super(nume, prenume, adresa);
        this.an_incepere_activitate = an_incepere_activitate;
    }

    public Integer getAn_incepere_activitate() {
        return an_incepere_activitate;
    }

    public void setAn_incepere_activitate(Integer an_incepere_activitate) {
        this.an_incepere_activitate = an_incepere_activitate;
    }


    //@Override
    public void citire()
    {
        super.citire();
        Scanner keyboard = new Scanner(System.in);

        System.out.println("An incepere activitate: ");
        this.setAn_incepere_activitate(keyboard.nextInt());
        keyboard.nextLine();

    }

    @Override
    public int compareTo(Profesor profesor)
    {
        Integer rezultat_comparatie_nume = this.getNume().compareTo(profesor.getNume());
        if (rezultat_comparatie_nume == 0)
        {
            return this.getPrenume().compareTo(profesor.getPrenume());
        }
        return rezultat_comparatie_nume;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "an_incepere_activitate=" + an_incepere_activitate +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}

