package Entities.PersoanaProfesor;

import java.util.Scanner;

public class Profesor_laborator extends Profesor
{
    private Integer nr_exercitii_saptamanale;

    public Profesor_laborator() {
    }

    public Profesor_laborator(String nume, String prenume, String adresa, Integer an_incepere_activitate, Integer nr_exercitii_saptamanale) {
        super(nume, prenume, adresa, an_incepere_activitate);
        this.nr_exercitii_saptamanale = nr_exercitii_saptamanale;
    }

    public Integer getNr_exercitii_saptamanale() {
        return nr_exercitii_saptamanale;
    }

    public void setNr_exercitii_saptamanale(Integer nr_exercitii_saptamanale) {
        this.nr_exercitii_saptamanale = nr_exercitii_saptamanale;
    }


    public void citire()
    {
        super.citire();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Numar exercitii saptamanale:");
        this.setNr_exercitii_saptamanale(keyboard.nextInt());
        keyboard.nextLine();

    }

    @Override
    public String toString() {
        return "Profesor_laborator{" +
                "nr_exercitii_saptamanale=" + nr_exercitii_saptamanale +
                ", an_incepere_activitate=" + an_incepere_activitate +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
