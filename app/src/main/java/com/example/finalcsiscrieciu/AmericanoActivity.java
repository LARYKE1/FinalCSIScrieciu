package com.example.finalcsiscrieciu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AmericanoActivity extends AppCompatActivity {

    CoffeType coffeAmericano;
    EditText editText;
    Button sendOrder,cancelOrder,addQuantity,removeQuantity;
    ImageView latteimg;
    CheckBox ckType1,ckType2,ckType3,extra1,extra2,extra3;
    TextView txtDescription,priceType1,priceType2,priceType3, priceExtra1,priceExtra2,priceExtra3;
    int quantity=1;
    String extra="None";
    double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_americano);

        coffeAmericano=new CoffeType(R.drawable.americano,"Americano","Best Americano in the world","Americano 1",5.99,"None",quantity);


        FindByView();
        PopulateDisplay(coffeAmericano);

        functionalities();


        sendOrder.setOnClickListener(view -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("coffeType", coffeAmericano);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
        cancelOrder.setOnClickListener(view -> {
            Intent resultIntent=new Intent();
            setResult(Activity.RESULT_CANCELED,resultIntent);
            finish();
        });
    }


    private void FindByView(){
        sendOrder=(Button)findViewById(R.id.btnOrderA);
        cancelOrder=findViewById(R.id.btnCancelOrderA);
        addQuantity=(Button)findViewById(R.id.quantityAddA);
        removeQuantity=(Button)findViewById(R.id.removeQuantityA);
        editText=(EditText)findViewById(R.id.editTxtQuantityA);
        latteimg=(ImageView) findViewById(R.id.americanoImg);
        txtDescription=(TextView) findViewById(R.id.txtDescriptionAmericano);
        ckType1=(CheckBox) findViewById(R.id.checkboxAmericano1);
        ckType2=(CheckBox) findViewById(R.id.checkboxAmericano2);
        ckType3=(CheckBox) findViewById(R.id.checkboxAmericano3);
        extra1=(CheckBox)findViewById(R.id.txtExtrasAmericano1);
        extra2=(CheckBox)findViewById(R.id.txtExtrasAmericano2);
        extra3=(CheckBox)findViewById(R.id.txtExtrasAmericano3);
        priceType1=(TextView) findViewById(R.id.txtPriceAmericano1);
        priceType2=(TextView) findViewById(R.id.txtPriceAmericano2);
        priceType3=(TextView) findViewById(R.id.txtPriceAmericano3);
        priceExtra1=(TextView) findViewById(R.id.txtPriceExtraAmericano1);
        priceExtra2=(TextView) findViewById(R.id.txtPriceExtraAmericano2);
        priceExtra3=(TextView) findViewById(R.id.txtPriceExtraAmericano3);
    }

    private void PopulateDisplay(CoffeType coffe){
        latteimg.setImageResource(coffe.getImageResource());
        txtDescription.setText(coffe.getDescription());
        priceType1.setText(String.format("$%.2f",coffe.getPrice()));
        priceType2.setText(String.format("$%.2f",(coffe.getPrice()+1)));
        priceType3.setText(String.format("$%.2f",(coffe.getPrice()+2)));
        priceExtra1.setText(String.format("$%.2f",3.99));
        priceExtra2.setText(String.format("$%.2f",0.99));
        priceExtra3.setText(String.format("$%.2f",0.00));
    }

    private void functionalities(){
        ckType1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(ckType1.isChecked()){
                    coffeAmericano.setName("Americano 1");
                }
            }
        });
        ckType2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(ckType2.isChecked()){
                    coffeAmericano.setName("Americano 2");
                    coffeAmericano.setPrice(Double.parseDouble(priceType2.getText().toString().substring(1)));
                }else{
                    coffeAmericano.setName("Americano 3");
                    coffeAmericano.setPrice(Double.parseDouble(priceType1.getText().toString().substring(1)));
                }
            }
        });
        ckType3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(ckType3.isChecked()){
                    coffeAmericano.setName("Latte 3");
                    coffeAmericano.setPrice(Double.parseDouble(priceType3.getText().toString().substring(1)));
                }else{
                    coffeAmericano.setName("Latte 1");
                    coffeAmericano.setPrice(Double.parseDouble(priceType1.getText().toString().substring(1)));

                }
            }
        });

        extra1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                double newprice=coffeAmericano.getPrice()+Double.parseDouble(priceExtra1.getText().toString().substring(1));
                double oldprice=coffeAmericano.getPrice()-Double.parseDouble(priceExtra1.getText().toString().substring(1));
                if(extra1.isChecked()){
                    coffeAmericano.setExtra("With Sugar");
                    coffeAmericano.setPrice(newprice);
                }else{
                    coffeAmericano.setExtra("-");
                    coffeAmericano.setPrice(oldprice);
                }
            }
        });

        extra2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                double newprice=coffeAmericano.getPrice()+Double.parseDouble(priceExtra2.getText().toString().substring(1));
                double oldprice=coffeAmericano.getPrice()-Double.parseDouble(priceExtra2.getText().toString().substring(1));
                if(extra2.isChecked()){
                    coffeAmericano.setExtra("With Milk");
                    coffeAmericano.setPrice(newprice);
                }else{
                    coffeAmericano.setExtra("-");
                    coffeAmericano.setPrice(oldprice);
                }
            }
        });
        extra3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                double newprice=coffeAmericano.getPrice()+Double.parseDouble(priceExtra3.getText().toString().substring(1));
                double oldprice=coffeAmericano.getPrice()-Double.parseDouble(priceExtra3.getText().toString().substring(1));
                if(extra3.isChecked()){
                    coffeAmericano.setExtra("With Honey");
                    coffeAmericano.setPrice(newprice);
                }else{
                    coffeAmericano.setExtra("-");
                    coffeAmericano.setPrice(oldprice);
                }
            }
        });


        addQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                updateValue();
                coffeAmericano.setQuantity(quantity);

            }
        });
        removeQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity--;
                updateValue();
                coffeAmericano.setQuantity(quantity);

            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                coffeAmericano.setQuantity(0);
                String quantityText = editText.getText().toString();
                if (!quantityText.isEmpty()) {
                    coffeAmericano.setQuantity(Integer.parseInt(quantityText));

                }
            }
        });



    }

    void updateValue(){
        editText.setText(String.valueOf(quantity));
    }
}