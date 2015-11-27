package it.poliba.studentlife;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

/**
 * Created by roberto on 27/11/15.
 */
public class FragmentLogin extends Fragment {

    public View onCreateView (LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_selezione_utente, parent, false);
        return v;
    }

    public void onViewCreated (View v, Bundle savedInstanceState) {
        Button login = (Button) v.findViewById(R.id.login_button);
        final RadioGroup select = (RadioGroup) v.findViewById(R.id.login_select);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                switch ( select.getCheckedRadioButtonId() ) {
                    case R.id.login_professore : {
                        activity.setmUser("professore");
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.frame_layout, new FragmentListRicevimento())
                                .addToBackStack(null)
                                .commit();
                        break;
                    }

                    case R.id.login_studente : {
                        activity.setmUser("studente");
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.frame_layout, new FragmentListRicevimento())
                                .addToBackStack(null)
                                .commit();
                        break;
                    }

                    default: {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Attenzione").setMessage("Seleziona il modo in cui vuoi operare")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                                .create()
                                .show();
                        break;
                    }
                }
            }
        });
    }

}
