package ServiceClasses;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    static Menu meniu;

    static {
        try {
            meniu = new Menu();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Menu() throws SQLException, ClassNotFoundException {
    }

    public static Menu getInstance(){
        return meniu;
    }
    Service service = SingletonService.getSingleton_Service();

    public void showMeniu() {
        Scanner scan = new Scanner(System.in);
        int tip = 0;
        while (tip!=100) {
            System.out.println("Meniu Interactiv");
            System.out.println("1. Actiuni Materii");
            System.out.println("2. Actiuni Profesori");
            System.out.println("3. Actiuni Studenti");
            System.out.println("4. Actiuni Note");
            System.out.println("100. Terminare Program");
            tip = scan.nextInt();
            try {
                switch (tip) {
                    case 1:
                        tip = 0;
                        while (tip != 10) {
                            System.out.println("Meniu Actiuni Materii");
                            System.out.println("1. Citeste si insereaza materii dintr-un csv");
                            System.out.println("2. Sterge din Materii pentru o anumita conditie");
                            System.out.println("3. Updateaza in Materii coloanele cu valori specificate " +
                                    "pentru o anumite conditie");
                            System.out.println("4. Afiseaza o materie pe baza id-ului intr-un csv");
                            System.out.println("10. Inapoi La Meniul Principal");
                            tip = scan.nextInt();
                            scan.nextLine();
                            switch (tip) {
                                case 1:
                                    service.citireMaterii_csv();
                                    break;
                                case 2:
                                    System.out.println("Conditii sterge materie: ");
                                    String condtii = scan.nextLine();
                                    service.deleteMaterii(condtii);
                                    break;
                                case 3:
                                    System.out.println("Coloane update materie: ");
                                    String coloane = scan.nextLine();
                                    System.out.println("Conditii update materie: ");
                                    String conditii = scan.nextLine();
                                    service.updateMaterii(coloane, conditii);
                                    break;
                                case 4:
                                    System.out.println("Id materie: ");
                                    Integer id = scan.nextInt();
                                    scan.nextLine();
                                    service.afisareMaterie_csv(id);
                                case 10:
                                    break;
                            }
                        }
                        break;
                    case 2:
                        tip = 0;
                        while (tip != 10) {
                            System.out.println("Meniu Actiuni Profesori");
                            System.out.println("1. Citeste si insereaza profesori dintr-un csv");
                            System.out.println("2. Sterge din Profesori pentru o anumita conditie");
                            System.out.println("3. Updateaza in Profesori coloanele cu valori specificate " +
                                    "pentru o anumite conditie");
                            System.out.println("4. Afiseaza un profesor pe baza id-ului intr-un csv");
                            System.out.println("10. Inapoi La Meniul Principal");
                            tip = scan.nextInt();
                            scan.nextLine();
                            switch (tip) {
                                case 1:
                                    service.citireProfesori_csv();
                                    break;
                                case 2:
                                    System.out.println("Conditii sterge profesor: ");
                                    String condtii = scan.nextLine();
                                    service.deleteProfesori(condtii);
                                    break;
                                case 3:
                                    System.out.println("Coloane update profesor: ");
                                    String coloane = scan.nextLine();
                                    System.out.println("Conditii update profesor: ");
                                    String conditii = scan.nextLine();
                                    service.updateProfesori(coloane, conditii);
                                    break;
                                case 4:
                                    System.out.println("Id profesor: ");
                                    Integer id = scan.nextInt();
                                    scan.nextLine();
                                    service.afisareProfesor_csv(id);
                                case 10:
                                    break;
                            }
                        }
                        break;
                    case 3:
                        tip = 0;
                        while (tip != 10) {
                            System.out.println("Meniu Actiuni Studenti");
                            System.out.println("1. Citeste si insereaza studenti dintr-un csv");
                            System.out.println("2. Sterge din Studenti pentru o anumita conditie");
                            System.out.println("3. Updateaza in Studenti coloanele cu valori specificate " +
                                    "pentru o anumite conditie");
                            System.out.println("4. Afiseaza un student pe baza id-ului intr-un csv");
                            System.out.println("10. Inapoi La Meniul Principal");
                            tip = scan.nextInt();
                            scan.nextLine();
                            switch (tip) {
                                case 1:
                                    service.citireStudenti_csv();
                                    break;
                                case 2:
                                    System.out.println("Conditii sterge student: ");
                                    String condtii = scan.nextLine();
                                    service.deleteStudenti(condtii);
                                    break;
                                case 3:
                                    System.out.println("Coloane update student: ");
                                    String coloane = scan.nextLine();
                                    System.out.println("Conditii update student: ");
                                    String conditii = scan.nextLine();
                                    service.updateStudenti(coloane, conditii);
                                    break;
                                case 4:
                                    System.out.println("Id student: ");
                                    Integer id = scan.nextInt();
                                    scan.nextLine();
                                    service.afisareStudent_csv(id);
                                case 10:
                                    break;
                            }
                        }
                        break;
                    case 4:
                        tip = 0;
                        while (tip != 10) {
                            System.out.println("Meniu Actiuni Note");
                            System.out.println("1. Citeste si insereaza note dintr-un csv");
                            System.out.println("2. Sterge din Note pentru o anumita conditie");
                            System.out.println("3. Updateaza in Note coloanele cu valori specificate " +
                                    "pentru o anumite conditie");
                            System.out.println("4. Afiseaza o nota pe baza id-ului intr-un csv");
                            System.out.println("10. Inapoi La Meniul Principal");
                            tip = scan.nextInt();
                            scan.nextLine();
                            switch (tip) {
                                case 1:
                                    service.citireNote_csv();
                                    break;
                                case 2:
                                    System.out.println("Conditii sterge nota: ");
                                    String condtii = scan.nextLine();
                                    service.deleteNote(condtii);
                                    break;
                                case 3:
                                    System.out.println("Coloane update nota: ");
                                    String coloane = scan.nextLine();
                                    System.out.println("Conditii update nota: ");
                                    String conditii = scan.nextLine();
                                    service.updateNote(coloane, conditii);
                                    break;
                                case 4:
                                    System.out.println("Id nota: ");
                                    Integer id = scan.nextInt();
                                    scan.nextLine();
                                    service.afisareNota_csv(id);
                                case 10:
                                    break;
                            }
                        }
                        break;
                    case 100:
                        System.out.println("Exitting...");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}