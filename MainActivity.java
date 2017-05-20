package com.example.ben.projetgsb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {
    private Button btnInsuline;
    private TextView txtInsuline;
    private EditText editGlycemie;
    private RadioButton radioProt1;
    private RadioButton radioProt2;
    private EditText editTextNom;
    private EditText editTextPrenom;
    private TextView textView5;
    private TextView textView6;
    private Button btnAjouter;
    private MaladesBDD maladesBDD;
    private Button btnVoirMalade;
    private static final int MYSECONDACTIVITY_REQUEST=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsuline = (Button) findViewById(R.id.btnInsuline);
        txtInsuline = (TextView) findViewById(R.id.txtInsuline);
        editGlycemie = (EditText) findViewById(R.id.editGlycemie);
        radioProt1 = (RadioButton) findViewById(R.id.radioProt1);
        radioProt2 = (RadioButton) findViewById(R.id.radioProt2);
        editTextNom = (EditText) findViewById(R.id.editTextNom);
        editTextPrenom = (EditText) findViewById(R.id.editTextPrenom);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        btnAjouter = (Button) findViewById(R.id.btnAjouter);
        btnVoirMalade = (Button) findViewById(R.id.btnVoirMalade);

        btnInsuline.setOnClickListener(afficheInsuline);
        btnAjouter.setOnClickListener(ajouterMalade);
        btnVoirMalade.setOnClickListener(voirMalade);

        maladesBDD = new MaladesBDD(this);
        maladesBDD.open();
    }

    private View.OnClickListener afficheInsuline = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            double glycemie = Double.parseDouble(editGlycemie.getText().toString());
            if (radioProt1.isChecked()) {
                MapProtocoles Mp = new MapProtocoles();
                Mp.initialiser();
                Protocole p = Mp.getProtocole(1);
                int insu = p.insuline(glycemie);
                txtInsuline.setText(String.valueOf(insu));
                if (glycemie>=2) {
                    btnAjouter.setVisibility(View.VISIBLE);
                    editTextNom.setVisibility(View.VISIBLE);
                    editTextPrenom.setVisibility(View.VISIBLE);
                    textView5.setVisibility(View.VISIBLE);
                    textView6.setVisibility(View.VISIBLE);
                }
                else {
                    btnAjouter.setVisibility(View.INVISIBLE);
                    editTextNom.setVisibility(View.INVISIBLE);
                    editTextPrenom.setVisibility(View.INVISIBLE);
                    textView5.setVisibility(View.INVISIBLE);
                    textView6.setVisibility(View.INVISIBLE);
                }
            }

            if (radioProt2.isChecked()) {
                MapProtocoles Mp = new MapProtocoles();
                Mp.initialiser();
                Protocole p = Mp.getProtocole(2);
                int insu = p.insuline(glycemie);
                txtInsuline.setText(String.valueOf(insu));
                if (glycemie>=2) {
                    btnAjouter.setVisibility(View.VISIBLE);
                    editTextNom.setVisibility(View.VISIBLE);
                    editTextPrenom.setVisibility(View.VISIBLE);
                    textView5.setVisibility(View.VISIBLE);
                    textView6.setVisibility(View.VISIBLE);
                }
                else {
                    btnAjouter.setVisibility(View.INVISIBLE);
                    editTextNom.setVisibility(View.INVISIBLE);
                    editTextPrenom.setVisibility(View.INVISIBLE);
                    textView5.setVisibility(View.INVISIBLE);
                    textView6.setVisibility(View.INVISIBLE);
                }
            }
        }
    };


    private View.OnClickListener ajouterMalade = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String nom = editTextNom.getText().toString();
            String prenom = editTextPrenom.getText().toString();
            double glycemie = Double.parseDouble(editGlycemie.getText().toString());
            Malade unMalade = new Malade(nom, prenom, glycemie);
            maladesBDD.ajoutMalade(unMalade);
            Toast.makeText(MainActivity.this,"Le malade a été ajouter à la liste des malades",Toast.LENGTH_SHORT).show();
            editTextNom.setText("");
            editTextPrenom.setText("");
        }
    };

    private View.OnClickListener voirMalade = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ArrayList<Malade> lesMalades = maladesBDD.getTousLesMalades();
            Intent intent = new Intent(MainActivity.this, afficherMalade.class);
            intent.putExtra("message",lesMalades);
            startActivityForResult(intent,MYSECONDACTIVITY_REQUEST);
        }
    };
}