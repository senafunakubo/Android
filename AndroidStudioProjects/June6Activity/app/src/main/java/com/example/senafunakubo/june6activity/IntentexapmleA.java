package com.example.senafunakubo.june6activity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.EditText;


public class IntentexapmleA extends AppCompatActivity {
    //1. declare all the controls
    TextView t1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intentexample1);

        //2. bind all the controls
        t1 = (TextView) findViewById(R.id.txtA);
        b1 = (Button) findViewById(R.id.btnA);
        final EditText editText = (EditText) findViewById(R.id.editText);

        //3. define the onClickListener event
        b1.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntentexapmleA.this,IntentexampleB.class);
                String message = editText.getText().toString();
                i.putExtra("message", message); //"message"はkey
                startActivity(i);
            }
        });
    }


}
