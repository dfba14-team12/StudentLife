package it.poliba.studentlife;

/**
 * Created by roberto on 27/11/15.
 */
public class Prenotazione {

    private String alunno;

    private Prenotazione () {}

    public Prenotazione (String alunno) {
        this.alunno = alunno;
    }

    public String getAlunno () { return alunno; }

}
