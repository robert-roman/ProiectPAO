package Entities.PersoanaStudent;

import java.util.Scanner;

public class Student_doctorat extends Student {

    private float nota_master;

    public Student_doctorat(){}

    public Student_doctorat(String nume, String prenume, String adresa, String specializare, Integer an, Integer grupa, float nota_master) {
        super(nume, prenume, adresa, specializare, an, grupa);
        this.nota_master = nota_master;
    }

    public float getNota_master() {
        return nota_master;
    }

    public void setNota_master(float nota_master) {
        this.nota_master = nota_master;
    }

    @Override
    public void criteriu()
    {
        System.out.println("Criteriul pentru programul licenta este nota de la lucrarea de masterat");
        System.out.println("Elevul "+this.nume+" "+this.prenume+" a fost admis cu media "+this.nota_master);
    }

    public void citire()
    {
        super.citire();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Nota lucrare masterat:");
        this.setNota_master(keyboard.nextFloat());
        keyboard.nextLine();
    }

    @Override
    public String toString() {
        return "Student_doctorat{" +
                "nota_master=" + nota_master +
                ", specializare='" + specializare + '\'' +
                ", an=" + an +
                ", grupa='" + grupa + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
