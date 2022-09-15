package Entities.PersoanaStudent;


import java.util.Scanner;

public class Student_master extends Student{
    private float nota_licenta;

    public Student_master(){}

    public Student_master(String nume, String prenume, String adresa, String specializare, Integer an, Integer grupa, float nota_licenta) {
        super(nume, prenume, adresa, specializare, an, grupa);
        this.nota_licenta = nota_licenta;
    }

    public float getNota_licenta() {
        return nota_licenta;
    }

    public void setNota_licenta(float nota_licenta) {
        this.nota_licenta = nota_licenta;
    }

    @Override
    public void criteriu()
    {
        System.out.println("Criteriul pentru programul licenta este nota de la lucrarea de licenta");
        System.out.println("Elevul "+this.nume+" "+this.prenume+" a fost admis cu media "+this.nota_licenta);
    }

    public void citire()
    {
        super.citire();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Nota lucrare de licenta:");
        this.setNota_licenta(keyboard.nextFloat());
        keyboard.nextLine();

    }

    @Override
    public String toString() {
        return "Student_Master{" +
                "nota_licenta=" + nota_licenta +
                ", specializare='" + specializare + '\'' +
                ", an=" + an +
                ", grupa='" + grupa + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa='" + adresa + '\'' +
                "nota_licenta=" + nota_licenta +
                '}';
    }

}
