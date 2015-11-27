package it.poliba.studentlife;

import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.client.Firebase;

/**
 * Created by roberto on 26/11/15.
 */
public class FragmentListRicevimento extends ListFragment {

    private static final String FIREBASE_URL = "https://blinding-fire-8278.firebaseio.com/";

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_lista_ricevimenti, container, false);
        return v;
    }

    @Override
    public void onViewCreated (View v, Bundle savedInstanceState) {
        /*Ricevimento ricevimento = new Ricevimento("ora", "giorno", "aula", "telefono");

        RicevimentoArrayListAdapter adapter = new RicevimentoArrayListAdapter(getActivity(),
                new Ricevimento[] {ricevimento});

        setListAdapter(adapter);*/

        Firebase mFirebaseRef = new Firebase(FIREBASE_URL).child("prenotazioni").child("tommy");
/*
        ChatListAdapter cla = new ChatListAdapter(mFirebaseRef, getActivity(), R.layout.chat_message, "prova");
        setListAdapter(cla);
       */

        final RicevimentoListAdapter rla = new RicevimentoListAdapter(mFirebaseRef, getActivity(),
                R.layout.layout_ricevimento_list_adapter, "studente-1");

        setListAdapter(rla);

        getListView();

        Button esporta = (Button) v.findViewById(R.id.esporta);
        esporta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExportToCSV csv = new ExportToCSV(getActivity().getApplicationContext());
                Uri uri = csv.createCSV(rla.mModels);
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setData(uri);
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                shareIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.setType("*/*");
                startActivity(Intent.createChooser(shareIntent, "Condividi usando..."));
            }
        });

        MainActivity activity = (MainActivity) getActivity();
        if (activity.getmUser() == "professore") {
            activity.findViewById(R.id.fab).setVisibility(View.VISIBLE);
        }
    }



}
