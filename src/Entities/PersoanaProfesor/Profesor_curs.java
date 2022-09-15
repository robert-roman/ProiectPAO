package Entities.PersoanaProfesor;

import java.util.Scanner;
public class Profesor_curs extends Profesor
{
    private Integer nr_elevi_prezenti;

    public Profesor_curs(){}

    public Profesor_curs(String nume, String prenume, String adresa, Integer an_incepere_activitate, Integer nr_elevi_prezenti) {
        super(nume, prenume, adresa, an_incepere_activitate);
        this.nr_elevi_prezenti = nr_elevi_prezenti;
    }


    public Integer getNr_elevi_prezenti() {
        return nr_elevi_prezenti;
    }

    public void setNr_elevi_prezenti(Integer nr_elevi_prezenti) {
        this.nr_elevi_prezenti = nr_elevi_prezenti;
    }



    public void citire()
    {
        super.citire();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Numar elevi prezenti:");
        this.setNr_elevi_prezenti(keyboard.nextInt());
        keyboard.nextLine();

    }

    @Override
    public String toString() {
        return "Profesor_curs{" +
                "nr_elevi_prezenti=" + nr_elevi_prezenti +
                ", an_incepere_activitate=" + an_incepere_activitate +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
