package it.poliba.studentlife;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.client.Query;

/**
 * @author greg
 * @since 6/21/13
 *
 * This class is an example of how to use FirebaseListAdapter. It uses the <code>Chat</code> class to encapsulate the
 * data for each individual chat message
 */
public class RicevimentoListAdapter extends FirebaseListAdapter<Ricevimento> {

    // The mUsername for this client. We use this to indicate which messages originated from this user
    private String mUsername;
    private Activity refActivity;
    private Query mFirebaseRef;

    public RicevimentoListAdapter(Query ref, Activity activity, int layout, String mUsername) {
        super(ref, Ricevimento.class, layout, activity);
        this.mUsername = mUsername;
        this.refActivity = activity;
        this.mFirebaseRef = ref;
    }

    /**
     * Bind an instance of the <code>Chat</code> class to our view. This method is called by <code>FirebaseListAdapter</code>
     * when there is a data change, and we are given an instance of a View that corresponds to the layout that we passed
     * to the constructor, as well as a single <code>Chat</code> instance that represents the current data to bind.
     *
     * @param view A view instance corresponding to the layout we passed to the constructor.
     * @param chat An instance representing the current state of a chat message
     */
    @Override
    protected void populateView(View view, final Ricevimento ricevimento) {
        // Map a Chat object to an entry in our listview
        TextView ora = (TextView) view.findViewById(R.id.ora);
        TextView giorno = (TextView) view.findViewById(R.id.data);
        TextView telefono = (TextView) view.findViewById(R.id.telefono);
        TextView aula = (TextView) view.findViewById(R.id.aula);

        ora.setText(ricevimento.getOra());
        giorno.setText(ricevimento.getGiorno());
        telefono.setText(ricevimento.getTelefono());
        aula.setText(ricevimento.getAula());

        //mKeys[currentSelected]


        ImageButton btn = (ImageButton) view.findViewById(R.id.prenota);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(refActivity);

                builder.setTitle("Attenzione").setMessage("La tua prenotazione sarà inviata al docente, confermi?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                confermaPrenotazione(ricevimento.getId());
                                dialogInterface.dismiss();
                            }
                        })
                        .create().show();

            }
        });

        btn.setTag(mKeys.get(currentSelected));
    }

    private void confermaPrenotazione (String id) {
        mFirebaseRef.getRef().getParent().child("alunni").child(id).push().setValue(new Prenotazione(mUsername));
    }
}
