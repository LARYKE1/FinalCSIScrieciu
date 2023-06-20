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

public class MochaActivity extends AppCompatActivity {

    CoffeType coffeMocha;
    EditText editText;
    Button sendOrder,cancelOrder,addQuantity,removeQuantity;
    ImageView Mochaimg;
    CheckBox ckType1,ckType2,ckType3,extra1,extra2,extra3;
    TextView txtDescription,priceType1,priceType2,priceType3, priceExtra1,priceExtra2,priceExtra3;
    double defaultPrice=7.00;
    int quantity=1;
    String extra="None";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mocha);

        coffeMocha=new CoffeType(R.drawable.mocha,"Mocha Starbucks","Mocha is a popular coffee beverage",
                "Mocha 1",defaultPrice,extra,quantity);

        FindByView();
        PopulateDisplay(coffeMocha);

        functionalities();

        sendOrder.setOnClickListener(view -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("coffeType", coffeMocha);
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
        sendOrder=(Button)findViewById(R.id.btnOrder);
        cancelOrder=(Button)findViewById(R.id.btnCancelOrder);
        addQuantity=(Button)findViewById(R.id.quantityAddMocha);
        removeQuantity=(Button)findViewById(R.id.removeQuantityMocha);
        editText=(EditText)findViewById(R.id.editTxtQuantityMocha);
        Mochaimg=(ImageView) findViewById(R.id.MochaImg);
        txtDescription=(TextView) findViewById(R.id.txtDescriptionMocha);
        ckType1=(CheckBox) findViewById(R.id.checkboxMocha1);
        ckType2=(CheckBox) findViewById(R.id.checkboxMocha2);
        ckType3=(CheckBox) findViewById(R.id.checkboxMocha3);
        extra1=(CheckBox)findViewById(R.id.txtExtrasMocha1);
        extra2=(CheckBox)findViewById(R.id.txtExtrasMocha2);
        extra3=(CheckBox)findViewById(R.id.txtExtrasMocha3);
        priceType1=(TextView) findViewById(R.id.txtPriceMocha1);
        priceType2=(TextView) findViewById(R.id.txtPriceMocha2);
        priceType3=(TextView) findViewById(R.id.txtPriceMocha3);
        priceExtra1=(TextView) findViewById(R.id.txtPriceExtraMocha1);
        priceExtra2=(TextView) findViewById(R.id.txtPriceExtraMocha2);
        priceExtra3=(TextView) findViewById(R.id.txtPriceExtraMocha3);
    }
    private void PopulateDisplay(CoffeType coffe){
        Mochaimg.setImageResource(coffe.getImageResource());
        txtDescription.setText(coffe.getDescription());
        priceType1.setText(String.format("$%.2f",coffe.getPrice()));
        priceType2.setText(String.format("$%.2f",(coffe.getPrice()+1)));
        priceType3.setText(String.format("$%.2f",(coffe.getPrice()+2)));
        priceExtra1.setText(String.format("$%.2f",0.59));
        priceExtra2.setText(String.format("$%.2f",1.19));
        priceExtra3.setText(String.format("$%.2f",2.50));


    }
    void updateValue(){
        editText.setText(String.valueOf(quantity));
    }
    private void functionalities(){
        ckType1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(ckType1.isChecked()){
                    coffeMocha.setName("Mocha 1");
                }
            }
        });
        ckType2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(ckType2.isChecked()){
                    coffeMocha.setName("Mocha 2");
                    coffeMocha.setPrice(Double.parseDouble(priceType2.getText().toString().substring(1)));
                }else{
                    coffeMocha.setName("Mocha 1");
                    coffeMocha.setPrice(Double.parseDouble(priceType1.getText().toString().substring(1)));
                }
            }
        });
        ckType3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(ckType3.isChecked()){
                    coffeMocha.setName("Mocha 3");
                    coffeMocha.setPrice(Double.parseDouble(priceType3.getText().toString().substring(1)));
                }else{
                    coffeMocha.setName("Mocha 1");
                    coffeMocha.setPrice(Double.parseDouble(priceType1.getText().toString().substring(1)));
                }
            }
        });

        extra1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                double newprice=coffeMocha.getPrice()+Double.parseDouble(priceExtra1.getText().toString().substring(1));
                double oldprice=coffeMocha.getPrice()-Double.parseDouble(priceExtra1.getText().toString().substring(1));
                if(extra1.isChecked()){
                    coffeMocha.setExtra("With Sugar");
                    coffeMocha.setPrice(newprice);
                }else{
                    coffeMocha.setExtra("-");
                    coffeMocha.setPrice(oldprice);
                }
            }
        });

        extra2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                double newprice=coffeMocha.getPrice()+Double.parseDouble(priceExtra2.getText().toString().substring(1));
                double oldprice=coffeMocha.getPrice()-Double.parseDouble(priceExtra2.getText().toString().substring(1));
                if(extra2.isChecked()){
                    coffeMocha.setExtra("With Milk");
                    coffeMocha.setPrice(newprice);
                }else{
                    coffeMocha.setExtra("-");
                    coffeMocha.setPrice(oldprice);
                }
            }
        });
        extra3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                double newprice=coffeMocha.getPrice()+Double.parseDouble(priceExtra3.getText().toString().substring(1));
                double oldprice=coffeMocha.getPrice()-Double.parseDouble(priceExtra3.getText().toString().substring(1));
                if(extra3.isChecked()){
                    coffeMocha.setExtra("With Honey");
                    coffeMocha.setPrice(newprice);
                }else{
                    coffeMocha.setExtra("-");
                    coffeMocha.setPrice(oldprice);
                }
            }
        });


        addQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                updateValue();
                coffeMocha.setQuantity(quantity);

            }
        });
        removeQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity--;
                updateValue();
                coffeMocha.setQuantity(quantity);

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
                coffeMocha.setQuantity(0);
                String quantityText = editText.getText().toString();
                if (!quantityText.isEmpty()) {
                    coffeMocha.setQuantity(Integer.parseInt(quantityText));

                }
            }
        });


    }
}