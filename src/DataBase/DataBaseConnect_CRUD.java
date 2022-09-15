package DataBase;


import Entities.Materie;
import Entities.Nota;
import Entities.PersoanaProfesor.Profesor;
import Entities.PersoanaProfesor.Profesor_curs;
import Entities.PersoanaProfesor.Profesor_laborator;
import Entities.PersoanaProfesor.Profesor_seminar;
import Entities.PersoanaStudent.Student;
import Entities.PersoanaStudent.Student_doctorat;
import Entities.PersoanaStudent.Student_licenta;
import Entities.PersoanaStudent.Student_master;
import ServiceClasses.Audit;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DataBaseConnect_CRUD
{
    static Connection connection;
    Audit audit = new Audit();
    public DataBaseConnect_CRUD() throws SQLException
    {
        String address = "jdbc:postgresql://localhost:5432/ProiectPAO";
        connection = DriverManager.getConnection(address, "postgres", "1234567");
    }

    public void emptyTableMaterie() throws SQLException
    {
        Statement statement = connection.createStatement();
        statement.execute("truncate table Materie cascade");
    }
    

    public void emptyTableProfesor() throws SQLException
    {
        Statement statement = connection.createStatement();
        statement.execute("truncate table Profesor cascade");
    }

    public void emptyTableStudent() throws SQLException
    {
        Statement statement = connection.createStatement();
        statement.execute("truncat table Student cascade");
    }

    public void emptyTableNota() throws SQLException 
    {
        Statement statement = connection.createStatement();
        statement.execute("truncate table Nota cascade");
    }

    public List<Profesor_curs> getProfesori_curs() throws SQLException
    {
        List<Profesor_curs> profesori_curs = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet profesori_cursSet = statement.executeQuery("select * from \"Profesori\" where specializare=\'curs\'");
        while (profesori_cursSet.next())
        {
            profesori_curs.add(new Profesor_curs(profesori_cursSet.getString("nume"),
                    profesori_cursSet.getString("prenume"),
                    profesori_cursSet.getString("adresa"),
                    profesori_cursSet.getInt("an_incepere_activitate"),
                    //profesori_cursSet.getInt("info_corespunzator"
                    profesori_cursSet.getInt("info_corespunzator")));
        }
        return profesori_curs;
    }

    public List<Profesor_laborator> getProfesori_laborator() throws SQLException
    {
        List<Profesor_laborator> profesori_laborator = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet profesori_laboratorSet = statement.executeQuery("select * from \"Profesori\" where specializare=\'laborator\'");
        while (profesori_laboratorSet.next())
        {
            profesori_laborator.add(new Profesor_laborator(profesori_laboratorSet.getString("nume"),
                    profesori_laboratorSet.getString("prenume"),
                    profesori_laboratorSet.getString("adresa"),
                    profesori_laboratorSet.getInt("an_incepere_activitate"),
                    profesori_laboratorSet.getInt("info_corespunzator")));
        }
        return profesori_laborator;
    }

    public List<Entities.PersoanaProfesor.Profesor_seminar> getProfesori_seminar() throws SQLException
    {
        List<Entities.PersoanaProfesor.Profesor_seminar> profesori_seminar = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet profesori_seminarSet = statement.executeQuery("select * from \"Profesori\" where specializare=\'seminar\'");
        while (profesori_seminarSet.next())
        {
            profesori_seminar.add(new Entities.PersoanaProfesor.Profesor_seminar(profesori_seminarSet.getString("nume"),
                    profesori_seminarSet.getString("prenume"),
                    profesori_seminarSet.getString("adresa"),
                    profesori_seminarSet.getInt("an_incepere_activitate"),
                    profesori_seminarSet.getInt("info_corespunzator")));
        }
        return profesori_seminar;
    }
    
    public void addProfesor (Entities.PersoanaProfesor.Profesor profesor, String specializare) throws SQLException
    {
        Integer info_corespunzator = null;
        switch (specializare)
        {
            case "curs":
                info_corespunzator = ((Profesor_curs) profesor).getNr_elevi_prezenti();
                break;
            case "laborator":
                info_corespunzator = ((Profesor_laborator) profesor).getNr_exercitii_saptamanale();
                break;
            case "seminar":
                info_corespunzator = ((Profesor_seminar) profesor).getBonus_maxim_seminar();
                break;
        }
        Statement statement = connection.createStatement();
        String ssql = "insert into \"Profesori\" (nume, prenume, adresa, an_incepere_activitate, " +
                "specializare, info_corespunzator) values (" +
                "\'" + profesor.getNume() + "\', \'" + profesor.getPrenume() + "\', \'"+ profesor.getAdresa() + "\', " +
                profesor.getAn_incepere_activitate() + ", \'" + specializare + "\', " + info_corespunzator + ")";
        //System.out.println(ssql);
        statement.execute(ssql);
    }
    
    public Integer getIdForProfesor(Entities.PersoanaProfesor.Profesor profesor) throws SQLException
    {
        Integer id_cautat = -1;
        Statement statement = connection.createStatement();
        String ssql= "select id from \"Profesori\" where nume=\'"+ profesor.getNume() +
                "\' and prenume= \'" + profesor.getPrenume() + "\' and adresa= \'" + profesor.getAdresa() +
                "\' and an_incepere_activitate = \'" + profesor.getAn_incepere_activitate() + "\'";
        //System.out.println(ssql);
        ResultSet profesor_obtinut = statement.executeQuery(ssql);
        if (profesor_obtinut.next())
        {
            id_cautat = profesor_obtinut.getInt("id");
        }
        return id_cautat;
    }

    public Entities.PersoanaProfesor.Profesor getProfesorCautat(Integer id) throws SQLException
    {
        Entities.PersoanaProfesor.Profesor profesor_cautat = null;
        Statement statement = connection.createStatement();
        String ssql= "select * from \"Profesori\" where id= "  + id;
        ResultSet profesor_cautatSet = statement.executeQuery(ssql);
        if (profesor_cautatSet.next())
        {
            if ((profesor_cautatSet.getString("specializare")).compareTo("curs")==0) {
                profesor_cautat = (Profesor_curs) profesor_cautat;
                profesor_cautat = new Entities.PersoanaProfesor.Profesor_curs
                        (profesor_cautatSet.getString("nume"),
                        profesor_cautatSet.getString("prenume"),
                        profesor_cautatSet.getString("adresa"),
                        profesor_cautatSet.getInt("an_incepere_activitate"), 
                        profesor_cautatSet.getInt("info_corespunzator"));
            }
            else if ((profesor_cautatSet.getString("specializare")).compareTo("laborator")==0) {
                profesor_cautat = (Profesor_laborator) profesor_cautat;
                profesor_cautat = new Entities.PersoanaProfesor.Profesor_laborator
                        (profesor_cautatSet.getString("nume"),
                                profesor_cautatSet.getString("prenume"),
                                profesor_cautatSet.getString("adresa"),
                                profesor_cautatSet.getInt("an_incepere_activitate"),
                                profesor_cautatSet.getInt("info_corespunzator"));
            }
            else {
                profesor_cautat = (Profesor_seminar) profesor_cautat;
                profesor_cautat = new Entities.PersoanaProfesor.Profesor_seminar
                        (profesor_cautatSet.getString("nume"),
                                profesor_cautatSet.getString("prenume"),
                                profesor_cautatSet.getString("adresa"),
                                profesor_cautatSet.getInt("an_incepere_activitate"),
                                profesor_cautatSet.getInt("info_corespunzator"));
            }
        }
        return profesor_cautat;
    }
    
    public List<Entities.PersoanaStudent.Student_licenta> getStudenti_licenta() throws SQLException
    {
        List<Entities.PersoanaStudent.Student_licenta> studenti_licenta = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet studenti_licentaSet = statement.executeQuery("select * from \"Studenti\" where tip_studiu=\'licenta\'");
        while (studenti_licentaSet.next())
        {
            studenti_licenta.add(new Entities.PersoanaStudent.Student_licenta(studenti_licentaSet.getString("nume"),
                    studenti_licentaSet.getString("prenume"),
                    studenti_licentaSet.getString("adresa"),
                    studenti_licentaSet.getString("specializare"),
                    studenti_licentaSet.getInt("an"),
                    studenti_licentaSet.getInt("grupa"),
                    studenti_licentaSet.getFloat("nota_corespondenta")));
        }
        return  studenti_licenta;
    }

    public List<Entities.PersoanaStudent.Student_master> getStudenti_master() throws SQLException
    {
        List<Entities.PersoanaStudent.Student_master> studenti_master = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet studenti_masterSet = statement.executeQuery("select * from \"Studenti\" where tip_studiu=\'master\'");
        while (studenti_masterSet.next())
        {
            studenti_master.add(new Entities.PersoanaStudent.Student_master(studenti_masterSet.getString("nume"),
                    studenti_masterSet.getString("prenume"),
                    studenti_masterSet.getString("adresa"),
                    studenti_masterSet.getString("specializare"),
                    studenti_masterSet.getInt("an"),
                    studenti_masterSet.getInt("grupa"),
                    studenti_masterSet.getFloat("nota_corespondenta")));
        }
        return  studenti_master;
    }

    public List<Entities.PersoanaStudent.Student_doctorat> getStudenti_doctorat() throws SQLException
    {
        List<Entities.PersoanaStudent.Student_doctorat> studenti_doctorat = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet studenti_doctoratSet = statement.executeQuery("select * from \"Studenti\" where tip_studiu=\'doctorat\'");
        while (studenti_doctoratSet.next())
        {
            studenti_doctorat.add(new Entities.PersoanaStudent.Student_doctorat(studenti_doctoratSet.getString("nume"),
                    studenti_doctoratSet.getString("prenume"),
                    studenti_doctoratSet.getString("adresa"),
                    studenti_doctoratSet.getString("specializare"),
                    studenti_doctoratSet.getInt("an"),
                    studenti_doctoratSet.getInt("grupa"),
                    studenti_doctoratSet.getFloat("nota_corespondenta")));
        }
        return  studenti_doctorat;
    }
    
    public void addStudent (Entities.PersoanaStudent.Student student, String tip_studiu) throws SQLException
    {
        Float info_corespunzator = null;
        switch (tip_studiu)
        {
            case "licenta":
                info_corespunzator = ((Entities.PersoanaStudent.Student_licenta) student).getNota_admitere();
                break;
            case "master":
                info_corespunzator = ((Entities.PersoanaStudent.Student_master) student).getNota_licenta();
                break;
            case "doctorat":
                info_corespunzator = ((Entities.PersoanaStudent.Student_doctorat) student).getNota_master();
                break;
        }

        Statement statement = connection.createStatement();
        String ssql = "insert into \"Studenti\" (nume, prenume, adresa," +
                " specializare, an, grupa, tip_studiu, nota_corespondenta) values (" +
                "\'" + student.getNume() + "\', \'" + student.getPrenume() + "\', \'"+ student.getAdresa() + "\', \'" +
                student.getSpecializare() + "\', " + student.getAn() + ", " +
                student.getGrupa() + ", \'"+ tip_studiu + "\', " + info_corespunzator + ")";
        //System.out.println(ssql);
        statement.execute(ssql);
    }

    public Integer getIdForStudent(Entities.PersoanaStudent.Student student) throws SQLException
    {
        Integer id_cautat = -1;
        Statement statement = connection.createStatement();
        String ssql= "select id from \"Studenti\" where nume=\'"+ student.getNume() +
                "\' and prenume= \'" + student.getPrenume() + "\' and adresa= \'" + student.getAdresa() +
                "\' and specializare = \'" + student.getSpecializare() + "\' and an= " + student.getAn()
                + " and grupa= " + student.getGrupa();
        //System.out.println(ssql);
        ResultSet student_obtinut = statement.executeQuery(ssql);
        if (student_obtinut.next())
        {
            id_cautat = student_obtinut.getInt("id");
        }
        return id_cautat;
    }

    public Entities.PersoanaStudent.Student getStudentCautat(Integer id) throws SQLException
    {
        Entities.PersoanaStudent.Student student_cautat = null;
        Statement statement = connection.createStatement();
        String ssql= "select * from \"Studenti\" where id= "  + id;
        ResultSet student_cautatSet = statement.executeQuery(ssql);
        if (student_cautatSet.next())
        {
            if ((student_cautatSet.getString("tip_studiu")).compareTo("licenta")==0) {
                student_cautat = (Entities.PersoanaStudent.Student_licenta) student_cautat;
                student_cautat = new Entities.PersoanaStudent.Student_licenta(student_cautatSet.getString("nume"),
                        student_cautatSet.getString("prenume"),
                        student_cautatSet.getString("adresa"),
                        student_cautatSet.getString("specializare"),
                        student_cautatSet.getInt("an"),
                        student_cautatSet.getInt("grupa"),
                        student_cautatSet.getFloat("nota_corespondenta"));
            }
            else if ((student_cautatSet.getString("tip_studiu")).compareTo("master")==0) {
                student_cautat = (Entities.PersoanaStudent.Student_master) student_cautat;
                student_cautat = new Entities.PersoanaStudent.Student_master(student_cautatSet.getString("nume"),
                        student_cautatSet.getString("prenume"),
                        student_cautatSet.getString("adresa"),
                        student_cautatSet.getString("specializare"),
                        student_cautatSet.getInt("an"),
                        student_cautatSet.getInt("grupa"),
                        student_cautatSet.getFloat("nota_corespondenta"));
            }
            else{
                student_cautat = (Entities.PersoanaStudent.Student_doctorat) student_cautat;
                student_cautat = new Entities.PersoanaStudent.Student_doctorat(student_cautatSet.getString("nume"),
                        student_cautatSet.getString("prenume"),
                        student_cautatSet.getString("adresa"),
                        student_cautatSet.getString("specializare"),
                        student_cautatSet.getInt("an"),
                        student_cautatSet.getInt("grupa"),
                        student_cautatSet.getFloat("nota_corespondenta"));
            }
        }
        return student_cautat;
    }
    
    public void addMaterie (Materie materie) throws SQLException
    {
        Statement statement = connection.createStatement();
        String ssql= "insert into \"Materii\" (denumire, nr_credite) values (" + "\'" + materie.getDenumire() +"\', " +
                materie.getNr_credite() + ")";
        //System.out.println(ssql);
        statement.execute(ssql);
    }

    public Integer getIdForMaterie(Materie materie) throws SQLException
    {
        Integer id_cautat = -1;
        Statement statement = connection.createStatement();
        String ssql= "select id from \"Materii\" where denumire=\'" + materie.getDenumire() +
                "\' and nr_credite= \'" + materie.getNr_credite() + "\'";
        //System.out.println(ssql);
        ResultSet materie_obtinuta = statement.executeQuery(ssql);
        if (materie_obtinuta.next())
        {
            id_cautat = materie_obtinuta.getInt("id");
        }
        return  id_cautat;
    }

    public Materie getMaterieCautata(Integer id) throws SQLException
    {
        Materie materie_cautata = null;
        Statement statement = connection.createStatement();
        String ssql= "select * from \"Materii\" where id= "  + id;
        ResultSet materie_cautataSet = statement.executeQuery(ssql);
        if (materie_cautataSet.next())
        {
            materie_cautata = new Materie(materie_cautataSet.getString("denumire"),
                                                  materie_cautataSet.getInt("nr_credite"));
        }
        return materie_cautata;
    }

    public void addNota (Nota nota) throws  SQLException
    {

        Integer id_materie = getIdForMaterie(nota.getMaterie());
        Integer id_profesor = getIdForProfesor(nota.getProfesor());
        Integer id_student = getIdForStudent(nota.getStudent());
        Statement statement = connection.createStatement();
        String ssql= "insert into \"Note\" (materie, profesor, student, nota_materie) values ("
                + id_materie + ", " + id_profesor
                + ", " + id_student + ", " + nota.getNota_materie() + ")";
        statement.execute(ssql);
    }

    public Integer getIdForNota (Nota nota) throws SQLException
    {
        Integer id_cautat = -1;
        Statement statement = connection.createStatement();
        String ssql= "select id from \"Note\" where materie= " + getIdForMaterie(nota.getMaterie()) +  " and profesor= "
                + getIdForProfesor(nota.getProfesor())  + " and student= " + getIdForStudent(nota.getStudent())
                + " and nota_materie= " + nota.getNota_materie();
        //System.out.println(ssql);
        ResultSet nota_obtinuta = statement.executeQuery(ssql);
        if (nota_obtinuta.next())
        {
            id_cautat = nota_obtinuta.getInt("id");
        }
        return  id_cautat;
    }
    
    public List<Nota> getNoteStudent (Entities.PersoanaStudent.Student student) throws SQLException
    {
        List<Nota> note_student = new ArrayList<>();
        Statement statement = connection.createStatement();
        String ssql= "select * from \"Note\" where  student= " + getIdForStudent(student);
        ResultSet note_studentSet = statement.executeQuery(ssql);
        while (note_studentSet.next())
        {
            Materie materie_cautata = getMaterieCautata(note_studentSet.getInt("materie"));
            Entities.PersoanaProfesor.Profesor profesor_cautat = getProfesorCautat(note_studentSet.getInt("profesor"));
            Entities.PersoanaStudent.Student student_cautat = getStudentCautat(note_studentSet.getInt("student"));
            note_student.add(new Nota(materie_cautata, profesor_cautat, student_cautat,
                    note_studentSet.getInt("nota_materie")));
        }
        return  note_student;
    }

    public Integer PunctajTotalStudent (Entities.PersoanaStudent.Student student) throws SQLException
    {
        List<Nota> note_student = getNoteStudent(student);
        Integer nr_note_student = note_student.size();
        if (nr_note_student == 0)
            return -1;
        Integer punctaj_total = 0;
        for (Nota nota: note_student)
        {
            Integer nota_materie = nota.getNota_materie();
            Integer nr_credite = nota.getMaterie().getNr_credite();
            punctaj_total+= nota_materie*nr_credite;
        }
        return punctaj_total;
    }
    
    public List<Nota> getNoteProfesor (Entities.PersoanaProfesor.Profesor profesor) throws SQLException
    {
        List<Nota> note_profesor = new ArrayList<>();
        Statement statement = connection.createStatement();
        String ssql= "select * from \"Note\" where  profesor= " + getIdForProfesor(profesor);
        ResultSet note_profesorSet = statement.executeQuery(ssql);
        while (note_profesorSet.next())
        {
            Materie materie_cautata = getMaterieCautata(note_profesorSet.getInt("materie"));
            Entities.PersoanaProfesor.Profesor profesor_cautat = getProfesorCautat(note_profesorSet.getInt("profesor"));
            Entities.PersoanaStudent.Student student_cautat = getStudentCautat(note_profesorSet.getInt("profesor"));
            note_profesor.add(new Nota(materie_cautata, profesor_cautat, student_cautat,
                    note_profesorSet.getInt("nota_materie")));
        }
        return  note_profesor;
    }

    public float RataPromovabilitateProfesor (Entities.PersoanaProfesor.Profesor profesor) throws SQLException
    {
        List<Nota> note_profesor = getNoteProfesor(profesor);
        Integer nr_note_profesor = note_profesor.size();
        if (nr_note_profesor == 0)
            return -1;
        Integer nr_note_detrecere = 0;
        for (Nota nota: note_profesor)
        {
            Integer nota_materie = nota.getNota_materie();
            if (nota_materie >= 5)
            {
                nr_note_detrecere++;
            }
        }
        float rezultat = (( float) nr_note_detrecere/nr_note_profesor) *100;
        return rezultat;
    }
    
    public Entities.PersoanaStudent.Student StudentCelMaiMarePunctaj () throws SQLException
    {
        List<Entities.PersoanaStudent.Student_licenta> studenti_licenta = getStudenti_licenta();
        List<Entities.PersoanaStudent.Student_master> studenti_master = getStudenti_master();
        List<Entities.PersoanaStudent.Student_doctorat> studenti_doctorat = getStudenti_doctorat();
        
        Integer punctaj_maxim = -1;
        Entities.PersoanaStudent.Student student_punctaj_maxim = null;
        
        for (Entities.PersoanaStudent.Student_licenta student_licenta: studenti_licenta)
        {
            Integer punctaj_curent = PunctajTotalStudent(student_licenta);
            if (punctaj_curent > punctaj_maxim)
            {
                punctaj_maxim = punctaj_curent;
                student_punctaj_maxim = student_licenta;
            }
        }
        for (Entities.PersoanaStudent.Student_master student_master: studenti_master)
        {
            Integer punctaj_curent = PunctajTotalStudent(student_master);
            if (punctaj_curent > punctaj_maxim)
            {
                punctaj_maxim = punctaj_curent;
                student_punctaj_maxim = student_master;
            }
        }

        for (Entities.PersoanaStudent.Student_doctorat student_doctorat: studenti_doctorat)
        {
            Integer punctaj_curent = PunctajTotalStudent(student_doctorat);
            if (punctaj_curent > punctaj_maxim)
            {
                punctaj_maxim = punctaj_curent;
                student_punctaj_maxim = student_doctorat;
            }
        }
        return  student_punctaj_maxim;
    }
    
    public Entities.PersoanaStudent.Student_licenta StudentCeaMaiMicaNotaAdmitere () throws SQLException
    {
        Entities.PersoanaStudent.Student_licenta student_nota_minima_admitere = null;
        Statement statement = connection.createStatement();
        ResultSet student_nota_minima_admitereSet = statement.executeQuery("select * from \"Studenti\" where " +
                "tip_studiu=\'licenta\' order by nota_corespondenta asc");
        if (student_nota_minima_admitereSet.next())
        {
            student_nota_minima_admitere = (new Entities.PersoanaStudent.Student_licenta(student_nota_minima_admitereSet.getString("nume"),
                    student_nota_minima_admitereSet.getString("prenume"),
                    student_nota_minima_admitereSet.getString("adresa"),
                    student_nota_minima_admitereSet.getString("specializare"),
                    student_nota_minima_admitereSet.getInt("an"),
                    student_nota_minima_admitereSet.getInt("grupa"),
                    student_nota_minima_admitereSet.getFloat("nota_corespondenta")));
        }
        return  student_nota_minima_admitere;
    }

    public Entities.PersoanaStudent.Student_master StudentCeaMaiMareNotaLicenta() throws  SQLException
    {
        Entities.PersoanaStudent.Student_master student_nota_maxima_licenta = null;
        Statement statement = connection.createStatement();
        ResultSet student_nota_maxima_licentaSet = statement.executeQuery("select * from \"Studenti\" where " +
                "tip_studiu=\'master\' order by nota_corespondenta desc");
        if (student_nota_maxima_licentaSet.next())
        {
            student_nota_maxima_licenta = (new Entities.PersoanaStudent.Student_master(student_nota_maxima_licentaSet.getString("nume"),
                    student_nota_maxima_licentaSet.getString("prenume"),
                    student_nota_maxima_licentaSet.getString("adresa"),
                    student_nota_maxima_licentaSet.getString("specializare"),
                    student_nota_maxima_licentaSet.getInt("an"),
                    student_nota_maxima_licentaSet.getInt("grupa"),
                    student_nota_maxima_licentaSet.getFloat("nota_corespondenta")));
        }
        return  student_nota_maxima_licenta;
    }

    public Entities.PersoanaStudent.Student_doctorat StudentDoctoratUltimBuget(Integer nr_locuri_buget) throws SQLException
    {
        List<Entities.PersoanaStudent.Student_doctorat> studenti_doctorat = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet studenti_doctoratSet = statement.executeQuery("select * from \"Studenti\" where " +
                "tip_studiu=\'doctorat\' order by nota_corespondenta desc");
        while (studenti_doctoratSet.next())
        {
            studenti_doctorat.add(new Entities.PersoanaStudent.Student_doctorat(studenti_doctoratSet.getString("nume"),
                    studenti_doctoratSet.getString("prenume"),
                    studenti_doctoratSet.getString("adresa"),
                    studenti_doctoratSet.getString("specializare"),
                    studenti_doctoratSet.getInt("an"),
                    studenti_doctoratSet.getInt("grupa"),
                    studenti_doctoratSet.getFloat("nota_corespondenta")));
        }
        Integer nr_studenti_doctorat = studenti_doctorat.size();
        if (nr_locuri_buget> nr_studenti_doctorat)
        {
            return studenti_doctorat.get(nr_studenti_doctorat-1);
        }
        return studenti_doctorat.get(nr_locuri_buget-1);
    }

    public Entities.PersoanaProfesor.Profesor ProfesorCeaMicaRataPromovabilitate() throws  SQLException
    {
        List<Entities.PersoanaProfesor.Profesor_curs> profesori_curs = getProfesori_curs();
        List<Entities.PersoanaProfesor.Profesor_laborator> profesori_laborator = getProfesori_laborator();
        List<Entities.PersoanaProfesor.Profesor_seminar> profesori_seminar = getProfesori_seminar();

        float rata_promovabilitate_minima = 101;
        Entities.PersoanaProfesor.Profesor profesor_cea_mai_mica_rata_promovabilitate = null;
        
        for (Entities.PersoanaProfesor.Profesor_curs profesor_curs :profesori_curs)
        {
            float rata_promovabilitate_profesor_curs = RataPromovabilitateProfesor(profesor_curs);
            if (rata_promovabilitate_profesor_curs >=0 && rata_promovabilitate_profesor_curs < rata_promovabilitate_minima)
            {
                rata_promovabilitate_minima = rata_promovabilitate_profesor_curs;
                profesor_cea_mai_mica_rata_promovabilitate = profesor_curs;
            }
        }

        for (Entities.PersoanaProfesor.Profesor_laborator profesor_laborator :profesori_laborator)
        {
            float rata_promovabilitate_profesor_laborator = RataPromovabilitateProfesor(profesor_laborator);
            if (rata_promovabilitate_profesor_laborator >=0 && rata_promovabilitate_profesor_laborator < rata_promovabilitate_minima)
            {
                rata_promovabilitate_minima = rata_promovabilitate_profesor_laborator;
                profesor_cea_mai_mica_rata_promovabilitate = profesor_laborator;
            }
        }

        for (Entities.PersoanaProfesor.Profesor_seminar profesor_seminar :profesori_seminar)
        {
            float rata_promovabilitate_profesor_seminar = RataPromovabilitateProfesor(profesor_seminar);
            if (rata_promovabilitate_profesor_seminar >=0 && rata_promovabilitate_profesor_seminar < rata_promovabilitate_minima)
            {
                rata_promovabilitate_minima = rata_promovabilitate_profesor_seminar;
                profesor_cea_mai_mica_rata_promovabilitate = profesor_seminar;
            }
        }
        return profesor_cea_mai_mica_rata_promovabilitate;
    }

    public Entities.PersoanaProfesor.Profesor_curs ProfesorCeaMaiMarePrezenta() throws SQLException
    {
        Statement statement = connection.createStatement();
        ResultSet profesori_cursSet = statement.executeQuery("select * from \"Profesori\" " +
                "where specializare=\'curs\' order by info_corespunzator desc");

        Entities.PersoanaProfesor.Profesor_curs profesor_curs_cea_mai_mare_prezenta = null;
        if (profesori_cursSet.next())
        {
            profesor_curs_cea_mai_mare_prezenta = (new Profesor_curs(profesori_cursSet.getString("nume"),
                    profesori_cursSet.getString("prenume"),
                    profesori_cursSet.getString("adresa"),
                    profesori_cursSet.getInt("an_incepere_activitate"),
                    //profesori_cursSet.getInt("info_corespunzator"
                    profesori_cursSet.getInt("info_corespunzator")));
        }
        return profesor_curs_cea_mai_mare_prezenta;
    }

    public Entities.PersoanaProfesor.Profesor_laborator ProfesorCeleMaiPutineExercitii() throws SQLException
    {
        Statement statement = connection.createStatement();
        ResultSet profesori_laboratorSet = statement.executeQuery("select * from \"Profesori\" " +
                "where specializare=\'laborator\' order by info_corespunzator asc");

        Entities.PersoanaProfesor.Profesor_laborator profesor_laborator_cele_mai_putin_exercitii = null;
        if (profesori_laboratorSet.next())
        {
            profesor_laborator_cele_mai_putin_exercitii = (new Profesor_laborator(profesori_laboratorSet.getString("nume"),
                    profesori_laboratorSet.getString("prenume"),
                    profesori_laboratorSet.getString("adresa"),
                    profesori_laboratorSet.getInt("an_incepere_activitate"),
                    //profesori_laboratorSet.getInt("info_corespunzator"
                    profesori_laboratorSet.getInt("info_corespunzator")));
        }
        return profesor_laborator_cele_mai_putin_exercitii;
    }

    public Entities.PersoanaProfesor.Profesor_seminar ProfesorCelMaiMareBonus() throws SQLException
    {
        Statement statement = connection.createStatement();
        ResultSet profesori_seminarSet = statement.executeQuery("select * from \"Profesori\" " +
                "where specializare=\'seminar\' order by info_corespunzator desc");

        Entities.PersoanaProfesor.Profesor_seminar profesor_seminar_cel_mai_mare_bonus = null;
        if (profesori_seminarSet.next())
        {
            profesor_seminar_cel_mai_mare_bonus = (new Profesor_seminar(profesori_seminarSet.getString("nume"),
                    profesori_seminarSet.getString("prenume"),
                    profesori_seminarSet.getString("adresa"),
                    profesori_seminarSet.getInt("an_incepere_activitate"),
                    //profesori_seminarSet.getInt("info_corespunzator"
                    profesori_seminarSet.getInt("info_corespunzator")));
        }
        return profesor_seminar_cel_mai_mare_bonus;
    }

    public void citireProfesori_csv() {
        String csvPath = "C:\\Users\\Robert Roman\\IdeaProjects\\ProiectPAOMaven\\src\\CSV_Files\\csv_citire_profesor.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvPath));

            String line = reader.readLine();
            while ((line = reader.readLine()) != null)
            {
                String[] lineBreakdown = line.split(",");
                String nume = lineBreakdown[0];
                String prenume = lineBreakdown[1];
                String adresa = lineBreakdown[2];
                Integer an_incepere_activitate = Integer.parseInt(lineBreakdown[3]);
                String specializare = lineBreakdown[4];
                Integer info_corespunzator = Integer.parseInt(lineBreakdown[5]);

                if (specializare.compareTo("curs") == 0) {
                    Profesor_curs profesor_inserat = new Profesor_curs(nume, prenume, adresa, an_incepere_activitate, info_corespunzator);
                    addProfesor(((Profesor) profesor_inserat), specializare);
                } else if (specializare.compareTo("laborator") == 0) {
                    Profesor_laborator profesor_inserat = new Profesor_laborator(nume, prenume, adresa, an_incepere_activitate, info_corespunzator);
                    addProfesor(((Profesor) profesor_inserat), specializare);
                } else {
                    Profesor_seminar profesor_inserat = new Profesor_seminar(nume, prenume, adresa, an_incepere_activitate, info_corespunzator);
                    addProfesor(((Profesor) profesor_inserat), specializare);
                }
                audit.insereazaComanda("inserare Profesor");

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void citireStudenti_csv() {
        String csvPath = "C:\\Users\\Robert Roman\\IdeaProjects\\ProiectPAOMaven\\src\\CSV_Files\\csv_citire_student.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvPath));

            String line = reader.readLine();
            while ((line = reader.readLine()) != null)
            {
                String[] lineBreakdown = line.split(",");
                String nume = lineBreakdown[0];
                String prenume = lineBreakdown[1];
                String adresa = lineBreakdown[2];
                String specializare = lineBreakdown[3];
                Integer an = Integer.parseInt(lineBreakdown[4]);
                Integer grupa = Integer.parseInt(lineBreakdown[5]);
                String tip_studiu = lineBreakdown[6];
                float nota_corespondenta = Float.parseFloat(lineBreakdown[7]);

                if (tip_studiu.compareTo("licenta") == 0) {
                    Student_licenta student_inserat = new Student_licenta(nume, prenume, adresa, specializare, an, grupa, nota_corespondenta);
                    addStudent(((Student) student_inserat), tip_studiu);
                } else if (tip_studiu.compareTo("master") == 0) {
                    Student_master student_inserat = new Student_master(nume, prenume, adresa, specializare, an, grupa, nota_corespondenta);
                    addStudent(((Student) student_inserat), tip_studiu);
                } else {
                    Student_doctorat student_inserat = new Student_doctorat(nume, prenume, adresa, specializare, an, grupa, nota_corespondenta);
                    addStudent(((Student) student_inserat), tip_studiu);
                }
                audit.insereazaComanda("inserare Student");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void citireMaterii_csv() throws SQLException
    {
        String csvPath = "C:\\Users\\Robert Roman\\IdeaProjects\\ProiectPAOMaven\\src\\CSV_Files\\csv_citire_materie.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvPath));

            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] lineBreakdown = line.split(",");
                String denumire = lineBreakdown[0];
                Integer nr_credite = Integer.parseInt(lineBreakdown[1]);

                addMaterie(new Materie(denumire, nr_credite));
                audit.insereazaComanda("inserare Materie");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void citireNote_csv() throws SQLException
    {
        String csvPath = "C:\\Users\\Robert Roman\\IdeaProjects\\ProiectPAOMaven\\src\\CSV_Files\\csv_citire_nota.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvPath));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] lineBreakdown = line.split(",");
                Integer id_materie = Integer.parseInt(lineBreakdown[0]);
                Materie materie = getMaterieCautata(id_materie);
                Integer id_profesor = Integer.parseInt(lineBreakdown[1]);
                Profesor profesor = getProfesorCautat(id_profesor);
                Integer id_student = Integer.parseInt(lineBreakdown[2]);
                Student student = getStudentCautat(id_student);
                Integer nota_materie = Integer.parseInt(lineBreakdown[3]);
                addNota(new Nota(materie, profesor, student, nota_materie));
                audit.insereazaComanda("inserare Nota");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void printareStudent_csv(Integer id) throws SQLException
    {
        Student student_cautat = getStudentCautat(id);
            try
        {
            FileWriter myWriter = new FileWriter("C:\\Users\\Robert Roman\\IdeaProjects\\ProiectPAOMaven\\src\\CSV_Files\\csv_afisare_student.csv");
            myWriter.write(String.valueOf(student_cautat));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printareProfesor_csv(Integer id) throws SQLException
    {
        Profesor profesor_cautat = getProfesorCautat(id);
        try
        {
            FileWriter myWriter = new FileWriter("C:\\Users\\Robert Roman\\IdeaProjects\\ProiectPAOMaven\\src\\CSV_Files\\csv_afisare_profesor.csv");
            myWriter.write(String.valueOf(profesor_cautat));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printareMaterie_csv(Integer id) throws SQLException
    {
        Materie materie_cautat = getMaterieCautata(id);
        try
        {
            FileWriter myWriter = new FileWriter("C:\\Users\\Robert Roman\\IdeaProjects\\ProiectPAOMaven\\src\\CSV_Files\\csv_afisare_materie.csv");
            myWriter.write(String.valueOf(materie_cautat));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Nota getNotaCautata(Integer id) throws SQLException
    {
        Nota nota_cautata = null;
        Statement statement = connection.createStatement();
        String ssql= "select * from \"Note\" where id= "  + id;
        ResultSet nota_cautataSet = statement.executeQuery(ssql);
        if (nota_cautataSet.next())
        {
            Materie materie = getMaterieCautata(nota_cautataSet.getInt("materie"));
            Profesor profesor = getProfesorCautat(nota_cautataSet.getInt("profesor"));
            Student student = getStudentCautat(nota_cautataSet.getInt("student"));
            nota_cautata = new Nota(materie, profesor, student,
                    nota_cautataSet.getInt("nota_materie"));
        }
        return nota_cautata;
    }
    
    public void printareNota_csv(Integer id) throws SQLException
    {
        Nota nota_cautat = getNotaCautata(id);
        try
        {
            FileWriter myWriter = new FileWriter("C:\\Users\\Robert Roman\\IdeaProjects\\ProiectPAOMaven\\src\\CSV_Files\\csv_afisare_nota.csv");
            myWriter.write(String.valueOf(nota_cautat));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudenti(String coloane_update, String conditii) throws SQLException, IOException {
        Statement statement = connection.createStatement();
        if (conditii.length()==0) statement.execute("update \"Studenti\" set " + coloane_update);
        else statement.execute("update \"Studenti\" set " + coloane_update + " where " + conditii);
        audit.insereazaComanda("update Studenti");
    }

    public void deleteFromStudenti(String conditii) throws SQLException, IOException {
        Statement statement = connection.createStatement();
        if (conditii.length()==0) statement.execute("delete from \"Studenti\"");
        else statement.execute("delete from \"Studenti\" where " + conditii);
        audit.insereazaComanda("sterge din Studenti");
    }

    public void updateProfesori(String coloane_update, String conditii) throws SQLException, IOException {
        Statement statement = connection.createStatement();
        if (conditii.length()==0) statement.execute("update \"Profesori\" set " + coloane_update);
        else statement.execute("update \"Profesori\" set " + coloane_update + " where " + conditii);
        audit.insereazaComanda("update Profesori");
    }

    public void deleteFromProfesori(String conditii) throws SQLException, IOException {
        Statement statement = connection.createStatement();
        if (conditii.length()==0) statement.execute("delete from \"Profesori\"");
        else statement.execute("delete from \"Profesori\" where " + conditii);
        audit.insereazaComanda("stergere din Profesori");
    }

    public void updateMaterii(String coloane_update, String conditii) throws SQLException, IOException {
        Statement statement = connection.createStatement();
        if (conditii.length()==0) statement.execute("update \"Materii\" set " + coloane_update);
        else statement.execute("update \"Materii\" set " + coloane_update + " where " + conditii);
        audit.insereazaComanda("update Materii");
    }

    public void deleteFromMaterii(String conditii) throws SQLException, IOException {
        Statement statement = connection.createStatement();
        if (conditii.length()==0) statement.execute("delete from \"Materii\"");
        else statement.execute("delete from \"Materii\" where " + conditii);
        audit.insereazaComanda("stergere din Materii");
    }

    public void updateNote(String coloane_update, String conditii) throws SQLException, IOException {
        Statement statement = connection.createStatement();
        if (conditii.length()==0) statement.execute("update \"Note\" set " + coloane_update);
        else statement.execute("update \"Note\" set " + coloane_update + " where " + conditii);
        audit.insereazaComanda("update Note");
    }

    public void deleteFromNote(String conditii) throws SQLException, IOException {
        Statement statement = connection.createStatement();
        if (conditii.length()==0) statement.execute("delete from \"Note\"");
        else statement.execute("delete from \"Note\" where " + conditii);
        audit.insereazaComanda("stergere din Note");
    }
}
