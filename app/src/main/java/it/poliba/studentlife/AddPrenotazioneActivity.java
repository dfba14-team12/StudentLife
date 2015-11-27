package it.poliba.studentlife;

import android.animation.Animator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class AddPrenotazioneActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private static final String FIREBASE_URL = "https://blinding-fire-8278.firebaseio.com/";
    Calendar pickedTime;
    Calendar pickedDate;
    String ora;
    String giorno;
    String aula;
    String telefono;
    EditText oraEditText;
    EditText giornoEditText;
    EditText aulaEditText;
    EditText telefonoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prenotazione);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        oraEditText = (EditText) findViewById(R.id.input_ora);
        giornoEditText = (EditText) findViewById(R.id.input_giorno);
        aulaEditText = (EditText) findViewById(R.id.input_aula);
        telefonoEditText = (EditText) findViewById(R.id.input_telefono);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_prenotazione_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        giornoEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        AddPrenotazioneActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        oraEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog dpd = TimePickerDialog.newInstance(
                        AddPrenotazioneActivity.this,
                        now.get(Calendar.HOUR),
                        now.get(Calendar.MINUTE),
                        true
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giorno = giornoEditText.getText().toString();
                aula = aulaEditText.getText().toString();
                telefono = telefonoEditText.getText().toString();
                Calendar cal = Calendar.getInstance();
                String id = cal.getTimeInMillis() + "";
                Ricevimento ricevimento = new Ricevimento(aula, giorno, ora, telefono, id);
                Firebase mFirebase = new Firebase(FIREBASE_URL);
                mFirebase.child("prenotazioni").child("tommy").push().setValue(ricevimento);
                startAnimation();
            }
        });
    }

    private void startAnimation() {
        // previously invisible view
        View myView = findViewById(R.id.ripple);

// get the center for the clipping circle
        int cx = myView.getWidth();
        int cy = 0;

// get the final radius for the clipping circle
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

// create the animator for this view (the start radius is zero)
        Animator anim =
                null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
        }

// make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }

    public void finishActivity(View v){
        finish();
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        giorno = dayOfMonth+"/"+monthOfYear+"/"+year;
        giornoEditText.setText(giorno);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String hour = hourOfDay+"";
        if (hourOfDay<10) {
            hour = "0"+hour;
        }
        String minutes = hourOfDay+"";
        if (minute<10) {
            minutes = "0"+minutes;
        }
        ora = hour + ":"+minutes;
        oraEditText.setText(ora);
    }
}
