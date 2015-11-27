package it.poliba.studentlife;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;


public class ExportToCSV {

    Context context;

    public ExportToCSV(Context mContext){
        this.context = mContext;
    }

    public Uri createCSV(final List<Ricevimento> ricevimento) {

        final File file = new File(context.getFilesDir(), "ricevimenti.csv"); //Getting a file within the dir.
        file.delete();


        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream);

            osw.append("Ora");
            osw.append(',');

            osw.append("Giorno");
            osw.append(',');

            osw.append("Aula");
            osw.append(',');

            osw.append("Telefono");
            osw.append('\n');


            for (Ricevimento r : ricevimento){
                osw.append(r.getOra());
                osw.append(',');

                osw.append(r.getGiorno());
                osw.append(',');

                osw.append(r.getAula());
                osw.append(',');

                osw.append(r.getTelefono());
                osw.append('\n');

            }
            osw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return FileProvider.getUriForFile(context, "it.poliba.studentlife", file);
    }

}
