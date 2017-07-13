package com.example.senafunakubo.pizzaorder;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class PizzaOrder extends AppCompatActivity {
    EditText editText;
    Button orderButton;
    RadioButton radioButton;
    RadioButton regular;
    RadioButton medium;
    RadioButton large;
    RadioGroup radiogroup;
    CheckBox topping1;
    CheckBox topping2;
    CheckBox topping3;
    CheckBox topping4;
    TextView personName;
    TextView pizzaSize;
    TextView pizzaTopping;
    TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_order);

        orderButton = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        regular = (RadioButton) findViewById(R.id.radiobutton_re);
        medium = (RadioButton) findViewById(R.id.radiobutton_me);
        large = (RadioButton) findViewById(R.id.radiobutton_la);
        topping1 = (CheckBox) findViewById(R.id.checkbox_pe);
        topping2 = (CheckBox) findViewById(R.id.checkbox_ma);
        topping3 = (CheckBox) findViewById(R.id.checkbox_bl);
        topping4 = (CheckBox) findViewById(R.id.checkbox_pi);
        personName = (TextView) findViewById(R.id.textView_personName);
        pizzaSize = (TextView) findViewById(R.id.textView_sizeReal);
        pizzaTopping = (TextView) findViewById(R.id.textView_toppingReal);
        price = (TextView) findViewById(R.id.textView_priceReal);
    }

//        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
//        {
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                // checkedId is the RadioButton selected
//                int selectedId = radiogroup.getCheckedRadioButtonId();
//                radioButton = (RadioButton) findViewById(selectedId);
//            }
//        });
//
//
//        orderButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                String message = editText.getText().toString();
//                String radioButtonSt = radioButton.getText().toString();
//                String topping = getCheckBoxClicked(v);
//                String forToast = message +"\nYour order is..." + "\nSIZE: " + radioButtonSt + "\nTOPPING: "+topping;
//                Toast.makeText(getApplicationContext(), forToast, Toast.LENGTH_SHORT).show();
//
//                personName.setText(message);
//                pizzaSize.setText(radioButtonSt);
//                pizzaTopping.setText(topping);
//            }
//        });
//    }


    public int checkForSize(){
        int basicPrice = 0;

        if(regular.isChecked())
            basicPrice = 8;
        else if(medium.isChecked())
            basicPrice = 10;
        else if(large.isChecked())
            basicPrice = 12;

        return basicPrice;
    }

    public int checkForTopping(){

        int toppingPrice = 0;

        if(topping1.isChecked())
        {
            toppingPrice = toppingPrice + 2;
        }
        if(topping2.isChecked())
        {
            toppingPrice = toppingPrice + 2;
        }
        if(topping3.isChecked())
        {
            toppingPrice = toppingPrice + 2;
        }
        if(topping4.isChecked())
        {
            toppingPrice = toppingPrice + 2;
        }

        return toppingPrice;
    }

    public String getCheckBoxClicked(View v){

        String strTopping="";

        if(topping1.isChecked())
        {
            strTopping= strTopping + " " + topping1.getText().toString();
        }
        if(topping2.isChecked())
        {
            strTopping= strTopping + " " +topping2.getText().toString();
        }
        if(topping3.isChecked())
        {
            strTopping= strTopping + " " +topping3.getText().toString();
        }
        if(topping4.isChecked())
        {
            strTopping= strTopping + " " +topping4.getText().toString();
        }

        return strTopping;
    }

    public String checkSize() {
                // checkedId is the RadioButton selected
                int selectedId = radiogroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                String size = radioButton.getText().toString();

          return size;
     }


    public void createOrder(View v){
        Log.d("in order","error");
        int basicPrice,toppingPrice;

        basicPrice = checkForSize();
        toppingPrice = checkForTopping();
        int totalPrice = basicPrice + toppingPrice;
        String totalPriceSt = Integer.toString(totalPrice);

        String person = editText.getText().toString();
        String size = checkSize();
        String topping = getCheckBoxClicked(v);

        createOrderSummary(totalPriceSt,person,size,topping);
    }

    public void createOrderSummary(String total, String Person, String Size, String Topping){
        String forToast = Person +"\nYour order is..." + "\nSIZE: " + Size + "\nTOPPING: "+Topping + "\nPrice: $" + total;

        // For toast
            Toast.makeText(getApplicationContext(), forToast, Toast.LENGTH_SHORT).show();

        // For showing up the data on display
                personName.setText(Person);
                pizzaSize.setText(Size);
                pizzaTopping.setText(Topping);
                price.setText("$" + total);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "inabacker24@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "your order");
        intent.putExtra(Intent.EXTRA_TEXT, forToast);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
