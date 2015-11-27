package it.poliba.studentlife;

/**
 * Created by roberto on 26/11/15.
 */
public class Ricevimento {

    private String ora;
    private String giorno;
    private String aula;
    private String telefono;
    private String id;

    private Ricevimento () {}

    public Ricevimento (String aula, String giorno, String ora, String telefono, String id) {
        this.ora = ora;
        this.giorno = giorno;
        this.aula = aula;
        this.telefono = telefono;
        this.id = id;
    }


    public String getOra () { return  ora; }
    public String getTelefono () { return  telefono; }
    public String getGiorno () { return  giorno; }
    public String getAula () { return  aula; }
    public String getId () { return  id; }

}
