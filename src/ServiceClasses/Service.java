package ServiceClasses;

import DataBase.DataBaseConnect_CRUD;
import Entities.Persoana;
import Entities.PersoanaProfesor.Profesor;
import Entities.PersoanaProfesor.Profesor_curs;
import Entities.PersoanaProfesor.Profesor_laborator;
import Entities.PersoanaProfesor.Profesor_seminar;
import Entities.PersoanaStudent.Student;
import Entities.PersoanaStudent.Student_doctorat;
import Entities.PersoanaStudent.Student_licenta;
import Entities.PersoanaStudent.Student_master;
import DataBase.SingletonDB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Service
{
    public Service() throws SQLException, ClassNotFoundException {}
    public ArrayList<Persoana> persoane=new ArrayList<Persoana>();
    public Scanner keyboard = new Scanner(System.in);
    public SortedSet<Profesor> profesori = new TreeSet<Profesor>();

    public SortedSet<Student> studenti = new TreeSet<Student>();

    DataBaseConnect_CRUD db = SingletonDB.getSingleton_DataBase();


    public void citire_tastatura_persoana()
    {
        System.out.println("Introduceti tipul: profesor/student");
        String tip_persoana= keyboard.nextLine();

        if(Objects.equals(tip_persoana.toLowerCase(),"profesor"))
        {
            System.out.println("Introduceti tipul profesorului: curs/laborator/seminar");
            String tip_profesor = keyboard.nextLine();

            if(Objects.equals(tip_profesor.toLowerCase(),"curs"))
            {
                Profesor_curs p_citire = new Profesor_curs();
                p_citire.citire();
                persoane.add(p_citire);
                profesori.add(p_citire);
            }

            if(Objects.equals(tip_profesor.toLowerCase(),"laborator"))
            {
                Profesor_laborator p_citire = new Profesor_laborator();
                p_citire.citire();
                persoane.add(p_citire);
                profesori.add(p_citire);

            }

            if(Objects.equals(tip_profesor.toLowerCase(),"seminar"))
            {
                Profesor_seminar p_citire = new Profesor_seminar();
                p_citire.citire();
                persoane.add(p_citire);
                profesori.add(p_citire);

            }
        }

        if(Objects.equals(tip_persoana.toLowerCase(),"student"))
        {
            System.out.println("Introduceti tipul studentului: licenta/master/doctorat");
            String tip_student = keyboard.nextLine();

            if(Objects.equals(tip_student.toLowerCase(),"licenta"))
            {
                Student_licenta p_citire = new Student_licenta();
                p_citire.citire();
                persoane.add(p_citire);
                studenti.add(p_citire);
            }

            if(Objects.equals(tip_student.toLowerCase(),"master"))
            {
                Student_master p_citire = new Student_master();
                p_citire.citire();
                persoane.add(p_citire);
                studenti.add(p_citire);

            }

            if(Objects.equals(tip_student.toLowerCase(),"doctorat"))
            {
                Student_doctorat p_citire = new Student_doctorat();
                p_citire.citire();
                persoane.add(p_citire);
                studenti.add(p_citire);
            }
        }
    }

    public void citireStudenti_csv()
    {
        db.citireStudenti_csv();
    }
    
    public void afisareStudent_csv(Integer id) throws SQLException {
        db.printareStudent_csv(id);
    }
    
    public void updateStudenti(String update, String conditii) throws SQLException, IOException {
        db.updateStudenti(update, conditii);
    }
    
    public void deleteStudenti(String conditii) throws SQLException, IOException {
        db.deleteFromStudenti(conditii);
    }

    public void citireProfesori_csv()
    {
        db.citireProfesori_csv();
    }

    public void afisareProfesor_csv(Integer id) throws SQLException {
        db.printareProfesor_csv(id);
    }

    public void updateProfesori(String update, String conditii) throws SQLException, IOException {
        db.updateProfesori(update, conditii);
    }

    public void deleteProfesori(String conditii) throws SQLException, IOException {
        db.deleteFromProfesori(conditii);
    }

    public void citireMaterii_csv() throws SQLException {
        db.citireMaterii_csv();
    }

    public void afisareMaterie_csv(Integer id) throws SQLException {
        db.printareMaterie_csv(id);
    }

    public void updateMaterii(String update, String conditii) throws SQLException, IOException {
        db.updateMaterii(update, conditii);
    }

    public void deleteMaterii(String conditii) throws SQLException, IOException {
        db.deleteFromMaterii(conditii);
    }

    public void citireNote_csv() throws SQLException {
        db.citireNote_csv();
    }

    public void afisareNota_csv(Integer id) throws SQLException {
        db.printareNota_csv(id);
    }

    public void updateNote(String update, String conditii) throws SQLException, IOException {
        db.updateNote(update, conditii);
    }

    public void deleteNote(String conditii) throws SQLException, IOException {
        db.deleteFromNote(conditii);
    }
    
//    //Student/i cu cel mai mare punctaj
//    public Student cel_mai_mare_punctaj() throws SQLException {
//        Student student_punctaj_maxim = db.StudentCelMaiMarePunctaj();
//        String tip_student = String.valueOf(student_punctaj_maxim.getClass());
//        tip_student = tip_student.substring(6);
//        return  student_punctaj_maxim;
//    }
//
//    //Student_licenta cu cea mai mica nota admitere
//    public Student_licenta prag_de_intrare() throws SQLException
//    {
//        Student_licenta student_nota_admitere_minima = db.StudentCeaMaiMicaNotaAdmitere();
//        return student_nota_admitere_minima;
//    }
//
//    //Student_Master cu cea mai mare nota licenta
//    public Student_master sef_de_promotie() throws SQLException
//    {
//        Student_master student_nota_licenta_maxima = db.StudentCeaMaiMareNotaLicenta();
//        return student_nota_licenta_maxima;
//    }
//
//    //Student_doctorat cu a X-a nota master (note ordonate descrescator)
//    public Student_doctorat ultima_medie_buget(Integer nr_locuri_buget_doctorat) throws SQLException {
//        Student_doctorat student_doctorat_ultim_buget = db.StudentDoctoratUltimBuget(nr_locuri_buget_doctorat);
//        return student_doctorat_ultim_buget;
//    }
//
//    //cu cea mai mica rata de promovabilitate
//    public Profesor cel_mai_sever() throws SQLException {
//        Profesor profesor_cel_mai_sever = db.ProfesorCeaMicaRataPromovabilitate();
//        return profesor_cel_mai_sever;
//    }
//
//    public Profesor_curs cea_mai_mare_prezenta() throws SQLException {
//        Profesor_curs profesor_curs_cea_mai_mare_prezenta = db.ProfesorCeaMaiMarePrezenta();
//        return profesor_curs_cea_mai_mare_prezenta;
//    }
//
//    public Profesor_laborator cel_mai_nesolicitant() throws SQLException {
//        Profesor_laborator profesor_laborator_cel_mai_nesolicitant = db.ProfesorCeleMaiPutineExercitii();
//        return  profesor_laborator_cel_mai_nesolicitant;
//    }
//
//    public Profesor_seminar cel_mai_interactiv() throws SQLException {
//        Profesor_seminar profesor_seminar_cel_mai_interactiv = db.ProfesorCelMaiMareBonus();
//        return profesor_seminar_cel_mai_interactiv;
//    }

}
