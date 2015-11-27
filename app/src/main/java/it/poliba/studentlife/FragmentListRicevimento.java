package it.poliba.studentlife;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        RicevimentoListAdapter rla = new RicevimentoListAdapter(mFirebaseRef, getActivity(),
                R.layout.layout_ricevimento_list_adapter, "studente-1");

        setListAdapter(rla);

        getListView();

        MainActivity activity = (MainActivity) getActivity();
        if (activity.getmUser() == "professore") {
            activity.findViewById(R.id.fab).setVisibility(View.VISIBLE);
        }
    }



}
