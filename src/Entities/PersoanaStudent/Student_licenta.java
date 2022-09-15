package Entities.PersoanaStudent;
//import pachet_proiect.Student;
//import pachet_proiect.Nota_student;

import java.util.Scanner;


public class Student_licenta extends  Student{
    private float nota_admitere;

    public Student_licenta(){
    }

    public Student_licenta(String nume, String prenume, String adresa, String specializare, Integer an, Integer grupa, float nota_admitere) {
        super(nume, prenume, adresa, specializare, an, grupa);
        this.nota_admitere = nota_admitere;
    }

    public float getNota_admitere() {
        return nota_admitere;
    }

    public void setNota_admitere(float nota_admitere) {
        this.nota_admitere = nota_admitere;
    }

    @Override
    public void criteriu()
    {
        System.out.println("Criteriul pentru programul licenta este nota de admitere");
        System.out.println("Elevul "+this.nume+" "+this.prenume+" a fost admis cu media "+this.nota_admitere);
    }

    public void citire()
    {
        super.citire();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Nota de admitere:");
        this.setNota_admitere(keyboard.nextFloat());
        keyboard.nextLine();
    }

    @Override
    public String toString() {
        return "Student_licenta{" +
                "nota_admitere=" + nota_admitere +
                ", specializare='" + specializare + '\'' +
                ", an=" + an +
                ", grupa='" + grupa + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
