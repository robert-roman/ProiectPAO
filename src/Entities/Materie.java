package Entities;
import java.util.Scanner;

public class Materie {
    private String denumire;
    private Integer nr_credite;

    public Materie(){}
    public Materie(String denumire, Integer nr_credite) {
        this.denumire = denumire;
        this.nr_credite = nr_credite;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public Integer getNr_credite() {
        return nr_credite;
    }

    public void setNr_credite(Integer nr_credite) {
        this.nr_credite = nr_credite;
    }

    public void citire()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Denumire materie: ");
        this.setDenumire(keyboard.nextLine());
        System.out.println("Numar credite materie: ");
        this.setNr_credite(keyboard.nextInt());
    }

    @Override
    public String toString() {
        return "Materie{" +
                "denumire='" + denumire + '\'' +
                ", nr_credite=" + nr_credite +
                '}';
    }
}
