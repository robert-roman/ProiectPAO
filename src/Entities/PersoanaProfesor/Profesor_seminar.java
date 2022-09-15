package Entities.PersoanaProfesor;

import java.util.Scanner;

public class Profesor_seminar extends Profesor
{
    private Integer bonus_maxim_seminar;

    public Profesor_seminar() {
    }

    public Profesor_seminar(String nume, String prenume, String adresa, Integer an_incepere_activitate, Integer bonus_maxim_seminar) {
        super(nume, prenume, adresa, an_incepere_activitate);
        this.bonus_maxim_seminar = bonus_maxim_seminar;
    }

    public Integer getBonus_maxim_seminar() {
        return bonus_maxim_seminar;
    }

    public void setBonus_maxim_seminar(Integer bonus_maxim_seminar) {
        this.bonus_maxim_seminar = bonus_maxim_seminar;
    }



    public void citire()
    {
        super.citire();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Bonus maxim seminar:");
        this.setBonus_maxim_seminar(keyboard.nextInt());
        keyboard.nextLine();

    }

    @Override
    public String toString() {
        return "Profesor_seminar{" +
                "bonus_maxim_seminar=" + bonus_maxim_seminar +
                ", an_incepere_activitate=" + an_incepere_activitate +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}


