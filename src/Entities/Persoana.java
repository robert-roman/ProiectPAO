package Entities;
import java.util.Scanner;

public abstract class Persoana
{
    // protected int id_persoana;
    protected String nume;
    protected String prenume;
    protected String adresa;

    public Persoana()
    {
        this.nume="nu este atribuit numele";
        this.prenume="nu este atribuit prenume";
        this.adresa="nu este atribuita adresa";
    }

    public Persoana(String nume, String prenume, String adresa)
    {
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
    }


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void citire()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduceti informatiile persoanei");
        System.out.println("Nume: ");
        this.setNume(keyboard.nextLine());
        System.out.println("Prenume: ");
        this.setPrenume(keyboard.nextLine());
        System.out.println("Adresa: ");
        this.setAdresa(keyboard.nextLine());
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
