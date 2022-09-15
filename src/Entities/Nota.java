package Entities;

import Entities.PersoanaProfesor.Profesor;
import Entities.PersoanaStudent.Student;

import java.util.Scanner;

public class Nota{
    private Materie materie;
    private Profesor profesor;

    private Student student;

    private Integer nota_materie;


    public Nota() {
    }

    public Nota(Materie materie, Profesor profesor, Student student, Integer nota_materie) {
        this.materie = materie;
        this.profesor = profesor;
        this.student = student;
        this.nota_materie = nota_materie;
    }

    public Materie getMaterie() {
        return materie;
    }

    public void setMaterie(Materie materie) {
        this.materie = materie;
    }

    public Integer getNota_materie() {
        return nota_materie;
    }

    public void setNota_materie(Integer nota_materie) {
        this.nota_materie = nota_materie;
    }

    public Profesor getProfesor() {return profesor;}

    public void setProfesor(Profesor profesor) {this.profesor = profesor;}

    public Student getStudent() {return student;}

    public void setStudent(Student student) {this.student = student;}

    public void citire()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Materie: ");
        this.materie.citire();
        System.out.println("Nota materie: ");
        this.setNota_materie(keyboard.nextInt());
        keyboard.nextLine();
        System.out.println("Profesor: ");
        this.profesor.citire();
        System.out.println("Student: ");
        this.student.citire();
    }

    @Override
    public String toString() {
        return "Nota{" +
                "materie=" + materie +
                ", nota_materie=" + nota_materie +
                ", profesor=" + profesor +
                ", student=" + student +
                '}';
    }
}
