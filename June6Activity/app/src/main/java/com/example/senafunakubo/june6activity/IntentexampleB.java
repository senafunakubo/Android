package com.example.senafunakubo.june6activity;

/**
 * Created by senafunakubo on 2017-07-06.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.net.Uri;

public class IntentexampleB extends MainActivity{
    //1. declare all the controls
    TextView t2;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intentexample2);

        //2. bind all the controls
        t2 = (TextView) findViewById(R.id.txtB);
        b2 = (Button) findViewById(R.id.btnB);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");

        TextView txtView = (TextView) findViewById(R.id.txtC);
        txtView.setText(message);

        //3. define the onClickListener event
        b2.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntentexampleB.this,IntentexapmleA.class);
                startActivity(i);

//                Intent gmail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","kumasum178@yahoo.co.jp",null));
//                gmail.putExtra(Intent.EXTRA_SUBJECT, "subject");
//                gmail.putExtra(Intent.EXTRA_TEXT,"body");
//                gmail.setType("text/plain");
//                startActivity(Intent.createChooser(gmail,"SendEmail"));


                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, "inabacker24@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                intent.putExtra(Intent.EXTRA_TEXT, "Type something here");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });
    }
}
