package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int kol_vo = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double cena;
    double stoimost;
    ImageView kartinka;
    EditText usernameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateSpinner();
        kartinka = findViewById(R.id.imageView4);
        usernameEditText = findViewById(R.id.editTextTextPersonName);
        stoimost = 0;
        CreateMap();
    }
    void CreateSpinner(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Pizza");
        spinnerArrayList.add("Sushi");
        spinnerArrayList.add("Wok");
        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

    }
    void CreateMap(){
        goodsMap = new HashMap();
        goodsMap.put("Pizza", 600.00);
        goodsMap.put("Sushi", 900.00);
        goodsMap.put("Wok", 500.00);
    }

    public void priplusovat(View view) {
        kol_vo = kol_vo + 1;
        TextView Kol_vo_predmetov1 = findViewById(R.id.textView6);
        TextView stoimost_predmetov1 = findViewById(R.id.textView5);
        stoimost_predmetov1.setText("" + cena * kol_vo + " рублей");
        Kol_vo_predmetov1.setText("" + kol_vo);
    }

    public void otnyat(View view) {
        kol_vo = kol_vo - 1;

        TextView Kol_vo_predmetov2 = findViewById(R.id.textView6);
        TextView stoimost_predmetov2 = findViewById(R.id.textView5);
        if (stoimost > 0 & kol_vo == 0)
        {
            Kol_vo_predmetov2.setText("" + kol_vo);
            stoimost_predmetov2.setText("" + cena * Math.abs(kol_vo) + " рублей");
        }
        else if (kol_vo == 0 & stoimost == 0) {
            kol_vo = 0;
            Kol_vo_predmetov2.setText("" + kol_vo);
            stoimost_predmetov2.setText("-" + cena * kol_vo + " рублей");
        }
        else{
            Kol_vo_predmetov2.setText("" + kol_vo);
            stoimost_predmetov2.setText("" + cena * kol_vo + " рублей");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        cena = (double)goodsMap.get(goodsName);
        /*if (goodsName.equals("Pizza"))
        {
            kartinka.setImageResource(R.drawable.img_1);
        }
        else if (goodsName.equals("Sushi"))
        {
            kartinka.setImageResource(R.drawable.img);
        }
        else if (goodsName.equals("Wok"))
        {
            kartinka.setImageResource(R.drawable.wok);
        }*/
        switch(goodsName)
        {
            case "Sushi":
                kartinka.setImageResource(R.drawable.img);
                break;
            case "Wok":
                kartinka.setImageResource(R.drawable.wok);
                break;
            default:
                kartinka.setImageResource(R.drawable.pizza);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void Dobavit(View view) {
        TextView zakaz_stoimost = findViewById(R.id.Obshaya_stoimost);
        stoimost += cena * kol_vo;
        zakaz_stoimost.setText("Общая стоимость заказа: " + stoimost + " рублей");
        cena = 0;
        TextView cena1 = findViewById(R.id.textView5);
        cena1.setText("" + cena + " рублей");
        kol_vo = 0;
        TextView kol_vo1 = findViewById(R.id.textView6);
        kol_vo1.setText("" + kol_vo);
    }

    public void AddToCart(View view)
    {
        Order order = new Order();
        stoimost += cena * kol_vo;
        order.username = usernameEditText.getText().toString();
        Log.d("user1",order.username);
        order.goodsName = goodsName;
        Log.d("user1",order.goodsName);
        order.quantity = kol_vo;
        Log.d("user1","" + order.quantity);
        order.orderPrice = stoimost;
        Log.d("user1","" + order.orderPrice);
    }
}