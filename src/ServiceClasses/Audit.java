package ServiceClasses;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Audit {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");
    FileWriter audit;
    public void insereazaComanda(String comanda) throws IOException{
        audit.append(comanda);
        audit.append(",");
        audit.append(dateTimeFormatter.format(LocalDateTime.now()));
        audit.append('\n');
        audit.flush();
    }

    public Audit(){
        try {
            this.audit = new FileWriter("C:\\Users\\Robert Roman\\IdeaProjects\\ProiectPAOMaven\\src\\CSV_Files\\audit.csv",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}