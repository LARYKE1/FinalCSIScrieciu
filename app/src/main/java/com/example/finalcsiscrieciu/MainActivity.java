package com.example.finalcsiscrieciu;
import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnMenu,btnOrder;
    Intent inte;
    public static final int REQUEST_CODE_ORDER = 1;
    private static List<CoffeType> coffeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMenu=(Button) findViewById(R.id.buttonMenuActivity);
        btnOrder=(Button) findViewById(R.id.buttonViewOrder);
        this.registerForContextMenu(btnMenu);

        btnOrder.setOnClickListener(view -> {
            showOrderDialog();
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.context_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.latte:
                inte=new Intent(getApplicationContext(),LatteActivity.class);

                startActivityIfNeeded(inte, REQUEST_CODE_ORDER);
                return true;

            case R.id.mocha:
                inte=new Intent(getApplicationContext(),MochaActivity.class);
                startActivityIfNeeded(inte,REQUEST_CODE_ORDER);
                return true;

            case R.id.americano:
                inte=new Intent(getApplicationContext(),AmericanoActivity.class);
                startActivityIfNeeded(inte,REQUEST_CODE_ORDER);
                return true;

            default:
                return super.onContextItemSelected(menuItem);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.latte:
                inte=new Intent(getApplicationContext(),LatteActivity.class);

                startActivityIfNeeded(inte, REQUEST_CODE_ORDER);
                return true;

            case R.id.mocha:
                inte=new Intent(getApplicationContext(),MochaActivity.class);
                startActivityIfNeeded(inte,REQUEST_CODE_ORDER);
                return true;

            case R.id.americano:
                inte=new Intent(getApplicationContext(),AmericanoActivity.class);
                startActivityIfNeeded(inte,REQUEST_CODE_ORDER);
                return true;

            default:
                return super.onContextItemSelected(menuItem);
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);

        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ORDER) {
            if (resultCode == Activity.RESULT_OK) {
                CoffeType coffeType = (CoffeType) data.getSerializableExtra("coffeType");
                coffeList.add(coffeType);
                showOrderDialog();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }

    private void showOrderDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Order Details");

        StringBuilder orderDetails = new StringBuilder();
        double subtotal = 0.0;
        int itemNumber=1;
        DecimalFormat decimalFormat=new DecimalFormat("#.00");
        for (CoffeType item : coffeList) {
            orderDetails.append(itemNumber++).append(".")
                    .append(item.getName())
                    .append(" ").append(item.getExtra())
                    .append("\n")
                    .append("Quantity: ").append(item.getQuantity())
                    .append("->Price:$").append(item.getPrice()*item.getQuantity())
                    .append("\n").append("Price/Item $").append(item.getPrice())
                    .append("\n");
            subtotal+=(item.getPrice()*item.getQuantity());

        }

        double tax = subtotal * 0.1;
        double total = subtotal + tax;

        orderDetails.append("\n")
                .append("Subtotal: ").append(decimalFormat.format(subtotal)).append("\n")
                .append("Tax: ").append(decimalFormat.format(tax)).append("\n")
                .append("Total: ").append(decimalFormat.format(total));

        builder.setMessage(orderDetails.toString());
        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }





}