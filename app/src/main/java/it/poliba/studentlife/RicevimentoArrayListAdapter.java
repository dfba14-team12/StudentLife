package it.poliba.studentlife;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by roberto on 26/11/15.
 */
public class RicevimentoArrayListAdapter extends ArrayAdapter<Ricevimento> {

    public RicevimentoArrayListAdapter(Context context, Ricevimento[] objects) {
        super(context, R.layout.layout_ricevimento_list_adapter, objects);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View current = convertView;
        Ricevimento item = getItem(position);
        if (current == null) {
            current = LayoutInflater.from(getContext()).inflate(R.layout.layout_ricevimento_list_adapter, parent, false);
        }
        TextView data = (TextView) current.findViewById(R.id.data);
        TextView ora = (TextView) current.findViewById(R.id.ora);

        data.setText(item.getGiorno());
        ora.setText(item.getOra());

        return current;
    }


}
