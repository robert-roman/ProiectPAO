package Entities.PersoanaStudent;
//import pachet_proiect.Persoana;

import Entities.Persoana;

import java.util.Scanner;
public class Student extends Persoana implements Comparable<Student>
{
    //protected String categorie;
    protected String specializare;
    protected Integer an;
    protected Integer grupa;
    public Student() {
    }

    public Student(String nume, String prenume, String adresa, String specializare, Integer an, Integer grupa) {
        super(nume, prenume, adresa);
        this.specializare = specializare;
        this.an = an;
        this.grupa = grupa;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public Integer getAn() {
        return an;
    }

    public void setAn(Integer an) {
        this.an = an;
    }

    public Integer getGrupa() {
        return grupa;
    }

    public void setGrupa(Integer grupa) {
        this.grupa = grupa;
    }


    /*public Integer rezultate_final() //punctajul de la finalul anului
    {
        Integer total=0;
        Integer size=this.note.size();
        for(Integer i=0; i<size; i++)
        {
            total += this.note.get(i).getNota_materie() * this.note.get(i).materie.getNr_credite();
        }
        return total;
    }*/

    public Integer rezultate_final()
    {
        return this.getAn()*17;
    }

    public void criteriu(){};

    public void citire()
    {
        super.citire();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Specializare: ");
        this.setSpecializare(keyboard.nextLine());
        System.out.println("An: ");
        this.setAn(keyboard.nextInt());
        keyboard.nextLine();
        System.out.println("Grupa: ");
        this.setGrupa(keyboard.nextInt());
        keyboard.nextLine();
    }

    @Override
    public int compareTo(Student student)
    {
        Integer rezultat_comparatie_nume = this.getNume().compareTo(student.getNume());
        if (rezultat_comparatie_nume == 0)
        {
            return  this.getPrenume().compareTo(student.getPrenume());
        }
        return rezultat_comparatie_nume;
    }

    @Override
    public String toString() {
        return "Student{" +
                "specializare='" + specializare + '\'' +
                ", an=" + an +
                ", grupa='" + grupa + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
