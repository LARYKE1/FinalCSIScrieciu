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

import java.util.HashSet;
import java.util.Set;

public class LatteActivity extends AppCompatActivity {

    CoffeType coffeLatte;
    EditText editText;
    Button sendOrder,cancelOrder,addQuantity,removeQuantity;
    ImageView latteimg;
    CheckBox ckType1,ckType2,ckType3,extra1,extra2,extra3;
    TextView txtDescription,priceType1,priceType2,priceType3, priceExtra1,priceExtra2,priceExtra3;
    double defaultPrice=7.99;
    int quantity=1;
    String extra="None";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latte);


        coffeLatte=new CoffeType(R.drawable.latte1,"Starbucks latte","The best coffe in town","Default",defaultPrice,extra,quantity);

        FindByView();
        PopulateDisplay(coffeLatte);

        functionalities();
        double totalPrice=coffeLatte.getPrice()*coffeLatte.getQuantity();


        sendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), String.valueOf(coffeLatte.getQuantity()* coffeLatte.getPrice()), Toast.LENGTH_SHORT).show();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("coffeType", coffeLatte);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent=new Intent();
                setResult(Activity.RESULT_CANCELED,resultIntent);
                finish();
            }
        });


    }
    private void FindByView(){
        sendOrder=(Button)findViewById(R.id.btnOrder);
        cancelOrder=(Button)findViewById(R.id.btnCancelOrder);
        addQuantity=(Button)findViewById(R.id.quantityAdd);
        removeQuantity=(Button)findViewById(R.id.removeQuantity);
        editText=(EditText)findViewById(R.id.editTxtQuantity);
        latteimg=(ImageView) findViewById(R.id.latteImg);
        txtDescription=(TextView) findViewById(R.id.txtDescriptionLatte);
        ckType1=(CheckBox) findViewById(R.id.checkboxLatte1);
        ckType2=(CheckBox) findViewById(R.id.checkboxLatte2);
        ckType3=(CheckBox) findViewById(R.id.checkboxLatte3);
        extra1=(CheckBox)findViewById(R.id.txtExtrasLatte1);
        extra2=(CheckBox)findViewById(R.id.txtExtrasLatte2);
        extra3=(CheckBox)findViewById(R.id.txtExtrasLatte3);
        priceType1=(TextView) findViewById(R.id.txtPriceLatte1);
        priceType2=(TextView) findViewById(R.id.txtPriceLatte2);
        priceType3=(TextView) findViewById(R.id.txtPriceLatte3);
        priceExtra1=(TextView) findViewById(R.id.txtPriceExtraLatte1);
        priceExtra2=(TextView) findViewById(R.id.txtPriceExtraLatte2);
        priceExtra3=(TextView) findViewById(R.id.txtPriceExtraLatte3);
    }
    private void PopulateDisplay(CoffeType coffe){
        latteimg.setImageResource(coffe.getImageResource());
        txtDescription.setText(coffe.getDescription());
        priceType1.setText(String.format("$%.2f",coffe.getPrice()));
        priceType2.setText(String.format("$%.2f",(coffe.getPrice()+1)));
        priceType3.setText(String.format("$%.2f",(coffe.getPrice()+2)));
        priceExtra1.setText(String.format("$%.2f",1.99));
        priceExtra2.setText(String.format("$%.2f",0.99));
        priceExtra3.setText(String.format("$%.2f",0.50));


    }
    void updateValue(){
        editText.setText(String.valueOf(quantity));
    }
    private void functionalities(){
        ckType1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(ckType1.isChecked()){
                    coffeLatte.setName("Latte 1");
                }
            }
        });
        ckType2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(ckType2.isChecked()){
                    coffeLatte.setName("Latte 2");
                    coffeLatte.setPrice(Double.parseDouble(priceType2.getText().toString().substring(1)));
                }else{
                    coffeLatte.setName("Latte 1");
                    coffeLatte.setPrice(Double.parseDouble(priceType1.getText().toString().substring(1)));
                }
            }
        });
        ckType3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(ckType3.isChecked()){
                    coffeLatte.setName("Latte 3");
                    coffeLatte.setPrice(Double.parseDouble(priceType3.getText().toString().substring(1)));
                }else{
                    coffeLatte.setName("Latte 1");
                    coffeLatte.setPrice(Double.parseDouble(priceType1.getText().toString().substring(1)));

                }
            }
        });

        extra1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                double newprice=coffeLatte.getPrice()+Double.parseDouble(priceExtra1.getText().toString().substring(1));
                double oldprice=coffeLatte.getPrice()-Double.parseDouble(priceExtra1.getText().toString().substring(1));
                if(extra1.isChecked()){
                    coffeLatte.setExtra("With Sugar");
                    coffeLatte.setPrice(newprice);
                }else{
                    coffeLatte.setExtra("-");
                    coffeLatte.setPrice(oldprice);
                }
            }
        });

        extra2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                double newprice=coffeLatte.getPrice()+Double.parseDouble(priceExtra2.getText().toString().substring(1));
                double oldprice=coffeLatte.getPrice()-Double.parseDouble(priceExtra2.getText().toString().substring(1));
                if(extra2.isChecked()){
                    coffeLatte.setExtra("With Milk");
                    coffeLatte.setPrice(newprice);
                }else{
                    coffeLatte.setExtra("-");
                    coffeLatte.setPrice(oldprice);
                }
            }
        });
        extra3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                double newprice=coffeLatte.getPrice()+Double.parseDouble(priceExtra3.getText().toString().substring(1));
                double oldprice=coffeLatte.getPrice()-Double.parseDouble(priceExtra3.getText().toString().substring(1));
                if(extra3.isChecked()){
                    coffeLatte.setExtra("With Honey");
                    coffeLatte.setPrice(newprice);
                }else{
                    coffeLatte.setExtra("-");
                    coffeLatte.setPrice(oldprice);
                }
            }
        });


        addQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                updateValue();
                coffeLatte.setQuantity(quantity);

            }
        });
        removeQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity--;
                updateValue();
                coffeLatte.setQuantity(quantity);

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
                coffeLatte.setQuantity(0);
                String quantityText = editText.getText().toString();
                if (!quantityText.isEmpty()) {
                    coffeLatte.setQuantity(Integer.parseInt(quantityText));

                }
            }
        });


    }
}