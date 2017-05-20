package com.example.ben.projetgsb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class afficherMalade extends AppCompatActivity {
    private ListView lstAff;
    private Button btnMail;
    private Button btnSms;
    private static final int MYSECONDACTIVITY_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_afficher_malade);
        lstAff = (ListView) findViewById(R.id.lstMalades);
        btnMail = (Button) findViewById(R.id.btnMedecin);
        btnSms = (Button) findViewById(R.id.btnSms);

        btnMail.setOnClickListener(mailMedecin);
        btnSms.setOnClickListener(smsMedecin);

        ArrayList<String> lstMalades = (ArrayList<String>)getIntent().getSerializableExtra("message");
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(afficherMalade.this, android.R.layout.simple_list_item_1, lstMalades);
        lstAff.setAdapter(adapter);
    }

    private View.OnClickListener mailMedecin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ArrayList<String> lstMalades = (ArrayList<String>)getIntent().getSerializableExtra("message");
            Intent returnIt = new Intent(Intent.ACTION_SEND);
            String[] tos = { "ben.ji-78@hotmail.fr" };
            returnIt.putExtra(Intent.EXTRA_EMAIL, tos);
            returnIt.putExtra(Intent.EXTRA_TEXT, lstMalades.toString());
            returnIt.putExtra(Intent.EXTRA_SUBJECT, "subject");
            returnIt.setType("message/rfc882");
            Intent.createChooser(returnIt, "Choose Email Client");
            startActivityForResult(returnIt,MYSECONDACTIVITY_REQUEST);
        }
    };

    private View.OnClickListener smsMedecin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ArrayList<String> lstMalades = (ArrayList<String>)getIntent().getSerializableExtra("message");
            Uri smsUri = Uri.parse("tel:100861");
            Intent returnIt = new Intent(Intent.ACTION_VIEW, smsUri);
            returnIt.putExtra("sms_body", lstMalades.toString());
            returnIt.setType("vnd.android-dir/mms-sms");
           startActivityForResult(returnIt, MYSECONDACTIVITY_REQUEST);
        }
    };
}
